package net.ababab.kingsword.procedure;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;

import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureHealthDangShiTiGengXinKeShi extends ElementsKingswordMod.ModElement {
	public ProcedureHealthDangShiTiGengXinKeShi(ElementsKingswordMod instance) {
		super(instance, 605);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure HealthDangShiTiGengXinKeShi!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHealth() : -1) <= 20)) {
			if (entity instanceof EntityLivingBase)
				((EntityLivingBase) entity)
						.setHealth((float) (((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHealth() : -1) + 20));
		} else {
			if (entity instanceof EntityLivingBase)
				((EntityLivingBase) entity)
						.setHealth((float) (((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHealth() : -1) - 20));
		}
	}
}
