
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

import net.ababab.kingsword.procedure.ProcedureWoodenSwordDangYouJianDianJiKongQiShi;
import net.ababab.kingsword.procedure.ProcedureWoodenSwordDangHuoZhaoDeShiTiBeiGaiWuPinJiZhong;
import net.ababab.kingsword.creativetab.TabXmj;
import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import net.ababab.kingsword.Wood;
import net.ababab.kingsword.Woodtwo;
import net.ababab.kingsword.Woodthree;
import net.ababab.kingsword.*;
import net.ababab.kingsword.creativetab.TabXmj;
import net.ababab.kingsword.procedure.ProcedureWoodenSwordDangHuoZhaoDeShiTiBeiGaiWuPinJiZhong;
import net.ababab.kingsword.procedure.ProcedureWoodenSwordDangYouJianDianJiKongQiShi;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumAction;

@ElementsKingswordMod.ModElement.Tag
public class ItemWoodenSword extends ElementsKingswordMod.ModElement {
	@GameRegistry.ObjectHolder("kingsword:wooden_sword")
	public static final Item block = null;
	public ItemWoodenSword(ElementsKingswordMod instance) {
		super(instance, 337);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(block, 0, new ModelResourceLocation("kingsword:wooden_sword", "inventory"));
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			setMaxDamage(0);
			maxStackSize = 1;
			setUnlocalizedName("wooden_sword");
			setRegistryName("wooden_sword");
			setCreativeTab(TabXmj.tab);
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
		public EnumAction getItemUseAction(ItemStack itemstack) {
			return EnumAction.BOW;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, IBlockState par2Block) {
			return 1F;
		}

		@Override
		public void addInformation(ItemStack itemstack, World world, List<String> list, ITooltipFlag flag) {
			super.addInformation(itemstack, world, list, flag);
			list.add(Kingu.makeColour("被狗咬了亿口的木剑,上面还有一个浅浅的牙印"));
			list.add(Nb3.makeColour("听说这把剑打人超疼的,就跟被狗咬了一样"));
			list.add("");
			list.add("\u5728\u4E3B\u624B\u65F6:");
			list.add(Cai.wozhizhan(" \u65E0\u9650") + Woodthree.makeColour(" 攻击速度"));
			list.add(Cai.wozhizhan(" \u65E0\u9650") + Woodthree.makeColour(" 攻击伤害"));
		}

		@Override
		public String getItemStackDisplayName(ItemStack Stack)
 		{
    		return Wood.makeColour("木剑");
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
				ProcedureWoodenSwordDangYouJianDianJiKongQiShi.executeProcedure($_dependencies);
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
				ProcedureWoodenSwordDangHuoZhaoDeShiTiBeiGaiWuPinJiZhong.executeProcedure($_dependencies);
			}
			return true;
		}
	}
}
