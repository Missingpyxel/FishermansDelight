package missingpixel.fishermansdelight.common.world.item;

import missingpixel.fishermansdelight.common.registry.ItemRegistry;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.Tag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import vectorwing.farmersdelight.common.tag.ForgeTags;
import vectorwing.farmersdelight.common.tag.ModTags;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class FishermansDelightBucketItem extends MobBucketItem implements IAutomatedItem {

    private final Supplier<EntityType<?>> fishType;

    public FishermansDelightBucketItem(Supplier<EntityType<?>> entityType) {
        super(entityType, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH, new Item.Properties().tab(ItemRegistry.TAB));

        fishType = entityType;
    }

    @Override
    public EntityType<?> getFishType() {
        return fishType.get();
    }

    @Override
    public Tag.Named[] getItemTags() {
        return new Tag.Named[]{};
    }

    @Override
    public String getModelPath() {
        return "item/handheld";
    }
}
