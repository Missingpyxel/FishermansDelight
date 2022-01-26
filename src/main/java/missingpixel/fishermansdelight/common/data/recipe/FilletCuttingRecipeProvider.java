package missingpixel.fishermansdelight.common.data.recipe;

import com.teammetallurgy.aquaculture.api.AquacultureAPI;
import com.teammetallurgy.aquaculture.init.AquaItems;
import com.teammetallurgy.aquaculture.loot.FishWeightHandler;
import missingpixel.fishermansdelight.common.data.DataGeneratorHelper;
import missingpixel.fishermansdelight.common.data.ingredient.ExclusionIngredient;
import missingpixel.fishermansdelight.common.registry.ItemRegistry;
import missingpixel.fishermansdelight.FishermansDelight;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoader;
import net.minecraftforge.registries.RegistryObject;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.tag.ForgeTags;
import vectorwing.farmersdelight.data.builder.CuttingBoardRecipeBuilder;

import java.util.Collection;
import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class FilletCuttingRecipeProvider extends RecipeProvider {

    private static final Logger LOGGER = LogManager.getLogger();

    public FilletCuttingRecipeProvider(DataGenerator p_125973_) {
        super(p_125973_);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        // I apologize gravely for this but it must be done, fuck it, force fish data registry
        FishWeightHandler.registerFishData();

        HashMap<Item,Item> filletMap = new HashMap<>();
        // Fill the preference array
        filletMap.put(AquaItems.PINK_SALMON.get(), ModItems.SALMON_SLICE.get());
        filletMap.put(AquaItems.ATLANTIC_COD.get(), ModItems.COD_SLICE.get());
        filletMap.put(AquaItems.RAINBOW_TROUT.get(),ItemRegistry.TROUT_STEAK.get());
        filletMap.put(AquaItems.BROWN_TROUT.get(),ItemRegistry.TROUT_STEAK.get());
        filletMap.put(AquaItems.TUNA.get(),ItemRegistry.TUNA_STEAK.get());

        // Vanilla Fish Extra Fillets

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.COD), Ingredient.of(ItemRegistry.NEPTUNIUM_KNIFE.get()), ModItems.COD_SLICE.get() ,3)
                .addResult(Items.BONE_MEAL)
                .build(consumer, new ResourceLocation(FishermansDelight.MODID, "cutting/" + "cod_neptunium"));

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.SALMON), Ingredient.of(ItemRegistry.NEPTUNIUM_KNIFE.get()), ModItems.SALMON_SLICE.get(), 3)
                .addResult(Items.BONE_MEAL)
                .build(consumer, new ResourceLocation(FishermansDelight.MODID, "cutting/" + "salmon_neptunium"));

        // Aquaculture Fish
        for(RegistryObject<Item> object : AquaItems.ITEM_DEFERRED.getEntries()) {

            if(AquacultureAPI.FISH_DATA.hasFilletAmount(object.get())) {

                Item fish = object.get();
                Item filletIngredient = (filletMap.containsKey(fish)) ? filletMap.get(fish) : AquaItems.FISH_FILLET.get();
                int fishFilletAmount = (int)(2 + AquacultureAPI.FISH_DATA.getFilletAmount(fish) * 0.25f);

                // Aquaculture Fish Cutting Board
                DataGeneratorHelper.FDModConditionalRecipe("cutting/" + fish.getRegistryName().getPath(),
                        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(fish), ExclusionIngredient.exclusion(Ingredient.of(ForgeTags.TOOLS_KNIVES),Ingredient.of(ItemRegistry.NEPTUNIUM_KNIFE.get())), filletIngredient, fishFilletAmount)
                        .addResult(Items.BONE_MEAL)::build,
                        "aquaculture",
                        consumer);

                DataGeneratorHelper.FDModConditionalRecipe("cutting/" + fish.getRegistryName().getPath() + "_neptunium",
                        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(fish), Ingredient.of(ItemRegistry.NEPTUNIUM_KNIFE.get()), filletIngredient,fishFilletAmount + 1)
                        .addResult(Items.BONE_MEAL)::build,
                        "aquaculture",
                        consumer);
            }
        }
    }
}
