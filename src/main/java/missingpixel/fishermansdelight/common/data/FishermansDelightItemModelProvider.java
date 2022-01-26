package missingpixel.fishermansdelight.common.data;

import missingpixel.fishermansdelight.common.registry.ItemRegistry;
import missingpixel.fishermansdelight.common.world.item.IAutomatedItem;
import missingpixel.fishermansdelight.FishermansDelight;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class FishermansDelightItemModelProvider extends ItemModelProvider {
    public FishermansDelightItemModelProvider(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper) {
        super(generator, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for(RegistryObject<Item> item : ItemRegistry.ITEMS.getEntries()) {
            if(((IAutomatedItem) item.get()).getModelPath() != null) {
                String itemName = item.get().getRegistryName().getPath();

                withExistingParent(itemName, ((IAutomatedItem) item.get()).getModelPath()).texture("layer0", new ResourceLocation(FishermansDelight.MODID, "item/" + itemName));
            }
        }

        // Spawn Eggs
        withExistingParent("shrimp_spawn_egg","item/template_spawn_egg");
    }
}
