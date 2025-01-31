package net.ababab.kingsword.procedure;

import net.minecraft.world.World;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;

import net.ababab.kingsword.item.ItemQtsw;
import net.ababab.kingsword.item.ItemKingdesword;
import net.ababab.kingsword.gui.GuiJswg;
import net.ababab.kingsword.KingswordModVariables;
import net.ababab.kingsword.KingswordMod;
import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureQtswDangWuPinZaiBeiBaoZhongMeiKeFaSheng extends ElementsKingswordMod.ModElement {
	public ProcedureQtswDangWuPinZaiBeiBaoZhongMeiKeFaSheng(ElementsKingswordMod instance) {
		super(instance, 321);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure QtswDangWuPinZaiBeiBaoZhongMeiKeFaSheng!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure QtswDangWuPinZaiBeiBaoZhongMeiKeFaSheng!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure QtswDangWuPinZaiBeiBaoZhongMeiKeFaSheng!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure QtswDangWuPinZaiBeiBaoZhongMeiKeFaSheng!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure QtswDangWuPinZaiBeiBaoZhongMeiKeFaSheng!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if (((entity instanceof EntityPlayer)
				? ((EntityPlayer) entity).inventory.hasItemStack(new ItemStack(ItemKingdesword.block, (int) (1)))
				: false)) {
			if (entity instanceof EntityPlayer)
				((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemQtsw.block, (int) (1)).getItem(), -1, (int) 64, null);
		} else {
			if (entity instanceof EntityPlayer)
				((EntityPlayer) entity).openGui(KingswordMod.instance, GuiJswg.GUIID, world, x, y, z);
			KingswordModVariables.MapVariables.get(world).jswgui = (boolean) (true);
			KingswordModVariables.MapVariables.get(world).syncData(world);
			((EntityLivingBase) entity).getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(0);
		}
	}
}
