package missingpixel.fishermansdelight.common.world.item.food;

import missingpixel.fishermansdelight.common.registry.ItemRegistry;
import missingpixel.fishermansdelight.common.world.item.tag.FishermansDelightTags;
import missingpixel.fishermansdelight.common.world.item.IAutomatedItem;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.ModList;
import vectorwing.farmersdelight.common.tag.ForgeTags;

public class FishermansDelightCookedFilletItem extends Item implements IAutomatedItem {
    public FishermansDelightCookedFilletItem(Item.Properties props) {
        super(props.food(FishermansDelightFoodValues.COOKED_FILLET));
    }

    @Override
    public Tag.Named[] getItemTags() {
        if(ModList.get().isLoaded("aquaculture")) {
            return new Tag.Named[]{FishermansDelightTags.COOKED_FILLET, ForgeTags.COOKED_FISHES};
        }
        return new Tag.Named[]{};
    }

    @Override
    public String getModelPath() {
        return "item/generated";
    }
}
