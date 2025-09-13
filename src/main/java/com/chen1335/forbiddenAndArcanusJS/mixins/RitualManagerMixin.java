package com.chen1335.forbiddenAndArcanusJS.mixins;

import com.chen1335.forbiddenAndArcanusJS.forbiddenArcanusFix.ForbiddenArcanusRecipeFix;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.stal111.forbidden_arcanus.common.block.entity.forge.ritual.Ritual;
import com.stal111.forbidden_arcanus.common.block.entity.forge.ritual.RitualManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.apache.logging.log4j.util.Cast;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Iterator;
import java.util.List;

@Mixin(RitualManager.class)
public class RitualManagerMixin {
    @Shadow
    private ServerLevel level;

    @Shadow
    private BlockPos pos;

    @WrapOperation(method = "updateValidRitual", at = @At(value = "INVOKE", target = "Ljava/util/List;iterator()Ljava/util/Iterator;"))
    private Iterator<Holder<Ritual>> updateValidRitual(List<Holder<Ritual>> instance, Operation<Iterator<Holder<Ritual>>> original) {
        if (this.level == null) {
            return original.call(instance);
        }
        List<? extends RecipeHolder<?>> recipes = this.level.getRecipeManager().getAllRecipesFor(Cast.cast(ForbiddenArcanusRecipeFix.RITUAL_RECIPE_TYPE.value()));
        return (Iterator) recipes.stream().map(RecipeHolder::value).map(Holder::direct).toList().iterator();
    }


    @Inject(method = "reset", at = @At("HEAD"), cancellable = true)
    private void reset(CallbackInfo ci) {
        if (pos == null || level == null) {
            ci.cancel();
        }
    }
}
