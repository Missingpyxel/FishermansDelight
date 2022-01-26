package missingpixel.fishermansdelight;

import com.teammetallurgy.aquaculture.api.AquacultureAPI;
import com.teammetallurgy.aquaculture.integration.jei.JEIIntegration;
import mezz.jei.JustEnoughItems;
import missingpixel.fishermansdelight.client.FishermansDelightClient;
import missingpixel.fishermansdelight.common.data.ingredient.ExclusionIngredient;
import missingpixel.fishermansdelight.common.registry.EntityTypeRegistry;
import missingpixel.fishermansdelight.common.registry.ItemRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IIngredientSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(FishermansDelight.MODID)
public class FishermansDelight
{
    public static final String MODID = "fishermansdelight";
    public static final Logger LOGGER = LogManager.getLogger();

    public FishermansDelight() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ItemRegistry.initItems();
        EntityTypeRegistry.initEntities();

        bus.addGenericListener(RecipeSerializer.class, FishermansDelight::registerRecipeSerializers);

        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> bus.addListener(FishermansDelightClient::initClient));

        MinecraftForge.EVENT_BUS.register(this);
    }

    private static void registerRecipeSerializers(final RegistryEvent.Register<RecipeSerializer<?>> event) {
        CraftingHelper.register(ExclusionIngredient.ID, ExclusionIngredient.SERIALIZER);
        LOGGER.log(Level.INFO, "LAVATORY");
    }
}
