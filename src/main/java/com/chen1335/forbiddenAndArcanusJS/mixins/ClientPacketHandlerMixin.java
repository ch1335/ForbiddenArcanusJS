package com.chen1335.forbiddenAndArcanusJS.mixins;

import com.chen1335.forbiddenAndArcanusJS.Util;
import com.stal111.forbidden_arcanus.common.block.entity.forge.HephaestusForgeBlockEntity;
import com.stal111.forbidden_arcanus.common.network.ClientPacketHandler;
import com.stal111.forbidden_arcanus.common.network.clientbound.UpdateForgeRitualPacket;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ClientPacketHandler.class,remap = false)
public abstract class ClientPacketHandlerMixin {

    @Shadow
    private static ClientLevel getLevel() {
        return null;
    }

    @Inject(method = "handleUpdateRitual", at = @At("HEAD"))
    private static void handleUpdateRitual(UpdateForgeRitualPacket packet, CallbackInfo ci) {
        Level level = getLevel();

        if (level == null || !(level.getBlockEntity(packet.pos()) instanceof HephaestusForgeBlockEntity blockEntity)) {
            return;
        }

        blockEntity.getRitualManager().setActiveRitual(Util.getRitualByKey(level, packet.ritual()));
    }
}
