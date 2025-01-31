
package net.ababab.kingsword.item.crafting;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import net.minecraft.item.ItemStack;

import net.ababab.kingsword.item.ItemSw;
import net.ababab.kingsword.item.ItemKingdesword2;
import net.ababab.kingsword.ElementsKingswordMod;

@ElementsKingswordMod.ModElement.Tag
public class RecipeDeadhc extends ElementsKingswordMod.ModElement {
	public RecipeDeadhc(ElementsKingswordMod instance) {
		super(instance, 645);
	}

	@Override
	public void init(FMLInitializationEvent event) {
		GameRegistry.addSmelting(new ItemStack(ItemKingdesword2.block, (int) (1)), new ItemStack(ItemSw.block, (int) (1)), 1F);
	}
}
