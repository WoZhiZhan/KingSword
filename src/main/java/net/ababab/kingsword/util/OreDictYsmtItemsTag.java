
package net.ababab.kingsword.util;

import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import net.minecraft.item.ItemStack;

import net.ababab.kingsword.block.BlockYsmtWood;
import net.ababab.kingsword.block.BlockYsmtLog;
import net.ababab.kingsword.ElementsKingswordMod;

@ElementsKingswordMod.ModElement.Tag
public class OreDictYsmtItemsTag extends ElementsKingswordMod.ModElement {
	public OreDictYsmtItemsTag(ElementsKingswordMod instance) {
		super(instance, 151);
	}

	@Override
	public void init(FMLInitializationEvent event) {
		OreDictionary.registerOre("ysmt_log", new ItemStack(BlockYsmtWood.block, (int) (1)));
		OreDictionary.registerOre("ysmt_log", new ItemStack(BlockYsmtLog.block, (int) (1)));
	}
}
