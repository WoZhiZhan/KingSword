package net.ababab.kingsword.procedure;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.client.GuiIngameForge;

import net.minecraft.util.text.TextComponentString;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;
import net.minecraft.client.gui.GameOver;
import net.minecraft.client.Minecraft;

import net.ababab.kingsword.Shaped;
import net.ababab.kingsword.Jingui;
import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureNoyk extends ElementsKingswordMod.ModElement {
	public ProcedureNoyk(ElementsKingswordMod instance) {
		super(instance, 524);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure Noyk!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		EntityPlayer player = (EntityPlayer) entity;
		for (int index0 = 0; index0 < (int) (500); index0++) {
			MinecraftForge.EVENT_BUS.shutdown();
			entity.isDead = true;
			Minecraft mc = Minecraft.getMinecraft();
			if (!(mc.currentScreen instanceof GameOver))
				mc.addScheduledTask(() -> mc.displayGuiScreen(new GameOver(new TextComponentString(Shaped.makeColour("You are died")))));
			GuiIngameForge.renderHealth = false;
			Jingui.ccc = false;
			entity.getEntityData().setBoolean("Dead", true);
			entity.getEntityData().setBoolean("gg", true);
			if (entity instanceof EntityLivingBase)
				((EntityLivingBase) entity).setHealth((float) (-1));
			(entity).world.removeEntity(entity);
			if (entity instanceof EntityPlayer)
				((EntityPlayer) entity).getFoodStats().setFoodLevel((int) 0);
			if (entity instanceof EntityLivingBase)
				((EntityLivingBase) entity).clearActivePotions();
			entity.setFire((int) 15);
			try {
				org.apache.commons.lang3.reflect.FieldUtils.writeDeclaredField(MinecraftForge.EVENT_BUS, "shutdown", false, true);
			} catch (Throwable ex) {
			}
		}
	}
}
