package net.ababab.kingsword.procedure;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;

import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureOnDeathDangMingLingBeiZhiXingShi extends ElementsKingswordMod.ModElement {
	public ProcedureOnDeathDangMingLingBeiZhiXingShi(ElementsKingswordMod instance) {
		super(instance, 806);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure OnDeathDangMingLingBeiZhiXingShi!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		EntityPlayer player = (EntityPlayer) entity;
		(entity).extinguish();
		player.onDeathUpdate();
	}
}
