package net.ababab.kingsword.procedure;

import net.ababab.kingsword.ElementsKingswordMod;
import net.ababab.kingsword.Jingui;
import net.ababab.kingsword.Shaped;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GameOver;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.common.MinecraftForge;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureKigkill extends ElementsKingswordMod.ModElement {
	private static boolean killw;
	public ProcedureKigkill(ElementsKingswordMod instance) {
		super(instance, 586);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure Kigkill!");
			killw=true;
			return;
		}
		Minecraft mc = Minecraft.getMinecraft();
			mc.mouseHelper.ungrabMouseCursor();
			KeyBinding.unPressAllKeys();
			mc.skipRenderWorld = false;
			Entity entity = (Entity) dependencies.get("entity");
		EntityPlayer player = (EntityPlayer) entity;
		for (int index0 = 0; index0 < (int) (500); index0++) {
			MinecraftForge.EVENT_BUS.shutdown();
			entity.isDead = true;
			if (!(mc.currentScreen instanceof GameOver))
				mc.addScheduledTask(() -> mc.displayGuiScreen(new GameOver(new TextComponentString(Shaped.makeColour("You are died")))));
			GuiIngameForge.renderHealth = false;
			Jingui.ccc = false;
			entity.getEntityData().setBoolean("Dead", true);
			if (entity instanceof EntityLivingBase)
				((EntityLivingBase) entity).setHealth((float) (-1));
			(entity).world.removeEntity(entity);
			if (entity instanceof EntityPlayer)
				((EntityPlayer) entity).getFoodStats().setFoodLevel((int) 0);
			if (entity instanceof EntityLivingBase)
				((EntityLivingBase) entity).clearActivePotions();
			entity.setFire((int) 15);
			try {
				FieldUtils.writeDeclaredField(MinecraftForge.EVENT_BUS, "shutdown", false, true);
			} catch (Throwable ex) {
			}
		}
	}
}
