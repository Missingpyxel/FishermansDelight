package missingpixel.fishermansdelight.common.data;

import com.teammetallurgy.aquaculture.init.AquaItems;
import missingpixel.fishermansdelight.common.integration.OptionalIngredient;
import missingpixel.fishermansdelight.common.integration.OptionalIngredientRegistry;
import missingpixel.fishermansdelight.common.registry.ItemRegistry;
import missingpixel.fishermansdelight.common.world.item.tag.FishermansDelightTags;
import missingpixel.fishermansdelight.common.world.item.IAutomatedItem;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.registry.ModItems;

public class FishermansDelightItemTagsProvider extends ItemTagsProvider {

        public FishermansDelightItemTagsProvider(DataGenerator p_126530_, BlockTagsProvider p_126531_, String modId, @Nullable ExistingFileHelper existingFileHelper) {
                super(p_126530_, p_126531_, modId, existingFileHelper);
        }

        private void regsiterTagsForIngredient(OptionalIngredient ingredient) {
                for(Tag.Named<Item> tag : ingredient.getTags().keySet()) {
                        for(ResourceLocation item : ingredient.getItems()) {
                                tag(tag).addOptional(new ResourceLocation(item.getNamespace(), ingredient.getTags().get(tag) + item.getPath()));
                        }
                }
        }

        @Override
        protected void addTags() {

                tag(FishermansDelightTags.RAW_FILLET)
                        .add(ModItems.COD_SLICE.get())
                        .add(ModItems.SALMON_SLICE.get())
                        .add(AquaItems.FISH_FILLET.get());

                tag(FishermansDelightTags.COOKED_FILLET)
                        .add(ModItems.COOKED_COD_SLICE.get())
                        .add(ModItems.COOKED_SALMON_SLICE.get())
                        .add(AquaItems.COOKED_FILLET.get());

                // LOBSTER
                regsiterTagsForIngredient(OptionalIngredientRegistry.CRAB);
                regsiterTagsForIngredient(OptionalIngredientRegistry.LOBSTER);

                for(RegistryObject<Item> item : ItemRegistry.ITEMS.getEntries()) {
                        for (Tag.Named tag : ((IAutomatedItem) item.get()).getItemTags()) {
                                tag(tag).add(item.get());
                        }
                }
        }
}
