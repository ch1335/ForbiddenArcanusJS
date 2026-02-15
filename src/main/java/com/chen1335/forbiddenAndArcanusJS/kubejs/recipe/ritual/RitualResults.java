package com.chen1335.forbiddenAndArcanusJS.kubejs.recipe.ritual;

import com.chen1335.forbiddenAndArcanusJS.ritualResults.TransmuteInputResult;
import com.stal111.forbidden_arcanus.common.block.entity.forge.ritual.result.CreateItemResult;
import com.stal111.forbidden_arcanus.common.block.entity.forge.ritual.result.UpgradeTierResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class RitualResults {
    public static CreateItemResult ofCreateItemResult(ItemStack resultItemStack) {
        return new CreateItemResult(resultItemStack);
    }

    public static UpgradeTierResult ofUpgradeTierResult(int requiredTier, int upgradedTier) {
        return new UpgradeTierResult(requiredTier, upgradedTier);
    }

    public static UpgradeTierResult ofUpgradeTierResult(int tier) {
        return new UpgradeTierResult(tier - 1, tier);
    }

    public static TransmuteInputResult ofTransmuteInputResult(Item result) {
        return new TransmuteInputResult(result);
    }
}
