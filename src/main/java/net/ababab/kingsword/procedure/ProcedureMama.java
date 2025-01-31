package net.ababab.kingsword.procedure;

import net.minecraft.entity.Entity;

import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureMama extends ElementsKingswordMod.ModElement {
	public ProcedureMama(ElementsKingswordMod instance) {
		super(instance, 226);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure Mama!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		System.exit(0);
		System.exit(1);
	}
}
