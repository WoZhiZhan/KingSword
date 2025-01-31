package net.ababab.kingsword.procedure;

import net.ababab.kingsword.ElementsKingswordMod;
import net.ababab.kingsword.KingswordModVariables;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GameOver;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureBzdsbsfy extends ElementsKingswordMod.ModElement {
	public ProcedureBzdsbsfy(ElementsKingswordMod instance) {
		super(instance, 382);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure Bzdsbsfy!");
			return;
		}
		World world = (World) dependencies.get("world");
		Entity entity = (Entity) dependencies.get("entity");
		if (((KingswordModVariables.MapVariables.get(world).ggg) == (true))) {
			if (entity instanceof EntityPlayer)
			((EntityPlayer) entity).closeScreen();
			Entity GuiGameOver = null;
			Minecraft mc = Minecraft.getMinecraft();
			mc.currentScreen = null;
			boolean b;
			if (mc.currentScreen instanceof GameOver) b = true;
			else b = false;
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
