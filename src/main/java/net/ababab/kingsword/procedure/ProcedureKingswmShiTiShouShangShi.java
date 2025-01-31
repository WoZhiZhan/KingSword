package net.ababab.kingsword.procedure;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;

import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureKingswmShiTiShouShangShi extends ElementsKingswordMod.ModElement {
	public ProcedureKingswmShiTiShouShangShi(ElementsKingswordMod instance) {
		super(instance, 443);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure KingswmShiTiShouShangShi!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof EntityLivingBase)
			((EntityLivingBase) entity).setHealth((float) 100);
	}
}
