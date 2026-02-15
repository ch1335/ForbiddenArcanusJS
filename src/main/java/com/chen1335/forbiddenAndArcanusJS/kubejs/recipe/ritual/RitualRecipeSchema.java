package com.chen1335.forbiddenAndArcanusJS.kubejs.recipe.ritual;

import com.chen1335.forbiddenAndArcanusJS.kubejs.recipe.Schemas;
import com.stal111.forbidden_arcanus.common.block.entity.forge.MagicCircle;
import com.stal111.forbidden_arcanus.common.block.entity.forge.essence.EssencesDefinition;
import com.stal111.forbidden_arcanus.common.block.entity.forge.ritual.RitualInput;
import com.stal111.forbidden_arcanus.common.block.entity.forge.ritual.RitualRequirements;
import com.stal111.forbidden_arcanus.common.block.entity.forge.ritual.result.RitualResult;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.BooleanComponent;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.List;

public interface RitualRecipeSchema {
    RecipeKey<List<RitualInput>> INPUTS = Schemas.INPUTS.key("inputs").optional(type -> new ArrayList<>()).alwaysWrite();

    RecipeKey<Ingredient> MAIN_INGREDIENT = Schemas.INGREDIENT.key("main_ingredient");

    RecipeKey<RitualResult> RESULT = Schemas.RESULT.key("result");

    RecipeKey<EssencesDefinition> ESSENCES_DEFINITION = Schemas.ESSENCES_DEFINITION.key("essences").optional(type -> new EssencesDefinition(0, 0, 0, 0)).alwaysWrite();

    RecipeKey<RitualRequirements> RITUAL_REQUIREMENTS = Schemas.REQUIREMENTS.key("additional_requirements").defaultOptional();

    RecipeKey<MagicCircle.Config> MAGIC_CIRCLE_CONFIG = Schemas.MAGIC_CIRCLE_CONFIG.key("magic_circle").defaultOptional();


    RecipeSchema SCHEMA = new RecipeSchema(RitualRecipeJS.class, RitualRecipeJS::new,
            RESULT,
            MAIN_INGREDIENT,
            INPUTS,
            ESSENCES_DEFINITION,
            RITUAL_REQUIREMENTS,
            MAGIC_CIRCLE_CONFIG
    ).constructor(
            RESULT,
            MAIN_INGREDIENT
    );
}
