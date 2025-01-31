package net.ababab.kingsword.procedure;

import net.minecraft.entity.Entity;

import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureHanpizhijianDangShiTiBeiGongJuJiZhongShi extends ElementsKingswordMod.ModElement {
	public ProcedureHanpizhijianDangShiTiBeiGongJuJiZhongShi(ElementsKingswordMod instance) {
		super(instance, 213);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure HanpizhijianDangShiTiBeiGongJuJiZhongShi!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		(entity).world.removeEntity(entity);
	}
}
