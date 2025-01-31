package net.ababab.kingsword.procedure;

import net.minecraft.entity.Entity;

import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureKhdDangMingLingBeiZhiXingShi extends ElementsKingswordMod.ModElement {
	public ProcedureKhdDangMingLingBeiZhiXingShi(ElementsKingswordMod instance) {
		super(instance, 797);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure KhdDangMingLingBeiZhiXingShi!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		(entity).extinguish();
		entity.setEntityId(2);
	}
}
