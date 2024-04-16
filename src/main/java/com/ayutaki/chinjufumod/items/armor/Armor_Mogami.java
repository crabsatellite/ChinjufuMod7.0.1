package com.ayutaki.chinjufumod.items.armor;

import java.util.function.Consumer;

import com.ayutaki.chinjufumod.handler.ArmorLayer_CM;
import com.ayutaki.chinjufumod.items.armor.model.BaseArmor;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

public class Armor_Mogami extends Base_CruiserItem {

	public Armor_Mogami(ArmorMaterial material, ArmorItem.Type slot, Item.Properties properties) {
		super(material, slot, properties);
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void initializeClient(Consumer<net.minecraftforge.client.extensions.common.IClientItemExtensions> consumer) {
		consumer.accept(new IClientItemExtensions() {
			@Override
			public HumanoidModel<?> getHumanoidArmorModel(LivingEntity entityIn, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> defModel) {
				EntityModelSet entityModel = Minecraft.getInstance().getEntityModels();
				ModelPart part = entityModel.bakeLayer(slot == EquipmentSlot.LEGS ? ArmorLayer_CM.GISOU_INNER : ArmorLayer_CM.GISOU_OUTER);
				return new BaseArmor(part); }
		});
	}
}
