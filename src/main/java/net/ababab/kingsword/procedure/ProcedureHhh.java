package net.ababab.kingsword.procedure;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;

import net.ababab.kingsword.KingswordModVariables;
import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;
import java.util.HashMap;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureHhh extends ElementsKingswordMod.ModElement {
	public ProcedureHhh(ElementsKingswordMod instance) {
		super(instance, 487);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure Hhh!");
			return;
		}
		World world = (World) dependencies.get("world");
		if (((KingswordModVariables.MapVariables.get(world).lcdiao) == (true))) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("world", world);
				ProcedureQcw.executeProcedure($_dependencies);
			}
		}
	}

	@Override
	public void init(FMLInitializationEvent event) {
		this.executeProcedure(new java.util.HashMap<>());
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(this);
	}
}
