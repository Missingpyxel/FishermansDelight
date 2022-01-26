package missingpixel.fishermansdelight.common.world.item.food;

import missingpixel.fishermansdelight.common.registry.ItemRegistry;
import missingpixel.fishermansdelight.common.world.item.IAutomatedItem;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;

public class FishermansDelightFishItem extends Item implements IAutomatedItem {

    private Tag.Named[] extraTags;

    public FishermansDelightFishItem (Tag.Named... tags) {
        super(new Properties().tab(ItemRegistry.TAB).food(FishermansDelightFoodValues.BLAND));
        this.extraTags = tags;
    }

    @Override
    public Tag.Named[] getItemTags() {
        return extraTags;
    }

    @Override
    public String getModelPath() {
        return "item/generated";
    }
}