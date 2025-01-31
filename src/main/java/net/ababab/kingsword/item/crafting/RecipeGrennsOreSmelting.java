
package net.ababab.kingsword.item.crafting;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import net.minecraft.item.ItemStack;

import net.ababab.kingsword.item.ItemGrenns;
import net.ababab.kingsword.block.BlockGrennsOre;
import net.ababab.kingsword.ElementsKingswordMod;

@ElementsKingswordMod.ModElement.Tag
public class RecipeGrennsOreSmelting extends ElementsKingswordMod.ModElement {
	public RecipeGrennsOreSmelting(ElementsKingswordMod instance) {
		super(instance, 700);
	}

	@Override
	public void init(FMLInitializationEvent event) {
		GameRegistry.addSmelting(new ItemStack(BlockGrennsOre.block, (int) (1)), new ItemStack(ItemGrenns.block, (int) (1)), 1.4F);
	}
}
