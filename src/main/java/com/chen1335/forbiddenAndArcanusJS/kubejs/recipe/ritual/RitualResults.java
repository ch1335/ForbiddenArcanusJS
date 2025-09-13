package com.chen1335.forbiddenAndArcanusJS.kubejs.recipe.ritual;

import com.stal111.forbidden_arcanus.common.block.entity.forge.ritual.result.CreateItemResult;
import com.stal111.forbidden_arcanus.common.block.entity.forge.ritual.result.TransmuteInputResult;
import com.stal111.forbidden_arcanus.common.block.entity.forge.ritual.result.UpgradeTierResult;
import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class RitualResults {
    public static CreateItemResult ofCreateItemResult(ItemStack resultItemStack) {
        return new CreateItemResult(resultItemStack);
    }

    public static TransmuteInputResult ofTransmuteInputResult(Holder<Item> result) {
        return new TransmuteInputResult(result);
    }

    public static UpgradeTierResult ofUpgradeTierResult(int tier) {
        return new UpgradeTierResult(tier);
    }
}
