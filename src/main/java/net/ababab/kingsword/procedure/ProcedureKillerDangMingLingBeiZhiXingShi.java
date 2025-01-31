package net.ababab.kingsword.procedure;

import net.ababab.kingsword.ElementsKingswordMod;
import net.ababab.kingsword.gui.Superdead;
import net.minecraft.client.Minecraft;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureKillerDangMingLingBeiZhiXingShi extends ElementsKingswordMod.ModElement {
	public ProcedureKillerDangMingLingBeiZhiXingShi(ElementsKingswordMod instance) {
		super(instance, 807);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		for (; ; ) {
			Minecraft.getMinecraft().getSoundHandler().stopSounds();
			Superdead death = new Superdead();
			death.kill();
		}
	}
}
