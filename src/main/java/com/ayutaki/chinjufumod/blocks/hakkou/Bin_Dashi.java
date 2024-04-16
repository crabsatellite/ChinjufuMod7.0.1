package com.ayutaki.chinjufumod.blocks.hakkou;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.BaseStage4_Face;
import com.ayutaki.chinjufumod.blocks.base.CollisionHelper;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Hakkou_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.SoundType;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Bin_Dashi extends BaseStage4_Face {

	private static final AxisAlignedBB AABB_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.109375, 0.0, 0.671875, 0.328125, 0.34375, 0.890625);
	private static final AxisAlignedBB AABB_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.109375, 0.0, 0.671875, 0.328125, 0.34375, 0.890625);
	private static final AxisAlignedBB AABB_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.109375, 0.0, 0.671875, 0.328125, 0.34375, 0.890625);
	private static final AxisAlignedBB AABB_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.109375, 0.0, 0.671875, 0.328125, 0.34375, 0.890625);
	private static final AxisAlignedBB[] AABB = { AABB_SOUTH, AABB_WEST, AABB_NORTH, AABB_EAST };

	public Bin_Dashi(String name) {
		super(name);
		setSoundType(SoundType.STONE);
		setHardness(0.5F);
		setResistance(1.0F);
		setLightOpacity(0);
	}

	/* Collision */
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		EnumFacing facing = state.getValue(H_FACING);
		return AABB[facing.getHorizontalIndex()];
	}

	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		/** Have no collision. **/
		return NULL_AABB;
	}

	/* A torch can be placed on top. true or false */
	public boolean isTopSolid(IBlockState state) {
		return false;
	}

	/* A torch can be placed on the side. */
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

	/* Rendering */
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	/*Drop Item and Clone Item.*/
	public boolean canSilkHarvest(World worldIn, EntityPlayer playerIn, int x, int y, int z, int metadata) {
		return false;
	}
	
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		
		if (this == Hakkou_Blocks.DASHI_bot) {
			if (i == 1) { stack.add(new ItemStack(Items_Teatime.DASHI_bot_1, 1, 0)); }
			if (i == 2) { stack.add(new ItemStack(Items_Teatime.DASHI_bot_2, 1, 0)); }
			if (i == 3) { stack.add(new ItemStack(Items_Teatime.DASHI_bot_3, 1, 0)); }
			if (i == 4) { stack.add(new ItemStack(Items_Teatime.DASHI_bot_4, 1, 0)); } }
		
		if (this == Dish_Blocks.OSAUCE_bot) {
			if (i == 1) { stack.add(new ItemStack(Items_Teatime.OSAUCE_bot_1, 1, 0)); }
			if (i == 2) { stack.add(new ItemStack(Items_Teatime.OSAUCE_bot_2, 1, 0)); }
			if (i == 3) { stack.add(new ItemStack(Items_Teatime.OSAUCE_bot_3, 1, 0)); }
			if (i == 4) { stack.add(new ItemStack(Items_Teatime.OSAUCE_bot_4, 1, 0)); } }
		
		if (this == Dish_Blocks.MAYO_bot) {
			if (i == 1) { stack.add(new ItemStack(Items_Teatime.MAYO_bot_1, 1, 0)); }
			if (i == 2) { stack.add(new ItemStack(Items_Teatime.MAYO_bot_2, 1, 0)); }
			if (i == 3) { stack.add(new ItemStack(Items_Teatime.MAYO_bot_3, 1, 0)); }
			if (i == 4) { stack.add(new ItemStack(Items_Teatime.MAYO_bot_4, 1, 0)); } }
		
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		if (this == Dish_Blocks.OSAUCE_bot) { return new ItemStack(Items_Teatime.OSAUCE_bot_1, 1, 0); }
		if (this == Dish_Blocks.MAYO_bot) { return new ItemStack(Items_Teatime.MAYO_bot_1, 1, 0); }
		return new ItemStack(Items_Teatime.DASHI_bot_1, 1, 0);
	}
}
