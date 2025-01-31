package net.ababab.kingsword.procedure;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.client.GuiIngameForge;

import net.minecraft.world.World;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;
import net.minecraft.client.Minecraft;

import net.ababab.kingsword.KingswordModVariables;
import net.ababab.kingsword.Jingui;
import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureKingbeibao extends ElementsKingswordMod.ModElement {
	public ProcedureKingbeibao(ElementsKingswordMod instance) {
		super(instance, 543);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure Kingbeibao!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure Kingbeibao!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		World world = (World) dependencies.get("world");
		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayer player = (EntityPlayer) entity;
		KingswordModVariables.MapVariables.get(world).qiangdafy = (boolean) (true);
		KingswordModVariables.MapVariables.get(world).syncData(world);
		if (entity instanceof EntityLivingBase)
			((EntityLivingBase) entity).clearActivePotions();
		(entity).extinguish();
		if (entity instanceof EntityLivingBase)
			((EntityLivingBase) entity).setHealth((float) 1024);
		if (entity instanceof EntityPlayer)
			((EntityPlayer) entity).getFoodStats().setFoodLevel((int) 20);
		try {
			org.apache.commons.lang3.reflect.FieldUtils.writeDeclaredField(MinecraftForge.EVENT_BUS, "shutdown", false, true);
		} catch (Throwable ex) {
		}
		Jingui.ccc = true;
		player.isDead = false;
		player.deathTime = 0;
		player.ticksExisted = 0;
		GuiIngameForge.renderHealth = true;
		player.updateBlocked = false;
		GuiIngameForge.renderExperiance = false;
		player.setHealth(40);
	}
}
