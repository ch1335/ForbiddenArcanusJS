package com.chen1335.forbiddenAndArcanusJS.kubejs.recipe;

import com.chen1335.forbiddenAndArcanusJS.Util;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.serialization.JsonOps;
import com.stal111.forbidden_arcanus.common.block.entity.forge.MagicCircle;
import com.stal111.forbidden_arcanus.common.block.entity.forge.essence.EssencesDefinition;
import com.stal111.forbidden_arcanus.common.block.entity.forge.ritual.RitualInput;
import com.stal111.forbidden_arcanus.common.block.entity.forge.ritual.RitualRequirements;
import com.stal111.forbidden_arcanus.common.block.entity.forge.ritual.result.RitualResult;
import com.stal111.forbidden_arcanus.common.recipe.ClibanoRecipe;
import dev.latvian.mods.kubejs.item.ItemStackJS;
import dev.latvian.mods.kubejs.item.ingredient.IngredientJS;
import dev.latvian.mods.kubejs.recipe.RecipeJS;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponent;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Objects;

public class Schemas {

    public static final RecipeComponent<Ingredient> INGREDIENT = new RecipeComponent<>() {

        @Override
        public Class<?> componentClass() {
            return Ingredient.class;
        }

        @Override
        public JsonElement write(RecipeJS recipeJS, Ingredient ingredient) {
            return ingredient.toJson();
        }

        @Override
        public Ingredient read(RecipeJS recipeJS, Object o) {
            return IngredientJS.of(o);
        }
    };

    public static final RecipeComponent<List<RitualInput>> INPUTS = new RecipeComponent<>() {

        @Override
        public Class<?> componentClass() {
            return List.class;
        }

        @Override
        public JsonElement write(RecipeJS recipeJS, List<RitualInput> ritualInputs) {
            return RitualInput.CODEC.listOf().encodeStart(JsonOps.INSTANCE, ritualInputs).get().orThrow();
        }

        @Override
        public List<RitualInput> read(RecipeJS recipeJS, Object o) {
            if (o instanceof List) {
                return Util.cast(o);
            } else if (o instanceof JsonElement jsonObject) {
                return RitualInput.CODEC.listOf().decode(JsonOps.INSTANCE, jsonObject).get().orThrow().getFirst();
            }
            throw new IllegalArgumentException("can not cast %s to List<RitualInput>".formatted(o));
        }
    };

    public static final RecipeComponent<RitualResult> RESULT = new RecipeComponent<>() {

        @Override
        public Class<?> componentClass() {
            return RitualResult.class;
        }

        @Override
        public JsonElement write(RecipeJS recipeJS, RitualResult ritualResult) {
            return RitualResult.DIRECT_CODEC.encodeStart(JsonOps.INSTANCE, ritualResult).get().orThrow();
        }

        @Override
        public RitualResult read(RecipeJS recipeJS, Object o) {
            if (o instanceof RitualResult ritualResult) {
                return ritualResult;
            } else if (o instanceof JsonElement jsonObject) {
                return RitualResult.DIRECT_CODEC.decode(JsonOps.INSTANCE, jsonObject).get().orThrow().getFirst();
            }
            throw new IllegalArgumentException("can not cast %s to RitualResult".formatted(o));
        }
    };

    public static final RecipeComponent<RitualRequirements> REQUIREMENTS = new RecipeComponent<>() {

        @Override
        public Class<?> componentClass() {
            return RitualRequirements.class;
        }

        @Override
        public JsonElement write(RecipeJS recipeJS, RitualRequirements ritualRequirements) {
            return RitualRequirements.CODEC.encodeStart(JsonOps.INSTANCE, ritualRequirements).get().orThrow();
        }

        @Override
        public RitualRequirements read(RecipeJS recipeJS, Object o) {
            if (o instanceof RitualRequirements requirements) {
                return requirements;
            } else if (o instanceof JsonElement jsonObject) {
                return RitualRequirements.CODEC.decode(JsonOps.INSTANCE, jsonObject).get().orThrow().getFirst();
            }
            throw new IllegalArgumentException("can not cast %s to RitualRequirements".formatted(o));
        }
    };

