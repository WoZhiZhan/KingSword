
package net.ababab.kingsword.item;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.event.ModelRegistryEvent;

import net.minecraft.world.World;
import net.minecraft.util.ResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.Item;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;

import net.ababab.kingsword.procedure.ProcedureZuizhongkingArmorTouKuiShiJianMeiYouXiKe;
import net.ababab.kingsword.creativetab.TabKingdwpl;
import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;
import java.util.HashMap;
import net.ababab.kingsword.Nb;
import net.ababab.kingsword.creativetab.TabKingdwpl;
import net.ababab.kingsword.procedure.ProcedureKingdezbArmorTouKuiShiJianMeiYouXiKe;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@ElementsKingswordMod.ModElement.Tag
public class ItemZuizhongkingArmor extends ElementsKingswordMod.ModElement {
	@GameRegistry.ObjectHolder("kingsword:zuizhongking_armorhelmet")
	public static final Item helmet = null;
	@GameRegistry.ObjectHolder("kingsword:zuizhongking_armorbody")
	public static final Item body = null;
	@GameRegistry.ObjectHolder("kingsword:zuizhongking_armorlegs")
	public static final Item legs = null;
	@GameRegistry.ObjectHolder("kingsword:zuizhongking_armorboots")
	public static final Item boots = null;
	public ItemZuizhongkingArmor(ElementsKingswordMod instance) {
		super(instance, 546);
	}

	@Override
	public void initElements() {
		ItemArmor.ArmorMaterial enuma = EnumHelper.addArmorMaterial("ZUIZHONGKING_ARMOR", "kingsword:zuizhongking", 0,
				new int[]{1024, 1024, 1024, 1024}, 90,
				(net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY.getObject(new ResourceLocation("")), 10f);
		elements.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.HEAD) {
			@Override
			public void onArmorTick(World world, EntityPlayer entity, ItemStack itemstack) {
				super.onArmorTick(world, entity, itemstack);
				int x = (int) entity.posX;
				int y = (int) entity.posY;
				int z = (int) entity.posZ;
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("entity", entity);
					ProcedureZuizhongkingArmorTouKuiShiJianMeiYouXiKe.executeProcedure($_dependencies);
				}
			}
			@Override
			public String getItemStackDisplayName(ItemStack Stack)
			{
				return Nb.makeColour("最终King头盔");
			}
		}.setUnlocalizedName("zuizhongking_armorhelmet").setRegistryName("zuizhongking_armorhelmet").setCreativeTab(TabKingdwpl.tab));
		elements.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.CHEST) {
			@Override
			public void onArmorTick(World world, EntityPlayer entity, ItemStack itemstack) {
				int x = (int) entity.posX;
				int y = (int) entity.posY;
				int z = (int) entity.posZ;
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("entity", entity);
					ProcedureZuizhongkingArmorTouKuiShiJianMeiYouXiKe.executeProcedure($_dependencies);
				}
			}
			@Override
			public String getItemStackDisplayName(ItemStack Stack)
			{
				return Nb.makeColour("最终King胸甲");
			}
		}.setUnlocalizedName("zuizhongking_armorbody").setRegistryName("zuizhongking_armorbody").setCreativeTab(TabKingdwpl.tab));
		elements.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.LEGS) {
			@Override
			public void onArmorTick(World world, EntityPlayer entity, ItemStack itemstack) {
				int x = (int) entity.posX;
				int y = (int) entity.posY;
				int z = (int) entity.posZ;
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("entity", entity);
					ProcedureZuizhongkingArmorTouKuiShiJianMeiYouXiKe.executeProcedure($_dependencies);
				}
			}
			@Override
			public String getItemStackDisplayName(ItemStack Stack)
			{
				return Nb.makeColour("最终King护腿");
			}
		}.setUnlocalizedName("zuizhongking_armorlegs").setRegistryName("zuizhongking_armorlegs").setCreativeTab(TabKingdwpl.tab));
		elements.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.FEET) {
			@Override
			public void onArmorTick(World world, EntityPlayer entity, ItemStack itemstack) {
				int x = (int) entity.posX;
				int y = (int) entity.posY;
				int z = (int) entity.posZ;
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("entity", entity);
					ProcedureZuizhongkingArmorTouKuiShiJianMeiYouXiKe.executeProcedure($_dependencies);
				}
			}
			@Override
			public String getItemStackDisplayName(ItemStack Stack)
			{
				return Nb.makeColour("最终King靴子");
			}
		}.setUnlocalizedName("zuizhongking_armorboots").setRegistryName("zuizhongking_armorboots").setCreativeTab(TabKingdwpl.tab));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(helmet, 0, new ModelResourceLocation("kingsword:zuizhongking_armorhelmet", "inventory"));
		ModelLoader.setCustomModelResourceLocation(body, 0, new ModelResourceLocation("kingsword:zuizhongking_armorbody", "inventory"));
		ModelLoader.setCustomModelResourceLocation(legs, 0, new ModelResourceLocation("kingsword:zuizhongking_armorlegs", "inventory"));
		ModelLoader.setCustomModelResourceLocation(boots, 0, new ModelResourceLocation("kingsword:zuizhongking_armorboots", "inventory"));
	}
}
