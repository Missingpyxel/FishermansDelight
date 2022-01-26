package missingpixel.fishermansdelight.common.world.item.tag;

import missingpixel.fishermansdelight.FishermansDelight;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;

public class FishermansDelightTags {
    public static final Tag.Named<Item> RAW_FILLET = fdItemTag("raw_fillet");
    public static final Tag.Named<Item> COOKED_FILLET = fdItemTag("cooked_fillet");

    public static final Tag.Named<Item> RAW_LOBSTER = fdOptionalItemTag("raw_lobster");
    public static final Tag.Named<Item> COOKED_LOBSTER = fdOptionalItemTag("cooked_lobster");

    public static final Tag.Named<Item> RAW_CRAB = fdOptionalItemTag("raw_crab");
    public static final Tag.Named<Item> COOKED_CRAB = fdOptionalItemTag("cooked_crab");

    public static final Tag.Named<Item> RAW_SHELLFISH = fdOptionalItemTag("raw_shellfish");
    public static final Tag.Named<Item> COOKED_SHELLFISH = fdOptionalItemTag("cooked_shellfish");

    public static final Tag.Named<Item> RAW_SHRIMP = forgeItemTag("raw_shrimp");
    //public static final Tag.Named<Item> COOKED_SHRIMP = forgeItemTag("cooked_shrimp");

    public static final Tag.Named<Item> fdItemTag(String name) {
        return ItemTags.bind(FishermansDelight.MODID + ":" + name);
    }

    public static final Tag.Named<Item> fdOptionalItemTag(String name) {
        return ItemTags.createOptional(new ResourceLocation("fishermansdelight",name));
    }

    public static final Tag.Named<Item> forgeItemTag(String name) {
        return ItemTags.bind("forge:" + name);
    }
}
