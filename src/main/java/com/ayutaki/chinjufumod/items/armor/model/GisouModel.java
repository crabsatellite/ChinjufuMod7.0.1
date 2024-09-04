package com.ayutaki.chinjufumod.items.armor.model;

import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class GisouModel {
	
	public static MeshDefinition createInner() {
		MeshDefinition meshD = new MeshDefinition();
		PartDefinition root = meshD.getRoot();
		float scale = 0.4F;
		
		/** Base **/
		root.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.ZERO);
		root.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);
		root.addOrReplaceChild("body", CubeListBuilder.create()
				.texOffs(16, 16)
				.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, new CubeDeformation(scale)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		root.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.ZERO);
		root.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.ZERO);
		PartDefinition bipedRightLeg = root.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.ZERO);
		PartDefinition bipedLeftLeg = root.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.ZERO);

		
		/** Add **/
		bipedRightLeg.addOrReplaceChild("right_skirt", CubeListBuilder.create()
				.texOffs(0, 32)
				.addBox(-2.09F, -1.0F, -2.0F, 4, 12, 4, new CubeDeformation(scale)),
				PartPose.offset(0.0F, 0.0F, 0.0F)); //RIGHT_SKIRT
		bipedLeftLeg.addOrReplaceChild("left_skirt", CubeListBuilder.create()
				.texOffs(16, 32)
				.addBox(-1.91F, -1.0F, -1.99F, 4, 12, 4, new CubeDeformation(scale)),
				PartPose.offset(0.0F, 0.0F, 0.0F)); //LEFT_SKIRT
		
		bipedRightLeg.addOrReplaceChild("right_wa", CubeListBuilder.create()
				.texOffs(0, 48)
				.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, new CubeDeformation(scale)),
				PartPose.offset(0.0F, 0.0F, 0.0F)); //RIGHT_WA
		bipedLeftLeg.addOrReplaceChild("left_wa", CubeListBuilder.create()
				.texOffs(0, 48)
				.mirror(true)
				.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, new CubeDeformation(scale)),
				PartPose.offset(0.0F, 0.0F, 0.0F)); //LEFT_WA
		
		bipedRightLeg.addOrReplaceChild("right_gyorai", CubeListBuilder.create()
				.texOffs(0, 64)
				.addBox(-2.3F, 0.0F, -2.0F, 4, 12, 4, new CubeDeformation(scale)),
				PartPose.offset(0.0F, 0.0F, 0.0F)); //RIGHT_GYORAI
		bipedLeftLeg.addOrReplaceChild("left_gyorai", CubeListBuilder.create()
				.texOffs(0, 64)
				.mirror(true)
				.addBox(-1.7F, 0.0F, -2.0F, 4, 12, 4, new CubeDeformation(scale)),
				PartPose.offset(0.0F, 0.0F, 0.0F)); //LEFT_GYORAI
	
		return meshD;
	}

	public static MeshDefinition createOuter() {
		MeshDefinition meshD = new MeshDefinition();
		PartDefinition root = meshD.getRoot();
		float scale = 0.55F;
		
		/** Base **/
		root.addOrReplaceChild("head", CubeListBuilder.create()
				.texOffs(0, 0)
				.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, new CubeDeformation(scale)), 
				PartPose.offset(0.0F, 0.0F, 0.0F));
		root.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);
		PartDefinition bipedBody = root.addOrReplaceChild("body", CubeListBuilder.create()
				.texOffs(16, 16)
				.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, new CubeDeformation(scale)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition bipedRightArm = root.addOrReplaceChild("right_arm", CubeListBuilder.create()
				.texOffs(40, 16)
				.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, new CubeDeformation(scale)), 
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition bipedLeftArm = root.addOrReplaceChild("left_arm", CubeListBuilder.create()
				.texOffs(40, 16)
				.mirror(true)
				.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, new CubeDeformation(scale)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition bipedRightLeg = root.addOrReplaceChild("right_leg", CubeListBuilder.create()
				.texOffs(0, 16)
				.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, new CubeDeformation(scale)), 
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition bipedLeftLeg = root.addOrReplaceChild("left_leg", CubeListBuilder.create()
				.texOffs(0, 16)
				.mirror(true)
				.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, new CubeDeformation(scale)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		
		
		/** Add **/
		bipedBody.addOrReplaceChild("entotsu", CubeListBuilder.create()
				.texOffs(0, 50)
				.addBox(-11.0F, -10.0F, 4.01F, 22, 28, 1, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F)); //ENTOTSU
		bipedBody.addOrReplaceChild("koshi_r", CubeListBuilder.create()
				.texOffs(0, 32)
				.addBox(-4.25F, -0.25F, -2.5F, 5, 12, 6, new CubeDeformation(0.5F)),
				PartPose.offset(0.0F, 0.0F, 0.0F)); //KOSHI_R
		bipedBody.addOrReplaceChild("koshi_l", CubeListBuilder.create()
				.texOffs(0, 32)
				.mirror(true)
				.addBox(-0.75F, -0.25F, -2.5F, 5, 12, 6, new CubeDeformation(0.5F)),
				PartPose.offset(0.0F, 0.0F, 0.0F)); //KOSHI_L
		
		bipedBody.addOrReplaceChild("eri", CubeListBuilder.create()
				.texOffs(32, 32)
				.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, new CubeDeformation(scale + 0.1F)),
				PartPose.offset(0.0F, 0.0F, 0.0F)); //ERI
		bipedBody.addOrReplaceChild("ribon", CubeListBuilder.create()
				.texOffs(32, 96)
				.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, new CubeDeformation(scale + 0.05F)),
				PartPose.offset(0.0F, 0.0F, 0.0F)); //RIBON
		

		bipedRightArm.addOrReplaceChild("right_sode", CubeListBuilder.create()
				.texOffs(32, 80)
				.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, new CubeDeformation(scale - 0.2F)), 
				PartPose.offset(0.0F, 0.0F, 0.0F)); //RIGHT_SODE
		bipedLeftArm.addOrReplaceChild("left_sode", CubeListBuilder.create()
				.texOffs(32, 80)
				.mirror(true)
				.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, new CubeDeformation(scale - 0.2F)),
				PartPose.offset(0.0F, 0.0F, 0.0F)); //LEFT_SODE
		

		bipedRightLeg.addOrReplaceChild("right_sox", CubeListBuilder.create()
				.texOffs(0, 80)
				.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, new CubeDeformation(0.15F)), 
				PartPose.offset(0.0F, 0.0F, 0.0F)); //RIGHT_SOX
		bipedLeftLeg.addOrReplaceChild("left_sox", CubeListBuilder.create()
				.texOffs(0, 80)
				.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, new CubeDeformation(0.15F)),
				PartPose.offset(0.0F, 0.0F, 0.0F)); //LEFT_SOX

		return meshD;
	}
}
