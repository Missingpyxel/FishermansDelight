package missingpixel.fishermansdelight.client.renderer;

import missingpixel.fishermansdelight.FishermansDelight;
import missingpixel.fishermansdelight.client.model.ShrimpModel;
import missingpixel.fishermansdelight.common.world.entities.ShrimpEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class ShrimpRenderer extends MobRenderer<ShrimpEntity, ShrimpModel<ShrimpEntity>> {
    public ShrimpRenderer(EntityRendererProvider.Context context) {
        super(context, new ShrimpModel<>(context.bakeLayer(ShrimpModel.LAYER_LOCATION)), 0.3f);
    }

    @Override
    public ResourceLocation getTextureLocation(ShrimpEntity p_114482_) {
        return new ResourceLocation(FishermansDelight.MODID,"textures/entity/shrimp.png");
    }
}
