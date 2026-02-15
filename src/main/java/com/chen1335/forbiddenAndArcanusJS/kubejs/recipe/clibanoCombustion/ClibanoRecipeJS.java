package com.chen1335.forbiddenAndArcanusJS.kubejs.recipe.clibanoCombustion;

import com.stal111.forbidden_arcanus.common.block.entity.clibano.ClibanoFireType;
import com.stal111.forbidden_arcanus.common.recipe.ClibanoRecipe;
import dev.latvian.mods.kubejs.recipe.RecipeJS;
import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;

public class ClibanoRecipeJS extends RecipeJS {

    @Info("Single input")
    public ClibanoRecipeJS input(Ingredient ingredient) {
        setValue(ClibanoRecipeSchema.INPUT, ingredient);
        return this;
    }

    public ClibanoRecipeJS experience(float experience) {
        setValue(ClibanoRecipeSchema.EXPERIENCE, experience);
        return this;
    }

    public ClibanoRecipeJS cookingTime(int time) {
        setValue(ClibanoRecipeSchema.CLIBANO_COOKING_TIMES, time);
        return this;
    }

    public ClibanoRecipeJS residue(String name, double chance) {
        setValue(ClibanoRecipeSchema.RESIDUE_CHANCE, new ClibanoRecipe.ResidueInfo(name, chance));
        return this;
    }


    public ClibanoRecipeJS fireType(ClibanoFireType fireType) {
        setValue(ClibanoRecipeSchema.FIRE_TYPE, fireType);
        return this;
    }


    public ClibanoRecipeJS group(String group) {
        setValue(ClibanoRecipeSchema.GROUP, group);
        return this;
    }

    public ClibanoRecipeJS cookingBookCategory(CookingBookCategory cookingBookCategory) {
        setValue(ClibanoRecipeSchema.COOKING_BOOK_CATEGORY, cookingBookCategory);
        return this;
    }
}
