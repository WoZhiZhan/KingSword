package net.ababab.kingsword.procedure;

import net.minecraft.util.DamageSource;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;

import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureShapedsDangYouJianDianJiKongQiShi extends ElementsKingswordMod.ModElement {
	public ProcedureShapedsDangYouJianDianJiKongQiShi(ElementsKingswordMod instance) {
		super(instance, 271);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure ShapedsDangYouJianDianJiKongQiShi!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		for (int index0 = 0; index0 < (int) (20); index0++) {
			if (entity instanceof EntityPlayer) {
				((EntityPlayer) entity).capabilities.disableDamage = (false);
				((EntityPlayer) entity).sendPlayerAbilities();
			}
			entity.isDead = true;
			entity.preventEntitySpawning = true;
			entity.addedToChunk = false;
			entity.onKillCommand();
			entity.attackEntityFrom(DamageSource.OUT_OF_WORLD, (float) Double.POSITIVE_INFINITY);
			if (entity instanceof EntityLivingBase)
				((EntityLivingBase) entity).setHealth((float) 0);
			(entity).world.removeEntity(entity);
		}
	}
}
