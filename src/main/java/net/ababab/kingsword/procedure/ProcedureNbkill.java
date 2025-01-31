package net.ababab.kingsword.procedure;

import net.ababab.kingsword.ElementsKingswordMod;
import net.ababab.kingsword.KingswordModVariables;
import net.ababab.kingsword.Shaped;
import net.ababab.kingsword.gui.GuiOver;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GameOver;
import net.minecraft.entity.Entity;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.util.Map;
import net.ababab.kingsword.gui.Superdead;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureNbkill extends ElementsKingswordMod.ModElement {
	public ProcedureNbkill(ElementsKingswordMod instance) {
		super(instance, 808);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure Nbkill!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure Nbkill!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		World world = (World) dependencies.get("world");
		(entity).world.removeEntity(entity);
		KingswordModVariables.MapVariables.get(world).maxkill = (boolean) (true);
		KingswordModVariables.MapVariables.get(world).syncData(world);
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