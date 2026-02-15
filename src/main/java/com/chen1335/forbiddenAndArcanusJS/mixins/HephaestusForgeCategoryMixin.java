package com.chen1335.forbiddenAndArcanusJS.mixins;

import com.chen1335.forbiddenAndArcanusJS.mixinsAPI.IRitualExtension;
import com.stal111.forbidden_arcanus.common.block.entity.forge.ritual.Ritual;
import com.stal111.forbidden_arcanus.common.integration.hephaestus_forge.HephaestusForgeCategory;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(HephaestusForgeCategory.class)
public abstract class HephaestusForgeCategoryMixin implements IRecipeCategory<Ritual> {
    @Override
    public @Nullable ResourceLocation getRegistryName(@NotNull Ritual recipe) {
        return IRitualExtension.class.cast(recipe).getId();
    }

}

