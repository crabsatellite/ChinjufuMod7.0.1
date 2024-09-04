package com.ayutaki.chinjufumod.blocks.hakkou;

import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BaseTaru_Yoh extends RotatedPillarBlock implements SimpleWaterloggedBlock {

  /* Property */
  public static final IntegerProperty STAGE_1_6 = IntegerProperty.create("stage", 1, 6);
  public static final BooleanProperty WATERLOGGED = BooleanProperty.create("waterlogged");

  /* Collision */
  protected static final VoxelShape AABB_BOX =
      Shapes.or(
          Block.box(1.0D, 14.0D, 1.0D, 15.0D, 16.0D, 15.0D),
          Block.box(0.5D, 12.0D, 0.5D, 15.5D, 14.0D, 15.5D),
          Block.box(0.0D, 4.0D, 0.0D, 16.0D, 12.0D, 16.0D),
          Block.box(0.5D, 2.0D, 0.5D, 15.5D, 4.0D, 15.5D),
          Block.box(1.0D, 0.0D, 1.0D, 15.0D, 2.0D, 15.0D));

  protected static final VoxelShape AABB_X =
      Shapes.or(
          Block.box(0.0D, 1.0D, 1.0D, 2.0D, 15.0D, 15.0D),
          Block.box(2.0D, 0.5D, 0.5D, 4.0D, 15.5D, 15.5D),
          Block.box(4.0D, 0.0D, 0.0D, 12.0D, 16.0D, 16.0D),
          Block.box(12.0D, 0.5D, 0.5D, 14.0D, 15.5D, 15.5D),
          Block.box(14.0D, 1.0D, 1.0D, 16.0D, 15.0D, 15.0D));

  protected static final VoxelShape AABB_Z =
      Shapes.or(
          Block.box(1.0D, 1.0D, 0.0D, 15.0D, 15.0D, 2.0D),
          Block.box(0.5D, 0.5D, 2.0D, 15.5D, 15.5D, 4.0D),
          Block.box(0.0D, 0.0D, 4.0D, 16.0D, 16.0D, 12.0D),
          Block.box(0.5D, 0.5D, 12.0D, 15.5D, 15.5D, 14.0D),
          Block.box(1.0D, 1.0D, 14.0D, 15.0D, 15.0D, 16.0D));

  public BaseTaru_Yoh(BlockBehaviour.Properties properties) {
    super(properties);
    /** Default state * */
    registerDefaultState(
        this.stateDefinition
            .any()
            .setValue(AXIS, Direction.Axis.Y)
            .setValue(STAGE_1_6, Integer.valueOf(1))
            .setValue(WATERLOGGED, Boolean.valueOf(false)));
  }

  /* Gives a value when placed. +180 .getOpposite() */
  @Override
  public BlockState getStateForPlacement(BlockPlaceContext context) {
    Level worldIn = context.getLevel();
    BlockPos pos = context.getClickedPos();
    FluidState fluid = worldIn.getFluidState(pos);

    return this.defaultBlockState()
        .setValue(AXIS, context.getClickedFace().getAxis())
        .setValue(WATERLOGGED, Boolean.valueOf(fluid.getType() == Fluids.WATER));
  }

  /* Waterlogged */
  @SuppressWarnings("deprecation")
  public FluidState getFluidState(BlockState state) {
    return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
  }

  @Override
  public boolean canPlaceLiquid(BlockGetter worldIn, BlockPos pos, BlockState state, Fluid fluid) {
    return !state.getValue(BlockStateProperties.WATERLOGGED) && fluid == Fluids.WATER;
  }

  @Override
  public boolean placeLiquid(
      LevelAccessor worldIn, BlockPos pos, BlockState state, FluidState fluid) {
    if (!state.getValue(BlockStateProperties.WATERLOGGED) && fluid.getType() == Fluids.WATER) {
      if (!worldIn.isClientSide()) {
        worldIn.setBlock(
            pos, state.setValue(BlockStateProperties.WATERLOGGED, Boolean.valueOf(true)), 3);
        worldIn.scheduleTick(pos, fluid.getType(), fluid.getType().getTickDelay(worldIn));
      }
      return true;
    } else {
      return false;
    }
  }

  @Override
  public ItemStack pickupBlock(LevelAccessor worldIn, BlockPos pos, BlockState state) {
    if (state.getValue(BlockStateProperties.WATERLOGGED)) {
      worldIn.setBlock(
          pos, state.setValue(BlockStateProperties.WATERLOGGED, Boolean.valueOf(false)), 3);

      if (!state.canSurvive(worldIn, pos)) {
        worldIn.destroyBlock(pos, true);
      }
      return new ItemStack(Items.WATER_BUCKET);
    } else {
      return ItemStack.EMPTY;
    }
  }

  @Override
  public Optional<SoundEvent> getPickupSound() {
    return Fluids.WATER.getPickupSound();
  }

  /* Update BlockState. */
  @SuppressWarnings("deprecation")
  @Override
  public BlockState updateShape(
      BlockState state,
      Direction facing,
      BlockState facingState,
      LevelAccessor worldIn,
      BlockPos pos,
      BlockPos facingPos) {
    if ((Boolean) state.getValue(WATERLOGGED)) {
      worldIn.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
    }

    return super.updateShape(state, facing, facingState, worldIn, pos, facingPos);
  }

  /* Create Blockstate */
  @Override
  protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
    builder.add(AXIS, STAGE_1_6, WATERLOGGED);
  }

  /* Collisions for each property. */
  @Override
  public VoxelShape getShape(
      BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
    switch ((Direction.Axis) state.getValue(AXIS)) {
      case X:
        return AABB_X;
      case Z:
        return AABB_Z;
      case Y:
      default:
        return AABB_BOX;
    }
  }

  /* Flammable Block */
  @Override
  public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction direct) {
    return true;
  }

  @Override
  public int getFireSpreadSpeed(
      BlockState state, BlockGetter world, BlockPos pos, Direction direct) {
    return 5;
  }

  @Override
  public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction direct) {
    return 20;
  }
}
