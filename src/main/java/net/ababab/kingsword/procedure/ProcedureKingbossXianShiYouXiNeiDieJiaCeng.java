package net.ababab.kingsword.procedure;

import net.minecraft.world.World;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.Minecraft;

import net.ababab.kingsword.KingswordModVariables;
import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureKingbossXianShiYouXiNeiDieJiaCeng extends ElementsKingswordMod.ModElement {
	public ProcedureKingbossXianShiYouXiNeiDieJiaCeng(ElementsKingswordMod instance) {
		super(instance, 460);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure KingbossXianShiYouXiNeiDieJiaCeng!");
			return false;
		}
		World world = (World) dependencies.get("world");
		if (((KingswordModVariables.MapVariables.get(world).boss) == (true))) {
			Minecraft mc = Minecraft.getMinecraft();
			mc.fontRenderer = new FontRenderer(mc.gameSettings, new ResourceLocation("textures/font/ascii.png"), mc.renderEngine, false);
			return (true);
		}
		return (false);
	}
}
