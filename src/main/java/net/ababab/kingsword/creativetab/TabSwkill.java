
package net.ababab.kingsword.creativetab;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;

import net.minecraft.item.ItemStack;
import net.minecraft.creativetab.CreativeTabs;

import net.ababab.kingsword.item.ItemKingclear;
import net.ababab.kingsword.ElementsKingswordMod;
import net.ababab.kingsword.*;
import net.minecraft.util.*;

@ElementsKingswordMod.ModElement.Tag
public class TabSwkill extends ElementsKingswordMod.ModElement {
	public TabSwkill(ElementsKingswordMod instance) {
		super(instance, 482);
	}

	@Override
	public void initElements() {
		tab = new CreativeTabs("tabswkill") {
			@SideOnly(Side.CLIENT)
			@Override
			public ItemStack getTabIconItem() {
				return new ItemStack(ItemKingclear.block, (int) (1));
			}

			@SideOnly(Side.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}

			@SideOnly(Side.CLIENT)
		    public String func_78024_c()
    		{
       		return Nb.makeColour("ÉúÎï¤ÎKill");
			 }
			
		};
	}
	public static CreativeTabs tab;
}
