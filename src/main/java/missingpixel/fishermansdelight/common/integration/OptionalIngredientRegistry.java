package missingpixel.fishermansdelight.common.integration;

import missingpixel.fishermansdelight.common.world.item.tag.FishermansDelightTags;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;

public class OptionalIngredientRegistry {

    public static OptionalIngredient LOBSTER = new OptionalIngredient(
            new ResourceLocation("alexsmobs", "lobster_tail")
    );

    public static OptionalIngredient CRAB = new OptionalIngredient(
            new ResourceLocation("quark", "crab_leg")
    );

    static {
        LOBSTER.addTag(FishermansDelightTags.RAW_LOBSTER);
        LOBSTER.addTag(FishermansDelightTags.COOKED_LOBSTER, "cooked_");
        LOBSTER.addTag(FishermansDelightTags.RAW_SHELLFISH);
        LOBSTER.addTag(FishermansDelightTags.COOKED_SHELLFISH, "cooked_");

        CRAB.addTag(FishermansDelightTags.RAW_CRAB);
        CRAB.addTag(FishermansDelightTags.COOKED_CRAB, "cooked_");
        CRAB.addTag(FishermansDelightTags.RAW_SHELLFISH);
        CRAB.addTag(FishermansDelightTags.COOKED_SHELLFISH, "cooked_");
    }
}
