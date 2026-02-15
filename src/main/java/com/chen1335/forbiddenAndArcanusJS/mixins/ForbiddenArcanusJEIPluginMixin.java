package com.chen1335.forbiddenAndArcanusJS.mixins;

import com.chen1335.forbiddenAndArcanusJS.Util;
import com.chen1335.forbiddenAndArcanusJS.forbiddenArcanusFix.ForbiddenArcanusRecipeFix;
import com.chen1335.forbiddenAndArcanusJS.jei.UpdateItemCategory;
import com.chen1335.forbiddenAndArcanusJS.mixinsAPI.IForbiddenArcanusJEIPluginExtension;
import com.chen1335.forbiddenAndArcanusJS.ritualResults.TransmuteInputResult;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.stal111.forbidden_arcanus.common.block.entity.forge.ritual.Ritual;
import com.stal111.forbidden_arcanus.common.block.entity.forge.ritual.result.CreateItemResult;
import com.stal111.forbidden_arcanus.common.block.entity.forge.ritual.result.UpgradeTierResult;
import com.stal111.forbidden_arcanus.common.integration.ForbiddenArcanusJEIPlugin;
import com.stal111.forbidden_arcanus.core.init.ModBlocks;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Objects;

@Pseudo
@Mixin(value = ForbiddenArcanusJEIPlugin.class, remap = false)
public class ForbiddenArcanusJEIPluginMixin implements IForbiddenArcanusJEIPluginExtension {

    @WrapOperation(method = "registerRecipes", at = @At(value = "INVOKE", target = "Lmezz/jei/api/registration/IRecipeRegistration;addRecipes(Lmezz/jei/api/recipe/RecipeType;Ljava/util/List;)V"))
    private void warpAdd(IRecipeRegistration instance, RecipeType<?> tRecipeType, List<?> ts, Operation<Void> original) {
        Level level = Objects.requireNonNull(Minecraft.getInstance().level);
        List<Ritual> recipes = Util.cast(level.getRecipeManager().getAllRecipesFor(Util.cast(ForbiddenArcanusRecipeFix.RITUAL_RECIPE_TYPE.get())));

        if (tRecipeType == ForbiddenArcanusJEIPlugin.HEPHAESTUS_SMITHING) {
            original.call(instance, tRecipeType, recipes.stream().filter((ritual) -> ritual.result() instanceof CreateItemResult).toList());
        } else if (tRecipeType == ForbiddenArcanusJEIPlugin.HEPHAESTUS_FORGE_UPGRADING) {
            original.call(instance, tRecipeType, recipes.stream().filter((ritual) -> ritual.result() instanceof UpgradeTierResult).toList());
        } else if (tRecipeType == HEPHAESTUS_ITEM_UPGRADING) {
            original.call(instance, tRecipeType, recipes.stream().filter((ritual) -> ritual.result() instanceof TransmuteInputResult).toList());
        } else {
            original.call(instance, tRecipeType, ts);
        }
    }

    @Inject(method = "registerRecipes", at = @At("RETURN"))
    private void registerRecipes(IRecipeRegistration registration, CallbackInfo ci) {
        Level level = Objects.requireNonNull(Minecraft.getInstance().level);
        List<Ritual> recipes = Util.cast(level.getRecipeManager().getAllRecipesFor(Util.cast(ForbiddenArcanusRecipeFix.RITUAL_RECIPE_TYPE.get())));

        registration.addRecipes(HEPHAESTUS_ITEM_UPGRADING, recipes.stream().filter((ritual) -> ritual.result() instanceof TransmuteInputResult).toList());
    }

    @Inject(method = "registerRecipeCatalysts", at = @At("RETURN"))
    private void registerRecipeCatalysts(IRecipeCatalystRegistration registration, CallbackInfo ci) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.HEPHAESTUS_FORGE.get()), HEPHAESTUS_ITEM_UPGRADING);
    }

    @Inject(method = "registerCategories", at = @At("RETURN"))
    private void registerCategories(IRecipeCategoryRegistration registration, CallbackInfo ci) {
        IGuiHelper guiHelper = registration.getJeiHelpers().getGuiHelper();

        registration.addRecipeCategories(new UpdateItemCategory(guiHelper));
    }

}
