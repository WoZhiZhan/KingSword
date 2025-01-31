package net.ababab.kingsword.procedure;

import net.minecraft.util.DamageSource;
import net.minecraft.entity.Entity;

import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureFiveSwordDangShiTiBeiGongJuJiZhongShi extends ElementsKingswordMod.ModElement {
	public ProcedureFiveSwordDangShiTiBeiGongJuJiZhongShi(ElementsKingswordMod instance) {
		super(instance, 782);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure FiveSwordDangShiTiBeiGongJuJiZhongShi!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		entity.motionX = 0;
		entity.motionY = 1.5;
		entity.motionZ = 0;
		entity.attackEntityFrom(DamageSource.OUT_OF_WORLD, (float) 50);
	}
}
