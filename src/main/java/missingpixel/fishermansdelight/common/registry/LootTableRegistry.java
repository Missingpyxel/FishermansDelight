package missingpixel.fishermansdelight.common.registry;

import missingpixel.fishermansdelight.FishermansDelight;
import missingpixel.fishermansdelight.common.registry.loot.UncommonFishRarityModifier;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = FishermansDelight.MODID ,bus = Mod.EventBusSubscriber.Bus.MOD)
public class LootTableRegistry {

    @SubscribeEvent

    public static void registerModifierSerializers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {
        event.getRegistry().register(new UncommonFishRarityModifier.Serializer().setRegistryName(
                new ResourceLocation(FishermansDelight.MODID,"uncommon_fish_rarity")
        ));
    }
}
