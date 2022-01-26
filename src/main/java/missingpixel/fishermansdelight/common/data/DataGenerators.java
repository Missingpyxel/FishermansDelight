package missingpixel.fishermansdelight.common.data;

import missingpixel.fishermansdelight.FishermansDelight;
import missingpixel.fishermansdelight.common.data.recipe.CookingRecipeProivder;
import missingpixel.fishermansdelight.common.data.recipe.FilletCuttingRecipeProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.common.crafting.conditions.ItemExistsCondition;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = FishermansDelight.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper file = event.getExistingFileHelper();

        if (event.includeServer()) {

            BlockTagsProvider blockTagsProvider = new FishermansDelightBlockTagsProvider(gen, FishermansDelight.MODID, file);

            gen.addProvider(blockTagsProvider);

            gen.addProvider(new FishermansDelightItemTagsProvider(gen,blockTagsProvider,FishermansDelight.MODID,file));
            gen.addProvider(new FishermansDelightItemModelProvider(gen,FishermansDelight.MODID,file));

            gen.addProvider(new FilletCuttingRecipeProvider(gen));
            gen.addProvider(new CookingRecipeProivder(gen));

        }
    }
}
