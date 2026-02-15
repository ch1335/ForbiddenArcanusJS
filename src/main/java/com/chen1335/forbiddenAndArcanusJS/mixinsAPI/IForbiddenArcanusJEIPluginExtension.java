package com.chen1335.forbiddenAndArcanusJS.mixinsAPI;

import com.stal111.forbidden_arcanus.ForbiddenArcanus;
import com.stal111.forbidden_arcanus.common.block.entity.forge.ritual.Ritual;
import mezz.jei.api.recipe.RecipeType;

public interface IForbiddenArcanusJEIPluginExtension {
    RecipeType<Ritual> HEPHAESTUS_ITEM_UPGRADING = RecipeType.create(ForbiddenArcanus.MOD_ID, "item_upgrading", Ritual.class);

}
