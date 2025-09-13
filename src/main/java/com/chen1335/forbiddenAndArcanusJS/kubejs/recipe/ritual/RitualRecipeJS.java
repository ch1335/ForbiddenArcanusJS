package com.chen1335.forbiddenAndArcanusJS.kubejs.recipe.ritual;

import com.stal111.forbidden_arcanus.ForbiddenArcanus;
import com.stal111.forbidden_arcanus.common.block.entity.forge.circle.MagicCircleType;
import com.stal111.forbidden_arcanus.common.block.entity.forge.essence.EssencesDefinition;
import com.stal111.forbidden_arcanus.common.block.entity.forge.ritual.RitualInput;
import com.stal111.forbidden_arcanus.common.block.entity.forge.ritual.result.CreateItemResult;
import com.stal111.forbidden_arcanus.common.item.enhancer.EnhancerDefinition;
import com.stal111.forbidden_arcanus.data.hephaestus_forge.ModMagicCircles;
import dev.latvian.mods.kubejs.recipe.KubeRecipe;
import dev.latvian.mods.kubejs.recipe.schema.KubeRecipeFactory;
import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.kubejs.util.RegistryAccessContainer;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.Objects;

public class RitualRecipeJS extends KubeRecipe {
    public static final KubeRecipeFactory RECIPE_FACTORY = new KubeRecipeFactory(ForbiddenArcanus.location("ritual"), RitualRecipeJS.class, RitualRecipeJS::new);

    @Info("Add one input and do not exceed 8 inputs")
    public RitualRecipeJS addInput(Ingredient ingredient, int amount) {
        Objects.requireNonNull(getValue(RitualRecipeSchema.INPUTS)).add(new RitualInput(ingredient, amount));
        return this;
    }

    @Info("the main Ingredient")
    public RitualRecipeJS mainIngredient(Ingredient ingredient) {
        setValue(RitualRecipeSchema.MAIN_INGREDIENT, ingredient);
        return this;
    }

    @Info("essences require,optional")
    public RitualRecipeJS essences(int aureal, int souls, int blood, int experience) {
        setValue(RitualRecipeSchema.ESSENCES_DEFINITION, new EssencesDefinition(aureal, souls, blood, experience));
        return this;
    }

    @Info("tier require,optional,default tier 1")
    public RitualRecipeJS tier(int tier) {
        setValue(RitualRecipeSchema.TIER, tier);
        return this;
    }

    @Info("match tier exact,optional,default false")
    public RitualRecipeJS matchTierExact(boolean exact) {
        setValue(RitualRecipeSchema.MATCH_TIER_EXACT, exact);
        return this;
    }


    public final RitualRecipeJS enhancers(Holder<EnhancerDefinition> enhancerDefinitions) {
        setValue(RitualRecipeSchema.ENHANCER_DEFINITION, HolderSet.direct(enhancerDefinitions));
        return this;
    }

    public final RitualRecipeJS enhancers(Holder<EnhancerDefinition> enhancerDefinitions1, Holder<EnhancerDefinition> enhancerDefinitions2) {
        setValue(RitualRecipeSchema.ENHANCER_DEFINITION, HolderSet.direct(enhancerDefinitions1, enhancerDefinitions2));
        return this;
    }

    public final RitualRecipeJS enhancers(Holder<EnhancerDefinition> enhancerDefinitions1, Holder<EnhancerDefinition> enhancerDefinitions2, Holder<EnhancerDefinition> enhancerDefinitions3) {
        setValue(RitualRecipeSchema.ENHANCER_DEFINITION, HolderSet.direct(enhancerDefinitions1, enhancerDefinitions2, enhancerDefinitions3));
        return this;
    }

    public final RitualRecipeJS enhancers(Holder<EnhancerDefinition> enhancerDefinitions1, Holder<EnhancerDefinition> enhancerDefinitions2, Holder<EnhancerDefinition> enhancerDefinitions3, Holder<EnhancerDefinition> enhancerDefinitions4) {
        setValue(RitualRecipeSchema.ENHANCER_DEFINITION, HolderSet.direct(enhancerDefinitions1, enhancerDefinitions2, enhancerDefinitions3, enhancerDefinitions4));
        return this;
    }

    @Info("the magic circles,require if result is not CreateItemResult")
    public RitualRecipeJS magicCircles(Holder<MagicCircleType> magicCircleType) {
        setValue(RitualRecipeSchema.MAGIC_CIRCLE_TYPE, magicCircleType);
        return this;
    }

    @Info("the duration of the recipe,optional")
    public RitualRecipeJS duration(int duration) {
        setValue(RitualRecipeSchema.DURATION, duration);
        return this;
    }


    @Override
    public void afterLoaded() {
        super.afterLoaded();
        RegistryAccess.Frozen access = RegistryAccessContainer.current.access();
        if (getValue(RitualRecipeSchema.RESULT) instanceof CreateItemResult && getValue(RitualRecipeSchema.MAGIC_CIRCLE_TYPE) == null) {
            setValue(RitualRecipeSchema.MAGIC_CIRCLE_TYPE, access.holderOrThrow(ModMagicCircles.CREATE_ITEM));
        }
    }
}
