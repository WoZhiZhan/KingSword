package net.ababab.kingsword.procedure;

import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;

import net.ababab.kingsword.KingswordModVariables;
import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureSssw extends ElementsKingswordMod.ModElement {
	public ProcedureSssw(ElementsKingswordMod instance) {
		super(instance, 269);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure Sssw!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure Sssw!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		World world = (World) dependencies.get("world");
		if (((KingswordModVariables.MapVariables.get(world).hhhhhhhhhhh) == (true))) {
			if (((entity.height) == 875)) {
				for (int index0 = 0; index0 < (int) (200); index0++) {
					((EntityLivingBase) entity).getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(0);
					(entity).world.removeEntity(entity);
					((EntityLivingBase) entity).getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(0);
					(entity).world.removeEntity(entity);
					if (entity instanceof EntityLivingBase)
						((EntityLivingBase) entity).setHealth((float) 0);
					if (entity instanceof EntityLivingBase)
						((EntityLivingBase) entity).setHealth((float) 0);
					if (entity instanceof EntityLivingBase)
						((EntityLivingBase) entity).setHealth((float) 0);
					if (entity instanceof EntityLivingBase)
						((EntityLivingBase) entity).setHealth((float) 0);
					if (entity instanceof EntityLivingBase)
						((EntityLivingBase) entity).setHealth((float) 0);
					if (entity instanceof EntityLivingBase)
						((EntityLivingBase) entity).clearActivePotions();
				}
			}
		}
	}

	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			Entity entity = event.player;
			World world = entity.world;
			int i = (int) entity.posX;
			int j = (int) entity.posY;
			int k = (int) entity.posZ;
			java.util.HashMap<String, Object> dependencies = new java.util.HashMap<>();
			dependencies.put("x", i);
			dependencies.put("y", j);
			dependencies.put("z", k);
			dependencies.put("world", world);
			dependencies.put("entity", entity);
			dependencies.put("event", event);
			this.executeProcedure(dependencies);
		}
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(this);
	}
}
