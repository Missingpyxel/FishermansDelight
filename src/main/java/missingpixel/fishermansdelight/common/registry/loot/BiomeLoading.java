package missingpixel.fishermansdelight.common.registry.loot;

import missingpixel.fishermansdelight.FishermansDelight;
import missingpixel.fishermansdelight.common.registry.EntityTypeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.Level;

@Mod.EventBusSubscriber(modid= FishermansDelight.MODID)
public class BiomeLoading {

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onBiomeLoading(BiomeLoadingEvent event) {

        ResourceKey<Biome> resourceKey = ResourceKey.create(Registry.BIOME_REGISTRY,event.getName());

        if(BiomeDictionary.hasType(resourceKey,BiomeDictionary.Type.OCEAN)) {
            if(BiomeDictionary.hasType(resourceKey,BiomeDictionary.Type.COLD)) {
                event.getSpawns().getSpawner(MobCategory.WATER_AMBIENT).add(new MobSpawnSettings.SpawnerData(EntityTypeRegistry.SHRIMP.get(),2,1,1));
            }
            else if(BiomeDictionary.hasType(resourceKey,BiomeDictionary.Type.HOT)) {
                event.getSpawns().getSpawner(MobCategory.WATER_AMBIENT).add(new MobSpawnSettings.SpawnerData(EntityTypeRegistry.SHRIMP.get(),16,1,3));
            }
            else {
                event.getSpawns().getSpawner(MobCategory.WATER_AMBIENT).add(new MobSpawnSettings.SpawnerData(EntityTypeRegistry.SHRIMP.get(),8,1,2));
            }
        }
    }
}
