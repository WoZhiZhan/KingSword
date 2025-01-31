
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
public class ItemYellowsArmor extends ElementsKingswordMod.ModElement {
	@GameRegistry.ObjectHolder("kingsword:yellows_armorhelmet")
	public static final Item helmet = null;
	@GameRegistry.ObjectHolder("kingsword:yellows_armorbody")
	public static final Item body = null;
	@GameRegistry.ObjectHolder("kingsword:yellows_armorlegs")
	public static final Item legs = null;
	@GameRegistry.ObjectHolder("kingsword:yellows_armorboots")
	public static final Item boots = null;
	public ItemYellowsArmor(ElementsKingswordMod instance) {
		super(instance, 732);
	}

	@Override
	public void initElements() {
		ItemArmor.ArmorMaterial enuma = EnumHelper.addArmorMaterial("YELLOWS_ARMOR", "kingsword:yellows", 38, new int[]{5, 15, 13, 5}, 23,
				(net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY.getObject(new ResourceLocation("")), 0f);
		elements.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.HEAD).setUnlocalizedName("yellows_armorhelmet")
				.setRegistryName("yellows_armorhelmet").setCreativeTab(TabFivearmor.tab));
		elements.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.CHEST).setUnlocalizedName("yellows_armorbody")
				.setRegistryName("yellows_armorbody").setCreativeTab(TabFivearmor.tab));
		elements.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.LEGS).setUnlocalizedName("yellows_armorlegs")
				.setRegistryName("yellows_armorlegs").setCreativeTab(TabFivearmor.tab));
		elements.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.FEET).setUnlocalizedName("yellows_armorboots")
				.setRegistryName("yellows_armorboots").setCreativeTab(TabFivearmor.tab));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(helmet, 0, new ModelResourceLocation("kingsword:yellows_armorhelmet", "inventory"));
		ModelLoader.setCustomModelResourceLocation(body, 0, new ModelResourceLocation("kingsword:yellows_armorbody", "inventory"));
		ModelLoader.setCustomModelResourceLocation(legs, 0, new ModelResourceLocation("kingsword:yellows_armorlegs", "inventory"));
		ModelLoader.setCustomModelResourceLocation(boots, 0, new ModelResourceLocation("kingsword:yellows_armorboots", "inventory"));
	}
}
