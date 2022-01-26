package missingpixel.fishermansdelight.common.world.item.food;

import missingpixel.fishermansdelight.common.registry.ItemRegistry;
import missingpixel.fishermansdelight.common.world.item.IAutomatedItem;
import missingpixel.fishermansdelight.common.world.item.tag.FishermansDelightTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import vectorwing.farmersdelight.common.item.ConsumableItem;

public class FishermansDelightPlateFood extends ConsumableItem implements IAutomatedItem {
    public FishermansDelightPlateFood(CreativeModeTab tab, FoodProperties properties, boolean hasFoodEffectTooltip) {
        super(new Item.Properties().food(properties).craftRemainder(Items.BOWL).stacksTo(16).tab(tab), hasFoodEffectTooltip);
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
