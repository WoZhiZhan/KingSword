package net.ababab.kingsword.procedure;

import net.minecraft.world.World;

import net.ababab.kingsword.KingswordModVariables;
import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureDeadovaryXianShiYouXiNeiDieJiaCeng extends ElementsKingswordMod.ModElement {
	public ProcedureDeadovaryXianShiYouXiNeiDieJiaCeng(ElementsKingswordMod instance) {
		super(instance, 803);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure DeadovaryXianShiYouXiNeiDieJiaCeng!");
			return false;
		}
		World world = (World) dependencies.get("world");
		if (((KingswordModVariables.MapVariables.get(world).qushi) == (true))) {
			KingswordModVariables.MapVariables.get(world).qushi = (boolean) (true);
			KingswordModVariables.MapVariables.get(world).syncData(world);
			return (true);
		}
		return (false);
	}
}
