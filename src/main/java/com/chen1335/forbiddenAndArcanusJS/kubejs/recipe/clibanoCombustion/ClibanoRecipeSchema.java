package com.chen1335.forbiddenAndArcanusJS.kubejs.recipe.clibanoCombustion;

import com.chen1335.forbiddenAndArcanusJS.kubejs.recipe.Schemas;
import com.stal111.forbidden_arcanus.common.block.entity.clibano.ClibanoFireType;
import com.stal111.forbidden_arcanus.common.recipe.ClibanoRecipe;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.EnumComponent;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;
import dev.latvian.mods.kubejs.recipe.component.StringComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;

public interface ClibanoRecipeSchema {
    RecipeKey<String> GROUP = StringComponent.ANY.key("group").defaultOptional();

    RecipeKey<CookingBookCategory> COOKING_BOOK_CATEGORY = Schemas.COOKING_BOOK_CATEGORY.key("category").optional(CookingBookCategory.MISC).alwaysWrite();

    RecipeKey<Ingredient> INPUT = Schemas.INGREDIENT.key("ingredient").defaultOptional();

    RecipeKey<ItemStack> RESULT = Schemas.ITEM_STACK.key("result");

    RecipeKey<Float> EXPERIENCE = NumberComponent.FloatRange.FLOAT.min(0f).max(Float.MAX_VALUE).key("experience").optional(0f).alwaysWrite();

    RecipeKey<Integer> CLIBANO_COOKING_TIMES = NumberComponent.INT.min(1).max(Integer.MAX_VALUE).key("cooking_time").optional(ClibanoRecipe.DEFAULT_COOKING_TIME).alwaysWrite();

    RecipeKey<ClibanoRecipe.ResidueInfo> RESIDUE_CHANCE = Schemas.RESIDUE_INFO.key("residue").defaultOptional();

    RecipeKey<ClibanoFireType> FIRE_TYPE = new EnumComponent<>(ClibanoFireType.class).key("fire_type").optional(ClibanoFireType.FIRE);


    RecipeSchema SCHEMA = new RecipeSchema(ClibanoRecipeJS.class, ClibanoRecipeJS::new,
            RESULT,
            GROUP,
            INPUT,
            COOKING_BOOK_CATEGORY,
            EXPERIENCE,
            CLIBANO_COOKING_TIMES,
            RESIDUE_CHANCE,
            FIRE_TYPE
    ).constructor(
            RESULT
    );
}
