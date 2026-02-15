package com.chen1335.forbiddenAndArcanusJS.mixins;

import com.chen1335.forbiddenAndArcanusJS.Util;
import com.chen1335.forbiddenAndArcanusJS.forbiddenArcanusFix.ForbiddenArcanusRecipeFix;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.stal111.forbidden_arcanus.common.block.entity.forge.ritual.Ritual;
import com.stal111.forbidden_arcanus.common.block.entity.forge.ritual.result.CreateItemResult;
import com.stal111.forbidden_arcanus.common.block.entity.forge.ritual.result.UpgradeTierResult;
import com.stal111.forbidden_arcanus.common.integration.ForbiddenArcanusJEIPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;
import java.util.Objects;

@Pseudo
@Mixin(value = ForbiddenArcanusJEIPlugin.class, remap = false)
public class ForbiddenArcanusJEIPluginMixin {

    @WrapOperation(method = "registerRecipes", at = @At(value = "INVOKE", target = "Lmezz/jei/api/registration/IRecipeRegistration;addRecipes(Lmezz/jei/api/recipe/RecipeType;Ljava/util/List;)V"))
    private void warpAdd(IRecipeRegistration instance, RecipeType<?> tRecipeType, List<?> ts, Operation<Void> original) {
        Level level = Objects.requireNonNull(Minecraft.getInstance().level);
        List<Ritual> recipes = Util.cast(level.getRecipeManager().getAllRecipesFor(Util.cast(ForbiddenArcanusRecipeFix.RITUAL_RECIPE_TYPE.get())));

        if (tRecipeType == ForbiddenArcanusJEIPlugin.HEPHAESTUS_SMITHING) {
            original.call(instance, tRecipeType, recipes.stream().filter((ritual) -> ritual.result() instanceof CreateItemResult).toList());
        } else if (tRecipeType == ForbiddenArcanusJEIPlugin.HEPHAESTUS_FORGE_UPGRADING) {
            original.call(instance, tRecipeType, recipes.stream().filter((ritual) -> ritual.result() instanceof UpgradeTierResult).toList());
        } else {
            original.call(instance, tRecipeType, ts);
        }
    }
}