    public static final RecipeComponent<EssencesDefinition> ESSENCES_DEFINITION = new RecipeComponent<>() {

        @Override
        public Class<?> componentClass() {
            return EssencesDefinition.class;
        }

        @Override
        public JsonElement write(RecipeJS recipeJS, EssencesDefinition essencesDefinition) {
            return EssencesDefinition.CODEC.encodeStart(JsonOps.INSTANCE, essencesDefinition).get().orThrow();
        }

        @Override
        public EssencesDefinition read(RecipeJS recipeJS, Object o) {
            if (o instanceof EssencesDefinition essencesDefinition) {
                return essencesDefinition;
            } else if (o instanceof JsonElement jsonObject) {
                return EssencesDefinition.CODEC.decode(JsonOps.INSTANCE, jsonObject).get().orThrow().getFirst();
            }
            throw new IllegalArgumentException("can not cast %s to EssencesDefinition".formatted(o));
        }
    };


    public static final RecipeComponent<MagicCircle.Config> MAGIC_CIRCLE_CONFIG = new RecipeComponent<>() {

        @Override
        public Class<?> componentClass() {
            return MagicCircle.Config.class;
        }

        @Override
        public JsonElement write(RecipeJS recipeJS, MagicCircle.Config config) {
            return MagicCircle.Config.CODEC.encodeStart(JsonOps.INSTANCE, config).get().orThrow();
        }

        @Override
        public MagicCircle.Config read(RecipeJS recipeJS, Object o) {
            if (o instanceof MagicCircle.Config config) {
                return config;
            } else if (o instanceof JsonElement jsonObject) {
                return MagicCircle.Config.CODEC.decode(JsonOps.INSTANCE, jsonObject).get().orThrow().getFirst();
            }
            throw new IllegalArgumentException("can not cast %s to MagicCircleConfig".formatted(o));
        }
    };


    public static final RecipeComponent<CookingBookCategory> COOKING_BOOK_CATEGORY = new RecipeComponent<>() {

        @Override
        public Class<?> componentClass() {
            return CookingBookCategory.class;
        }

        @Override
        public JsonElement write(RecipeJS recipeJS, CookingBookCategory cookingBookCategory) {
            return CookingBookCategory.CODEC.encodeStart(JsonOps.INSTANCE, cookingBookCategory).get().orThrow();
        }

        @Override
        public CookingBookCategory read(RecipeJS recipeJS, Object o) {
            if (o instanceof CookingBookCategory cookingBookCategory) {
                return cookingBookCategory;
            } else if (o instanceof JsonElement jsonObject) {
                return CookingBookCategory.CODEC.decode(JsonOps.INSTANCE, jsonObject).get().orThrow().getFirst();
            }
            throw new IllegalArgumentException("can not cast %s to CookingBookCategory".formatted(o));
        }
    };


    public static final RecipeComponent<ItemStack> ITEM_STACK = new RecipeComponent<>() {
        @Override
        public Class<?> componentClass() {
            return ItemStack.class;
        }

        @Override
        public JsonElement write(RecipeJS recipeJS, ItemStack itemStack) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("item", Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(itemStack.getItem())).toString());
            if (itemStack.getTag() != null) {
                JsonElement jsonElement = CompoundTag.CODEC.encodeStart(JsonOps.INSTANCE, itemStack.getTag()).get().orThrow();
                jsonObject.add("nbt", jsonElement);
            }
            jsonObject.addProperty("count", itemStack.getCount());
            return jsonObject;
        }

        @Override
        public ItemStack read(RecipeJS recipeJS, Object o) {
            return ItemStackJS.of(o);
        }
    };
    public static final RecipeComponent<ClibanoRecipe.ResidueInfo> RESIDUE_INFO = new RecipeComponent<>() {

        @Override
        public Class<?> componentClass() {
            return ClibanoRecipe.ResidueInfo.class;
        }

        @Override
        public JsonElement write(RecipeJS recipeJS, ClibanoRecipe.ResidueInfo residueInfo) {
            JsonObject residue = new JsonObject();

            if (residueInfo == ClibanoRecipe.ResidueInfo.NONE) {
                return new JsonObject();
            }

            residue.addProperty("name", residueInfo.name());
            residue.addProperty("chance", residueInfo.chance());
            return residue;
        }

        @Override
        public ClibanoRecipe.ResidueInfo read(RecipeJS recipeJS, Object o) {
            if (o instanceof ClibanoRecipe.ResidueInfo residueInfo) {
                return residueInfo;
            } else if (o instanceof JsonObject jsonObject) {
                return new ClibanoRecipe.ResidueInfo(GsonHelper.getAsString(jsonObject, "name"), GsonHelper.getAsDouble(jsonObject, "chance"));
            }
            throw new IllegalArgumentException("can not cast %s to ResidueInfo".formatted(o));
        }
    };

}
