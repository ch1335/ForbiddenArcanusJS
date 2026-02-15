package com.chen1335.forbiddenAndArcanusJS.kubejs.recipe.ritual;

import com.stal111.forbidden_arcanus.common.block.entity.forge.MagicCircle;
import com.stal111.forbidden_arcanus.common.block.entity.forge.essence.EssencesDefinition;
import com.stal111.forbidden_arcanus.common.block.entity.forge.ritual.RitualInput;
import com.stal111.forbidden_arcanus.common.block.entity.forge.ritual.RitualRequirements;
import com.stal111.forbidden_arcanus.common.item.enhancer.EnhancerDefinition;
import dev.latvian.mods.kubejs.recipe.RecipeJS;
import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.core.Holder;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;
import java.util.Objects;

public class RitualRecipeJS extends RecipeJS {

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
        RitualRequirements value = getValue(RitualRecipeSchema.RITUAL_REQUIREMENTS);
        List<Holder<EnhancerDefinition>> enhancers = List.of();
        if (value != null) {
            enhancers = value.enhancers();
        }
        setValue(RitualRecipeSchema.RITUAL_REQUIREMENTS, new RitualRequirements(tier, enhancers));
        return this;
    }


    public final RitualRecipeJS enhancers(EnhancerDefinition enhancerDefinition) {
        RitualRequirements value = getValue(RitualRecipeSchema.RITUAL_REQUIREMENTS);
        int enhancers = 1;
        if (value != null) {
            enhancers = value.tier();
        }

        setValue(RitualRecipeSchema.RITUAL_REQUIREMENTS, new RitualRequirements(enhancers, List.of(Holder.direct(enhancerDefinition))));
        return this;
    }

    public final RitualRecipeJS enhancers(EnhancerDefinition enhancerDefinitions1, EnhancerDefinition enhancerDefinitions2) {
        RitualRequirements value = getValue(RitualRecipeSchema.RITUAL_REQUIREMENTS);
        int enhancers = 1;
        if (value != null) {
            enhancers = value.tier();
        }
        setValue(RitualRecipeSchema.RITUAL_REQUIREMENTS, new RitualRequirements(enhancers, List.of(Holder.direct(enhancerDefinitions1), Holder.direct(enhancerDefinitions2))));
        return this;
    }

    public final RitualRecipeJS enhancers(EnhancerDefinition enhancerDefinitions1, EnhancerDefinition enhancerDefinitions2, EnhancerDefinition enhancerDefinitions3) {
        RitualRequirements value = getValue(RitualRecipeSchema.RITUAL_REQUIREMENTS);
        int enhancers = 1;
        if (value != null) {
            enhancers = value.tier();
        }
        setValue(RitualRecipeSchema.RITUAL_REQUIREMENTS, new RitualRequirements(enhancers, List.of(Holder.direct(enhancerDefinitions1), Holder.direct(enhancerDefinitions2), Holder.direct(enhancerDefinitions3))));
        return this;
    }

    public final RitualRecipeJS enhancers(EnhancerDefinition enhancerDefinitions1, EnhancerDefinition enhancerDefinitions2, EnhancerDefinition enhancerDefinitions3, EnhancerDefinition enhancerDefinitions4) {
        RitualRequirements value = getValue(RitualRecipeSchema.RITUAL_REQUIREMENTS);
        int enhancers = 1;
        if (value != null) {
            enhancers = value.tier();
        }
        setValue(RitualRecipeSchema.RITUAL_REQUIREMENTS, new RitualRequirements(enhancers, List.of(Holder.direct(enhancerDefinitions1), Holder.direct(enhancerDefinitions2), Holder.direct(enhancerDefinitions3), Holder.direct(enhancerDefinitions4))));
        return this;
    }

    @Info("the magic circles,require if result is not CreateItemResult")
    public RitualRecipeJS magicCircles(MagicCircle.Config magicCircleType) {
        setValue(RitualRecipeSchema.MAGIC_CIRCLE_CONFIG, magicCircleType);
        return this;
    }


    @Override
    public void initValues(boolean created) {
        super.initValues(created);

    }

    @Override
    public void afterLoaded() {
        super.afterLoaded();
    }
}
