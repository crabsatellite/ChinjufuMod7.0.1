package com.ayutaki.chinjufumod.blocks.dish;

import java.util.ArrayList;
import java.util.List;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Donburi extends BaseStage4_FaceDown {

	private static final AxisAlignedBB AABB = new AxisAlignedBB(0.3625D, 0.0D, 0.3625D, 0.6375D, 0.1875D, 0.6375D);
	private static final AxisAlignedBB AABB_DOWN = new AxisAlignedBB(0.3625D, -0.5D, 0.3625D, 0.6375D, 0.01D, 0.6375D);

	public Donburi(String name) {
		super(name);
		setSoundType(SoundType.STONE);
		setHardness(1.0F);
		setResistance(5.0F);
		setLightOpacity(0);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

		ItemStack stack = playerIn.getHeldItem(hand);
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

		if (i != 4) {
			/** Hand is Empty. **/
			if (stack.isEmpty()) {
				CMEvents.soundEat(worldIn, pos);
				
				if (this == Dish_Blocks.DONBURI_MESHI) {
					/** 満腹は2で肉メモリの1個分 **/
					if (i == 1) { ((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.SATURATION, 2, 0)); }
					if (i == 2) { ((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.SATURATION, 2, 0)); }
					if (i == 3) { ((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.SATURATION, 2, 0)); }
				}
	
				if (this == Dish_Blocks.DONBURI_KATSU) {
					if (i == 1) {
						/** 採掘3600 1秒＝20 満腹は2で肉メモリの1個分 カツ調理分加算 +800tick **/
						((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.HASTE, 4200, 0));
						((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.SATURATION, 4, 0)); }
		
					if (i == 2) {
						/** 即時回復は0,0でよい 満腹は2で肉メモリの1個分 **/
						((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 0, 0));
						((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.SATURATION, 4, 0)); }
		
					if (i == 3) {
						/** リジェネは3600 即時回復は0,0でよい 満腹は2で肉メモリの1個分 カツ調理分加算 +800tick **/
						((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 0, 0));
						((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.SATURATION, 4, 0));
						((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 4200, 0)); }
				}
				
				if (this == Dish_Blocks.DONBURI_OYAKO || this == Dish_Blocks.DONBURI_GYU || this == Dish_Blocks.DONBURI_KAISEN) {
					if (i == 1) {
						((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.HASTE, 3600, 0));
						((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.SATURATION, 4, 0)); }
		
					if (i == 2) {
						((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 0, 0));
						((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.SATURATION, 4, 0)); }
		
					if (i == 3) {
						((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 0, 0));
						((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.SATURATION, 4, 0));
						((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 3600, 0)); }
				}
				
				worldIn.setBlockState(pos, state.withProperty(STAGE_1_4, Integer.valueOf(i + 1)), 3); }
			
			if (!stack.isEmpty()) { CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		
		if (i == 4) { CMEvents.textIsEmpty(worldIn, pos, playerIn); }
		
		/** 'true' to not put anything on top. **/
		return true;
	}

	/*Collision*/
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		state = state.getActualState(source, pos);
		boolean flag= !((Boolean)state.getValue(DOWN)).booleanValue();

		/** !down= true : false **/
		return flag? AABB : AABB_DOWN;
	}

	/*Drop Item and Clone Item.*/
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();

		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		if (i == 1) { stack.add(this.takeStack()); }
		if (i != 1) { stack.add(new ItemStack(Items_Teatime.Item_DISH, 1, 6)); }
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return this.takeStack();
	}

	private ItemStack takeStack() {
		if (this == Dish_Blocks.DONBURI_MESHI) { return new ItemStack(Items_Teatime.DONBURI_MESHI, 1, 0); }
		if (this == Dish_Blocks.DONBURI_KATSU) { return new ItemStack(Items_Teatime.DONBURI_KATSU, 1, 0); }
		if (this == Dish_Blocks.DONBURI_OYAKO) { return new ItemStack(Items_Teatime.DONBURI_OYAKO, 1, 0); }
		if (this == Dish_Blocks.DONBURI_GYU) { return new ItemStack(Items_Teatime.DONBURI_GYU, 1, 0); }
		if (this == Dish_Blocks.DONBURI_KAISEN) { return new ItemStack(Items_Teatime.DONBURI_KAISEN, 1, 0); }
		return null;
	}
}
