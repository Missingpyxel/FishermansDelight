package missingpixel.fishermansdelight.common.data.recipe;

import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
import missingpixel.fishermansdelight.FishermansDelight;
import missingpixel.fishermansdelight.common.data.DataGeneratorHelper;
import missingpixel.fishermansdelight.common.registry.ItemRegistry;
import missingpixel.fishermansdelight.common.world.item.food.FishermansDelightCookedFilletItem;
import missingpixel.fishermansdelight.common.world.item.tag.FishermansDelightTags;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.ItemExistsCondition;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.tag.ForgeTags;
import vectorwing.farmersdelight.data.builder.CookingPotRecipeBuilder;

import java.util.ArrayList;
import java.util.function.Consumer;

public class CookingRecipeProivder extends RecipeProvider {
    public CookingRecipeProivder(DataGenerator p_125973_) {
        super(p_125973_);
    }

    public static final int FAST_COOKING = 100;
    public static final int NORMAL_COOKING = 200;
    public static final int SLOW_COOKING = 400;

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        DataGeneratorHelper.FDCookingRecipe("cooked_tuna_steak", ItemRegistry.TUNA_STEAK.get(), ItemRegistry.COOKED_TUNA_STEAK.get(), 0.35F, consumer);
        DataGeneratorHelper.FDCookingRecipe("cooked_trout_steak", ItemRegistry.TROUT_STEAK.get(), ItemRegistry.COOKED_TROUT_FILLET.get(), 0.35F, consumer);

        DataGeneratorHelper.FDCookingRecipe("nori", Items.SEAGRASS, ItemRegistry.NORI.get(), 0.15f, consumer);

        // Definite Recipes
        shapelessRecipe("maki", ItemRegistry.MAKI.get(), consumer, new ItemLike[] {ModItems.COOKED_RICE.get(), ItemRegistry.NORI.get(), Items.BOWL}, new Ingredient[] {Ingredient.of(FishermansDelightTags.RAW_FILLET)});
        shapelessRecipe("nigiri", ItemRegistry.NIGIRI.get(), consumer, new ItemLike[] {ModItems.COOKED_RICE.get(), Items.BOWL}, new Ingredient[]{Ingredient.of(FishermansDelightTags.RAW_FILLET),Ingredient.of(FishermansDelightTags.RAW_SHRIMP)});

        // Conditional Recipes
        DataGeneratorHelper.FDTagHasItemRecipe("surfnturf",
                ShapelessRecipeBuilder.shapeless(ItemRegistry.SURF_N_TURF.get(),1)
                        .requires(ForgeTags.COOKED_BEEF)
                        .requires(FishermansDelightTags.COOKED_SHELLFISH)
                        .requires(ForgeTags.CROPS_ONION)
                        .requires(Items.BOWL)
                        .unlockedBy("for_surfnturf", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BEEF))::save,
                new ResourceLocation("fishermansdelight","raw_lobster"),
                consumer);
    }

    private static void shapelessRecipe(String name, ItemLike result, Consumer<FinishedRecipe> consumer, ItemLike[] items, Ingredient[] ingredients) {
        ShapelessRecipeBuilder builder = ShapelessRecipeBuilder.shapeless(result);

        for(ItemLike i : items) {
            builder.requires(i);
        }

        for(Ingredient i : ingredients) {
            builder.requires(i);
        }

        builder.unlockedBy("for_" + name, InventoryChangeTrigger.TriggerInstance.hasItems(items)).save(consumer);
    }
}
