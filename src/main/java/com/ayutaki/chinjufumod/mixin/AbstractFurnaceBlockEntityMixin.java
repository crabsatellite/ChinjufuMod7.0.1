package com.ayutaki.chinjufumod.mixin;

import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(AbstractFurnaceBlockEntity.class)
public interface AbstractFurnaceBlockEntityMixin {

  @Accessor("quickCheck")
  RecipeManager.CachedCheck<Container, ? extends AbstractCookingRecipe> getQuickCheck();

  @Invoker("getTotalCookTime")
  int invokeGetTotalCookTime(Level level, AbstractFurnaceBlockEntity furnaceEntity);
}
