
package net.ababab.kingsword.item;

import net.ababab.kingsword.ElementsKingswordMod;
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

import java.util.HashMap;
import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ItemKingdezbArmor extends ElementsKingswordMod.ModElement {
	@GameRegistry.ObjectHolder("kingsword:kingdezb_armorhelmet")
	public static final Item helmet = null;
	@GameRegistry.ObjectHolder("kingsword:kingdezb_armorbody")
	public static final Item body = null;
	@GameRegistry.ObjectHolder("kingsword:kingdezb_armorlegs")
	public static final Item legs = null;
	@GameRegistry.ObjectHolder("kingsword:kingdezb_armorboots")
	public static final Item boots = null;
	public ItemKingdezbArmor(ElementsKingswordMod instance) {
		super(instance, 11);
	}

	@Override
	public void initElements() {
		ItemArmor.ArmorMaterial enuma = EnumHelper.addArmorMaterial("KINGDEZB_ARMOR", "kingsword:q__", 1024, new int[]{1024, 1024, 1024, 1024}, 100,
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
					ProcedureKingdezbArmorTouKuiShiJianMeiYouXiKe.executeProcedure($_dependencies);
				}
			}
			@Override
			public String getItemStackDisplayName(ItemStack Stack)
			{
				return Nb.makeColour("King¤ÎÍ·¿ø");
			}
		}.setUnlocalizedName("kingdezb_armorhelmet").setRegistryName("kingdezb_armorhelmet").setCreativeTab(TabKingdwpl.tab));
		elements.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.CHEST) {
			@Override
			public void onArmorTick(World world, EntityPlayer entity, ItemStack itemstack) {
				int x = (int) entity.posX;
				int y = (int) entity.posY;
				int z = (int) entity.posZ;
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("entity", entity);
					ProcedureKingdezbArmorTouKuiShiJianMeiYouXiKe.executeProcedure($_dependencies);
				}
			}
			@Override
			public String getItemStackDisplayName(ItemStack Stack)
			{
				return Nb.makeColour("King¤ÎÐØ¼×");
			}
		}.setUnlocalizedName("kingdezb_armorbody").setRegistryName("kingdezb_armorbody").setCreativeTab(TabKingdwpl.tab));
		elements.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.LEGS) {
			@Override
			public void onArmorTick(World world, EntityPlayer entity, ItemStack itemstack) {
				int x = (int) entity.posX;
				int y = (int) entity.posY;
				int z = (int) entity.posZ;
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("entity", entity);
					ProcedureKingdezbArmorTouKuiShiJianMeiYouXiKe.executeProcedure($_dependencies);
				}
			}
			@Override
			public String getItemStackDisplayName(ItemStack Stack)
			{
				return Nb.makeColour("King¤Î»¤ÍÈ");
			}
		}.setUnlocalizedName("kingdezb_armorlegs").setRegistryName("kingdezb_armorlegs").setCreativeTab(TabKingdwpl.tab));
		elements.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.FEET) {
			@Override
			public void onArmorTick(World world, EntityPlayer entity, ItemStack itemstack) {
				int x = (int) entity.posX;
				int y = (int) entity.posY;
				int z = (int) entity.posZ;
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("entity", entity);
					ProcedureKingdezbArmorTouKuiShiJianMeiYouXiKe.executeProcedure($_dependencies);
				}
			}
			@Override
			public String getItemStackDisplayName(ItemStack Stack)
			{
				return Nb.makeColour("King¤ÎÑ¥×Ó");
			}
		}.setUnlocalizedName("kingdezb_armorboots").setRegistryName("kingdezb_armorboots").setCreativeTab(TabKingdwpl.tab));
	}
	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(helmet, 0, new ModelResourceLocation("kingsword:kingdezb_armorhelmet", "inventory"));
		ModelLoader.setCustomModelResourceLocation(body, 0, new ModelResourceLocation("kingsword:kingdezb_armorbody", "inventory"));
		ModelLoader.setCustomModelResourceLocation(legs, 0, new ModelResourceLocation("kingsword:kingdezb_armorlegs", "inventory"));
		ModelLoader.setCustomModelResourceLocation(boots, 0, new ModelResourceLocation("kingsword:kingdezb_armorboots", "inventory"));
	}
}
