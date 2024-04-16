package com.ayutaki.chinjufumod.blocks.chair;

import java.util.ArrayList;
import java.util.List;

import com.ayutaki.chinjufumod.entity.helper.SittableUtil;
import com.ayutaki.chinjufumod.registry.Items_Wadeco;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BaseZabuton extends Block {

	/* Property */
	public static final PropertyInteger STAGE_0_15 = PropertyInteger.create("stage", 0, 15);

	public BaseZabuton() {
		super(Material.WOOD);
		setSoundType(SoundType.CLOTH);
		setHardness(0.8F);
		setResistance(1.0F);
		setLightOpacity(0);

		setDefaultState(this.blockState.getBaseState().withProperty(STAGE_0_15, Integer.valueOf(0)));
	}

	@Override
	public boolean hasComparatorInputOverride(IBlockState state) {
		return true;
	}

	@Override
	public int getComparatorInputOverride(IBlockState state, World worldIn, BlockPos pos) {
		return SittableUtil.isSomeoneSitting(worldIn, pos.getX(), pos.getY(), pos.getZ()) ? 1 : 0;
	}

	/* Data value */
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(STAGE_0_15, Integer.valueOf(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((Integer)state.getValue(STAGE_0_15)).intValue();
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { STAGE_0_15 });
	}

	/* A torch can be placed on top. true or false */
	@Override
	public boolean isTopSolid(IBlockState state) {
		return false;
	}

	/* A torch can be placed on the side. */
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

	/* Rendering */
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	/*Drop Item and Clone Item.*/
	public boolean canSilkHarvest(World worldIn, EntityPlayer playerIn, int x, int y, int z, int metadata) {
		return false;
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		stack.add(this.takeStack(state));
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return this.takeStack(state);
	}
	
	private ItemStack takeStack(IBlockState state) {
		int i = ((Integer)state.getValue(STAGE_0_15)).intValue();

		if (i == 0) { return new ItemStack(Items_Wadeco.ZABUTON_item, 1, 0); }
		if (i == 1) { return new ItemStack(Items_Wadeco.ZABUTON_item, 1, 1); }
		if (i == 2) { return new ItemStack(Items_Wadeco.ZABUTON_item, 1, 2); }
		if (i == 3) { return new ItemStack(Items_Wadeco.ZABUTON_item, 1, 3); }
		if (i == 4) { return new ItemStack(Items_Wadeco.ZABUTON_item, 1, 4); }
		if (i == 5) { return new ItemStack(Items_Wadeco.ZABUTON_item, 1, 5); }
		if (i == 6) { return new ItemStack(Items_Wadeco.ZABUTON_item, 1, 6); }
		if (i == 7) { return new ItemStack(Items_Wadeco.ZABUTON_item, 1, 7); }
		if (i == 8) { return new ItemStack(Items_Wadeco.ZABUTON_item, 1, 8); }
		if (i == 9) { return new ItemStack(Items_Wadeco.ZABUTON_item, 1, 9); }
		if (i == 10) { return new ItemStack(Items_Wadeco.ZABUTON_item, 1, 10); }
		if (i == 11) { return new ItemStack(Items_Wadeco.ZABUTON_item, 1, 11); }
		if (i == 12) { return new ItemStack(Items_Wadeco.ZABUTON_item, 1, 12); }
		if (i == 13) { return new ItemStack(Items_Wadeco.ZABUTON_item, 1, 13); }
		if (i == 14) { return new ItemStack(Items_Wadeco.ZABUTON_item, 1, 14); }
		if (i == 15) { return new ItemStack(Items_Wadeco.ZABUTON_item, 1, 15); }
		return null;
	}
}
