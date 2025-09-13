package com.chen1335.forbiddenAndArcanusJS.forbiddenArcanusFix;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;
import org.jetbrains.annotations.NotNull;

public class RitualRecipeInput implements RecipeInput {
    @Override
    public @NotNull ItemStack getItem(int index) {
        return ItemStack.EMPTY;
    }

    @Override
    public int size() {
        return 0;
    }
}
