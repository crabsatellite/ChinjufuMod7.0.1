package com.ayutaki.chinjufumod.entity;

import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.registry.Items_Weapon;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class KB_Re2001Entity extends AbstractKK_Entity {

	private ItemStack projectile = new ItemStack(Items_Weapon.RE2001);
	private double baseDamage = 9.0D; /** Re.2001 CB改 火力+3 爆装+6**/
	
	public KB_Re2001Entity(World worldIn) {
		super(worldIn);
	}

	public KB_Re2001Entity(World worldIn, EntityLivingBase entity, ItemStack stack) {
		super(worldIn, entity, stack);
		this.projectile = stack.copy();
	}

	@Override
	protected void onHitEntity(RayTraceResult result) { 
		if (!world.isRemote && result.entityHit != null && result.entityHit instanceof EntityLivingBase && result.entityHit != getThrower()) {

			/** Do not attack Friendly Mobs. **/
			boolean friendly = (result.entityHit instanceof EntityVillager || result.entityHit instanceof EntityHorse || 
					result.entityHit instanceof EntityTameable || result.entityHit instanceof EntityIronGolem);
			
			if (friendly) {
				this.playSound(SoundEvents_CM.KK_STOP, 2.0F, 1.0F);
				dropAndKill(); }

			if (!friendly) {
				double atackDamage = this.baseDamage;
				float criticalA = (this.rand.nextInt(4) == 0)? 1.2F : 1.0F;
				float criticalB = (this.rand.nextInt(8) == 0)? 1.1F : 0.9F;
				float addKANBAKU = this.isCarrier()? criticalA : criticalB;

				result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float)atackDamage * addKANBAKU);
				this.playSound((addKANBAKU > 1.0F)? SoundEvents_CM.KK_ATACK2 : SoundEvents_CM.KK_ATACK, 2.0F, 1.0F);
			}
		}
	}

	protected ItemStack getItemStack() {
		return this.projectile.copy();
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		compound.setDouble("damage", this.baseDamage);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		if (compound.hasKey("damage", 99)) {
			this.baseDamage = compound.getDouble("damage"); }
	}

	public void setBaseDamage(double damage) {
		this.baseDamage = damage;
	}
	
	public double getBaseDamage() {
		return this.baseDamage;
	}
}
