
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

import net.ababab.kingsword.creativetab.TabFivearmor;
import net.ababab.kingsword.ElementsKingswordMod;

@ElementsKingswordMod.ModElement.Tag
public class ItemWhiteArmor extends ElementsKingswordMod.ModElement {
	@GameRegistry.ObjectHolder("kingsword:white_armorhelmet")
	public static final Item helmet = null;
	@GameRegistry.ObjectHolder("kingsword:white_armorbody")
	public static final Item body = null;
	@GameRegistry.ObjectHolder("kingsword:white_armorlegs")
	public static final Item legs = null;
	@GameRegistry.ObjectHolder("kingsword:white_armorboots")
	public static final Item boots = null;
	public ItemWhiteArmor(ElementsKingswordMod instance) {
		super(instance, 668);
	}

	@Override
	public void initElements() {
		ItemArmor.ArmorMaterial enuma = EnumHelper.addArmorMaterial("WHITE_ARMOR", "kingsword:white", 30, new int[]{4, 12, 10, 4}, 18,
				(net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY.getObject(new ResourceLocation("")), 0f);
		elements.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.HEAD).setUnlocalizedName("white_armorhelmet")
				.setRegistryName("white_armorhelmet").setCreativeTab(TabFivearmor.tab));
		elements.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.CHEST).setUnlocalizedName("white_armorbody")
				.setRegistryName("white_armorbody").setCreativeTab(TabFivearmor.tab));
		elements.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.LEGS).setUnlocalizedName("white_armorlegs")
				.setRegistryName("white_armorlegs").setCreativeTab(TabFivearmor.tab));
		elements.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.FEET).setUnlocalizedName("white_armorboots")
				.setRegistryName("white_armorboots").setCreativeTab(TabFivearmor.tab));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(helmet, 0, new ModelResourceLocation("kingsword:white_armorhelmet", "inventory"));
		ModelLoader.setCustomModelResourceLocation(body, 0, new ModelResourceLocation("kingsword:white_armorbody", "inventory"));
		ModelLoader.setCustomModelResourceLocation(legs, 0, new ModelResourceLocation("kingsword:white_armorlegs", "inventory"));
		ModelLoader.setCustomModelResourceLocation(boots, 0, new ModelResourceLocation("kingsword:white_armorboots", "inventory"));
	}
}
