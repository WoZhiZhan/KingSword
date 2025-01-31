
package net.ababab.kingsword.item.crafting;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import net.minecraft.item.ItemStack;

import net.ababab.kingsword.item.ItemWhite;
import net.ababab.kingsword.block.BlockWhiteOre;
import net.ababab.kingsword.ElementsKingswordMod;

@ElementsKingswordMod.ModElement.Tag
public class RecipeWhiteOreSmelting extends ElementsKingswordMod.ModElement {
	public RecipeWhiteOreSmelting(ElementsKingswordMod instance) {
		super(instance, 657);
	}

	@Override
	public void init(FMLInitializationEvent event) {
		GameRegistry.addSmelting(new ItemStack(BlockWhiteOre.block, (int) (1)), new ItemStack(ItemWhite.block, (int) (1)), 1.4F);
	}
}
