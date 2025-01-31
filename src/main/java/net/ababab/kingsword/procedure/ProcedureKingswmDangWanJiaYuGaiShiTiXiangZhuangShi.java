package net.ababab.kingsword.procedure;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;

import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureKingswmDangWanJiaYuGaiShiTiXiangZhuangShi extends ElementsKingswordMod.ModElement {
	public ProcedureKingswmDangWanJiaYuGaiShiTiXiangZhuangShi(ElementsKingswordMod instance) {
		super(instance, 444);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure KingswmDangWanJiaYuGaiShiTiXiangZhuangShi!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof EntityLivingBase)
			((EntityLivingBase) entity).setHealth((float) 0);
		(entity).world.removeEntity(entity);
	}
}
