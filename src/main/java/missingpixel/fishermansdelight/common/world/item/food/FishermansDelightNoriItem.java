package missingpixel.fishermansdelight.common.world.item.food;

import missingpixel.fishermansdelight.common.registry.ItemRegistry;
import missingpixel.fishermansdelight.common.world.item.tag.FishermansDelightTags;
import missingpixel.fishermansdelight.common.world.item.IAutomatedItem;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;

public class FishermansDelightNoriItem extends Item implements IAutomatedItem {
    public FishermansDelightNoriItem () {
        super(new Properties().tab(ItemRegistry.TAB).food(FishermansDelightFoodValues.BLAND));
    }

    @Override
    public Tag.Named[] getItemTags() {
        return new Tag.Named[]{};
    }

    @Override
    public String getModelPath() {
        return "item/generated";
    }
}
