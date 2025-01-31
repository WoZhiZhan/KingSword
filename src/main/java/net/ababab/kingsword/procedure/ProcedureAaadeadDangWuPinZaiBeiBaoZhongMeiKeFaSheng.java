package net.ababab.kingsword.procedure;

import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.world.World;
import net.minecraft.util.EnumHand;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;

import net.ababab.kingsword.item.ItemAaadead;
import net.ababab.kingsword.KingswordModVariables;
import net.ababab.kingsword.ElementsKingswordMod;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;
import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureAaadeadDangWuPinZaiBeiBaoZhongMeiKeFaSheng extends ElementsKingswordMod.ModElement {
	public ProcedureAaadeadDangWuPinZaiBeiBaoZhongMeiKeFaSheng(ElementsKingswordMod instance) {
		super(instance, 494);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure AaadeadDangWuPinZaiBeiBaoZhongMeiKeFaSheng!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure AaadeadDangWuPinZaiBeiBaoZhongMeiKeFaSheng!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		World world = (World) dependencies.get("world");
		KingswordModVariables.MapVariables.get(world).haishiqushi = (boolean) (true);
		KingswordModVariables.MapVariables.get(world).syncData(world);
		if (entity instanceof EntityLivingBase) {
			ItemStack _setstack = new ItemStack(ItemAaadead.block, (int) (1));
			_setstack.setCount(1);
			((EntityLivingBase) entity).setHeldItem(EnumHand.MAIN_HAND, _setstack);
			if (entity instanceof EntityPlayerMP)
				((EntityPlayerMP) entity).inventory.markDirty();
		}
		if (entity instanceof EntityPlayer) {
			ItemStack _setstack = new ItemStack(ItemAaadead.block, (int) (1));
			_setstack.setCount(1);
			ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
		}
		Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors()).schedule(() -> {
			if (entity instanceof EntityPlayer)
				((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemAaadead.block, (int) (1)).getItem(), -1, (int) 1, null);
		}, 100, TimeUnit.MILLISECONDS);
	}
}
