package com.chen1335.forbiddenAndArcanusJS.forbiddenArcanusFix;

import com.stal111.forbidden_arcanus.ForbiddenArcanus;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ForbiddenArcanusRecipeFix {
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Registries.RECIPE_TYPE, ForbiddenArcanus.MOD_ID);
    public static final RegistryObject<RecipeType<?>> RITUAL_RECIPE_TYPE = RECIPE_TYPES.register(
            "ritual",
            () -> new RecipeType<>() {
                @Override
                public String toString() {
                    return "ritual";
                }
            }
    );
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, ForbiddenArcanus.MOD_ID);

    public static final RegistryObject<RecipeSerializer<?>> ALTAR_RECIPE_SERIALIZER = RECIPE_SERIALIZERS.register(
            "ritual",
            RitualRecipeSerializer::new
    );


    public static void init(IEventBus modEventBus) {
        RECIPE_TYPES.register(modEventBus);
        RECIPE_SERIALIZERS.register(modEventBus);
    }
}
