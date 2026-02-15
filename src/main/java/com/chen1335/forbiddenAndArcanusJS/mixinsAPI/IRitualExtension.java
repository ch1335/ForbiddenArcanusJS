package com.chen1335.forbiddenAndArcanusJS.mixinsAPI;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;

public interface IRitualExtension extends Recipe<Container> {
    void FAJS$setId(ResourceLocation resourceLocation);
}
