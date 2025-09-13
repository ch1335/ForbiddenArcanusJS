package com.chen1335.forbiddenAndArcanusJS.forbiddenArcanusFix;

import com.stal111.forbidden_arcanus.common.block.entity.forge.TierPredicate;
import com.stal111.forbidden_arcanus.common.block.entity.forge.essence.EssencesDefinition;
import com.stal111.forbidden_arcanus.common.block.entity.forge.ritual.Ritual;
import com.stal111.forbidden_arcanus.common.block.entity.forge.ritual.RitualInput;
import com.stal111.forbidden_arcanus.common.block.entity.forge.ritual.RitualRequirements;
import com.stal111.forbidden_arcanus.common.block.entity.forge.ritual.result.RitualResult;
import com.stal111.forbidden_arcanus.core.registry.FARegistries;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.Ingredient;

public class StreamCodecs {
    public static final StreamCodec<RegistryFriendlyByteBuf, RitualInput> RITUAL_INPUT_STREAM_CODEC = StreamCodec.composite(
            Ingredient.CONTENTS_STREAM_CODEC,
            RitualInput::ingredient,
            ByteBufCodecs.INT,
            RitualInput::amount,
            RitualInput::new
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, RitualResult> RITUAL_RESULT_STREAM_CODEC = StreamCodec.of(
            (buffer, value) -> {
                ByteBufCodecs.TAG.encode(buffer, RitualResult.DIRECT_CODEC.encodeStart(NbtOps.INSTANCE, value).getOrThrow());
            },
            buffer -> RitualResult.DIRECT_CODEC.decode(NbtOps.INSTANCE, ByteBufCodecs.TAG.decode(buffer)).getOrThrow().getFirst());


    public static final StreamCodec<RegistryFriendlyByteBuf, EssencesDefinition> ESSENCES_DEFINITION_STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT,
            EssencesDefinition::aureal,
            ByteBufCodecs.INT,
            EssencesDefinition::souls,
            ByteBufCodecs.INT,
            EssencesDefinition::blood,
            ByteBufCodecs.INT,
            EssencesDefinition::experience,
            EssencesDefinition::new);


    public static final StreamCodec<RegistryFriendlyByteBuf, TierPredicate> TIER_PREDICATE_STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT,
            TierPredicate::tier,
            ByteBufCodecs.BOOL,
            TierPredicate::matchExact,
            TierPredicate::new
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, RitualRequirements> REQUIREMENTS_STREAM_CODEC = StreamCodec.composite(
            ESSENCES_DEFINITION_STREAM_CODEC,
            RitualRequirements::essences,
            TIER_PREDICATE_STREAM_CODEC,
            RitualRequirements::tier,
            ByteBufCodecs.holderSet(FARegistries.ENHANCER_DEFINITION),
            RitualRequirements::enhancers,
            RitualRequirements::new
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, Ritual> RITUAL_STREAM_CODEC = StreamCodec.composite(
            RITUAL_INPUT_STREAM_CODEC.apply(ByteBufCodecs.list()),
            Ritual::inputs,
            Ingredient.CONTENTS_STREAM_CODEC,
            Ritual::mainIngredient,
            RITUAL_RESULT_STREAM_CODEC,
            Ritual::result,
            REQUIREMENTS_STREAM_CODEC,
            Ritual::requirements,
            ByteBufCodecs.holderRegistry(FARegistries.MAGIC_CIRCLE),
            Ritual::magicCircleType,
            ByteBufCodecs.INT,
            Ritual::duration,
            Ritual::new
    );
}
