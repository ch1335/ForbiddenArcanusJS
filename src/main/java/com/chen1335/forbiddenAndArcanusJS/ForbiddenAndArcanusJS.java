package com.chen1335.forbiddenAndArcanusJS;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.server.ServerAboutToStartEvent;
import org.slf4j.Logger;

@Mod(ForbiddenAndArcanusJS.MODID)
public class ForbiddenAndArcanusJS {
    public static final String MODID = "forbidden_arcanus_js";
    private static final Logger LOGGER = LogUtils.getLogger();

    public ForbiddenAndArcanusJS(IEventBus modEventBus, ModContainer modContainer) {

    }


    public static void a(ServerAboutToStartEvent event) {

    }
}
