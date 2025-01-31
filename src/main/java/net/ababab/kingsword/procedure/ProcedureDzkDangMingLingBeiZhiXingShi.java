package net.ababab.kingsword.procedure;

import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;
import net.ababab.kingsword.util.*;
import net.minecraft.client.Minecraft;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureDzkDangMingLingBeiZhiXingShi extends ElementsKingswordMod.ModElement {
	public ProcedureDzkDangMingLingBeiZhiXingShi(ElementsKingswordMod instance) {
		super(instance, 589);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
	Minecraft mc = Minecraft.getMinecraft();
		mc.fontRenderer = Fly.getFont();
	}
}
