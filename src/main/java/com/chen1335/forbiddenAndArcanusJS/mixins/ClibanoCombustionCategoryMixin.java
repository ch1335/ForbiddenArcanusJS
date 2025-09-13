package com.chen1335.forbiddenAndArcanusJS.mixins;

import com.stal111.forbidden_arcanus.common.integration.ClibanoCombustionCategory;
import com.stal111.forbidden_arcanus.common.item.crafting.ClibanoRecipe;
import com.stal111.forbidden_arcanus.core.init.ModRecipeTypes;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;

import java.util.List;

@Mixin(ClibanoCombustionCategory.class)
public abstract class ClibanoCombustionCategoryMixin implements IRecipeCategory<ClibanoRecipe> {
    @Override
    public @Nullable ResourceLocation getRegistryName(@NotNull ClibanoRecipe recipe) {
        Level level = Minecraft.getInstance().level;
        List<RecipeHolder<ClibanoRecipe>> recipes = level.getRecipeManager().getAllRecipesFor(ModRecipeTypes.CLIBANO_COMBUSTION.value());
        for (RecipeHolder<ClibanoRecipe> recipeRecipeHolder : recipes) {
            if (recipeRecipeHolder.value().equals(recipe)) {
                return recipeRecipeHolder.id();
            }
        }
        return null;
    }
}
