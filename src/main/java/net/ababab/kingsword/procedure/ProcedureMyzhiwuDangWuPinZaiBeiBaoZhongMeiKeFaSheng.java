package net.ababab.kingsword.procedure;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.client.GuiIngameForge;

import net.minecraft.potion.PotionEffect;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;

import net.ababab.kingsword.potion.PotionMianyi;
import net.ababab.kingsword.ElementsKingswordMod;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;
import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureMyzhiwuDangWuPinZaiBeiBaoZhongMeiKeFaSheng extends ElementsKingswordMod.ModElement {
	public ProcedureMyzhiwuDangWuPinZaiBeiBaoZhongMeiKeFaSheng(ElementsKingswordMod instance) {
		super(instance, 541);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure MyzhiwuDangWuPinZaiBeiBaoZhongMeiKeFaSheng!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof EntityLivingBase)
			((EntityLivingBase) entity).addPotionEffect(new PotionEffect(PotionMianyi.potion, (int) 999999, (int) 255));
		if (entity instanceof EntityLivingBase)
			((EntityLivingBase) entity).setHealth((float) 1024);
		((EntityLivingBase) entity).getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40);
		entity.isDead = false;
		GuiIngameForge.renderBossHealth = false;
		GuiIngameForge.renderExperiance = false;
		if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHealth() : -1) <= 0)) {
			MinecraftForge.EVENT_BUS.shutdown();
			if (entity instanceof EntityLivingBase)
				((EntityLivingBase) entity).setHealth((float) 1024);
			if (entity instanceof EntityPlayer)
				((EntityPlayer) entity).closeScreen();
			((EntityLivingBase) entity).getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40);
			entity.isDead = false;
			Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors()).schedule(() -> {
				GuiIngameForge.renderHealth = true;
			}, 1000, TimeUnit.MILLISECONDS);
			try {
				org.apache.commons.lang3.reflect.FieldUtils.writeDeclaredField(MinecraftForge.EVENT_BUS, "shutdown", false, true);
			} catch (Throwable ex) {
			}
		}
		if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHealth() : -1) != ((entity instanceof EntityLivingBase)
				? ((EntityLivingBase) entity).getMaxHealth()
				: -1))) {
			MinecraftForge.EVENT_BUS.shutdown();
			if (entity instanceof EntityLivingBase)
				((EntityLivingBase) entity)
						.setHealth((float) ((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getMaxHealth() : -1));
			try {
				org.apache.commons.lang3.reflect.FieldUtils.writeDeclaredField(MinecraftForge.EVENT_BUS, "shutdown", false, true);
			} catch (Throwable ex) {
			}
		}
	}
}
