
package net.ababab.kingsword.item;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.event.ModelRegistryEvent;

import net.minecraft.world.World;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ActionResult;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.block.state.IBlockState;

import net.ababab.kingsword.procedure.ProcedureZtxtDangYouJianDianJiKongQiShi;
import net.ababab.kingsword.procedure.ProcedureKingdesword2DangShiTiBeiGongJuJiZhongShi;
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
public class ItemZtxt extends ElementsKingswordMod.ModElement {
	@GameRegistry.ObjectHolder("kingsword:ztxt")
	public static final Item block = null;
	public ItemZtxt(ElementsKingswordMod instance) {
		super(instance, 614);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(block, 0, new ModelResourceLocation("kingsword:ztxt", "inventory"));
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			setMaxDamage(0);
			maxStackSize = 1;
			setUnlocalizedName("ztxt");
			setRegistryName("ztxt");
			setCreativeTab(null);
			MinecraftForge.EVENT_BUS.register(this);
		}

		@SubscribeEvent
  public void evt(RenderTooltipEvent.Color evt) {
			float transcendF = Minecraft.getSystemTime() / 700.0F % 1;
			if (evt.getStack().getItem() == ItemZtxt.block)
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
		public void addInformation(ItemStack itemstack, World world, List<String> list, ITooltipFlag flag) {
			super.addInformation(itemstack, world, list, flag);
			list.add(Tnb.makeColour("普通的砖头变的不普通了"));
			list.add(Tnb.makeColour("但是这还是改变不了他是从澈锁里面捡来的"));
			list.add(Gnb.makeColour("砖头中的力量被激发了"));
			list.add(Tnb.makeColour("砖头中的小霸王"));
			list.add(Tnb.makeColour("Ordinary bricks have become extraordinary"));
			list.add(Tnb.makeColour("But that doesn't change the fact that he picked it up from the dieter lock"));
			list.add(Gnb.makeColour("The power in the brick is activated"));
			list.add(Tnb.makeColour("The bully of bricks"));
			list.add("在主手时:");
			list.add(Gnb.makeColour(" \u65E0\u9650") + Woodthree.makeColour(" 攻击速度"));
			list.add(Gnb.makeColour(" \u65E0\u9650") + Woodthree.makeColour(" 攻击伤害"));
		}

		@Override
		public String getItemStackDisplayName(ItemStack Stack)
 		{
    		return Gnb.makeColour("砖头 (max pro super 形态)");
  		}

		@Override
		public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer entity, EnumHand hand) {
			ActionResult<ItemStack> ar = super.onItemRightClick(world, entity, hand);
			ItemStack itemstack = ar.getResult();
			int x = (int) entity.posX;
			int y = (int) entity.posY;
			int z = (int) entity.posZ;
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("world", world);
				ProcedureZtxtDangYouJianDianJiKongQiShi.executeProcedure($_dependencies);
			}
			return ar;
		}

		@Override
		public boolean hitEntity(ItemStack itemstack, EntityLivingBase entity, EntityLivingBase sourceentity) {
			super.hitEntity(itemstack, entity, sourceentity);
			int x = (int) entity.posX;
			int y = (int) entity.posY;
			int z = (int) entity.posZ;
			World world = entity.world;
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				ProcedureKingdesword2DangShiTiBeiGongJuJiZhongShi.executeProcedure($_dependencies);
			}
			return true;
		}
	}
}
