package net.ababab.kingsword.procedure;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;
import net.minecraft.command.ICommandSender;

import net.ababab.kingsword.entity.EntityKing;
import net.ababab.kingsword.KingswordModVariables;
import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;
import java.util.List;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureCs3 extends ElementsKingswordMod.ModElement {
	public ProcedureCs3(ElementsKingswordMod instance) {
		super(instance, 187);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure Cs3!");
			return;
		}
		World world = (World) dependencies.get("world");
		List<Entity> list = world.getLoadedEntityList();
		for (Entity entityiterator : list) {
			if (((KingswordModVariables.MapVariables.get(world).wuxsc) == (true))) {
				if (((entityiterator) instanceof EntityKing.EntityCustom)) {
					if ((entityiterator) instanceof EntityLivingBase)
						((EntityLivingBase) (entityiterator)).setHealth((float) 1024);
				} else {
					if (dependencies.get("event") != null) {
						Object _obj = dependencies.get("event");
						if (_obj instanceof net.minecraftforge.fml.common.eventhandler.Event) {
							net.minecraftforge.fml.common.eventhandler.Event _evt = (net.minecraftforge.fml.common.eventhandler.Event) _obj;
							if (_evt.isCancelable())
								_evt.setCanceled(true);
						}
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void onCommand(CommandEvent event) {
		ICommandSender sender = event.getSender();
		Entity entity = sender.getCommandSenderEntity();
		if (entity != null) {
			int i = (int) sender.getPosition().getX();
			int j = (int) sender.getPosition().getY();
			int k = (int) sender.getPosition().getZ();
			String command = event.getCommand().getName();
			java.util.HashMap<String, Object> dependencies = new java.util.HashMap<>();
			dependencies.put("x", i);
			dependencies.put("y", j);
			dependencies.put("z", k);
			dependencies.put("world", entity.world);
			dependencies.put("entity", entity);
			dependencies.put("command", command);
			dependencies.put("event", event);
			this.executeProcedure(dependencies);
		}
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(this);
	}
}
