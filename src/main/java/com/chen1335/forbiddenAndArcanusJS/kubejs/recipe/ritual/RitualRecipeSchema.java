package com.chen1335.forbiddenAndArcanusJS.kubejs.recipe.ritual;

import com.chen1335.forbiddenAndArcanusJS.kubejs.recipe.Schemas;
import com.stal111.forbidden_arcanus.common.block.entity.forge.circle.MagicCircleType;
import com.stal111.forbidden_arcanus.common.block.entity.forge.essence.EssencesDefinition;
import com.stal111.forbidden_arcanus.common.block.entity.forge.ritual.RitualInput;
import com.stal111.forbidden_arcanus.common.block.entity.forge.ritual.result.RitualResult;
import com.stal111.forbidden_arcanus.common.item.enhancer.EnhancerDefinition;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.BooleanComponent;
import dev.latvian.mods.kubejs.recipe.component.IngredientComponent;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.List;

public interface RitualRecipeSchema {
    RecipeKey<List<RitualInput>> INPUTS = Schemas.INPUTS.inputKey("inputs").optional(type -> new ArrayList<>()).alwaysWrite().allowEmpty();

    RecipeKey<Ingredient> MAIN_INGREDIENT = IngredientComponent.INGREDIENT.inputKey("main_ingredient").allowEmpty();

    RecipeKey<RitualResult> RESULT = Schemas.RESULT.outputKey("result").allowEmpty();

    RecipeKey<EssencesDefinition> ESSENCES_DEFINITION = Schemas.ESSENCES_DEFINITION.inputKey("essences").optional(type -> new EssencesDefinition(0,0,0,0)).alwaysWrite().allowEmpty();

    RecipeKey<Integer> TIER = NumberComponent.IntRange.INT.range(1, Integer.MAX_VALUE).inputKey("forge_tier").optional(1).alwaysWrite().allowEmpty();

    RecipeKey<Boolean> MATCH_TIER_EXACT = BooleanComponent.BOOLEAN.inputKey("match_tier_exact").optional(false).allowEmpty();

    RecipeKey<HolderSet<EnhancerDefinition>> ENHANCER_DEFINITION = Schemas.ENHANCERS_DEFINITION.inputKey("enhancers").allowEmpty().defaultOptional();

    RecipeKey<Holder<MagicCircleType>> MAGIC_CIRCLE_TYPE = Schemas.MAGIC_CIRCLE_TYPE.inputKey("magic_circle").allowEmpty().defaultOptional();

    RecipeKey<Integer> DURATION = NumberComponent.IntRange.INT.range(1, Integer.MAX_VALUE).inputKey("duration").allowEmpty().defaultOptional();


    RecipeSchema SCHEMA = new RecipeSchema(
            RESULT,
            MAIN_INGREDIENT,
            INPUTS,
            ESSENCES_DEFINITION,
            TIER,
            MATCH_TIER_EXACT,
            ENHANCER_DEFINITION,
            MAGIC_CIRCLE_TYPE,
            DURATION
    ).factory(RitualRecipeJS.RECIPE_FACTORY).constructor(
            RESULT,
            MAIN_INGREDIENT
    );
}
