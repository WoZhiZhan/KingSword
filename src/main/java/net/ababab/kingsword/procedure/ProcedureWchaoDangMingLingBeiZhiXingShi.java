package net.ababab.kingsword.procedure;

import net.minecraft.world.World;

import net.ababab.kingsword.KingswordModVariables;
import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureWchaoDangMingLingBeiZhiXingShi extends ElementsKingswordMod.ModElement {
	public ProcedureWchaoDangMingLingBeiZhiXingShi(ElementsKingswordMod instance) {
		super(instance, 246);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure WchaoDangMingLingBeiZhiXingShi!");
			return;
		}
		World world = (World) dependencies.get("world");
		KingswordModVariables.MapVariables.get(world).wochao = (boolean) (true);
		KingswordModVariables.MapVariables.get(world).syncData(world);
	}
}
