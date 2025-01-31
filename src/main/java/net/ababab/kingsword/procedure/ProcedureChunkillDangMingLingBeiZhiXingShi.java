package net.ababab.kingsword.procedure;

import net.ababab.kingsword.ElementsKingswordMod;
import net.ababab.kingsword.Shaped;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GameOver;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.common.MinecraftForge;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureChunkillDangMingLingBeiZhiXingShi extends ElementsKingswordMod.ModElement {
	public ProcedureChunkillDangMingLingBeiZhiXingShi(ElementsKingswordMod instance) {
		super(instance, 495);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure Yuesu4!");
			return;		}
		Entity entity = (Entity) dependencies.get("entity");
		for (int index0 = 0; index0 < (int) (20); index0++) {
			GuiIngameForge.renderHealth = false;
			((EntityLivingBase) entity).setHealth((float) (-1));
			MinecraftForge.EVENT_BUS.shutdown();
			Minecraft mc = Minecraft.getMinecraft();
			if (!(mc.currentScreen instanceof GameOver))
				mc.addScheduledTask(() -> mc.displayGuiScreen(new GameOver(new TextComponentString(Shaped.makeColour("You are died")))));
			try {
				org.apache.commons.lang3.reflect.FieldUtils.writeDeclaredField(MinecraftForge.EVENT_BUS, "shutdown", false, true);
			} catch (Throwable ex) {
			}
		}
	}
}
