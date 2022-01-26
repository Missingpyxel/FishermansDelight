package missingpixel.fishermansdelight.common;

import missingpixel.fishermansdelight.FishermansDelight;
import missingpixel.fishermansdelight.common.registry.EntityTypeRegistry;
import missingpixel.fishermansdelight.common.world.entities.ShrimpEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.Level;

@Mod.EventBusSubscriber(modid= FishermansDelight.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class FishermansDelightCommon {

    @SubscribeEvent
    public static void onAttributeCreate(EntityAttributeCreationEvent event) {
        event.put(EntityTypeRegistry.SHRIMP.get(), ShrimpEntity.prepareAttributes().build());
    }
}
