package com.ayutaki.chinjufumod.items.jpdeco;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.ChinjufuModTabs;
import com.ayutaki.chinjufumod.blocks.base.BaseStage4_Face;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.base.ItemBlockBace;
import com.ayutaki.chinjufumod.registry.Garden_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ShishiOdoshi_Item extends ItemBlockBace {

	public ShishiOdoshi_Item(String name) {
		super(name, Garden_Blocks.SHISHIODOSHI);
		setUnlocalizedName(name);
		setCreativeTab(ChinjufuModTabs.WADECO);
	}

	/* Place block */
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing,
			float hitX, float hitY, float hitZ) {

		IBlockState state = worldIn.getBlockState(pos);
		Block block = state.getBlock();

		if (!block.isReplaceable(worldIn, pos)) { pos = pos.offset(facing); }

			/* 4.0F / 360.0F) + 0.5D -> add 180... 4.0F / 360.0F) + 2.5D */
			int i = MathHelper.floor((double)(playerIn.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
			EnumFacing direction = EnumFacing.getHorizontal(i);
			ItemStack stack = playerIn.getHeldItem(hand);

		if (!stack.isEmpty() && playerIn.canPlayerEdit(pos, facing, stack) && worldIn.mayPlace(Garden_Blocks.SHISHIODOSHI, pos, false, facing, (Entity)null)) {

			if (playerIn.isSneaking()) {
				/** ブロックの設置 **/
				worldIn.setBlockState(pos, Garden_Blocks.SHISHIODOSHIB.getDefaultState()
						.withProperty(BaseStage4_Face.H_FACING, direction)
						.withProperty(BaseStage4_Face.STAGE_1_4, Integer.valueOf(1)), 10);
				worldIn.setBlockState(pos.up(), Garden_Blocks.SHISHIODOSHI_TB.getDefaultState()
						.withProperty(BaseStage4_Face.H_FACING, direction)
						.withProperty(BaseStage4_Face.STAGE_1_4, Integer.valueOf(1)), 10);

				CMEvents.Consume_1Stone(worldIn, pos, playerIn, hand);
				return EnumActionResult.SUCCESS; }

			else {
				/** ブロックの設置 **/
				worldIn.setBlockState(pos, Garden_Blocks.SHISHIODOSHI.getDefaultState()
						.withProperty(BaseStage4_Face.H_FACING, direction)
						.withProperty(BaseStage4_Face.STAGE_1_4, Integer.valueOf(1)), 10);
				worldIn.setBlockState(pos.up(), Garden_Blocks.SHISHIODOSHI_T.getDefaultState()
						.withProperty(BaseStage4_Face.H_FACING, direction)
						.withProperty(BaseStage4_Face.STAGE_1_4, Integer.valueOf(1)), 10);

				CMEvents.Consume_1Stone(worldIn, pos, playerIn, hand);
				return EnumActionResult.SUCCESS; }
		}

		else { return EnumActionResult.FAIL; }
	}


	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag advanced) {
		int meta = stack.getMetadata();
		tooltip.add(I18n.format("tips.block_shishiodoshi.name", meta));
	}
}
