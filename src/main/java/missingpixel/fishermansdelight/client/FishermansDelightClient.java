package missingpixel.fishermansdelight.client;

import missingpixel.fishermansdelight.FishermansDelight;
import missingpixel.fishermansdelight.client.model.ShrimpModel;
import missingpixel.fishermansdelight.client.renderer.ShrimpRenderer;
import missingpixel.fishermansdelight.common.registry.EntityTypeRegistry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid= FishermansDelight.MODID,value= Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class FishermansDelightClient {
    public static void initClient(FMLClientSetupEvent event) {
    }

    @SubscribeEvent
    public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ShrimpModel.LAYER_LOCATION, ShrimpModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void onRegisterRenderer(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityTypeRegistry.SHRIMP.get(), ShrimpRenderer::new);
    }
}
