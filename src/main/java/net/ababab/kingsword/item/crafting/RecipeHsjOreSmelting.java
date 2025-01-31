
package net.ababab.kingsword.item.crafting;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import net.minecraft.item.ItemStack;

import net.ababab.kingsword.item.ItemHsj;
import net.ababab.kingsword.block.BlockHsjOre;
import net.ababab.kingsword.ElementsKingswordMod;

@ElementsKingswordMod.ModElement.Tag
public class RecipeHsjOreSmelting extends ElementsKingswordMod.ModElement {
	public RecipeHsjOreSmelting(ElementsKingswordMod instance) {
		super(instance, 46);
	}

	@Override
	public void init(FMLInitializationEvent event) {
		GameRegistry.addSmelting(new ItemStack(BlockHsjOre.block, (int) (1)), new ItemStack(ItemHsj.block, (int) (1)), 1.4F);
	}
}
