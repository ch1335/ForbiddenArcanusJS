package com.chen1335.forbiddenAndArcanusJS.forbiddenArcanusFix;

import com.chen1335.forbiddenAndArcanusJS.mixinsAPI.IRitualExtension;
import com.google.gson.JsonObject;
import com.mojang.serialization.JsonOps;
import com.stal111.forbidden_arcanus.common.block.entity.forge.ritual.Ritual;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class RitualRecipeSerializer implements RecipeSerializer<Recipe<?>> {


    @Override
    public Recipe<?> fromJson(ResourceLocation resourceLocation, JsonObject jsonObject) {
        Recipe<?> recipe = Recipe.class.cast(Ritual.CODEC.decode(JsonOps.INSTANCE, jsonObject).getOrThrow(false, RuntimeException::new).getFirst());
        IRitualExtension ritualExtension = (IRitualExtension) recipe;
        ritualExtension.FAJS$setId(resourceLocation);
        return recipe;
    }

    @Override
    public @Nullable Recipe<?> fromNetwork(ResourceLocation resourceLocation, FriendlyByteBuf byteBuf) {
        Ritual ritual = byteBuf.readJsonWithCodec(Ritual.NETWORK_CODEC);
        IRitualExtension ritualExtension = IRitualExtension.class.cast(ritual);
        ritualExtension.FAJS$setId(resourceLocation);
        return Recipe.class.cast(ritual);
    }

    @Override
    public void toNetwork(FriendlyByteBuf byteBuf, Recipe<?> recipe) {
        byteBuf.writeJsonWithCodec(Ritual.NETWORK_CODEC, Ritual.class.cast(recipe));
    }
}
