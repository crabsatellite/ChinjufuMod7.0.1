package com.ayutaki.chinjufumod.blocks.hakkou;

import com.ayutaki.chinjufumod.blocks.kitchen.Base_WineTana;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Hakkou_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class Kit_Tana2Sake extends Base_WineTana {

	public Kit_Tana2Sake(BlockBehaviour.Properties properties) {
		super(properties);
	}

	/* RightClick Action*/
	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack stack = playerIn.getItemInHand(hand);
		Item item = stack.getItem();
		int i = state.getValue(STAGE_1_4);

		if (item != this.takeItem()) {
			if (stack.isEmpty()) {
				playerIn.getInventory().add(new ItemStack(this.takeItem(), 1));
				CMEvents.soundTakeSakeBottle(worldIn, pos);
	
				if (i != 1) { worldIn.setBlock(pos, state.setValue(STAGE_1_4, Integer.valueOf(i - 1)), 3); }
				if (i == 1) { worldIn.setBlock(pos, Hakkou_Blocks.WINE_TANA.get().defaultBlockState().setValue(H_FACING, state.getValue(H_FACING)), 3); } }

			if (!stack.isEmpty()) { CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		
		if (item == this.takeItem()) {
			if (i != 4) {
				CMEvents.Consume1Item_Dish(worldIn, pos, playerIn, hand);
				
				worldIn.setBlock(pos, state.setValue(STAGE_1_4, Integer.valueOf(i + 1)), 3); }
			
			if (i == 4) { CMEvents.textFullItem(worldIn, pos, playerIn); }
		}

		/** SUCCESS to not put anything on top. **/
		return InteractionResult.SUCCESS;
	}
	
	private Item takeItem() {
		if (this == Hakkou_Blocks.KIT_SAKENAMA.get()) { return Items_Teatime.NAMASAKEBOT.get(); }
		if (this == Hakkou_Blocks.KIT_SAKE.get()) { return Items_Teatime.SAKEBOT.get(); }
		if (this == Hakkou_Blocks.KIT_SAKEJUKU.get()) { return Items_Teatime.JUKUSAKEBOT.get(); }
		if (this == Hakkou_Blocks.KIT_CIDER.get()) { return Items_Teatime.CIDERBOT.get(); }
		if (this == Hakkou_Blocks.KIT_CIDERJUKU.get()) { return Items_Teatime.JUKUCIDERBOT.get(); }
		if (this == Hakkou_Blocks.KIT_WINE.get()) { return Items_Teatime.WINEBOT.get(); }
		if (this == Hakkou_Blocks.KIT_WINEJUKU.get()) { return Items_Teatime.JUKUWINEBOT.get(); }
		if (this == Hakkou_Blocks.KIT_MEAD.get()) { return Items_Teatime.MEADBOT.get(); }
		if (this == Hakkou_Blocks.KIT_MEADJUKU.get()) { return Items_Teatime.JUKUMEADBOT.get(); }
		return null;
	}
}
