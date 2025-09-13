package com.chen1335.forbiddenAndArcanusJS.mixins;


import com.chen1335.forbiddenAndArcanusJS.forbiddenArcanusFix.ForbiddenArcanusRecipeFix;
import com.chen1335.forbiddenAndArcanusJS.forbiddenArcanusFix.RitualRecipeInput;
import com.mojang.serialization.Codec;
import com.stal111.forbidden_arcanus.common.block.entity.forge.ritual.Ritual;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.server.ServerLifecycleHooks;
import org.apache.logging.log4j.util.Cast;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.reflect.Field;
import java.util.Optional;

@Mixin(Ritual.class)
public class RitualMixin implements Recipe<RitualRecipeInput> {
    private static final Codec<Holder<?>> CODEC = Codec.stringResolver(ritualHolder -> {
        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
        Optional<? extends RecipeHolder<?>> o = server.getRecipeManager().getAllRecipesFor(Cast.cast(ForbiddenArcanusRecipeFix.RITUAL_RECIPE_TYPE.value())).stream().filter(tRecipeHolder -> tRecipeHolder.value().equals(Holder.class.cast(ritualHolder).value())).findFirst();
        return o.map(recipeRecipeHolder -> recipeRecipeHolder.id().toString()).orElse(null);
    }, s -> {
        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
        Holder<?> holder = Holder.direct(server.getRecipeManager().byKey(ResourceLocation.parse(s)).get().value());
        return holder;
    });

    @Override
    public boolean matches(@NotNull RitualRecipeInput input, @NotNull Level level) {

        return false;
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull RitualRecipeInput input, HolderLookup.@NotNull Provider registries) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return false;
    }

    @Override
    public @NotNull ItemStack getResultItem(HolderLookup.@NotNull Provider registries) {
        return ItemStack.EMPTY;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return (RecipeSerializer<?>) ForbiddenArcanusRecipeFix.ALTAR_RECIPE_SERIALIZER.get();
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return Cast.cast(ForbiddenArcanusRecipeFix.RITUAL_RECIPE_TYPE.value());
    }

    @Inject(method = "<clinit>", at = @At("RETURN"))
    private static void clinit(CallbackInfo ci) {
        try {
            Field field = Ritual.class.getDeclaredField("CODEC");
            field.set(null, CODEC);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
