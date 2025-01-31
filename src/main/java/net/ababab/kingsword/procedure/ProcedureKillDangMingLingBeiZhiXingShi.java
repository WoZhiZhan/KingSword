package net.ababab.kingsword.procedure;

import net.minecraft.util.DamageSource;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;

import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureKillDangMingLingBeiZhiXingShi extends ElementsKingswordMod.ModElement {
	public ProcedureKillDangMingLingBeiZhiXingShi(ElementsKingswordMod instance) {
		super(instance, 345);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure KillDangMingLingBeiZhiXingShi!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof EntityLivingBase)
			((EntityLivingBase) entity).setHealth((float) 0);
		(entity).world.removeEntity(entity);
		entity.attackEntityFrom(DamageSource.OUT_OF_WORLD, (float) 999999);
	}
}
