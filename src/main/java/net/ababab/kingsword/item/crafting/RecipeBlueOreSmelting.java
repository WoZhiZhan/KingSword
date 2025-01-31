
package net.ababab.kingsword.item.crafting;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import net.minecraft.item.ItemStack;

import net.ababab.kingsword.item.ItemBlue;
import net.ababab.kingsword.block.BlockBlueOre;
import net.ababab.kingsword.ElementsKingswordMod;

@ElementsKingswordMod.ModElement.Tag
public class RecipeBlueOreSmelting extends ElementsKingswordMod.ModElement {
	public RecipeBlueOreSmelting(ElementsKingswordMod instance) {
		super(instance, 742);
	}

	@Override
	public void init(FMLInitializationEvent event) {
		GameRegistry.addSmelting(new ItemStack(BlockBlueOre.block, (int) (1)), new ItemStack(ItemBlue.block, (int) (1)), 1.8199999999999998F);
	}
}
