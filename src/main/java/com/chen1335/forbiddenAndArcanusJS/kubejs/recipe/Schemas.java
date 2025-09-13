package com.chen1335.forbiddenAndArcanusJS.kubejs.recipe;

import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.stal111.forbidden_arcanus.common.block.entity.clibano.ClibanoCookingTimes;
import com.stal111.forbidden_arcanus.common.block.entity.clibano.residue.ResidueChance;
import com.stal111.forbidden_arcanus.common.block.entity.forge.circle.MagicCircleType;
import com.stal111.forbidden_arcanus.common.block.entity.forge.essence.EssencesDefinition;
import com.stal111.forbidden_arcanus.common.block.entity.forge.ritual.RitualInput;
import com.stal111.forbidden_arcanus.common.block.entity.forge.ritual.RitualRequirements;
import com.stal111.forbidden_arcanus.common.block.entity.forge.ritual.result.RitualResult;
import com.stal111.forbidden_arcanus.common.item.enhancer.EnhancerDefinition;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponent;
import dev.latvian.mods.rhino.type.TypeInfo;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;

public class Schemas {
    public static final RecipeComponent<List<RitualInput>> INPUTS = new RecipeComponent<List<RitualInput>>() {
        @Override
        public Codec<List<RitualInput>> codec() {
            return RitualInput.CODEC.listOf();
        }

        @Override
        public TypeInfo typeInfo() {
            return TypeInfo.RAW_LIST.withParams(TypeInfo.of(RitualInput.class));
        }
    };

    public static final RecipeComponent<RitualResult> RESULT = new RecipeComponent<RitualResult>() {
        @Override
        public Codec<RitualResult> codec() {
            return RitualResult.DIRECT_CODEC;
        }

        @Override
        public TypeInfo typeInfo() {
            return TypeInfo.of(RitualResult.class);
        }
    };

    public static final RecipeComponent<RitualRequirements> REQUIREMENTS = new RecipeComponent<RitualRequirements>() {
        @Override
        public Codec<RitualRequirements> codec() {
            return RitualRequirements.CODEC.codec();
        }

        @Override
        public TypeInfo typeInfo() {
            return TypeInfo.of(RitualRequirements.class);
        }
    };

    public static final RecipeComponent<EssencesDefinition> ESSENCES_DEFINITION = new RecipeComponent<EssencesDefinition>() {
        @Override
        public Codec<EssencesDefinition> codec() {
            return EssencesDefinition.CODEC;
        }

        @Override
        public TypeInfo typeInfo() {
            return TypeInfo.of(EssencesDefinition.class);
        }
    };

    public static final RecipeComponent<HolderSet<EnhancerDefinition>> ENHANCERS_DEFINITION = new RecipeComponent<HolderSet<EnhancerDefinition>>() {
        @Override
        public Codec<HolderSet<EnhancerDefinition>> codec() {
            return EnhancerDefinition.LIST_CODEC;
        }

        @Override
        public TypeInfo typeInfo() {
            return TypeInfo.RAW_LIST.withParams(TypeInfo.of(EnhancerDefinition.class));
        }
    };

    public static final RecipeComponent<Holder<MagicCircleType>> MAGIC_CIRCLE_TYPE = new RecipeComponent<Holder<MagicCircleType>>() {
        @Override
        public Codec<Holder<MagicCircleType>> codec() {
            return MagicCircleType.CODEC;
        }

        @Override
        public TypeInfo typeInfo() {
            return TypeInfo.of(Holder.class);
        }
    };


    public static final RecipeComponent<CookingBookCategory> COOKING_BOOK_CATEGORY = new RecipeComponent<CookingBookCategory>() {
        @Override
        public Codec<CookingBookCategory> codec() {
            return CookingBookCategory.CODEC;
        }

        @Override
        public TypeInfo typeInfo() {
            return TypeInfo.of(CookingBookCategory.class);
        }
    };

    public static final RecipeComponent<Either<Ingredient, Pair<Ingredient, Ingredient>>> INPUT = new RecipeComponent<Either<Ingredient, Pair<Ingredient, Ingredient>>>() {
        @Override
        public Codec<Either<Ingredient, Pair<Ingredient, Ingredient>>> codec() {
            return Codec.either(Ingredient.CODEC_NONEMPTY, Codec.mapPair(Ingredient.MAP_CODEC_NONEMPTY.fieldOf("first"), Ingredient.MAP_CODEC_NONEMPTY.fieldOf("second")).codec());
        }

        @Override
        public TypeInfo typeInfo() {
            return TypeInfo.of(Either.class);
        }
    };

    public static final RecipeComponent<ClibanoCookingTimes> CLIBANO_COOKING_TIMES = new RecipeComponent<ClibanoCookingTimes>() {
        @Override
        public Codec<ClibanoCookingTimes> codec() {
            return ClibanoCookingTimes.CODEC;
        }

        @Override
        public TypeInfo typeInfo() {
            return TypeInfo.of(ClibanoCookingTimes.class);
        }
    };

    public static final RecipeComponent<ResidueChance> RESIDUE_CHANCE = new RecipeComponent<ResidueChance>() {
        @Override
        public Codec<ResidueChance> codec() {
            return ResidueChance.CODEC;
        }

        @Override
        public TypeInfo typeInfo() {
            return TypeInfo.of(ResidueChance.class);
        }
    };

    public static final RecipeComponent<Holder<EnhancerDefinition>> ENHANCER_DEFINITION = new RecipeComponent<Holder<EnhancerDefinition>>() {
        @Override
        public Codec<Holder<EnhancerDefinition>> codec() {
            return EnhancerDefinition.REFERENCE_CODEC;
        }

        @Override
        public TypeInfo typeInfo() {
            return TypeInfo.of(Holder.class);
        }
    };
}
