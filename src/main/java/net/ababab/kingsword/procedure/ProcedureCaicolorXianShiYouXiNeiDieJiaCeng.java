package net.ababab.kingsword.procedure;

import net.minecraft.world.World;

import net.ababab.kingsword.KingswordModVariables;
import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureCaicolorXianShiYouXiNeiDieJiaCeng extends ElementsKingswordMod.ModElement {
	public ProcedureCaicolorXianShiYouXiNeiDieJiaCeng(ElementsKingswordMod instance) {
		super(instance, 500);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure CaicolorXianShiYouXiNeiDieJiaCeng!");
			return false;
		}
		World world = (World) dependencies.get("world");
		if (((KingswordModVariables.MapVariables.get(world).colorful) == (true))) {
			return (true);
		}
		return (false);
	}
}
