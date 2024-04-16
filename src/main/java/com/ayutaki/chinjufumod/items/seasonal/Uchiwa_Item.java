package com.ayutaki.chinjufumod.items.seasonal;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.ChinjufuModTabs;
import com.ayutaki.chinjufumod.blocks.season.Uchiwa;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Seasonal_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Uchiwa_Item extends Item {

	public Uchiwa_Item(String name) {
		super();
		setRegistryName(new ResourceLocation(ChinjufuMod.MOD_ID, name));
		setCreativeTab(ChinjufuModTabs.SEASONAL);
		/** Have sub items. **/
		setHasSubtypes(true);
	}

	/* Sub item meta and name. */
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 0:
		default:
			return "item." + "block_uchiwa_white";
		case 1:
			return "item." + "block_uchiwa_orange";
		case 2:
			return "item." + "block_uchiwa_magenta";
		case 3:
			return "item." + "block_uchiwa_lightb";
		case 4:
			return "item." + "block_uchiwa_yellow";
		case 5:
			return "item." + "block_uchiwa_lime";
		case 6:
			return "item." + "block_uchiwa_pink";
		case 7:
			return "item." + "block_uchiwa_gray";
		case 8:
			return "item." + "block_uchiwa_lightg";
		case 9:
			return "item." + "block_uchiwa_cyan";
		case 10:
			return "item." + "block_uchiwa_purple";
		case 11:
			return "item." + "block_uchiwa_blue";
		case 12:
			return "item." + "block_uchiwa_brown";
		case 13:
			return "item." + "block_uchiwa_green";
		case 14:
			return "item." + "block_uchiwa_red";
		case 15:
			return "item." + "block_uchiwa_black";
		}
	}

	@Override
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
	
	/* Call this when you use the item. ex) Place a block. */
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		
		IBlockState state = worldIn.getBlockState(pos);
		Block block = state.getBlock();
		
		if (!block.isReplaceable(worldIn, pos)) { pos = pos.offset(facing); }

		/* 4.0F / 360.0F) + 0.5D -> add 180... 4.0F / 360.0F) + 2.5D */
		int i = MathHelper.floor((double)(playerIn.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
		EnumFacing direction = EnumFacing.getHorizontal(i);
		ItemStack stack = playerIn.getHeldItem(hand);
	
		if (playerIn.canPlayerEdit(pos, facing, stack) && this.takeBlock(playerIn, hand).canPlaceBlockAt(worldIn, pos)) {

			worldIn.setBlockState(pos, this.takeBlock(playerIn, hand).getDefaultState().withProperty(Uchiwa.H_FACING, direction)
					.withProperty(Uchiwa.KAKUDO, Integer.valueOf(1)), 2);
			CMEvents.Consume_1Cloth(worldIn, pos, playerIn, hand);
			return EnumActionResult.SUCCESS;
		}
			
		else { return EnumActionResult.FAIL; }
	}
	
	private Block takeBlock(EntityPlayer playerIn, EnumHand hand) {
		ItemStack stack = playerIn.getHeldItem(hand);
		int k;
		k = stack.getMetadata();
		
		if (k == 0) { return Seasonal_Blocks.UCHIWA_white; }
		if (k == 1) { return Seasonal_Blocks.UCHIWA_orange; }
		if (k == 2) { return Seasonal_Blocks.UCHIWA_magenta; }
		if (k == 3) { return Seasonal_Blocks.UCHIWA_lightb; }
		if (k == 4) { return Seasonal_Blocks.UCHIWA_yellow; }
		if (k == 5) { return Seasonal_Blocks.UCHIWA_lime; }
		if (k == 6) { return Seasonal_Blocks.UCHIWA_pink; }
		if (k == 7) { return Seasonal_Blocks.UCHIWA_gray; }
		if (k == 8) { return Seasonal_Blocks.UCHIWA_lightg; }
		if (k == 9) { return Seasonal_Blocks.UCHIWA_cyan; }
		if (k == 10) { return Seasonal_Blocks.UCHIWA_purple; }
		if (k == 11) { return Seasonal_Blocks.UCHIWA_blue; }
		if (k == 12) { return Seasonal_Blocks.UCHIWA_brown; }
		if (k == 13) { return Seasonal_Blocks.UCHIWA_green; }
		if (k == 14) { return Seasonal_Blocks.UCHIWA_red; }
		if (k == 15) { return Seasonal_Blocks.UCHIWA_black; }
		return null;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag advanced) {
		int meta = stack.getMetadata();
		tooltip.add(I18n.format("tips.block_uchiwa.name", meta));
	}
}
