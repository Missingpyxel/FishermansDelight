package missingpixel.fishermansdelight.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import missingpixel.fishermansdelight.FishermansDelight;
import missingpixel.fishermansdelight.common.world.entities.ShrimpEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

public class ShrimpModel<T extends Entity> extends EntityModel<ShrimpEntity> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(FishermansDelight.MODID, "shrimp"), "main");
	private final ModelPart body;
	private float oldFreq = 0;
	private float oldPhaseShift = 0;

	public ShrimpModel(ModelPart root) {
		this.body = root.getChild("body");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 11).addBox(-1.0F, -2.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(0, 19).addBox(-1.0F, -2.0F, -5.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 23).addBox(-1.0F, -2.0F, -6.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.0F, -5.0F, -4.0F, 2.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, 0.0F, 0.0F));

		PartDefinition legLeft = body.addOrReplaceChild("legLeft", CubeListBuilder.create().texOffs(10, 13).addBox(0.0F, 0.0F, -3.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 0.0F, 0.0F));

		PartDefinition legRight = body.addOrReplaceChild("legRight", CubeListBuilder.create().texOffs(12, 0).addBox(-1.0F, 0.0F, -3.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 0.0F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition main = tail.addOrReplaceChild("main", CubeListBuilder.create().texOffs(18, 9).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(24, 0).addBox(-1.0F, 0.0F, 2.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 3.0F));

		PartDefinition back = main.addOrReplaceChild("back", CubeListBuilder.create().texOffs(18, 13).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 4.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, buffer, packedLight, packedOverlay);
	}

	@Override
	public void setupAnim(ShrimpEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

		int yoff = 20;
		float baseSpeed = 0.4f;
		float speedUp = 1f;

		if(!entity.isInWater()) {
			this.body.zRot = Mth.PI/2f;
			yoff = 24;
			speedUp = 1.5f;
		}
		else {
			this.body.zRot = 0;
		}

		this.body.y = Mth.sin(ageInTicks * baseSpeed + (3*Mth.PI)/2f * speedUp) * 3f + yoff;
		this.body.getChild("tail").getChild("main").xRot = Mth.sin(ageInTicks * baseSpeed + (1*Mth.PI)/6f  * speedUp) * 0.2f * 1.2f;
		this.body.getChild("tail").getChild("main").getChild("back").xRot = Mth.sin(ageInTicks * baseSpeed + (2*Mth.PI)/6f  * speedUp) * 0.2f * 1.2f;
		this.body.xRot = Mth.sin(ageInTicks * baseSpeed  * speedUp) * 0.25f;

		if(entity.partyShrimp()) {
			this.body.xRot = ageInTicks * 0.4f;
			this.body.y -= 5;
		}
	}
}