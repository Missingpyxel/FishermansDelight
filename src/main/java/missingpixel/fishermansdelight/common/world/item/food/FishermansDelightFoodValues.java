package missingpixel.fishermansdelight.common.world.item.food;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import vectorwing.farmersdelight.common.registry.ModEffects;

public class FishermansDelightFoodValues {

    public static final int BURST_EFFECT = 200;
    public static final int BRIEF_DURATION = 600;
    public static final int SHORT_DURATION = 1200;
    public static final int MEDIUM_DURATION = 3600;
    public static final int LONG_DURATION = 6000;

    public static final FoodProperties RAW_FILLET = new FoodProperties.Builder().nutrition(1).saturationMod(0.1f).fast().build();
    public static final FoodProperties COOKED_FILLET = new FoodProperties.Builder().nutrition(3).saturationMod(0.5f).fast().build();

    public static final FoodProperties BLAND = new FoodProperties.Builder().nutrition(1).saturationMod(0.1f).fast().build();

    public static final FoodProperties MAKI = (new FoodProperties.Builder())
            .nutrition(10).saturationMod(0.6f)
            .effect(() -> new MobEffectInstance(ModEffects.COMFORT.get(), SHORT_DURATION, 0), 1.0F).build();

    public static final FoodProperties NIGIRI = (new FoodProperties.Builder())
            .nutrition(12).saturationMod(0.7f)
            .effect(() -> new MobEffectInstance(ModEffects.COMFORT.get(), SHORT_DURATION, 0), 1.0F).build();

    public static final FoodProperties SURF_N_TURF = (new FoodProperties.Builder())
            .nutrition(12).saturationMod(0.8f)
            .effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT.get(), MEDIUM_DURATION, 0), 1.0F).build();
}

