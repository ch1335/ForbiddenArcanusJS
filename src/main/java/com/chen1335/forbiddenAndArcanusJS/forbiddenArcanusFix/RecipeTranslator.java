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
            return resourceLocation.getPath().contains(".json");
        });

        int casted = 0;

        for (Map.Entry<ResourceLocation, Resource> entry : fuckRecipe.entrySet()) {
            String namespace = entry.getKey().getNamespace();
            ForbiddenAndArcanusJS.LOGGER.debug("start cast recipe from:{}", entry.getKey());
            String[] strings = entry.getKey().toString().split("\\.")[0].split("/");
            ResourceLocation resourceLocation = ResourceLocation.parse(namespace + ":ritual/" + strings[strings.length - 1]);
            try (BufferedReader inputStream = entry.getValue().openAsReader()) {
                if (object.get(entry.getKey()) == null) {
                    JsonElement jsonElement = JsonParser.parseReader(new JsonReader(inputStream));


                    if (jsonElement == null) {
                        ForbiddenAndArcanusJS.LOGGER.error("error cast recipe:{} because this element is null", entry.getKey().toString());
                    } else if (jsonElement.isJsonObject()) {
                        try {
                            jsonElement.getAsJsonObject().addProperty("type", "forbidden_arcanus:ritual");
                            object.put(resourceLocation, jsonElement.getAsJsonObject());
                            casted++;
                        } catch (Exception e) {
                            ForbiddenAndArcanusJS.LOGGER.error("error cast recipe:{},error info:{},json info:{}", entry.getKey().toString(), e, jsonElement);
                        }
                    }
                }
            }
        }

        ForbiddenAndArcanusJS.LOGGER.error("success cast {} recipe", casted);
    }
}
