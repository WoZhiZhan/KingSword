
package net.ababab.kingsword.item;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.event.ModelRegistryEvent;

import net.minecraft.world.World;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.block.state.IBlockState;

import net.ababab.kingsword.creativetab.TabKingdwpl;
import net.ababab.kingsword.ElementsKingswordMod;

import java.util.List;
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
import net.minecraft.util.*;
import net.ababab.kingsword.util.Font;
import net.ababab.kingsword.util.text.Dfont;
import net.ababab.kingsword.util.text.KingswordFontRenderer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import java.util.Objects;

@ElementsKingswordMod.ModElement.Tag
public class ItemKingjieshao extends ElementsKingswordMod.ModElement {
	@GameRegistry.ObjectHolder("kingsword:kingjieshao")
	public static final Item block = null;
	public ItemKingjieshao(ElementsKingswordMod instance) {
		super(instance, 523);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(block, 0, new ModelResourceLocation("kingsword:kingjieshao", "inventory"));
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			setMaxDamage(0);
			maxStackSize = 64;
			setUnlocalizedName("kingjieshao");
			setRegistryName("kingjieshao");
			setCreativeTab(TabKingdwpl.tab);
			MinecraftForge.EVENT_BUS.register(this);
		}

		@SubscribeEvent
  public void evt(RenderTooltipEvent.Color evt) {
			float transcendF = Minecraft.getSystemTime() / 700.0F % 1;
			if (evt.getStack().getItem() == ItemKingjieshao.block)
      evt.setBackground(evt.getBackground() & 0xFF000000 | MathHelper.hsvToRGB(transcendF, 0.5F, 1F));
  }

		@Override
		public int getItemEnchantability() {
			return 0;
		}

		@Override
		public int getMaxItemUseDuration(ItemStack itemstack) {
			return 0;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, IBlockState par2Block) {
			return 1F;
		}

		@Override
		@SideOnly(Side.CLIENT)
		public boolean hasEffect(ItemStack itemstack) {
			return true;
		}

		@Override
		public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
    tooltip.add(Nb.makeColour("���ǲ��ֿ������"));
    tooltip.add(Nb.makeColour("��Kingʹ�ù�����"));
    tooltip.add(Nb.makeColour("ӵ����վ�Ԯ"));
    tooltip.add(Nb.makeColour("���๦�ܽ����� King�η��� ��"));
    tooltip.add(Nb.makeColour("����ָ��/wochao����һ������Ĺ���,/chaowo�ر�"));
    tooltip.add(Nb.makeColour("����bzdΪʲô������,Kingdesword2�в���"));
    tooltip.add(Nb.makeColour("����ָ��/hhhh�����¼�"));
    tooltip.add(Nb.makeColour("ָ��/kinggod ��tab"));
    tooltip.add(Nb.makeColour("����ָ��/kingui ��gui,�޷��ر�"));
    tooltip.add(Nb.makeColour("killָ���ǿ��(��)"));
    tooltip.add(Nb.makeColour("�����ļ���"));
    tooltip.add(Nb.makeColour("\u8FD9\u4E2A\u662Fking\u7684\u9632\u5FA1"));
        tooltip.add(Nb.makeColour("king\u306Esword 2 \u6CA1\u6709\u9632\u5FA1"));
        tooltip.add(Nb.makeColour("\u4EC5\u4EC5\u662F\u6CA1\u84C4\u529B\u7684\u72B6\u6001"));
        tooltip.add(Nb.makeColour("\u8F93\u5165\u6307\u4EE4:/kingdegongji \u5F00\u542F\u81EA\u52A8\u653B\u51FB\u6A21\u5F0F"));
        tooltip.add(Nb.makeColour("\u8F93\u5165\u6307\u4EE4:/gbkdgj \u5173\u95ED\u81EA\u52A8\u653B\u51FB"));
        tooltip.add(Nb.makeColour("\u6309K\u5F00\u542F\u8D85\u7EA7\u9632\u5FA1"));
        tooltip.add(Nb.makeColour("\u5F00\u542F\u540E\u6240\u6709\u6CA1king\u306Esword\u7684\u5B9E\u4F53\u90FD\u4F1A\u88ABtp\u5230\u865A\u7A7A"));
        tooltip.add(Nb.makeColour("\u6309J\u5173\u95ED"));
        tooltip.add(Nb.makeColour(
					"\u6E29\u99A8\u63D0\u793A:\u81EA\u52A8\u653B\u51FB\u5728\u5F88\u591A\u751F\u7269\u7684\u60C5\u51B5\u4E0B\u53EF\u80FD\u4F1A\u5D29"));
   		tooltip.add(Nb.makeColour("��G������,V�ر�"));
   		tooltip.add(Nb.makeColour("�ڽ����ɿ����������,��F7�����ռ���"));
   		tooltip.add(Nb.makeColour("ָ��/chunkill"));
   		tooltip.add(Nb.makeColour("ָ��/notkill"));
   		tooltip.add(Nb.makeColour("����ָ��/kinguangui ������gui,20����Զ��ر�"));
   		tooltip.add(Kingu.makeColour("King ") + Nb.makeColour("��Y�򿪿������,�����͹ر�ȫ�ֲ���") + Kingu.makeColour(" King"));
   		tooltip.add(Nb.makeColour("ָ��/notkill ����"));
			super.addInformation(stack, worldIn, tooltip, flagIn);

		}
		@Override
		public String getItemStackDisplayName(ItemStack Stack)
 		{
    		return "King����";
  		}

		@SideOnly(Side.CLIENT)
		public FontRenderer getFontRenderer(ItemStack stack)
 		{
  			return Dfont.getFont();
 		}
  		
	}
}
