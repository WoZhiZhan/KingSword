
package net.ababab.kingsword.creativetab;

import net.ababab.kingsword.*;
import net.ababab.kingsword.ElementsKingswordMod;
import net.ababab.kingsword.item.ItemKingdesword;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@ElementsKingswordMod.ModElement.Tag
public class TabKingdwpl extends ElementsKingswordMod.ModElement {
	public TabKingdwpl(ElementsKingswordMod instance) {
		super(instance, 18);
	}

	@Override
	public void initElements() {
		tab = new CreativeTabs("tabkingdwpl") {
			@SideOnly(Side.CLIENT)
			@Override
			public ItemStack getTabIconItem() {
				return new ItemStack(ItemKingdesword.block, (int) (1));
			}

			@SideOnly(Side.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}

			@SideOnly(Side.CLIENT)
			public String func_78015_f()
			{
				return "king.png";
			}

			@SideOnly(Side.CLIENT)
		    public String func_78024_c()
    		{
       			return Tnb.makeColour("King Sword");
			 }
			
		};
	}
	public static CreativeTabs tab;
}
