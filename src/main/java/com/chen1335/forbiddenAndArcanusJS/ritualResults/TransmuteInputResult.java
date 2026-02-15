package com.chen1335.forbiddenAndArcanusJS.ritualResults;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.stal111.forbidden_arcanus.common.block.entity.forge.ritual.RitualManager;
import com.stal111.forbidden_arcanus.common.block.entity.forge.ritual.result.RitualResult;
import com.stal111.forbidden_arcanus.common.block.entity.forge.ritual.result.RitualResultType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

public class TransmuteInputResult extends RitualResult {
    public static final Codec<TransmuteInputResult> CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(
                ForgeRegistries.ITEMS.getCodec().fieldOf("result_item").forGetter(TransmuteInputResult::result)
        ).apply(instance, TransmuteInputResult::new);
    });
    private final Item result;

    public TransmuteInputResult(Item result) {
        this.result = result;
    }

    @Override
    public void apply(RitualManager.MainIngredientAccessor accessor, Level level, BlockPos pos) {
        ItemStack itemStack = accessor.get();
        ItemStack defaultInstance = this.result.getDefaultInstance();
        defaultInstance.setTag(itemStack.getTag());
        accessor.set(defaultInstance);
    }

    public RitualResultType<? extends RitualResult> getType() {
        return RitualResultTypes.TRANSMUTE_INPUT.get();
    }

    public Item result() {
        return this.result;
    }
}
