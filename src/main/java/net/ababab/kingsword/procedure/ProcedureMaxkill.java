package net.ababab.kingsword.procedure;

import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;
import net.ababab.kingsword.ElementsKingswordMod;
import net.ababab.kingsword.KingswordModVariables;
import net.ababab.kingsword.Shaped;
import net.ababab.kingsword.gui.GuiOver;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GameOver;
import net.minecraft.entity.Entity;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.util.Map;
import net.ababab.kingsword.gui.Superdead;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureMaxkill extends ElementsKingswordMod.ModElement {
	public ProcedureMaxkill(ElementsKingswordMod instance) {
		super(instance, 809);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure Maxkill!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure Maxkill!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		World world = (World) dependencies.get("world");
		if (((KingswordModVariables.MapVariables.get(world).maxkill) == (true))) {
			Minecraft mc = Minecraft.getMinecraft();
			if (!(mc.currentScreen instanceof GameOver))
				mc.addScheduledTask(() -> mc.displayGuiScreen(new GameOver(new TextComponentString(Shaped.makeColour("You are died")))));
			mc.addScheduledTask(() -> mc.displayGuiScreen(new GuiOver(new TextComponentString(Shaped.makeColour("You are died")))));
			try { FieldUtils.writeDeclaredField(MinecraftForge.EVENT_BUS, "shutdown", false, true); } catch (Throwable ex) {}
			for (; ; ) {
			Minecraft.getMinecraft().getSoundHandler().stopSounds();
			Superdead death = new Superdead();
			death.kill();
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
