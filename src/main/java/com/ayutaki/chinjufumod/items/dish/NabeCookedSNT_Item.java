package com.ayutaki.chinjufumod.items.dish;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.ChinjufuModTabs;
import com.ayutaki.chinjufumod.blocks.dish.NabeCooked_SNT;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.base.ItemBlockBace;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;

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
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class NabeCookedSNT_Item extends ItemBlockBace {

	public NabeCookedSNT_Item(String name) {
		super(name, Dish_Blocks.NABE_cooked);
		setCreativeTab(ChinjufuModTabs.TEATIME);
		/** Have sub items. **/
		setHasSubtypes(true);
	}

	/* Sub item meta and name. */
	public String getUnlocalizedName(ItemStack stack) {

		switch (stack.getMetadata()) {
		case 1:
		default:
			return "item." + "block_food_nabeshio_b";
		case 2:
			return "item." + "block_food_nabenimame_b";
		case 3:
			return "item." + "block_food_nabetoufu_b";
		}
	}

	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
			items.add(new ItemStack(this, 1, 1));
			items.add(new ItemStack(this, 1, 2));
			items.add(new ItemStack(this, 1, 3));
		}
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
			int k;
			k = stack.getMetadata();

		if (!stack.isEmpty() && playerIn.canPlayerEdit(pos, facing, stack) && worldIn.mayPlace(Dish_Blocks.NABE_cooked, pos, false, facing, (Entity)null)) {

			if (k == 1) {
				/** Put the Block. **/
				IBlockState state1 = Dish_Blocks.NABE_cooked.getDefaultState().withProperty(NabeCooked_SNT.H_FACING, direction)
						.withProperty(NabeCooked_SNT.STAGE_1_3, Integer.valueOf(1));
				worldIn.setBlockState(pos, state1, 10); }

			if (k == 2) {
				IBlockState state1 = Dish_Blocks.NABE_cooked.getDefaultState().withProperty(NabeCooked_SNT.H_FACING, direction)
						.withProperty(NabeCooked_SNT.STAGE_1_3, Integer.valueOf(2));
				worldIn.setBlockState(pos, state1, 10); }

			if (k == 3) {
				IBlockState state1 = Dish_Blocks.NABE_cooked.getDefaultState().withProperty(NabeCooked_SNT.H_FACING, direction)
						.withProperty(NabeCooked_SNT.STAGE_1_3, Integer.valueOf(3));
				worldIn.setBlockState(pos, state1, 10); }

			CMEvents.Consume_1Stone(worldIn, pos, playerIn, hand);
			return EnumActionResult.SUCCESS;
		}

		else { return EnumActionResult.FAIL; }
	}

	/* ToolTip*/
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag advanced) {
		if (stack.getMetadata() == 1) { tooltip.add(I18n.format("tips.block_food_nabetoufu_b.name")); }
		if (stack.getMetadata() == 2) { tooltip.add(I18n.format("tips.block_food_nabenimame_b.name")); }
		if (stack.getMetadata() == 3) { tooltip.add(I18n.format("tips.block_food_nabetoufu_b.name")); }
	}

}
