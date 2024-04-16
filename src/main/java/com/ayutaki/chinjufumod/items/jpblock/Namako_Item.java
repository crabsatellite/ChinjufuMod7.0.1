package com.ayutaki.chinjufumod.items.jpblock;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.ChinjufuModTabs;
import com.ayutaki.chinjufumod.blocks.jpblock.Kawara;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.base.ItemBlockBace;
import com.ayutaki.chinjufumod.registry.JPBlock_Blocks;

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

public class Namako_Item extends ItemBlockBace {

	public Namako_Item(String name) {
		super(name, JPBlock_Blocks.NAMAKO);
		setCreativeTab(ChinjufuModTabs.WABLOCK);
		/** Have sub items. **/
		setHasSubtypes(true);
	}

	/* Sub item meta and name. */
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 0:
		default:
			return "item." + "block_namako_white";
		case 1:
			return "item." + "block_namako_orange";
		case 2:
			return "item." + "block_namako_magenta";
		case 3:
			return "item." + "block_namako_lightb";
		case 4:
			return "item." + "block_namako_yellow";
		case 5:
			return "item." + "block_namako_lime";
		case 6:
			return "item." + "block_namako_pink";
		case 7:
			return "item." + "block_namako_gray";
		case 8:
			return "item." + "block_namako_lightg";
		case 9:
			return "item." + "block_namako_cyan";
		case 10:
			return "item." + "block_namako_purple";
		case 11:
			return "item." + "block_namako_blue";
		case 12:
			return "item." + "block_namako_brown";
		case 13:
			return "item." + "block_namako_green";
		case 14:
			return "item." + "block_namako_red";
		case 15:
			return "item." + "block_namako_black";
		}
	}

	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
			items.add(new ItemStack(this, 1, 0));
			items.add(new ItemStack(this, 1, 1));
			items.add(new ItemStack(this, 1, 2));
			items.add(new ItemStack(this, 1, 3));
			items.add(new ItemStack(this, 1, 4));
			items.add(new ItemStack(this, 1, 5));
			items.add(new ItemStack(this, 1, 6));
			items.add(new ItemStack(this, 1, 7));
			items.add(new ItemStack(this, 1, 8));
			items.add(new ItemStack(this, 1, 9));
			items.add(new ItemStack(this, 1, 10));
			items.add(new ItemStack(this, 1, 11));
			items.add(new ItemStack(this, 1, 12));
			items.add(new ItemStack(this, 1, 13));
			items.add(new ItemStack(this, 1, 14));
			items.add(new ItemStack(this, 1, 15));
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

		if (!stack.isEmpty() && playerIn.canPlayerEdit(pos, facing, stack) && worldIn.mayPlace(JPBlock_Blocks.NAMAKO, pos, false, facing, (Entity)null)) {

			IBlockState state1 = JPBlock_Blocks.NAMAKO.getDefaultState().withProperty(Kawara.STAGE_0_15, Integer.valueOf(k));
			worldIn.setBlockState(pos, state1, 10);

			CMEvents.Consume_1Stone(worldIn, pos, playerIn, hand);
			return EnumActionResult.SUCCESS;
		}

		else { return EnumActionResult.FAIL; }
	}

	/* Tips */
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag advanced) {
		int meta = stack.getMetadata();
		tooltip.add(I18n.format("tips.block_namako.name", meta));
	}
}
