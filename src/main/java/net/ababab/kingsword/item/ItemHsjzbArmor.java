
package net.ababab.kingsword.item;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.event.ModelRegistryEvent;

import net.minecraft.util.ResourceLocation;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.Item;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;

import net.ababab.kingsword.creativetab.TabKingdwpl;
import net.ababab.kingsword.ElementsKingswordMod;

@ElementsKingswordMod.ModElement.Tag
public class ItemHsjzbArmor extends ElementsKingswordMod.ModElement {
	@GameRegistry.ObjectHolder("kingsword:hsjzb_armorhelmet")
	public static final Item helmet = null;
	@GameRegistry.ObjectHolder("kingsword:hsjzb_armorbody")
	public static final Item body = null;
	@GameRegistry.ObjectHolder("kingsword:hsjzb_armorlegs")
	public static final Item legs = null;
	@GameRegistry.ObjectHolder("kingsword:hsjzb_armorboots")
	public static final Item boots = null;
	public ItemHsjzbArmor(ElementsKingswordMod instance) {
		super(instance, 47);
	}

	@Override
	public void initElements() {
		ItemArmor.ArmorMaterial enuma = EnumHelper.addArmorMaterial("HSJZB_ARMOR", "kingsword:hsjzb", 300, new int[]{6, 13, 16, 9}, 27,
				(net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY.getObject(new ResourceLocation("")), 5f);
		elements.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.HEAD).setUnlocalizedName("hsjzb_armorhelmet")
				.setRegistryName("hsjzb_armorhelmet").setCreativeTab(TabKingdwpl.tab));
		elements.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.CHEST).setUnlocalizedName("hsjzb_armorbody")
				.setRegistryName("hsjzb_armorbody").setCreativeTab(TabKingdwpl.tab));
		elements.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.LEGS).setUnlocalizedName("hsjzb_armorlegs")
				.setRegistryName("hsjzb_armorlegs").setCreativeTab(TabKingdwpl.tab));
		elements.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.FEET).setUnlocalizedName("hsjzb_armorboots")
				.setRegistryName("hsjzb_armorboots").setCreativeTab(TabKingdwpl.tab));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(helmet, 0, new ModelResourceLocation("kingsword:hsjzb_armorhelmet", "inventory"));
		ModelLoader.setCustomModelResourceLocation(body, 0, new ModelResourceLocation("kingsword:hsjzb_armorbody", "inventory"));
		ModelLoader.setCustomModelResourceLocation(legs, 0, new ModelResourceLocation("kingsword:hsjzb_armorlegs", "inventory"));
		ModelLoader.setCustomModelResourceLocation(boots, 0, new ModelResourceLocation("kingsword:hsjzb_armorboots", "inventory"));
	}
}
