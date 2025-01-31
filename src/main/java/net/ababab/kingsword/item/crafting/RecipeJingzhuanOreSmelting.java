
package net.ababab.kingsword.item.crafting;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import net.minecraft.item.ItemStack;

import net.ababab.kingsword.item.ItemJingzhuan;
import net.ababab.kingsword.block.BlockJingzhuanOre;
import net.ababab.kingsword.ElementsKingswordMod;

@ElementsKingswordMod.ModElement.Tag
public class RecipeJingzhuanOreSmelting extends ElementsKingswordMod.ModElement {
	public RecipeJingzhuanOreSmelting(ElementsKingswordMod instance) {
		super(instance, 122);
	}

	@Override
	public void init(FMLInitializationEvent event) {
		GameRegistry.addSmelting(new ItemStack(BlockJingzhuanOre.block, (int) (1)), new ItemStack(ItemJingzhuan.block, (int) (1)),
				1.0499999999999998F);
	}
}
