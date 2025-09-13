package com.chen1335.forbiddenAndArcanusJS.kubejs;

import com.chen1335.forbiddenAndArcanusJS.kubejs.recipe.clibanoCombustion.ClibanoRecipeJS;
import com.chen1335.forbiddenAndArcanusJS.kubejs.recipe.clibanoCombustion.ClibanoRecipeSchema;
import com.chen1335.forbiddenAndArcanusJS.kubejs.recipe.ritual.RitualRecipeJS;
import com.chen1335.forbiddenAndArcanusJS.kubejs.recipe.ritual.RitualRecipeSchema;
import com.chen1335.forbiddenAndArcanusJS.kubejs.recipe.ritual.RitualResults;
import com.stal111.forbidden_arcanus.ForbiddenArcanus;
import dev.latvian.mods.kubejs.plugin.KubeJSPlugin;
import dev.latvian.mods.kubejs.recipe.schema.RecipeFactoryRegistry;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchemaRegistry;
import dev.latvian.mods.kubejs.script.BindingRegistry;

public class ForbiddenArcanusJSPlugin implements KubeJSPlugin {
    @Override
    public void registerRecipeFactories(RecipeFactoryRegistry registry) {
        registry.register(RitualRecipeJS.RECIPE_FACTORY);
        registry.register(ClibanoRecipeJS.RECIPE_FACTORY);
    }

    @Override
    public void registerRecipeSchemas(RecipeSchemaRegistry registry) {
        registry.register(ForbiddenArcanus.location("ritual"), RitualRecipeSchema.SCHEMA);
        registry.register(ForbiddenArcanus.location("clibano_combustion"), ClibanoRecipeSchema.SCHEMA);
    }

    @Override
    public void registerBindings(BindingRegistry bindings) {
        if (!bindings.type().isClient()) {
            bindings.add("RitualResults", RitualResults.class);
        }
    }
}
