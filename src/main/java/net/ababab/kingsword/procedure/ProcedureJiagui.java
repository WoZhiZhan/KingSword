package net.ababab.kingsword.procedure;

import net.minecraft.world.World;

import net.ababab.kingsword.KingswordModVariables;
import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureJiagui extends ElementsKingswordMod.ModElement {
	public ProcedureJiagui(ElementsKingswordMod instance) {
		super(instance, 811);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure Jiagui!");
			return false;
		}
		World world = (World) dependencies.get("world");
		if (((KingswordModVariables.MapVariables.get(world).jiagui) == (true))) {
			return (true);
		}
		return (false);
	}
}
