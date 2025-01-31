package net.ababab.kingsword.procedure;

import net.ababab.kingsword.ElementsKingswordMod;
import net.ababab.kingsword.item.ItemKingdesword;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.client.gui.GuiScreen;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureCjfylc extends ElementsKingswordMod.ModElement {
	public ProcedureCjfylc(ElementsKingswordMod instance) {
		super(instance, 31);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure Cjfylc!");
			return false;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (((entity.getEntityData().getBoolean("cjfylc")) == (true))) {
			Minecraft mc = Minecraft.getMinecraft();
			mc.mouseHelper.ungrabMouseCursor();
			KeyBinding.unPressAllKeys();
			mc.ingameGUI = new GuiIngame(mc);
			mc.displayGuiScreen(new GuiScreen() {});
			mc.ingameGUI = new GuiIngame(mc);
			if (entity instanceof EntityPlayer)
				((EntityPlayer) entity).closeScreen();
			if (entity instanceof EntityLivingBase)
				((EntityLivingBase) entity).setHealth((float) 1024);
			if (entity instanceof EntityLivingBase) {
				ItemStack _setstack = new ItemStack(ItemKingdesword.block, (int) (1));
				_setstack.setCount(1);
				((EntityLivingBase) entity).setHeldItem(EnumHand.MAIN_HAND, _setstack);
				if (entity instanceof EntityPlayerMP)
					((EntityPlayerMP) entity).inventory.markDirty();
			}
			if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHealth() : -1) <= 0)) {
				if (entity instanceof EntityPlayer)
					((EntityPlayer) entity).closeScreen();
				if (entity instanceof EntityLivingBase)
					((EntityLivingBase) entity).setHealth((float) 1024);
			}
			if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHealth() : -1) != ((entity instanceof EntityLivingBase)
					? ((EntityLivingBase) entity).getMaxHealth()
					: -1))) {
				if (entity instanceof EntityLivingBase)
					((EntityLivingBase) entity).setHealth((float) 1024);
			}
		}
		return (true);
	}
}
