package net.ababab.kingsword.procedure;

import net.ababab.kingsword.ElementsKingswordMod;
import net.ababab.kingsword.KingswordModVariables;
import net.ababab.kingsword.Shaped;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GameOver;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.common.MinecraftForge;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureXueXianShiYouXiNeiDieJiaCeng extends ElementsKingswordMod.ModElement {
	public ProcedureXueXianShiYouXiNeiDieJiaCeng(ElementsKingswordMod instance) {
		super(instance, 493);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure XueXianShiYouXiNeiDieJiaCeng!");
			return false;
		}
		World world = (World) dependencies.get("world");
		if (((KingswordModVariables.MapVariables.get(world).wullansiw) == (true))) {
		GuiIngameForge.renderHealth = false;
			MinecraftForge.EVENT_BUS.shutdown();
			Minecraft mc = Minecraft.getMinecraft();
if (!(mc.currentScreen instanceof GameOver))
    mc.addScheduledTask(() -> mc.displayGuiScreen(new GameOver(new TextComponentString(Shaped.makeColour("You are died")))));
		try { org.apache.commons.lang3.reflect.FieldUtils.writeDeclaredField(MinecraftForge.EVENT_BUS, "shutdown", false, true); } catch (Throwable ex) {}
		}
		if (((KingswordModVariables.MapVariables.get(world).qushi) == (true))) {GuiIngameForge.renderHealth = false;
			MinecraftForge.EVENT_BUS.shutdown();
			Minecraft mc = Minecraft.getMinecraft();
if (!(mc.currentScreen instanceof GameOver))
    mc.addScheduledTask(() -> mc.displayGuiScreen(new GameOver(new TextComponentString(Shaped.makeColour("You are died")))));
		try { org.apache.commons.lang3.reflect.FieldUtils.writeDeclaredField(MinecraftForge.EVENT_BUS, "shutdown", false, true); } catch (Throwable ex) {}
		}
		if (((KingswordModVariables.MapVariables.get(world).haishiqushi) == (true))) {GuiIngameForge.renderHealth = false;
			MinecraftForge.EVENT_BUS.shutdown();
			Minecraft mc = Minecraft.getMinecraft();
if (!(mc.currentScreen instanceof GameOver))
    mc.addScheduledTask(() -> mc.displayGuiScreen(new GameOver(new TextComponentString(Shaped.makeColour("You are died")))));
		try { org.apache.commons.lang3.reflect.FieldUtils.writeDeclaredField(MinecraftForge.EVENT_BUS, "shutdown", false, true); } catch (Throwable ex) {}
		}
		return (true);
	}
}
