package com.chen1335.forbiddenAndArcanusJS.kubejs.recipe.clibanoCombustion;

import com.chen1335.forbiddenAndArcanusJS.kubejs.recipe.Schemas;
import com.chen1335.forbiddenAndArcanusJS.kubejs.recipe.ritual.RitualRecipeJS;
import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Pair;
import com.stal111.forbidden_arcanus.common.block.entity.clibano.ClibanoCookingTimes;
import com.stal111.forbidden_arcanus.common.block.entity.clibano.ClibanoFireType;
import com.stal111.forbidden_arcanus.common.block.entity.clibano.residue.ResidueChance;
import com.stal111.forbidden_arcanus.common.item.enhancer.EnhancerDefinition;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.EnumComponent;
import dev.latvian.mods.kubejs.recipe.component.ItemStackComponent;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;
import dev.latvian.mods.kubejs.recipe.component.StringComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;

public interface ClibanoRecipeSchema {
    RecipeKey<String> GROUP = StringComponent.ANY.inputKey("group").optional("").allowEmpty();

    RecipeKey<CookingBookCategory> COOKING_BOOK_CATEGORY = Schemas.COOKING_BOOK_CATEGORY.inputKey("category").optional(CookingBookCategory.MISC).alwaysWrite().allowEmpty();

    RecipeKey<Either<Ingredient, Pair<Ingredient, Ingredient>>> INPUT = Schemas.INPUT.inputKey("ingredients").allowEmpty();

    RecipeKey<ItemStack> RESULT = ItemStackComponent.ITEM_STACK.outputKey("result").allowEmpty();

    RecipeKey<Float> EXPERIENCE = NumberComponent.FloatRange.FLOAT.range(0f, Float.MAX_VALUE).inputKey("experience").optional(0f).alwaysWrite().allowEmpty();

    RecipeKey<ClibanoCookingTimes> CLIBANO_COOKING_TIMES = Schemas.CLIBANO_COOKING_TIMES.inputKey("cooking_time").optional(ClibanoCookingTimes.of(100)).alwaysWrite().allowEmpty();

    RecipeKey<ResidueChance> RESIDUE_CHANCE = Schemas.RESIDUE_CHANCE.inputKey("residue").defaultOptional().allowEmpty();

    RecipeKey<ClibanoFireType> FIRE_TYPE = EnumComponent.of("fire_type", ClibanoFireType.class, ClibanoFireType.CODEC).inputKey("fire_type").optional(ClibanoFireType.FIRE).alwaysWrite().allowEmpty();

    RecipeKey<Holder<EnhancerDefinition>> ENHANCER_DEFINITION = Schemas.ENHANCER_DEFINITION.inputKey("enhancer").defaultOptional().allowEmpty();


    RecipeSchema SCHEMA = new RecipeSchema(
            RESULT,
            INPUT,
            GROUP,
            COOKING_BOOK_CATEGORY,
            EXPERIENCE,
            CLIBANO_COOKING_TIMES,
            RESIDUE_CHANCE,
            FIRE_TYPE,
            ENHANCER_DEFINITION
    ).factory(ClibanoRecipeJS.RECIPE_FACTORY).constructor(
            RESULT
    );
}
