package com.chen1335.forbiddenAndArcanusJS;

import com.chen1335.forbiddenAndArcanusJS.forbiddenArcanusFix.ForbiddenArcanusRecipeFix;
import com.chen1335.forbiddenAndArcanusJS.ritualResults.RitualResultTypes;
import com.mojang.logging.LogUtils;
import com.stal111.forbidden_arcanus.ForbiddenArcanus;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(ForbiddenAndArcanusJS.MODID)
public class ForbiddenAndArcanusJS {
    public static final String MODID = "forbidden_arcanus_js";
    public static final Logger LOGGER = LogUtils.getLogger();

    public ForbiddenAndArcanusJS(FMLJavaModLoadingContext context) {
        ForbiddenArcanusRecipeFix.init(context.getModEventBus());
        RitualResultTypes.init();
    }


    public static ResourceLocation FAId(String s) {
        return ResourceLocation.tryBuild(ForbiddenArcanus.MOD_ID, s);
    }
}
