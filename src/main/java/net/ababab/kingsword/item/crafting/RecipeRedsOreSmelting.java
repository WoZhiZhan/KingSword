
package net.ababab.kingsword.item.crafting;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import net.minecraft.item.ItemStack;

import net.ababab.kingsword.item.ItemReds;
import net.ababab.kingsword.block.BlockRedsOre;
import net.ababab.kingsword.ElementsKingswordMod;

@ElementsKingswordMod.ModElement.Tag
public class RecipeRedsOreSmelting extends ElementsKingswordMod.ModElement {
	public RecipeRedsOreSmelting(ElementsKingswordMod instance) {
		super(instance, 679);
	}

	@Override
	public void init(FMLInitializationEvent event) {
		GameRegistry.addSmelting(new ItemStack(BlockRedsOre.block, (int) (1)), new ItemStack(ItemReds.block, (int) (1)), 1.4F);
	}
}
