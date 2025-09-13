package com.chen1335.forbiddenAndArcanusJS.forbiddenArcanusFix;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.stal111.forbidden_arcanus.common.block.entity.forge.circle.MagicCircleType;
import com.stal111.forbidden_arcanus.common.block.entity.forge.ritual.Ritual;
import com.stal111.forbidden_arcanus.common.block.entity.forge.ritual.RitualInput;
import com.stal111.forbidden_arcanus.common.block.entity.forge.ritual.RitualRequirements;
import com.stal111.forbidden_arcanus.common.block.entity.forge.ritual.result.RitualResult;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.NotNull;

public class RitualRecipeSerializer implements RecipeSerializer {
    public static final MapCodec<Ritual> MAP_CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
            RitualInput.CODEC.listOf(1, 8).fieldOf("inputs").forGetter(Ritual::inputs),
            Ingredient.CODEC_NONEMPTY.fieldOf("main_ingredient").forGetter(Ritual::mainIngredient),
            RitualResult.DIRECT_CODEC.fieldOf("result").forGetter(Ritual::result),
            RitualRequirements.CODEC.forGetter(Ritual::requirements),
            MagicCircleType.CODEC.fieldOf("magic_circle").forGetter(Ritual::magicCircleType),
            ExtraCodecs.POSITIVE_INT.optionalFieldOf("duration", 500).forGetter(Ritual::duration)
    ).apply(instance, Ritual::new));


    @Override
    public @NotNull MapCodec<Ritual> codec() {
        return MAP_CODEC;
    }

    @Override
    public @NotNull StreamCodec<RegistryFriendlyByteBuf, Ritual> streamCodec() {
        return StreamCodecs.RITUAL_STREAM_CODEC;
    }


}
