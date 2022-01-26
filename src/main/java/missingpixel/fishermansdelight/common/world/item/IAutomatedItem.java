package missingpixel.fishermansdelight.common.world.item;

import net.minecraft.tags.Tag;

public interface IAutomatedItem {

    default Tag.Named[] getItemTags() {
        return new Tag.Named[0];
    }

    default String getModelPath() {
        return null;
    }
}
