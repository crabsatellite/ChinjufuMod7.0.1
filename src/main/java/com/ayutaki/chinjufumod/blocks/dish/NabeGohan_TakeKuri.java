package com.ayutaki.chinjufumod.blocks.dish;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class NabeGohan_TakeKuri extends BaseNabe {

	public NabeGohan_TakeKuri(BlockBehaviour.Properties properties) {
		super(properties);
	}

	/* RightClick Action*/
	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {

		ItemStack stack = playerIn.getItemInHand(hand);
		Item item = stack.getItem();
		int i = state.getValue(STAGE_1_4);

		if (item == Items_Teatime.CHAWAN.get()) {
			/** Collect with an Item **/
			CMEvents.Consume1Item_SnowB(worldIn, pos, playerIn, hand);
			
			if (stack.isEmpty()) { playerIn.getInventory().add(this.takeChawan()); }
			else if (!playerIn.getInventory().add(this.takeChawan())) {
				playerIn.drop(this.takeChawan(), false); }

			if (i != 4) { worldIn.setBlock(pos, state.setValue(BaseNabe.STAGE_1_4, Integer.valueOf(i + 1)), 3); }

			if (i == 4) {
				worldIn.setBlock(pos, Dish_Blocks.NABE_kara.get().defaultBlockState().setValue(H_FACING, state.getValue(H_FACING))
						.setValue(COOK, state.getValue(COOK)).setValue(DOWN, state.getValue(DOWN))
						.setValue(Nabe_kara.STAGE_1_4, Integer.valueOf(3)), 3); }
		}
		
		if (item == Items.BOWL) {
			if (i == 1) {
				/** Collect with an Item **/
				CMEvents.Consume1Item_SnowB(worldIn, pos, playerIn, hand);
				
				if (stack.isEmpty()) { playerIn.getInventory().add(this.takeBowl()); }
				else if (!playerIn.getInventory().add(this.takeBowl())) {
					playerIn.drop(this.takeBowl(), false); }
	
				worldIn.setBlock(pos, Dish_Blocks.NABE_kara.get().defaultBlockState().setValue(H_FACING, state.getValue(H_FACING))
						.setValue(COOK, state.getValue(COOK)).setValue(DOWN, state.getValue(DOWN))
						.setValue(Nabe_kara.STAGE_1_4, Integer.valueOf(3)), 3); }
			
			if (i != 1) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		}
		
		if (item != Items_Teatime.CHAWAN.get() && item != Items.BOWL) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		
		/** SUCCESS to not put anything on top. **/
		return InteractionResult.SUCCESS;
	}

	private ItemStack takeChawan() {
		if (this == Dish_Blocks.NABEGOHAN_TAKE.get()) { return new ItemStack(Items_Teatime.GOHAN_TAKE.get(), 1); }
		if (this == Dish_Blocks.NABEGOHAN_KURI.get()) { return new ItemStack(Items_Teatime.GOHAN_KURI.get(), 1); }
		return null;
	}
	
	private ItemStack takeBowl() {
		if (this == Dish_Blocks.NABEGOHAN_TAKE.get()) { return new ItemStack(Items_Teatime.MUSHIGOME_TAKE.get(), 1); }
		if (this == Dish_Blocks.NABEGOHAN_KURI.get()) { return new ItemStack(Items_Teatime.MUSHIGOME_KURI.get(), 1); }
		return null;
	}
	
	/* TickRandom */
	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, Random rand) {
		if (inWater(state, worldIn, pos)) {
			worldIn.scheduleTick(pos, this, 60);
			CMEvents.soundSnowBreak(worldIn, pos);
			worldIn.setBlock(pos, Dish_Blocks.NABE_kara.get().defaultBlockState()
					.setValue(H_FACING, state.getValue(H_FACING))
					.setValue(COOK, state.getValue(COOK))
					.setValue(DOWN, state.getValue(DOWN))
					.setValue(Nabe_kara.STAGE_1_4, Integer.valueOf(3))
					.setValue(Nabe_kara.WATERLOGGED, state.getValue(WATERLOGGED)), 3);
			this.dropRottenfood(worldIn, pos); }
		
		else { }
	}
	
	/* Clone Item in Creative. */
	@Override
	public ItemStack getCloneItemStack(BlockGetter worldIn, BlockPos pos, BlockState state) {
		return this.takeStack();
	}
	
	private ItemStack takeStack() {
		if (this == Dish_Blocks.NABEGOHAN_TAKE.get()) { return new ItemStack(Items_Teatime.NABEGOHAN_TAKE.get(), 1); }
		if (this == Dish_Blocks.NABEGOHAN_KURI.get()) { return new ItemStack(Items_Teatime.NABEGOHAN_KURI.get(), 1); }
		return null;
	}
	
	/* ToolTip */
	public void appendHoverText(ItemStack stack, @Nullable BlockGetter worldIn, List<Component> tooltip, TooltipFlag tipFlag) {
		tooltip.add(new TranslatableComponent("tips.block_food_nabegohantake_1").withStyle(ChatFormatting.GRAY));
	}
}
