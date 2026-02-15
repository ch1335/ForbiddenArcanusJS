package com.chen1335.forbiddenAndArcanusJS.wrappers;

import com.chen1335.forbiddenAndArcanusJS.Util;
import com.stal111.forbidden_arcanus.common.item.enhancer.EnhancerDefinition;
import com.stal111.forbidden_arcanus.core.registry.FARegistries;
import dev.latvian.mods.rhino.Context;
import net.minecraft.resources.ResourceLocation;

public class EnhancerDefinitionWrapper {
    public static EnhancerDefinition of(Context cx, Object o) {
        if (o instanceof EnhancerDefinition enhancerDefinition) {
            return enhancerDefinition;
        } else if (o instanceof String id) {
            return Util.frozen.registryOrThrow(FARegistries.ENHANCER_DEFINITION).get(ResourceLocation.tryParse(id));
        }
        throw new IllegalArgumentException("%s can not be cast to EnhancerDefinition".formatted(o));
    }
}
