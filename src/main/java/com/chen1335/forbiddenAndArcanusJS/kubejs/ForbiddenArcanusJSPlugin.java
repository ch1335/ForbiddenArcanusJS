package com.chen1335.forbiddenAndArcanusJS.kubejs;

import com.chen1335.forbiddenAndArcanusJS.ForbiddenAndArcanusJS;
import com.chen1335.forbiddenAndArcanusJS.kubejs.recipe.Schemas;
import com.chen1335.forbiddenAndArcanusJS.kubejs.recipe.clibanoCombustion.ClibanoRecipeSchema;
import com.chen1335.forbiddenAndArcanusJS.kubejs.recipe.combineResidue.CombineResiduesSchema;
import com.chen1335.forbiddenAndArcanusJS.kubejs.recipe.ritual.RitualRecipeSchema;
import com.chen1335.forbiddenAndArcanusJS.kubejs.recipe.ritual.RitualResults;
import com.chen1335.forbiddenAndArcanusJS.wrappers.EnhancerDefinitionWrapper;
import com.stal111.forbidden_arcanus.common.block.entity.forge.MagicCircle;
import com.stal111.forbidden_arcanus.common.block.entity.forge.essence.EssencesDefinition;
import com.stal111.forbidden_arcanus.common.item.enhancer.EnhancerDefinition;
import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.recipe.schema.RecipeComponentFactoryRegistryEvent;
import dev.latvian.mods.kubejs.recipe.schema.RegisterRecipeSchemasEvent;
import dev.latvian.mods.kubejs.script.BindingsEvent;
import dev.latvian.mods.kubejs.script.ScriptType;
import dev.latvian.mods.rhino.util.wrap.TypeWrappers;

public class ForbiddenArcanusJSPlugin extends KubeJSPlugin {

    @Override
    public void registerRecipeSchemas(RegisterRecipeSchemasEvent event) {
        event.register(ForbiddenAndArcanusJS.FAId("ritual"), RitualRecipeSchema.SCHEMA);
        event.register(ForbiddenAndArcanusJS.FAId("clibano_combustion"), ClibanoRecipeSchema.SCHEMA);
        event.register(ForbiddenAndArcanusJS.FAId("combine_residues"), CombineResiduesSchema.SCHEMA);
    }

    @Override
    public void registerRecipeComponents(RecipeComponentFactoryRegistryEvent event) {
        event.register("fa_ingredient", Schemas.INGREDIENT);
        event.register("fa_inputs", Schemas.INPUTS);
        event.register("fa_result", Schemas.RESULT);
        event.register("fa_requirements", Schemas.REQUIREMENTS);
        event.register("fa_essences_definition", Schemas.ESSENCES_DEFINITION);
        event.register("fa_magic_circle_config", Schemas.MAGIC_CIRCLE_CONFIG);
        event.register("fa_cooking_book_category", Schemas.COOKING_BOOK_CATEGORY);
        event.register("fa_item_stack", Schemas.ITEM_STACK);
        event.register("fa_residue_info", Schemas.RESIDUE_INFO);


    }

    @Override
    public void registerTypeWrappers(ScriptType type, TypeWrappers typeWrappers) {
        typeWrappers.register(EnhancerDefinition.class, EnhancerDefinitionWrapper::of);
    }

    @Override
    public void registerBindings(BindingsEvent event) {
        if (!event.getType().isClient()) {
            event.add("RitualResults", RitualResults.class);
            event.add("MagicCircleConfig", MagicCircle.Config.class);
            event.add("EssencesDefinition", EssencesDefinition.class);
        }
    }
}
