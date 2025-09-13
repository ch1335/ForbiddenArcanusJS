package com.chen1335.forbiddenAndArcanusJS.kubejs.recipe.clibanoCombustion;

import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Pair;
import com.stal111.forbidden_arcanus.ForbiddenArcanus;
import com.stal111.forbidden_arcanus.common.block.entity.clibano.ClibanoCookingTimes;
import com.stal111.forbidden_arcanus.common.block.entity.clibano.ClibanoFireType;
import com.stal111.forbidden_arcanus.common.block.entity.clibano.residue.ResidueChance;
import com.stal111.forbidden_arcanus.common.block.entity.clibano.residue.ResidueType;
import com.stal111.forbidden_arcanus.common.item.enhancer.EnhancerDefinition;
import dev.latvian.mods.kubejs.recipe.KubeRecipe;
import dev.latvian.mods.kubejs.recipe.schema.KubeRecipeFactory;
import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.core.Holder;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;

public class ClibanoRecipeJS extends KubeRecipe {
    public static final KubeRecipeFactory RECIPE_FACTORY = new KubeRecipeFactory(ForbiddenArcanus.location("clibano_combustion"), ClibanoRecipeJS.class, ClibanoRecipeJS::new);

    @Info("Single input")
    public ClibanoRecipeJS input(Ingredient ingredient) {
        setValue(ClibanoRecipeSchema.INPUT, Either.left(ingredient));
        return this;
    }
    @Info("two-input")
    public ClibanoRecipeJS inputs(Ingredient first, Ingredient second) {
        setValue(ClibanoRecipeSchema.INPUT, Either.right(Pair.of(first, second)));
        return this;
    }

    public ClibanoRecipeJS experience(float experience) {
        setValue(ClibanoRecipeSchema.EXPERIENCE, experience);
        return this;
    }

    public ClibanoRecipeJS cookingTime(int time) {
        setValue(ClibanoRecipeSchema.CLIBANO_COOKING_TIMES, ClibanoCookingTimes.of(time));
        return this;
    }

    public ClibanoRecipeJS residue(Holder<ResidueType> type, double chance) {
        setValue(ClibanoRecipeSchema.RESIDUE_CHANCE, new ResidueChance(type, chance));
        return this;
    }


    public ClibanoRecipeJS fireType(ClibanoFireType fireType) {
        setValue(ClibanoRecipeSchema.FIRE_TYPE, fireType);
        return this;
    }

    public ClibanoRecipeJS enhancer(Holder<EnhancerDefinition> enhancer) {
        setValue(ClibanoRecipeSchema.ENHANCER_DEFINITION, enhancer);
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
