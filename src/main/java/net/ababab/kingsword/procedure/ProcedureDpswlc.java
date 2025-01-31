package net.ababab.kingsword.procedure;

import net.minecraft.util.DamageSource;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;

import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureDpswlc extends ElementsKingswordMod.ModElement {
	public ProcedureDpswlc(ElementsKingswordMod instance) {
		super(instance, 37);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure Dpswlc!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		for (int index0 = 0; index0 < (int) (20); index0++) {
			entity.attackEntityFrom(DamageSource.OUT_OF_WORLD, (float) 9999999);
			(entity).world.removeEntity(entity);
			if (entity instanceof EntityLivingBase)
				((EntityLivingBase) entity).setHealth((float) 0);
			if (entity instanceof EntityPlayer)
				((EntityPlayer) entity).getFoodStats().setFoodLevel((int) 0);
		}
		if (entity instanceof EntityPlayer) {
			((EntityPlayer) entity).capabilities.allowFlying = (false);
			((EntityPlayer) entity).sendPlayerAbilities();
		}
	}
}
