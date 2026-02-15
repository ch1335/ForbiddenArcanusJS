package com.chen1335.forbiddenAndArcanusJS.kubejs.recipe.combineResidue;

import com.chen1335.forbiddenAndArcanusJS.kubejs.recipe.Schemas;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;
import dev.latvian.mods.kubejs.recipe.component.StringComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import net.minecraft.world.item.ItemStack;

public interface CombineResiduesSchema {
    RecipeKey<String> RESIDUE_NAME = StringComponent.ANY.key("residue_name");

    RecipeKey<Integer> RESIDUE_AMOUNT = NumberComponent.INT.min(0).max(Short.MAX_VALUE).key("residue_amount");

    RecipeKey<ItemStack> RESULT = Schemas.ITEM_STACK.key("result");

    RecipeSchema SCHEMA = new RecipeSchema(
            RESIDUE_NAME,
            RESIDUE_AMOUNT,
            RESULT
    );
}
