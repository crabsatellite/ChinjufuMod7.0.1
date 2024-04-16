package com.ayutaki.chinjufumod.blocks.amado;

import java.util.Random;

import com.ayutaki.chinjufumod.blocks.base.BaseStage2_Face;
import com.ayutaki.chinjufumod.blocks.base.BaseStage3_Face;
import com.ayutaki.chinjufumod.blocks.base.BaseStage4_Face;
import com.ayutaki.chinjufumod.blocks.base.CollisionHelper;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Slidedoor_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Tobukuro_TopL extends BaseStage3_Face {

	/* Collision */
	private static final AxisAlignedBB AABB_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.0, 0.0, -0.03125, 0.3125, 1.03125, 1.03125);
	private static final AxisAlignedBB AABB_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.0, 0.0, -0.03125, 0.3125, 1.03125, 1.03125);
	private static final AxisAlignedBB AABB_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.0, 0.0, -0.03125, 0.3125, 1.03125, 1.03125);
	private static final AxisAlignedBB AABB_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.0, 0.0, -0.03125, 0.3125, 1.03125, 1.03125);
	private static final AxisAlignedBB[] AABB = { AABB_SOUTH, AABB_WEST, AABB_NORTH, AABB_EAST };

	/** 1=4枚, 2=3枚, 3=2枚 **/
	public Tobukuro_TopL(String name) {
		super(name);
		setHardness(2.0F);
		setResistance(5.0F);
		setSoundType(SoundType.WOOD);
		setLightOpacity(1);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

		/** 1=4枚, 2=3枚, 3=2枚 **/
		int i = ((Integer)state.getValue(STAGE_1_3)).intValue();
		EnumFacing direction = state.getValue(H_FACING);

		IBlockState northState = worldIn.getBlockState(pos.north());
		IBlockState southState = worldIn.getBlockState(pos.south());
		IBlockState eastState = worldIn.getBlockState(pos.east());
		IBlockState westState = worldIn.getBlockState(pos.west());

		double x = (double) pos.getX();
		double y = (double) pos.getY();
		double z = (double) pos.getZ();

		CMEvents.soundAmado(worldIn, pos);

		IBlockState tobuBotL2_FACE = this.takeLeftBot2().getDefaultState().withProperty(H_FACING, state.getValue(H_FACING));
		IBlockState tobuBotL_FACE = this.takeLeftBot().getDefaultState().withProperty(H_FACING, state.getValue(H_FACING));
		IBlockState tobuTopL2_FACE = this.takeLeftTop2().getDefaultState().withProperty(H_FACING, state.getValue(H_FACING));

		IBlockState amadoBot_FACE = this.takeBot().getDefaultState().withProperty(H_FACING, state.getValue(H_FACING));
		IBlockState amadoTop_FACE = this.takeTop().getDefaultState().withProperty(H_FACING, state.getValue(H_FACING));

		switch (direction) {
		case NORTH :
		default :
			if (eastState.getMaterial().isReplaceable()) {
				CMEvents.soundFusumaS(worldIn, pos);

				worldIn.setBlockState(new BlockPos(x + 1, y - 1, z), amadoBot_FACE.withProperty(BaseStage4_Face.STAGE_1_4, Integer.valueOf(4)));
				worldIn.setBlockState(new BlockPos(x + 1, y, z), amadoTop_FACE.withProperty(BaseStage4_Face.STAGE_1_4, Integer.valueOf(4)));

				if (i != 3) {
					worldIn.setBlockState(pos, state.withProperty(STAGE_1_3, Integer.valueOf(i + 1)));
					worldIn.setBlockState(pos.down(), tobuBotL_FACE.withProperty(STAGE_1_3, Integer.valueOf(i + 1))); }

				if (i == 3) {
					worldIn.setBlockState(pos, tobuTopL2_FACE.withProperty(BaseStage2_Face.STAGE_1_2, Integer.valueOf(1)));
					worldIn.setBlockState(pos.down(), tobuBotL2_FACE.withProperty(BaseStage2_Face.STAGE_1_2, Integer.valueOf(1))); } }
			
			if (!eastState.getMaterial().isReplaceable()) { }
			break;

		case SOUTH :
			if (westState.getMaterial().isReplaceable()) {
				CMEvents.soundFusumaS(worldIn, pos);

				worldIn.setBlockState(new BlockPos(x - 1, y - 1, z), amadoBot_FACE.withProperty(BaseStage4_Face.STAGE_1_4, Integer.valueOf(4)));
				worldIn.setBlockState(new BlockPos(x - 1, y, z), amadoTop_FACE.withProperty(BaseStage4_Face.STAGE_1_4, Integer.valueOf(4)));

				if (i != 3) {
					worldIn.setBlockState(pos, state.withProperty(STAGE_1_3, Integer.valueOf(i + 1)));
					worldIn.setBlockState(pos.down(), tobuBotL_FACE.withProperty(STAGE_1_3, Integer.valueOf(i + 1))); }

				if (i == 3) {
					worldIn.setBlockState(pos, tobuTopL2_FACE.withProperty(BaseStage2_Face.STAGE_1_2, Integer.valueOf(1)));
					worldIn.setBlockState(pos.down(), tobuBotL2_FACE.withProperty(BaseStage2_Face.STAGE_1_2, Integer.valueOf(1))); } }
			
			if (!westState.getMaterial().isReplaceable()) { }
			break;

		case EAST :
			if (southState.getMaterial().isReplaceable()) {
				CMEvents.soundFusumaS(worldIn, pos);

				worldIn.setBlockState(new BlockPos(x, y - 1, z + 1), amadoBot_FACE.withProperty(BaseStage4_Face.STAGE_1_4, Integer.valueOf(4)));
				worldIn.setBlockState(new BlockPos(x, y, z + 1), amadoTop_FACE.withProperty(BaseStage4_Face.STAGE_1_4, Integer.valueOf(4)));

				if (i != 3) {
					worldIn.setBlockState(pos, state.withProperty(STAGE_1_3, Integer.valueOf(i + 1)));
					worldIn.setBlockState(pos.down(), tobuBotL_FACE.withProperty(STAGE_1_3, Integer.valueOf(i + 1))); }

				if (i == 3) {
					worldIn.setBlockState(pos, tobuTopL2_FACE.withProperty(BaseStage2_Face.STAGE_1_2, Integer.valueOf(1)));
					worldIn.setBlockState(pos.down(), tobuBotL2_FACE.withProperty(BaseStage2_Face.STAGE_1_2, Integer.valueOf(1))); } }
			
			if (!southState.getMaterial().isReplaceable()) { }
			break;
			
		case WEST :
			if (northState.getMaterial().isReplaceable()) {
				CMEvents.soundFusumaS(worldIn, pos);

				worldIn.setBlockState(new BlockPos(x, y - 1, z - 1), amadoBot_FACE.withProperty(BaseStage4_Face.STAGE_1_4, Integer.valueOf(4)));
				worldIn.setBlockState(new BlockPos(x, y, z - 1), amadoTop_FACE.withProperty(BaseStage4_Face.STAGE_1_4, Integer.valueOf(4)));

				if (i != 3) {
					worldIn.setBlockState(pos, state.withProperty(STAGE_1_3, Integer.valueOf(i + 1)));
					worldIn.setBlockState(pos.down(), tobuBotL_FACE.withProperty(STAGE_1_3, Integer.valueOf(i + 1))); }

				if (i == 3) {
					worldIn.setBlockState(pos, tobuTopL2_FACE.withProperty(BaseStage2_Face.STAGE_1_2, Integer.valueOf(1)));
					worldIn.setBlockState(pos.down(), tobuBotL2_FACE.withProperty(BaseStage2_Face.STAGE_1_2, Integer.valueOf(1))); } }
			
			if (!northState.getMaterial().isReplaceable()) { }
			break;
		} // switch

		return true;
	}

	private Block takeLeftBot2() {
		if (this == Slidedoor_Blocks.TOBUKURO_TOPL) { return Slidedoor_Blocks.TOBUKURO_BOTL2; }
		if (this == Slidedoor_Blocks.TOBUKUROS_TOPL) { return Slidedoor_Blocks.TOBUKUROS_BOTL2; }
		return null;
	}
	
	private Block takeLeftBot() {
		if (this == Slidedoor_Blocks.TOBUKURO_TOPL) { return Slidedoor_Blocks.TOBUKURO_BOTL; }
		if (this == Slidedoor_Blocks.TOBUKUROS_TOPL) { return Slidedoor_Blocks.TOBUKUROS_BOTL; }
		return null;
	}

	private Block takeLeftTop2() {
		if (this == Slidedoor_Blocks.TOBUKURO_TOPL) { return Slidedoor_Blocks.TOBUKURO_TOPL2; }
		if (this == Slidedoor_Blocks.TOBUKUROS_TOPL) { return Slidedoor_Blocks.TOBUKUROS_TOPL2; }
		return null;
	}
	
	private Block takeTop() {
		if (this == Slidedoor_Blocks.TOBUKURO_TOPL) { return Slidedoor_Blocks.AMADO_TOP; }
		if (this == Slidedoor_Blocks.TOBUKUROS_TOPL) { return Slidedoor_Blocks.AMADOS_TOP; }
		return null;
	}

	private Block takeBot() {
		if (this == Slidedoor_Blocks.TOBUKURO_TOPL) { return Slidedoor_Blocks.AMADO_BOT; }
		if (this == Slidedoor_Blocks.TOBUKUROS_TOPL) { return Slidedoor_Blocks.AMADOS_BOT; }
		return null;
	}
	
	/* A block that breaks at the same time when it is broken. */
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn) {
		if (worldIn.getBlockState(pos.down()).getBlock() instanceof Tobukuro_BotL) {
			if (playerIn.capabilities.isCreativeMode) { worldIn.destroyBlock(pos.down(), false); }
			else { worldIn.destroyBlock(pos.down(), true); }
		}
	}

	/*Drop Item and Clone Item.*/
	public boolean canSilkHarvest(World worldIn, EntityPlayer playerIn, int x, int y, int z, int metadata) {
		return false;
	}
	
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Items.AIR;
	}
	
	/* Collision */
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		EnumFacing facing = state.getValue(H_FACING);
		return AABB[facing.getHorizontalIndex()];
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
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	public boolean isFullCube(IBlockState state) {
		return false;
	}
}
