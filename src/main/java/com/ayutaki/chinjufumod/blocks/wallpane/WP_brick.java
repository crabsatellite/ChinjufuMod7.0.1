package com.ayutaki.chinjufumod.blocks.wallpane;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class WP_brick extends BaseSimpleWP {

	public WP_brick(String name) {
		super(name, Material.WOOD);
		setSoundType(SoundType.STONE);
		setHardness(1.0F);
		setResistance(10.0F);
		setLightOpacity(1);
	}
}
