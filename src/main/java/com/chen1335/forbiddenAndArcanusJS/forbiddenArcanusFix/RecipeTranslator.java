package com.chen1335.forbiddenAndArcanusJS.forbiddenArcanusFix;

import com.chen1335.forbiddenAndArcanusJS.ForbiddenAndArcanusJS;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

public class RecipeTranslator {
    public static void translate(Map<ResourceLocation, JsonElement> object, ResourceManager resourceManager, ProfilerFiller profiler) throws IOException {
        Map<ResourceLocation, Resource> fuckRecipe = resourceManager.listResources("forbidden_arcanus/hephaestus_forge/ritual", resourceLocation -> {
            return resourceLocation.toString().contains("forbidden_arcanus:forbidden_arcanus/hephaestus_forge/ritual");
        });
        for (Map.Entry<ResourceLocation, Resource> entry : fuckRecipe.entrySet()) {
            try (BufferedReader inputStream = entry.getValue().openAsReader()) {
                if (object.get(entry.getKey()) == null) {
                    JsonElement jsonElement = JsonParser.parseReader(new JsonReader(inputStream));
                    if (jsonElement == null) {
                        ForbiddenAndArcanusJS.LOGGER.error("error cast recipe:{} because this element is null", entry.getKey().toString());
                    } else if (jsonElement.isJsonObject()) {
                        try {
                            jsonElement.getAsJsonObject().addProperty("type", "forbidden_arcanus:ritual");
                            String[] strings = entry.getKey().toString().split("\\.")[0].split("/");
                            object.put(ResourceLocation.parse("forbidden_arcanus:ritual/" + strings[strings.length - 1]), jsonElement.getAsJsonObject());
                        } catch (Exception e) {
                            ForbiddenAndArcanusJS.LOGGER.error("error cast recipe:{},error info:{},json info:{}", entry.getKey().toString(), e, jsonElement);
                        }
                    }
                }
            }
        }
    }
}
