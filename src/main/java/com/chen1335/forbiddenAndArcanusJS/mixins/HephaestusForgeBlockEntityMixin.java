package com.chen1335.forbiddenAndArcanusJS.mixins;

import com.chen1335.forbiddenAndArcanusJS.mixinsAPI.IRitualManagerExtension;
import com.stal111.forbidden_arcanus.common.block.entity.forge.HephaestusForgeBlockEntity;
import com.stal111.forbidden_arcanus.common.block.entity.forge.ritual.RitualManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = HephaestusForgeBlockEntity.class, remap = false)
public abstract class HephaestusForgeBlockEntityMixin {
    @Shadow
    public abstract RitualManager getRitualManager();

    @Inject(method = "onLoad", at = @At("RETURN"))
    private void onLoad(CallbackInfo ci) {
        ((IRitualManagerExtension) this.getRitualManager()).FAJS$onLoad();
    }
}
