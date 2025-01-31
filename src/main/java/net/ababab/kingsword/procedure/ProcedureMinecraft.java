package net.ababab.kingsword.procedure;

import net.minecraft.world.World;
import net.minecraft.entity.Entity;

import net.ababab.kingsword.KingswordModVariables;
import net.ababab.kingsword.ElementsKingswordMod;
import net.minecraftforge.common.MinecraftForge;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureMinecraft extends ElementsKingswordMod.ModElement {
	public ProcedureMinecraft(ElementsKingswordMod instance) {
		super(instance, 242);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure Minecraft!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure Minecraft!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		World world = (World) dependencies.get("world");
		if (((KingswordModVariables.MapVariables.get(world).zhe) == (true))) {
			MinecraftForge.EVENT_BUS.shutdown();
		}
	}
}
