
package net.ababab.kingsword.creativetab;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;

import net.minecraft.item.ItemStack;
import net.minecraft.creativetab.CreativeTabs;

import net.ababab.kingsword.item.ItemWoodenSword;
import net.ababab.kingsword.ElementsKingswordMod;
import java.util.HashMap;
import net.ababab.kingsword.Az;
import net.ababab.kingsword.Za;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.client.resources.I18n;
import javax.annotation.Nullable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.util.text.TextComponentString;
import net.ababab.kingsword.*;

@ElementsKingswordMod.ModElement.Tag
public class TabXmj extends ElementsKingswordMod.ModElement {
	public TabXmj(ElementsKingswordMod instance) {
		super(instance, 337);
	}

	@Override
	public void initElements() {
		tab = new CreativeTabs("tabxmj") {
			@SideOnly(Side.CLIENT)
			@Override
			public ItemStack getTabIconItem() {
				return new ItemStack(ItemWoodenSword.block, (int) (1));
			}

			@SideOnly(Side.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}

			@SideOnly(Side.CLIENT)
     		 public String func_78024_c()
     		 {
     		   return Kingu.makeColour("Ð¡Ä¾½£");
    		  }
			
		};
	}
	public static CreativeTabs tab;
}
