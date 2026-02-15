package com.chen1335.forbiddenAndArcanusJS.mixins;


import com.chen1335.forbiddenAndArcanusJS.forbiddenArcanusFix.ForbiddenArcanusRecipeFix;
import com.chen1335.forbiddenAndArcanusJS.mixinsAPI.IRitualExtension;
import com.stal111.forbidden_arcanus.common.block.entity.forge.ritual.Ritual;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
@Mixin(Ritual.class)
public class RitualMixin implements IRitualExtension {
    @Unique
    private ResourceLocation FAJS$id = null;

    @Override
    public ResourceLocation getId() {
        return FAJS$id;
    }

    @Override
    public boolean matches(Container container, Level level) {
        return false;
    }

    @Override
    public ItemStack assemble(Container container, RegistryAccess registryAccess) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return false;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return ItemStack.EMPTY;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return ForbiddenArcanusRecipeFix.ALTAR_RECIPE_SERIALIZER.get();
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return ForbiddenArcanusRecipeFix.RITUAL_RECIPE_TYPE.get();
    }

    @Override
    public void FAJS$setId(ResourceLocation resourceLocation) {
        FAJS$id = resourceLocation;
    }
}
