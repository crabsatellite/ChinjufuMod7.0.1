package com.ayutaki.chinjufumod.items.fuel;

import com.ayutaki.chinjufumod.ItemGroups_CM;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.level.block.Block;

public class Seasonal_noSlab extends ItemNameBlockItem {

	public Seasonal_noSlab(Block block, Item.Properties properties) {
		super(block, properties.tab(ItemGroups_CM.SEASONAL));
	}
}
