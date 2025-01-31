
package net.ababab.kingsword.creativetab;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;

import net.minecraft.item.ItemStack;
import net.minecraft.creativetab.CreativeTabs;

import net.ababab.kingsword.item.ItemLeitingzhiren;
import net.ababab.kingsword.ElementsKingswordMod;
import net.ababab.kingsword.*;
import net.minecraft.util.*;

@ElementsKingswordMod.ModElement.Tag
public class TabLeizhijian extends ElementsKingswordMod.ModElement {
	public TabLeizhijian(ElementsKingswordMod instance) {
		super(instance, 569);
	}

	@Override
	public void initElements() {
		tab = new CreativeTabs("tableizhijian") {
			@SideOnly(Side.CLIENT)
			@Override
			public ItemStack getTabIconItem() {
				return new ItemStack(ItemLeitingzhiren.block, (int) (1));
			}

			@SideOnly(Side.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
			
			@SideOnly(Side.CLIENT)
		    public String func_78024_c()
    		{
       		return Nb.makeColour("À×Ö®½£");
			 }
			
		};
	}
	public static CreativeTabs tab;
}
