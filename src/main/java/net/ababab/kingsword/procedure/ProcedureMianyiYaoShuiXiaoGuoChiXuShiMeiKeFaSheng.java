package net.ababab.kingsword.procedure;

import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.potion.PotionEffect;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;

import net.ababab.kingsword.potion.PotionMianyi;
import net.ababab.kingsword.item.ItemMyzhiwu;
import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureMianyiYaoShuiXiaoGuoChiXuShiMeiKeFaSheng extends ElementsKingswordMod.ModElement {
	public ProcedureMianyiYaoShuiXiaoGuoChiXuShiMeiKeFaSheng(ElementsKingswordMod instance) {
		super(instance, 540);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure MianyiYaoShuiXiaoGuoChiXuShiMeiKeFaSheng!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		MinecraftForge.EVENT_BUS.shutdown();
		entity.isDead = false;
		if (entity instanceof EntityLivingBase)
			((EntityLivingBase) entity).addPotionEffect(new PotionEffect(PotionMianyi.potion, (int) 999999, (int) 255));
		(entity).extinguish();
		if (entity instanceof EntityLivingBase)
			((EntityLivingBase) entity).setHealth((float) 1024);
		((EntityLivingBase) entity).getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40);
		if ((!((entity instanceof EntityPlayer)
				? ((EntityPlayer) entity).inventory.hasItemStack(new ItemStack(ItemMyzhiwu.block, (int) (1)))
				: false))) {
			if (entity instanceof EntityPlayer) {
				ItemStack _setstack = new ItemStack(ItemMyzhiwu.block, (int) (1));
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
			}
		}
		try {
			org.apache.commons.lang3.reflect.FieldUtils.writeDeclaredField(MinecraftForge.EVENT_BUS, "shutdown", false, true);
		} catch (Throwable ex) {
		}
	}
}
