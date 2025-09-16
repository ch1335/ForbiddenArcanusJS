package com.chen1335.forbiddenAndArcanusJS.mixins;

import com.chen1335.forbiddenAndArcanusJS.forbiddenArcanusFix.ForbiddenArcanusRecipeFix;
import com.stal111.forbidden_arcanus.common.block.entity.forge.ritual.Ritual;
import com.stal111.forbidden_arcanus.common.integration.hephaestus_forge.HephaestusForgeCategory;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.level.Level;
import org.apache.logging.log4j.util.Cast;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;

import java.util.List;

@Mixin(HephaestusForgeCategory.class)
public abstract class HephaestusForgeCategoryMixin implements IRecipeCategory<Ritual> {
    @Override
    public @Nullable ResourceLocation getRegistryName(@NotNull Ritual recipe) {
        Level level = Minecraft.getInstance().level;
        List<RecipeHolder<Recipe<RecipeInput>>> recipes = level.getRecipeManager().getAllRecipesFor(Cast.cast(ForbiddenArcanusRecipeFix.RITUAL_RECIPE_TYPE.value()));
        for (RecipeHolder<Recipe<RecipeInput>> recipeRecipeHolder : recipes) {
            if (recipeRecipeHolder.value().equals(Cast.cast(recipe))) {
                return recipeRecipeHolder.id();
            }
        }
        return null;
    }
}
