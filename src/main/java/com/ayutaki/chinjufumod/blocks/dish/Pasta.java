package com.ayutaki.chinjufumod.blocks.dish;

import java.util.Random;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Pasta extends BaseFood_Stage4Water {

	/* Collision */
	protected static final VoxelShape AABB_BOX = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 2.0D, 12.0D);
	protected static final VoxelShape AABB_DOWN = Block.box(4.0D, -8.0D, 4.0D, 12.0D, 0.1D, 12.0D);

	public Pasta(BlockBehaviour.Properties properties) {
		super(properties);
	}

	/* RightClick Action*/
	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack stack = playerIn.getItemInHand(hand);
		int i = state.getValue(STAGE_1_4);

		if (i != 4) {
			/** Hand is empty. **/
			if (stack.isEmpty()) {
				CMEvents.soundEat(worldIn, pos);
	
				if (i == 1) {
					/** 採掘3600 1秒＝20 満腹は2で肉メモリの1個分 **/
					playerIn.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 3600, 0));
					playerIn.addEffect(new MobEffectInstance(MobEffects.SATURATION, 4, 0)); }
	
				if (i == 2) {
					/** 即時回復は0,0でよい 満腹は2で肉メモリの1個分 **/
					playerIn.addEffect(new MobEffectInstance(MobEffects.HEAL, 0, 0));
					playerIn.addEffect(new MobEffectInstance(MobEffects.SATURATION, 4, 0)); }
	
				if (i == 3) {
					/** リジェネは3600 即時回復は0,0でよい 満腹は2で肉メモリの1個分 **/
					playerIn.addEffect(new MobEffectInstance(MobEffects.HEAL, 0, 0));
					playerIn.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 3600, 0));
					playerIn.addEffect(new MobEffectInstance(MobEffects.SATURATION, 4, 0)); }
	
				worldIn.setBlock(pos, state.setValue(STAGE_1_4, Integer.valueOf(i + 1)), 3); }
			
			if (!stack.isEmpty()) { CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		
		if (i == 4) { CMEvents.textIsEmpty(worldIn, pos, playerIn); }
		
		/** SUCCESS to not put anything on top. **/
		return InteractionResult.SUCCESS;
	}

	/* TickRandom */
	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, Random rand) {
		int i = state.getValue(STAGE_1_4);
		
		if (i != 4) {
			if (inWater(state, worldIn, pos)) {
				worldIn.scheduleTick(pos, this, 60);
				CMEvents.soundSnowBreak(worldIn, pos);
				worldIn.setBlock(pos, state.setValue(STAGE_1_4, Integer.valueOf(4)), 3);
				this.dropRottenfood(worldIn, pos); }
			
			else { } }
		
		if (i == 4) { }
	}
	
	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		boolean flag= !((Boolean)state.getValue(DOWN)).booleanValue();
		return flag? AABB_BOX : AABB_DOWN;
	}

	/* Clone Item in Creative. */
	@Override
	public ItemStack getCloneItemStack(BlockGetter worldIn, BlockPos pos, BlockState state) {
		return this.takeStack();
	}
	
	private ItemStack takeStack() {
		if (this == Dish_Blocks.PASTACHEESE.get()) { return new ItemStack(Items_Teatime.PASTACHEESE.get(), 1); }
		if (this == Dish_Blocks.PASTAKINOKO.get()) { return new ItemStack(Items_Teatime.PASTAKINOKO.get(), 1); }
		if (this == Dish_Blocks.PASTATOMATO.get()) { return new ItemStack(Items_Teatime.PASTATOMATO.get(), 1); }
		if (this == Dish_Blocks.YAKISOBA.get()) { return new ItemStack(Items_Teatime.YAKISOBA.get(), 1); }
		if (this == Dish_Blocks.YAKISOBASHIO.get()) { return new ItemStack(Items_Teatime.YAKISOBASHIO.get(), 1); }
		return null;
	}
}
