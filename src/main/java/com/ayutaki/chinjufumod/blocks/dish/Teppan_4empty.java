package com.ayutaki.chinjufumod.blocks.dish;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Teppan_4empty extends BaseTeppan {

	protected static final int COOK_TIME = 1200;
	/* Property */
	public static final PropertyInteger STAGE_1_4 = PropertyInteger.create("stage", 1, 4);
	/* Collision */
	protected static final double cw = 0.0625;
	private static final AxisAlignedBB AABB_BOX = new AxisAlignedBB(2.5D * cw, 0.0D * cw, 2.5D * cw, 13.5D * cw, 1.0D * cw, 13.5D * cw);
	private static final AxisAlignedBB EMPTY_BOX = new AxisAlignedBB(2.5D * cw, 0.0D * cw, 2.5D * cw, 13.5D * cw, 0.5D * cw, 13.5D * cw);

	public Teppan_4empty(String name) {
		super(name);
		
		setDefaultState(this.blockState.getBaseState()
				.withProperty(H_FACING, EnumFacing.NORTH)
				.withProperty(STAGE_1_4, Integer.valueOf(1))
				.withProperty(DOWN, Boolean.valueOf(false)));
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		
		ItemStack stack = playerIn.getHeldItem(hand);
		Item item = stack.getItem();
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		/** 1=raw, 2=re, 3=sauce, 4=empty **/
		
		if (i < 3) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
		
		if (i == 3) {
			if (item == Items_Teatime.Item_SARA) {
				/** Collect with an Item **/
				CMEvents.Consume1Item_SnowB(worldIn, pos, playerIn, hand);
				
				/** Get EXP directly. **/
				playerIn.addExperience(1);
				worldIn.playSound(null, pos, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 0.5F, 0.75F);
	
				if (stack.isEmpty()) { playerIn.inventory.addItemStackToInventory(this.takeOkonomi()); }
				else if (!playerIn.inventory.addItemStackToInventory(this.takeOkonomi())) {
					playerIn.dropItem(this.takeOkonomi(), false); }
	
				worldIn.setBlockState(pos, Dish_Blocks.OKONOMIYAKI_nama.getDefaultState()
						.withProperty(H_FACING, state.getValue(H_FACING))
						.withProperty(STAGE_1_4, Integer.valueOf(4))
						.withProperty(DOWN, state.getValue(DOWN))); }
			
			if (item != Items_Teatime.Item_SARA) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		}

		/** 'true' to not put anything on top. **/
		return true;
	}
	
	private ItemStack takeOkonomi() {
		if (this == Dish_Blocks.OKONOMIYAKI_nama) { return new ItemStack(Items_Teatime.OKONOMIYAKI, 1, 0); }
		if (this == Dish_Blocks.OKONOMIS_nama) { return new ItemStack(Items_Teatime.OKONOMIYAKI, 1, 1); }
		if (this == Dish_Blocks.OKONOMIC_nama) { return new ItemStack(Items_Teatime.OKONOMIYAKI, 1, 2); }
		return null;
	}
	
	/* IBlockStateからItemStackのmetadataを生成。ドロップ時とテクスチャ・モデル参照時に呼ばれる */
	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;
		i = i | ((EnumFacing)state.getValue(H_FACING)).getHorizontalIndex();
		i = i | ((Integer)state.getValue(STAGE_1_4)).intValue() - 1 << 2;
		return i;
	}

	/* ItemStackのmetadataからIBlockStateを生成。設置時に呼ばれる */
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(H_FACING, EnumFacing.getHorizontal(meta))
				.withProperty(STAGE_1_4, Integer.valueOf(1 + (meta >> 2)));
	}

	@Override
	public int getComparatorInputOverride(IBlockState state, World worldIn, BlockPos pos) {
		return (4 - ((Integer)state.getValue(STAGE_1_4)).intValue()) * 2;
	}

	/*Create BlockStates in this block. */
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { DOWN, H_FACING, STAGE_1_4 });
	}
	
	/* Cooking */
	@Override
	public void observedNeighborChange(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		if (this.isCooking(worldIn, pos)) { worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn)); }
	}
	
	@Override
	public int tickRate(World worldIn) {
		return COOK_TIME;
	}
	
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		super.onBlockAdded(worldIn, pos, state);
		worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		super.updateTick(worldIn, pos, state, rand);
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

		if (i < 3) {
			if (this.isCooking(worldIn, pos)) {
				worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
				worldIn.setBlockState(pos, this.toClick().getDefaultState()
						.withProperty(H_FACING,state.getValue(H_FACING))
						.withProperty(DOWN,state.getValue(DOWN))
						.withProperty(Teppan_4emptyC.STAGE_1_2, Integer.valueOf(i)), 3);
				CMEvents.soundKotePlace(worldIn, pos); }
			
			if (!this.isCooking(worldIn, pos)) { }
		}
		
		else { }
	}
	
	private Block toClick() {
		if (this == Dish_Blocks.OKONOMIYAKI_nama) { return Dish_Blocks.OKONOMIYAKI_click; }
		if (this == Dish_Blocks.OKONOMIS_nama) { return Dish_Blocks.OKONOMIS_click; }
		if (this == Dish_Blocks.OKONOMIC_nama) { return Dish_Blocks.OKONOMIC_click; }
		return null;
	}
	
	/* add Effect */
	@Override
	public void randomDisplayTick(IBlockState state, World worldIn, BlockPos pos, Random rand) {
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		World par1World = worldIn;
		int par2 = x;
		int par3 = y;
		int par4 = z;
		Random par5Random = rand;

		double d0 = (double) ((float) par2 + 0.5F) + (double) (par5Random.nextFloat() - 0.5F) * 0.01D;
		double d1 = ((double) ((float) par3 + 0.5F) + (double) (par5Random.nextFloat() - 0.5F) * 0.01D) + 0.5D;
		double d2 = (double) ((float) par4 + 0.5F) + (double) (par5Random.nextFloat() - 0.5F) * 0.01D;
		double d3 = 0.12D;
		double d4 = 0.17D;
		
		if (i != 4) {
			if (this.isCooking(worldIn, pos)) {
				if (rand.nextDouble() < 0.1D) {
					worldIn.playSound(x, y, z, SoundEvents_CM.JUU, SoundCategory.BLOCKS, 0.2F, 1.0F, false); }
				
				if (i != 1) {
					for (int la = 0; la < 1; ++la) {
						par1World.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, d0 - d4 + 0.25, d1 + d3 -0.5, d2, 0.0D, 0.0D, 0.0D); }
				}
			}
			
			if (!this.isCooking(worldIn, pos)) { }
		}
		
		if (i == 4) { }
	}
	
	/* Collision */
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		int i = state.getValue(STAGE_1_4);
		return (i == 4)? EMPTY_BOX : AABB_BOX;
	}

	/*Drop Item and Clone Item.*/
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
	
		if (this != Dish_Blocks.OKONOMIC_nama) { 
			if (i != 4) { stack.add(this.takeStack()); }
			if (i == 4) { stack.add(new ItemStack(Items.BOWL, 1, 0)); } }

		if (this == Dish_Blocks.OKONOMIC_nama) { 
			if (i != 4) { stack.add(this.takeStack()); }
			if (i == 4) { 
				stack.add(new ItemStack(Items.BOWL, 1, 0));
				stack.add(new ItemStack(Items_Teatime.Item_SARA, 1, 0)); } }

		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return this.takeStack();
	}

	private ItemStack takeStack() {
		if (this == Dish_Blocks.OKONOMIYAKI_nama) { return new ItemStack(Items_Teatime.FRYPAN_NAMA_4, 1, 2); }
		if (this == Dish_Blocks.OKONOMIS_nama) { return new ItemStack(Items_Teatime.FRYPAN_NAMA_4, 1, 3); }
		if (this == Dish_Blocks.OKONOMIC_nama) { return new ItemStack(Items_Teatime.FRYPAN_NAMA_4, 1, 4); }
		return null;
	}
}
