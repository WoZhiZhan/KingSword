package net.ababab.kingsword.procedure;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;

import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureZuizhongkingArmorTouKuiShiJianMeiYouXiKe extends ElementsKingswordMod.ModElement {
	public ProcedureZuizhongkingArmorTouKuiShiJianMeiYouXiKe(ElementsKingswordMod instance) {
		super(instance, 547);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure ZuizhongkingArmorTouKuiShiJianMeiYouXiKe!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		entity.getEntityData().setBoolean("wawa", (true));
		if (entity instanceof EntityLivingBase)
			((EntityLivingBase) entity).setHealth((float) 1024);
		if (entity instanceof EntityPlayer) {
			((EntityPlayer) entity).capabilities.disableDamage = (true);
			((EntityPlayer) entity).sendPlayerAbilities();
		}
		(entity).extinguish();
		if (entity instanceof EntityLivingBase)
			((EntityLivingBase) entity).clearActivePotions();
	}
}
