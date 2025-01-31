
package net.ababab.kingsword.creativetab;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;

import net.minecraft.item.ItemStack;
import net.minecraft.creativetab.CreativeTabs;

import net.ababab.kingsword.item.ItemSwwp;
import net.ababab.kingsword.ElementsKingswordMod;
import net.ababab.kingsword.*;
import net.minecraft.util.*;

@ElementsKingswordMod.ModElement.Tag
public class TabDeadC extends ElementsKingswordMod.ModElement {
	public TabDeadC(ElementsKingswordMod instance) {
		super(instance, 387);
	}

	@Override
	public void initElements() {
		tab = new CreativeTabs("tabdead_c") {
			@SideOnly(Side.CLIENT)
			@Override
			public ItemStack getTabIconItem() {
				return new ItemStack(ItemSwwp.block, (int) (1));
			}

			@SideOnly(Side.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}

			@SideOnly(Side.CLIENT)
		    public String func_78024_c()
    		{
       		return Azaz.makeColour("Dead");
			 }
			
		};
	}
	public static CreativeTabs tab;
}
