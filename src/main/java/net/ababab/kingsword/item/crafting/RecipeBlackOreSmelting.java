
package net.ababab.kingsword.item.crafting;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import net.minecraft.item.ItemStack;

import net.ababab.kingsword.item.ItemBlack;
import net.ababab.kingsword.block.BlockBlackOre;
import net.ababab.kingsword.ElementsKingswordMod;

@ElementsKingswordMod.ModElement.Tag
public class RecipeBlackOreSmelting extends ElementsKingswordMod.ModElement {
	public RecipeBlackOreSmelting(ElementsKingswordMod instance) {
		super(instance, 763);
	}

	@Override
	public void init(FMLInitializationEvent event) {
		GameRegistry.addSmelting(new ItemStack(BlockBlackOre.block, (int) (1)), new ItemStack(ItemBlack.block, (int) (1)), 2.0999999999999996F);
	}
}
