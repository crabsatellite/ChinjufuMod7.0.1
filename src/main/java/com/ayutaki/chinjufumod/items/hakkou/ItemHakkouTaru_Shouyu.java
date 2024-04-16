package com.ayutaki.chinjufumod.items.hakkou;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.ChinjufuModTabs;
import com.ayutaki.chinjufumod.blocks.hakkou.Taru_Shouyu;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.base.ItemBlockBace;
import com.ayutaki.chinjufumod.registry.Hakkou_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemHakkouTaru_Shouyu extends ItemBlockBace {

	public ItemHakkouTaru_Shouyu(String name) {
		super(name, Hakkou_Blocks.SHOUYUTARU);
		setCreativeTab(ChinjufuModTabs.TEATIME);
		/** Have sub items. **/
		setHasSubtypes(true);
	}

	/* Sub item meta and name. */
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 1:
		default:
			return "item." + "block_taru_shouyu_f";
		case 2:
			return "item." + "block_taru_komezu_f";
		case 3:
			return "item." + "block_taru_kinoko_f";
		case 4:
			return "item." + "block_taru_nori_f";
		case 5:
			return "item." + "block_taru_pepper_f";
		}
	}

	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
			items.add(new ItemStack(this, 1, 1));
			items.add(new ItemStack(this, 1, 2));
			items.add(new ItemStack(this, 1, 3));
			items.add(new ItemStack(this, 1, 4));
			items.add(new ItemStack(this, 1, 5));
		}
	}

	/* Place block */
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing,
			float hitX, float hitY, float hitZ) {

		IBlockState state = worldIn.getBlockState(pos);
		Block block = state.getBlock();

		if (!block.isReplaceable(worldIn, pos)) { pos = pos.offset(facing); }

		ItemStack stack = playerIn.getHeldItem(hand);
		int k;
		k = stack.getMetadata();

		if (!stack.isEmpty() && playerIn.canPlayerEdit(pos, facing, stack) && worldIn.mayPlace(Hakkou_Blocks.SHOUYUTARU, pos, false, facing, (Entity)null)) {

			if (k == 1) {
				/** Put the Block. **/
				worldIn.setBlockState(pos, Hakkou_Blocks.SHOUYUTARU.getDefaultState().withProperty(Taru_Shouyu.STAGE_0_15, Integer.valueOf(1)), 10); }
			
			if (k == 2) {
				worldIn.setBlockState(pos, Hakkou_Blocks.SHOUYUTARU.getDefaultState().withProperty(Taru_Shouyu.STAGE_0_15, Integer.valueOf(6)), 10); }

			if (k == 3) {
				worldIn.setBlockState(pos, Hakkou_Blocks.SHOUYUTARU.getDefaultState().withProperty(Taru_Shouyu.STAGE_0_15, Integer.valueOf(11)), 10); }

			if (k == 4) {
				worldIn.setBlockState(pos, Hakkou_Blocks.SHOUYUTARU.getDefaultState().withProperty(Taru_Shouyu.STAGE_0_15, Integer.valueOf(13)), 10); }

			if (k == 5) {
				worldIn.setBlockState(pos, Hakkou_Blocks.SHOUYUTARU.getDefaultState().withProperty(Taru_Shouyu.STAGE_0_15, Integer.valueOf(0)), 10); }
			
			CMEvents.Consume_1Wood(worldIn, pos, playerIn, hand);
			return EnumActionResult.SUCCESS;
		}

		else { return EnumActionResult.FAIL; }
	}

	/* ToolTip*/
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag advanced) {
		if (stack.getMetadata() == 1) { tooltip.add(I18n.format("tips.block_taru_shouyu.name")); }
		if (stack.getMetadata() == 2) { tooltip.add(I18n.format("tips.block_taru_komezu.name")); }
		if (stack.getMetadata() == 3) { tooltip.add(I18n.format("tips.block_taru_kinoko.name")); }
		if (stack.getMetadata() == 4) { tooltip.add(I18n.format("tips.block_taru_nori.name")); }
		if (stack.getMetadata() == 5) { tooltip.add(I18n.format("tips.block_taru_pepper.name")); }
	}

}
