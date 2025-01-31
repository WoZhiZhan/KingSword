package net.ababab.kingsword.procedure;

import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;

import net.ababab.kingsword.item.ItemSqddx;
import net.ababab.kingsword.item.ItemCdbooks;
import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureSqddxDangYouJianDianJiKongQiShi extends ElementsKingswordMod.ModElement {
	public ProcedureSqddxDangYouJianDianJiKongQiShi(ElementsKingswordMod instance) {
		super(instance, 456);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure SqddxDangYouJianDianJiKongQiShi!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof EntityPlayer) {
			ItemStack _setstack = new ItemStack(ItemCdbooks.block, (int) (1));
			_setstack.setCount(1);
			ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
		}
		if (entity instanceof EntityPlayer)
			((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemSqddx.block, (int) (1)).getItem(), -1, (int) 1, null);
	}
}
