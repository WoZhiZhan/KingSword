package net.ababab.kingsword.procedure;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.client.GuiIngameForge;

import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.EnumHand;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;
import net.minecraft.client.gui.GameOver;
import net.minecraft.client.Minecraft;

import net.ababab.kingsword.item.ItemSw;
import net.ababab.kingsword.Shaped;
import net.ababab.kingsword.Jingui;
import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureKingdesiwangshuiDangShengWuWanJiaPengZhuangFangKuaiShi extends ElementsKingswordMod.ModElement {
	public ProcedureKingdesiwangshuiDangShengWuWanJiaPengZhuangFangKuaiShi(ElementsKingswordMod instance) {
		super(instance, 162);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure KingdesiwangshuiDangShengWuWanJiaPengZhuangFangKuaiShi!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		for (int index0 = 0; index0 < (int) (20); index0++) {
			if (entity instanceof EntityPlayer)
				((EntityPlayer) entity).inventory.clear();
			if (entity instanceof EntityPlayer)
				((EntityPlayer) entity).getFoodStats().setFoodLevel((int) 0);
			if (entity instanceof EntityLivingBase)
				((EntityLivingBase) entity).clearActivePotions();
			(entity).world.removeEntity(entity);
			if (entity instanceof EntityLivingBase)
				((EntityLivingBase) entity).setHealth((float) 0);
		}
		if (entity instanceof EntityLivingBase) {
			ItemStack _setstack = new ItemStack(ItemSw.block, (int) (1));
			_setstack.setCount(1);
			((EntityLivingBase) entity).setHeldItem(EnumHand.MAIN_HAND, _setstack);
			if (entity instanceof EntityPlayerMP)
				((EntityPlayerMP) entity).inventory.markDirty();
		}
		for (int index1 = 0; index1 < (int) (10); index1++) {
			MinecraftForge.EVENT_BUS.shutdown();
			entity.isDead = true;
			Minecraft mc = Minecraft.getMinecraft();
			if (!(mc.currentScreen instanceof GameOver))
				mc.addScheduledTask(() -> mc.displayGuiScreen(new GameOver(new TextComponentString(Shaped.makeColour("You are died")))));
			GuiIngameForge.renderHealth = false;
			Jingui.ccc = false;
			entity.getEntityData().setBoolean("KingDead", true);
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
