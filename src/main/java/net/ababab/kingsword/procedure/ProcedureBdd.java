package net.ababab.kingsword.procedure;

import net.minecraft.world.World;

import net.ababab.kingsword.KingswordModVariables;
import net.ababab.kingsword.ElementsKingswordMod;
import net.minecraftforge.common.MinecraftForge;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureBdd extends ElementsKingswordMod.ModElement {
	public ProcedureBdd(ElementsKingswordMod instance) {
		super(instance, 504);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure Bdd!");
			return;
		}
		World world = (World) dependencies.get("world");
		KingswordModVariables.MapVariables.get(world).shijian = (boolean) (true);
		KingswordModVariables.MapVariables.get(world).syncData(world);
		try { org.apache.commons.lang3.reflect.FieldUtils.writeDeclaredField(MinecraftForge.EVENT_BUS, "shutdown", false, true); } catch (Throwable ex) {}
	}
}
