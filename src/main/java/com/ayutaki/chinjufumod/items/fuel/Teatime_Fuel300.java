package com.ayutaki.chinjufumod.items.fuel;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.ItemGroups_CM;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;

public class Teatime_Fuel300 extends ItemNameBlockItem {

	public Teatime_Fuel300(Block block, Item.Properties properties) {
		super(block, properties.tab(ItemGroups_CM.TEATIME));
	}

	/* BurnTime in a Furnace */
	public int getBurnTime(ItemStack stack, @Nullable RecipeType<?> recipeType) {
		return 300;
	}
}
