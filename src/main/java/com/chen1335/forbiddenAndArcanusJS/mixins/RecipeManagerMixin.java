package com.chen1335.forbiddenAndArcanusJS.mixins;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.crafting.RecipeManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

@Mixin(RecipeManager.class)
public class RecipeManagerMixin {
    @Inject(method = "apply(Ljava/util/Map;Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/util/profiling/ProfilerFiller;)V", at = @At("HEAD"))
    private void apply(Map<ResourceLocation, JsonElement> object, ResourceManager resourceManager, ProfilerFiller profiler, CallbackInfo ci) {
        Map<ResourceLocation, Resource> fuckRecipe = resourceManager.listResources("forbidden_arcanus/hephaestus_forge/ritual", resourceLocation -> {
            return resourceLocation.toString().contains("forbidden_arcanus:forbidden_arcanus/hephaestus_forge/ritual");
        });
        for (Map.Entry<ResourceLocation, Resource> entry : fuckRecipe.entrySet()) {
            try (BufferedReader inputStream = entry.getValue().openAsReader()) {
                if (object.get(entry.getKey()) == null) {
                    JsonElement jsonElement = JsonParser.parseReader(new JsonReader(inputStream));
                    jsonElement.getAsJsonObject().addProperty("type", "forbidden_arcanus:ritual");
                    String[] strings = entry.getKey().toString().split("\\.")[0].split("/");

                    object.put(ResourceLocation.parse("forbidden_arcanus:ritual/" + strings[strings.length - 1]), jsonElement);
                }
            } catch (IOException ignored) {

            }
        }

    }


}
