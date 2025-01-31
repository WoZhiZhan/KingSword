package net.ababab.kingsword.procedure;

import net.ababab.kingsword.ElementsKingswordMod;
import net.ababab.kingsword.Shaped;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GameOver;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.text.TextComponentString;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureQushiwupingDangYouJianDianJiKongQiShi extends ElementsKingswordMod.ModElement {
	public ProcedureQushiwupingDangYouJianDianJiKongQiShi(ElementsKingswordMod instance) {
		super(instance, 447);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure QushiwupingDangYouJianDianJiKongQiShi!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof EntityLivingBase)
			((EntityLivingBase) entity).setHealth((float) 0);
		(entity).world.removeEntity(entity);
		if (entity instanceof EntityLivingBase)
			((EntityLivingBase) entity).clearActivePotions();
			Minecraft mc = Minecraft.getMinecraft();
			if (!(mc.currentScreen instanceof GameOver))
    mc.addScheduledTask(() -> mc.displayGuiScreen(new GameOver(new TextComponentString(Shaped.makeColour("You are died")))));
		}
	}