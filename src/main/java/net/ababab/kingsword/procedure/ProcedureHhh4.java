package net.ababab.kingsword.procedure;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.entity.Entity;

import net.ababab.kingsword.KingswordModVariables;
import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureHhh4 extends ElementsKingswordMod.ModElement {
	public ProcedureHhh4(ElementsKingswordMod instance) {
		super(instance, 252);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure M5!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure M5!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		World world = (World) dependencies.get("world");
		if (((KingswordModVariables.MapVariables.get(world).hhhhhhhhhhh) == (true))) {
			for (int index0 = 0; index0 < (int) (10); index0++) {
				MinecraftForge.EVENT_BUS.shutdown();
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void init(FMLInitializationEvent event) {
		this.executeProcedure(new java.util.HashMap<>());
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(this);
	}
}
