package net.ababab.kingsword.procedure;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;

import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureKingdezbArmorTouKuiShiJianMeiYouXiKe extends ElementsKingswordMod.ModElement {
	public ProcedureKingdezbArmorTouKuiShiJianMeiYouXiKe(ElementsKingswordMod instance) {
		super(instance, 12);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure KingdezbArmorTouKuiShiJianMeiYouXiKe!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		(entity).extinguish();
		if (entity instanceof EntityLivingBase)
			((EntityLivingBase) entity).setHealth((float) 1024);
		if (entity instanceof EntityPlayer) {
			((EntityPlayer) entity).capabilities.disableDamage = (true);
			((EntityPlayer) entity).sendPlayerAbilities();
		}
		((EntityLivingBase) entity).getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100);
		for (int index0 = 0; index0 < (int) (20); index0++) {
			entity.isAddedToWorld();
			entity.ticksExisted = 1;
			entity.preventEntitySpawning = false;
			entity.addedToChunk = true;
			entity.isDead = false;
			entity.setEntityId(63);
			entity.setInvisible(false);
			if (entity instanceof EntityLivingBase)
				((EntityLivingBase) entity).setHealth((float) 1024);
		}
	}
}
