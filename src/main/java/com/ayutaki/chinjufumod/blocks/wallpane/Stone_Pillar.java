package com.ayutaki.chinjufumod.blocks.wallpane;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.ChinjufuMod;

import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;

public class Stone_Pillar extends BlockRotatedPillar {

	public Stone_Pillar(String name) {
		super(Material.WOOD);
		setRegistryName(new ResourceLocation(ChinjufuMod.MOD_ID, name));
		setUnlocalizedName(name);

		setSoundType(SoundType.STONE);
		setHardness(2.0F);
		setResistance(10.0F);
	}

	/* Harvest by Pickaxe. */
	@Nullable
	@Override
	public String getHarvestTool(IBlockState state) {
		return "pickaxe";
	}

	@Override
	public int getHarvestLevel(IBlockState state) {
		return 0;
	}

}
