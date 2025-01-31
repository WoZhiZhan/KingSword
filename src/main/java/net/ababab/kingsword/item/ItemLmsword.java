
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
import net.minecraft.entity.Entity;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.block.state.IBlockState;

import net.ababab.kingsword.procedure.ProcedureLuomo;
import net.ababab.kingsword.procedure.ProcedureLmswordDangHuoZhaoDeShiTiBeiGaiWuPinJiZhong;
import net.ababab.kingsword.creativetab.TabKingdwpl;
import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import net.ababab.kingsword.Az;
import net.ababab.kingsword.*;
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
import net.ababab.kingsword.Shaped;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
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
import net.minecraft.util.*;

@ElementsKingswordMod.ModElement.Tag
public class ItemLmsword extends ElementsKingswordMod.ModElement {
	@GameRegistry.ObjectHolder("kingsword:lmsword")
	public static final Item block = null;
	public ItemLmsword(ElementsKingswordMod instance) {
		super(instance, 437);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(block, 0, new ModelResourceLocation("kingsword:lmsword", "inventory"));
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			setMaxDamage(0);
			maxStackSize = 1;
			setUnlocalizedName("lmsword");
			setRegistryName("lmsword");
			setCreativeTab(TabKingdwpl.tab);
			MinecraftForge.EVENT_BUS.register(this);
		}

		@SubscribeEvent
		public void evt(RenderTooltipEvent.Color evt) {
			float transcendF = Minecraft.getSystemTime() / 1200.0F % 10;
			if (evt.getStack().getItem() == ItemLmsword.block)
				evt.setBackground(evt.getBackground() & 0xFF000000 | MathHelper.hsvToRGB(transcendF, 0.6F, 1F));
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
			return 128000F;
		}

		@Override
		@SideOnly(Side.CLIENT)
		public boolean hasEffect(ItemStack itemstack) {
			return true;
		}

		@Override
		public boolean canHarvestBlock(IBlockState state, ItemStack stack) {
			return true;
		}

		@Override
		public void addInformation(ItemStack itemstack, World world, List<String> list, ITooltipFlag flag) {
			super.addInformation(itemstack, world, list, flag);
			list.add("");
			list.add(Azaz.makeColour("落寞之刃"));
			list.add(Zaza.makeColour("看起来被遗弃了很久,又被重新激活了"));
			list.add(Azaz.makeColour("落寞之刃激活！"));
			list.add(Zaza.makeColour("落寞之力,被封印的力量解除了"));
			list.add("");
			list.add("在主手时:");
			list.add(Nb.makeColour(" 落寞") + Woodthree.makeColour(" 攻击速度"));
			list.add(Nb.makeColour(" 落寞") + Woodthree.makeColour(" 攻击伤害"));
		}

		@Override
		public String getItemStackDisplayName(ItemStack Stack)
 		{
    		return Nb3.makeColour("落寞之刃");
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
				$_dependencies.put("entity", entity);
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				ProcedureLuomo.executeProcedure($_dependencies);
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
				ProcedureLmswordDangHuoZhaoDeShiTiBeiGaiWuPinJiZhong.executeProcedure($_dependencies);
			}
			return true;
		}

			}
		}
	