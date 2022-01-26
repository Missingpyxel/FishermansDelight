package missingpixel.fishermansdelight.common.data;

import missingpixel.fishermansdelight.FishermansDelight;
import missingpixel.fishermansdelight.common.data.ingredient.ExclusionIngredient;
import missingpixel.fishermansdelight.common.registry.ItemRegistry;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import net.minecraftforge.common.crafting.conditions.NotCondition;
import net.minecraftforge.common.crafting.conditions.TagEmptyCondition;
import vectorwing.farmersdelight.common.tag.ForgeTags;
import vectorwing.farmersdelight.data.builder.CuttingBoardRecipeBuilder;

import java.util.concurrent.Callable;
import java.util.function.Consumer;

public class DataGeneratorHelper {
    public static void FDModConditionalRecipe(String name,
                                              Consumer<Consumer<FinishedRecipe>> recipe,
                                              String modid,
                                              Consumer<FinishedRecipe> consumer) {
        ConditionalRecipe.builder()
                .addCondition(
                        new ModLoadedCondition(modid)
                )
                .addRecipe(
                        recipe
                )
                .build(consumer, new ResourceLocation(FishermansDelight.MODID, name));
    }

    public static void FDTagHasItemRecipe(String name,
                                          Consumer<Consumer<FinishedRecipe>> recipe,
                                          ResourceLocation tag,
                                          Consumer<FinishedRecipe> consumer) {
        ConditionalRecipe.builder()
                .addCondition(
                        new NotCondition(new TagEmptyCondition(tag))
                )
                .addRecipe(
                        recipe
                )
                .build(consumer, new ResourceLocation(FishermansDelight.MODID, name));
    }

    public static void FDCookingRecipe(String name, ItemLike ingredient, ItemLike result, float experience, Consumer<FinishedRecipe> consumer) {
        String namePrefix = new ResourceLocation(FishermansDelight.MODID, name).toString();
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient),
                        result, experience, 200)
                .unlockedBy("for_" + name , InventoryChangeTrigger.TriggerInstance.hasItems(ingredient))
                .save(consumer);

        SimpleCookingRecipeBuilder.cooking(Ingredient.of(ingredient),
                        result, experience, 600, RecipeSerializer.CAMPFIRE_COOKING_RECIPE)
                .unlockedBy("for_" + name , InventoryChangeTrigger.TriggerInstance.hasItems(ingredient))
                .save(consumer, namePrefix + "_from_campfire_cooking");

        SimpleCookingRecipeBuilder.cooking(Ingredient.of(ingredient),
                        result, experience, 100, RecipeSerializer.SMOKING_RECIPE)
                .unlockedBy("for_" + name , InventoryChangeTrigger.TriggerInstance.hasItems(ingredient))
                .save(consumer, namePrefix + "_from_smoking");
    }
}
