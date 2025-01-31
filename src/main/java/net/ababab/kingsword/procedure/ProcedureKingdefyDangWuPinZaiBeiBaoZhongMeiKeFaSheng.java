package net.ababab.kingsword.procedure;

import net.minecraft.util.EnumHand;
import net.minecraft.potion.PotionEffect;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;

import net.ababab.kingsword.potion.PotionKingdfyys;
import net.ababab.kingsword.item.ItemKingdesword;
import net.ababab.kingsword.item.ItemKingdefy;
import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureKingdefyDangWuPinZaiBeiBaoZhongMeiKeFaSheng extends ElementsKingswordMod.ModElement {
	public ProcedureKingdefyDangWuPinZaiBeiBaoZhongMeiKeFaSheng(ElementsKingswordMod instance) {
		super(instance, 19);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure KingdefyDangWuPinZaiBeiBaoZhongMeiKeFaSheng!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof EntityLivingBase)
			((EntityLivingBase) entity).setHealth((float) 1024);
		((EntityLivingBase) entity).getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40);
		if (entity instanceof EntityPlayer)
			((EntityPlayer) entity).addExperience((int) 5);
		if (entity instanceof EntityPlayer) {
			((EntityPlayer) entity).capabilities.disableDamage = (true);
			((EntityPlayer) entity).sendPlayerAbilities();
		}
		((EntityLivingBase) entity).setHealth(40);
		if (entity instanceof EntityPlayer)
			((EntityPlayer) entity).getFoodStats().setFoodLevel((int) 20);
		if (entity instanceof EntityLivingBase)
			((EntityLivingBase) entity).addPotionEffect(new PotionEffect(PotionKingdfyys.potion, (int) 99999, (int) 255));
		if (((entity instanceof EntityPlayer)
				? ((EntityPlayer) entity).inventory.hasItemStack(new ItemStack(ItemKingdesword.block, (int) (1)))
				: false)) {
			(entity).extinguish();
		} else {
			if (entity instanceof EntityLivingBase) {
				ItemStack _setstack = new ItemStack(ItemKingdesword.block, (int) (1));
				_setstack.setCount(1);
				((EntityLivingBase) entity).setHeldItem(EnumHand.MAIN_HAND, _setstack);
				if (entity instanceof EntityPlayerMP)
					((EntityPlayerMP) entity).inventory.markDirty();
			}
		}
		if (((entity instanceof EntityPlayer)
				? ((EntityPlayer) entity).inventory.hasItemStack(new ItemStack(ItemKingdefy.block, (int) (1)))
				: false)) {
			(entity).extinguish();
		} else {
			if (entity instanceof EntityLivingBase) {
				ItemStack _setstack = new ItemStack(ItemKingdefy.block, (int) (1));
				_setstack.setCount(1);
				((EntityLivingBase) entity).setHeldItem(EnumHand.MAIN_HAND, _setstack);
				if (entity instanceof EntityPlayerMP)
					((EntityPlayerMP) entity).inventory.markDirty();
			}
		}
	}
}
