package com.chen1335.forbiddenAndArcanusJS.mixins;

import com.chen1335.forbiddenAndArcanusJS.forbiddenArcanusFix.ForbiddenArcanusRecipeFix;
import com.stal111.forbidden_arcanus.common.block.entity.forge.ritual.Ritual;
import com.stal111.forbidden_arcanus.common.block.entity.forge.ritual.result.CreateItemResult;
import com.stal111.forbidden_arcanus.common.block.entity.forge.ritual.result.TransmuteInputResult;
import com.stal111.forbidden_arcanus.common.block.entity.forge.ritual.result.UpgradeTierResult;
import com.stal111.forbidden_arcanus.common.integration.ForbiddenArcanusJEIPlugin;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import org.apache.logging.log4j.util.Cast;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.util.List;
import java.util.Objects;

@Mixin(ForbiddenArcanusJEIPlugin.class)
public class ForbiddenArcanusJEIPluginMixin {
    @ModifyArgs(method = "registerRecipes", at = @At(value = "INVOKE", target = "Lmezz/jei/api/registration/IRecipeRegistration;addRecipes(Lmezz/jei/api/recipe/RecipeType;Ljava/util/List;)V"))
    private void registerRecipes(Args args) {
        Level level = Objects.requireNonNull(Minecraft.getInstance().level);
        List<? extends RecipeHolder<?>> recipes = level.getRecipeManager().getAllRecipesFor(Cast.cast(ForbiddenArcanusRecipeFix.RITUAL_RECIPE_TYPE.value()));
        if (args.get(0) == ForbiddenArcanusJEIPlugin.HEPHAESTUS_SMITHING) {
            args.set(1, recipes.stream().filter((ritual) -> Ritual.class.cast(ritual.value()).result() instanceof CreateItemResult || Ritual.class.cast(ritual.value()).result() instanceof TransmuteInputResult).map(RecipeHolder::value).toList());
        } else if (args.get(0) == ForbiddenArcanusJEIPlugin.HEPHAESTUS_FORGE_UPGRADING) {
            args.set(1, recipes.stream().filter((ritual) -> Ritual.class.cast(ritual.value()).result() instanceof UpgradeTierResult).map(RecipeHolder::value).toList());
        }
    }
}
