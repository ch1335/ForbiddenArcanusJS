package com.chen1335.forbiddenAndArcanusJS.mixins;

import com.chen1335.forbiddenAndArcanusJS.Util;
import com.chen1335.forbiddenAndArcanusJS.forbiddenArcanusFix.ForbiddenArcanusRecipeFix;
import com.chen1335.forbiddenAndArcanusJS.mixinsAPI.IRitualManagerExtension;
import com.stal111.forbidden_arcanus.common.block.entity.forge.essence.EssencesDefinition;
import com.stal111.forbidden_arcanus.common.block.entity.forge.ritual.Ritual;
import com.stal111.forbidden_arcanus.common.block.entity.forge.ritual.RitualManager;
import com.stal111.forbidden_arcanus.common.network.NetworkHandler;
import com.stal111.forbidden_arcanus.common.network.clientbound.CreateValidRitualIndicatorPacket;
import com.stal111.forbidden_arcanus.common.network.clientbound.RemoveValidRitualIndicatorPacket;
import com.stal111.forbidden_arcanus.common.network.clientbound.UpdateForgeRitualPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.crafting.Recipe;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = RitualManager.class, remap = false)
public abstract class RitualManagerMixin implements IRitualManagerExtension {
    @Shadow
    private ServerLevel level;

    @Shadow
    private BlockPos pos;

    @Shadow
    private @Nullable Ritual validRitual;

    @Shadow
    public abstract boolean isRitualActive();

    @Shadow
    public abstract boolean canStartRitual(Ritual ritual, EssencesDefinition definition);

    @Shadow
    private @Nullable Ritual activeRitual;

    @Shadow
    private int counter;

    @Shadow
    private int lightningCounter;

    @Shadow
    public abstract void setActiveRitual(@Nullable Ritual ritual);

    @Inject(method = "updateValidRitual", at = @At("HEAD"), cancellable = true)
    private void onUpdateValidRitual(EssencesDefinition definition, CallbackInfo ci) {
        boolean oldValue = this.validRitual != null;

        for (Recipe<?> recipe : level.getRecipeManager().getAllRecipesFor(Util.cast(ForbiddenArcanusRecipeFix.RITUAL_RECIPE_TYPE.get()))) {
            Ritual ritual = Ritual.class.cast(recipe);
            if (this.canStartRitual(ritual, definition)) {
                if (!oldValue) {
                    NetworkHandler.sendToTrackingChunk(this.level.getChunkAt(this.pos), new CreateValidRitualIndicatorPacket(this.pos));
                }

                this.validRitual = ritual;
                ci.cancel();
                return;
            }
        }

        this.validRitual = null;

        if (oldValue && !this.isRitualActive()) {
            NetworkHandler.sendToTrackingChunk(this.level.getChunkAt(this.pos), new RemoveValidRitualIndicatorPacket(this.pos));
        }
        ci.cancel();
    }

    @Inject(method = "setActiveRitual", at = @At("HEAD"), cancellable = true)
    private void onSetActiveRitual(Ritual ritual, CallbackInfo ci) {
        this.activeRitual = ritual;

        ResourceLocation resourceLocation = null;

        if (ritual != null) {
            resourceLocation = Util.getKeyByRitual(level, ritual);
        }

        NetworkHandler.sendToTrackingChunk(this.level.getChunkAt(pos), new UpdateForgeRitualPacket(pos, resourceLocation));

        ci.cancel();
    }

    @Inject(method = "save", at = @At("HEAD"), cancellable = true)
    private void onSave(CompoundTag tag, CallbackInfoReturnable<CompoundTag> cir) {
        if (this.isRitualActive()) {
            tag.putString("ActiveRitual", Util.getKeyByRitual(level, activeRitual).toString());
            tag.putInt("Counter", this.counter);

            if (this.lightningCounter != 0) {
                tag.putInt("LightningCounter", this.lightningCounter);
            }
        }

        cir.setReturnValue(tag);
    }

    @Unique
    private CompoundTag FAJS$capturedLoadInfo = new CompoundTag();

    @Inject(method = "load", at = @At("HEAD"), cancellable = true)
    private void onLoad(CompoundTag tag, CallbackInfo ci) {
        FAJS$capturedLoadInfo = tag;
        ci.cancel();
    }

    @Inject(method = "reset", at = @At("HEAD"), cancellable = true)
    private void reset(CallbackInfo ci) {
        if (pos == null || level == null) {
            ci.cancel();
        }
    }

    @Override
    public void FAJS$onLoad() {
        CompoundTag tag = FAJS$capturedLoadInfo;
        if (tag.contains("ActiveRitual")) {
            this.setActiveRitual(Util.getRitualByKey(level, ResourceLocation.tryParse(tag.getString("ActiveRitual"))));
            this.counter = tag.getInt("Counter");

            if (tag.contains("LightningCounter")) {
                this.lightningCounter = tag.getInt("LightningCounter");
            }
        }
    }
}
