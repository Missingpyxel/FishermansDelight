package missingpixel.fishermansdelight.common.registry;

import com.teammetallurgy.aquaculture.api.AquacultureAPI;
import missingpixel.fishermansdelight.common.integration.OptionalIngredientRegistry;
import missingpixel.fishermansdelight.common.world.item.FishermansDelightBucketItem;
import missingpixel.fishermansdelight.common.world.item.FishermansDelightKnifeItem;
import missingpixel.fishermansdelight.FishermansDelight;
import missingpixel.fishermansdelight.common.world.item.FishermansDelightSpawnEggItem;
import missingpixel.fishermansdelight.common.world.item.food.*;
import missingpixel.fishermansdelight.common.world.item.tab.FishermansDelightTab;
import missingpixel.fishermansdelight.common.world.item.tag.FishermansDelightTags;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.apache.logging.log4j.Level;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, FishermansDelight.MODID);
    public static final CreativeModeTab TAB = new FishermansDelightTab();
    private static CreativeModeTab aquaConditionalTab;

    private static CreativeModeTab crabConditionalTab;
    private static CreativeModeTab lobsterConditionalTab;
    private static CreativeModeTab shellfishConditionalTab;

    private static Tier aquaContitionalNeptuniumTier;

    public static void initItems() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        aquaConditionalTab = null;

        crabConditionalTab = null;
        lobsterConditionalTab = null;
        shellfishConditionalTab = null;

        aquaContitionalNeptuniumTier = Tiers.WOOD;

        if(ModList.get().isLoaded("aquaculture")) {
            aquaConditionalTab = TAB;
            aquaContitionalNeptuniumTier = AquacultureAPI.MATS.NEPTUNIUM;
        }

        if(!OptionalIngredientRegistry.CRAB.isIngredientEmpty()) {
            crabConditionalTab = TAB;
            shellfishConditionalTab = TAB;
        }

        if(!OptionalIngredientRegistry.LOBSTER.isIngredientEmpty()) {
            lobsterConditionalTab = TAB;
            shellfishConditionalTab = TAB;
        }

        ITEMS.register(bus);
    }

    // Fish
    public static final RegistryObject<Item> SHRIMP = ITEMS.register("shrimp", ()-> new FishermansDelightFishItem(FishermansDelightTags.RAW_SHRIMP));

    // Fillets
    public static final RegistryObject<Item> TROUT_STEAK = ITEMS.register("trout_steak", () -> new FishermansDelightRawFilletItem(new Item.Properties().tab(aquaConditionalTab)));
    public static final RegistryObject<Item> COOKED_TROUT_FILLET = ITEMS.register("cooked_trout_steak", () -> new FishermansDelightCookedFilletItem(new Item.Properties().tab(aquaConditionalTab)));

    public static final RegistryObject<Item> TUNA_STEAK = ITEMS.register("tuna_steak", () -> new FishermansDelightRawFilletItem(new Item.Properties().tab(aquaConditionalTab)));
    public static final RegistryObject<Item> COOKED_TUNA_STEAK = ITEMS.register("cooked_tuna_steak", () -> new FishermansDelightCookedFilletItem(new Item.Properties().tab(aquaConditionalTab)));

    // Food
    public static final RegistryObject<Item> NORI = ITEMS.register("nori", () -> new FishermansDelightNoriItem());

    public static final RegistryObject<Item> MAKI = ITEMS.register("maki", () -> new FishermansDelightPlateFood(TAB, FishermansDelightFoodValues.MAKI, true));
    public static final RegistryObject<Item> NIGIRI = ITEMS.register("nigiri", () -> new FishermansDelightPlateFood(TAB, FishermansDelightFoodValues.NIGIRI, true));
    public static final RegistryObject<Item> SURF_N_TURF = ITEMS.register("surf_n_turf", () -> new FishermansDelightPlateFood(shellfishConditionalTab, FishermansDelightFoodValues.NIGIRI, true));

    public static final RegistryObject<Item> NEPTUNIUM_KNIFE = ITEMS.register("neptunium_knife", () ->
            new FishermansDelightKnifeItem(aquaContitionalNeptuniumTier, -2f, 1.0f,
                    new Item.Properties().tab(aquaConditionalTab)));

    public static final RegistryObject<Item> SHRIMP_SPAWN_EGG = ITEMS.register("shrimp_spawn_egg", () ->
            new FishermansDelightSpawnEggItem(() -> EntityTypeRegistry.SHRIMP.get(), 0xFE8F78, 0xBF3D26, new Item.Properties().tab(TAB)));

    public static final RegistryObject<Item> SHRIMP_BUCKET = ITEMS.register("shrimp_bucket", () ->
            new FishermansDelightBucketItem(() -> EntityTypeRegistry.SHRIMP.get()));
}
