
package net.ababab.kingsword.creativetab;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;

import net.minecraft.item.ItemStack;
import net.minecraft.creativetab.CreativeTabs;

import net.ababab.kingsword.item.ItemFiveItem;
import net.ababab.kingsword.ElementsKingswordMod;
import net.ababab.kingsword.*;

@ElementsKingswordMod.ModElement.Tag
public class TabFivearmor extends ElementsKingswordMod.ModElement {
	public TabFivearmor(ElementsKingswordMod instance) {
		super(instance, 673);
	}

	@Override
	public void initElements() {
		tab = new CreativeTabs("tabfivearmor") {
			@SideOnly(Side.CLIENT)
			@Override
			public ItemStack getTabIconItem() {
				return new ItemStack(ItemFiveItem.block, (int) (1));
			}

			@SideOnly(Side.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
			@SideOnly(Side.CLIENT)
     		 public String func_78024_c()
     		 {
     		   return Bao.makeColour("ÎåÐÐ");
    		  }
		};
	}
	public static CreativeTabs tab;
}
