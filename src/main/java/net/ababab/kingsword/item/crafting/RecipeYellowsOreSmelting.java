
package net.ababab.kingsword.item.crafting;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import net.minecraft.item.ItemStack;

import net.ababab.kingsword.item.ItemYellows;
import net.ababab.kingsword.block.BlockYellowsOre;
import net.ababab.kingsword.ElementsKingswordMod;

@ElementsKingswordMod.ModElement.Tag
public class RecipeYellowsOreSmelting extends ElementsKingswordMod.ModElement {
	public RecipeYellowsOreSmelting(ElementsKingswordMod instance) {
		super(instance, 721);
	}

	@Override
	public void init(FMLInitializationEvent event) {
		GameRegistry.addSmelting(new ItemStack(BlockYellowsOre.block, (int) (1)), new ItemStack(ItemYellows.block, (int) (1)), 1.75F);
	}
}
