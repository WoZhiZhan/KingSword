
package net.ababab.kingsword.item.crafting;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import net.minecraft.item.ItemStack;

import net.ababab.kingsword.item.ItemSlzj;
import net.ababab.kingsword.item.ItemHpzj;
import net.ababab.kingsword.ElementsKingswordMod;

@ElementsKingswordMod.ModElement.Tag
public class RecipeHpzjhc extends ElementsKingswordMod.ModElement {
	public RecipeHpzjhc(ElementsKingswordMod instance) {
		super(instance, 227);
	}

	@Override
	public void init(FMLInitializationEvent event) {
		GameRegistry.addSmelting(new ItemStack(ItemSlzj.block, (int) (1)), new ItemStack(ItemHpzj.block, (int) (1)), 1F);
	}
}
