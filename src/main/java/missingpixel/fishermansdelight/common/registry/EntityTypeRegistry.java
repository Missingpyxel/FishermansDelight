package missingpixel.fishermansdelight.common.registry;

import missingpixel.fishermansdelight.FishermansDelight;
import missingpixel.fishermansdelight.common.world.entities.ShrimpEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityTypeRegistry {

    public static DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, FishermansDelight.MODID);

    public static void initEntities() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ENTITY_TYPES.register(bus);
    }

    public static final RegistryObject<EntityType<ShrimpEntity>> SHRIMP = ENTITY_TYPES.register("shrimp",
            () -> EntityType.Builder.of(ShrimpEntity::new, MobCategory.WATER_AMBIENT).sized(0.3f,0.3f)
                    .build(new ResourceLocation(FishermansDelight.MODID,"shrimp").toString()));
}

