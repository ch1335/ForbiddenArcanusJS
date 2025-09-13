package com.chen1335.forbiddenAndArcanusJS.mixins;


import com.chen1335.forbiddenAndArcanusJS.forbiddenArcanusFix.ForbiddenArcanusRecipeFix;
import com.stal111.forbidden_arcanus.ForbiddenArcanus;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ForbiddenArcanus.class)
public class ForbiddenArcanusMixin {
    @Inject(method = "<init>", at = @At("RETURN"))
    private void init(IEventBus modEventBus, ModContainer modContainer, CallbackInfo ci) {
        ForbiddenArcanusRecipeFix.init(modEventBus, modContainer);
    }

}
