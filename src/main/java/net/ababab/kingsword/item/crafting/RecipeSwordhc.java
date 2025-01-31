
package net.ababab.kingsword.item.crafting;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import net.minecraft.item.ItemStack;
import net.minecraft.init.Items;

import net.ababab.kingsword.item.ItemSwordpei;
import net.ababab.kingsword.ElementsKingswordMod;

@ElementsKingswordMod.ModElement.Tag
public class RecipeSwordhc extends ElementsKingswordMod.ModElement {
	public RecipeSwordhc(ElementsKingswordMod instance) {
		super(instance, 794);
	}

	@Override
	public void init(FMLInitializationEvent event) {
		GameRegistry.addSmelting(new ItemStack(Items.IRON_SWORD, (int) (1)), new ItemStack(ItemSwordpei.block, (int) (1)), 1F);
	}
}
