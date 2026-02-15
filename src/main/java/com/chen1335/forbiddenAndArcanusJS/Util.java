package com.chen1335.forbiddenAndArcanusJS;

import com.chen1335.forbiddenAndArcanusJS.forbiddenArcanusFix.ForbiddenArcanusRecipeFix;
import com.google.common.collect.ImmutableBiMap;
import com.stal111.forbidden_arcanus.common.block.entity.forge.ritual.Ritual;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

public class Util {
    public static RegistryAccess.Frozen frozen;

    public static <T> T cast(final Object o) {
        if (o == null) {
            return null;
        }
        @SuppressWarnings("unchecked") final T t = (T) o;
        return t;
    }

    public static ResourceLocation getKeyByRitual(Level level, Ritual ritual) {
        return ImmutableBiMap.copyOf(level.getRecipeManager().byName).inverse().get(ritual);
    }

    public static Ritual getRitualByKey(Level level, ResourceLocation resourceLocation) {
        return Util.cast(level.getRecipeManager().recipes.get(ForbiddenArcanusRecipeFix.RITUAL_RECIPE_TYPE.get()).get(resourceLocation));
    }
}
