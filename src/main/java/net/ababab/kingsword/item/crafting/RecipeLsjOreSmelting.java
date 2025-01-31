
package net.ababab.kingsword.item.crafting;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import net.minecraft.item.ItemStack;

import net.ababab.kingsword.item.ItemLsj;
import net.ababab.kingsword.block.BlockLsjOre;
import net.ababab.kingsword.ElementsKingswordMod;

@ElementsKingswordMod.ModElement.Tag
public class RecipeLsjOreSmelting extends ElementsKingswordMod.ModElement {
	public RecipeLsjOreSmelting(ElementsKingswordMod instance) {
		super(instance, 67);
	}

	@Override
	public void init(FMLInitializationEvent event) {
		GameRegistry.addSmelting(new ItemStack(BlockLsjOre.block, (int) (1)), new ItemStack(ItemLsj.block, (int) (1)), 1.4F);
	}
}
