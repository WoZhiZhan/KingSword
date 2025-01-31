package net.ababab.kingsword.procedure;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;

import net.ababab.kingsword.KingswordModVariables;
import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureSw3 extends ElementsKingswordMod.ModElement {
	public ProcedureSw3(ElementsKingswordMod instance) {
		super(instance, 300);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure Sw3!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure Sw3!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		World world = (World) dependencies.get("world");
		if (((KingswordModVariables.MapVariables.get(world).qushi) == (true))) {
			if (entity instanceof EntityLivingBase)
				((EntityLivingBase) entity).setHealth((float) 0);
			(entity).world.removeEntity(entity);
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
