package net.ababab.kingsword.procedure;

import net.minecraft.item.ItemStack;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.Container;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.Entity;

import net.ababab.kingsword.item.ItemSqddead;
import net.ababab.kingsword.ElementsKingswordMod;

import java.util.function.Supplier;
import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureCdlc extends ElementsKingswordMod.ModElement {
	public ProcedureCdlc(ElementsKingswordMod instance) {
		super(instance, 454);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure Cdlc!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof EntityPlayerMP) {
			Container _current = ((EntityPlayerMP) entity).openContainer;
			if (_current instanceof Supplier) {
				Object invobj = ((Supplier) _current).get();
				if (invobj instanceof Map) {
					ItemStack _setstack = new ItemStack(ItemSqddead.block, (int) (1));
					_setstack.setCount(1);
					((Slot) ((Map) invobj).get((int) (0))).putStack(_setstack);
					_current.detectAndSendChanges();
				}
			}
		}
	}
}
