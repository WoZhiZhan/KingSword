
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

import net.ababab.kingsword.procedure.ProcedureKingdesword2DangShiTiBeiGongJuJiZhongShi;
import net.ababab.kingsword.procedure.ProcedureJianmieswordDangYouJianDianJiKongQiShi;
import net.ababab.kingsword.creativetab.TabKingice;
import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;
import java.util.List;
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
public class ItemMegasword extends ElementsKingswordMod.ModElement {
	@GameRegistry.ObjectHolder("kingsword:megasword")
	public static final Item block = null;
	public ItemMegasword(ElementsKingswordMod instance) {
		super(instance, 640);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(block, 0, new ModelResourceLocation("kingsword:megasword", "inventory"));
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			setMaxDamage(0);
			maxStackSize = 1;
			setUnlocalizedName("megasword");
			setRegistryName("megasword");
			setCreativeTab(TabKingice.tab);
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
			list.add(Nb3.makeColour("Mega"));
		}

		@Override
		public String getItemStackDisplayName(ItemStack Stack)
 		{
    		return Maxnb.makeColour("Mega Sword");
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
				ProcedureKingdesword2DangShiTiBeiGongJuJiZhongShi.executeProcedure($_dependencies);
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
				$_dependencies.put("world", world);
				ProcedureJianmieswordDangYouJianDianJiKongQiShi.executeProcedure($_dependencies);
			}
			return true;
		}
	}
}
