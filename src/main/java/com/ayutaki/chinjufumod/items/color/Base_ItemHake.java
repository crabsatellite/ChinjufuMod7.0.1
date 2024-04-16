package com.ayutaki.chinjufumod.items.color;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.ItemGroups_CM;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class Base_ItemHake extends Item {

	public Base_ItemHake(Item.Properties properties) {
		super(properties.durability(128).tab(ItemGroups_CM.WADECO));
	}
	
	@Override
	public boolean isRepairable(ItemStack stack) {
		return false;
	}
	
	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag tipFlag) {
		tooltip.add(new TranslatableComponent("tips.item_hake_color").withStyle(ChatFormatting.GRAY));
	}
}
