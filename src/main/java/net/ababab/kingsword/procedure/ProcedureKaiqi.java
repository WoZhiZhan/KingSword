package net.ababab.kingsword.procedure;

import net.minecraft.client.Minecraft;

import net.ababab.kingsword.util.Font;
import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureKaiqi extends ElementsKingswordMod.ModElement {
	public ProcedureKaiqi(ElementsKingswordMod instance) {
		super(instance, 535);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		Minecraft mc = Minecraft.getMinecraft();
		mc.fontRenderer = Font.getFont();
	}
}
