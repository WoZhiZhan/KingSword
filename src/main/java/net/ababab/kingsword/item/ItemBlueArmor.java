
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
public class ItemBlueArmor extends ElementsKingswordMod.ModElement {
	@GameRegistry.ObjectHolder("kingsword:blue_armorhelmet")
	public static final Item helmet = null;
	@GameRegistry.ObjectHolder("kingsword:blue_armorbody")
	public static final Item body = null;
	@GameRegistry.ObjectHolder("kingsword:blue_armorlegs")
	public static final Item legs = null;
	@GameRegistry.ObjectHolder("kingsword:blue_armorboots")
	public static final Item boots = null;
	public ItemBlueArmor(ElementsKingswordMod instance) {
		super(instance, 753);
	}

	@Override
	public void initElements() {
		ItemArmor.ArmorMaterial enuma = EnumHelper.addArmorMaterial("BLUE_ARMOR", "kingsword:blue", 39, new int[]{5, 16, 13, 5}, 23,
				(net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY.getObject(new ResourceLocation("")), 0f);
		elements.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.HEAD).setUnlocalizedName("blue_armorhelmet")
				.setRegistryName("blue_armorhelmet").setCreativeTab(TabFivearmor.tab));
		elements.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.CHEST).setUnlocalizedName("blue_armorbody")
				.setRegistryName("blue_armorbody").setCreativeTab(TabFivearmor.tab));
		elements.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.LEGS).setUnlocalizedName("blue_armorlegs")
				.setRegistryName("blue_armorlegs").setCreativeTab(TabFivearmor.tab));
		elements.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.FEET).setUnlocalizedName("blue_armorboots")
				.setRegistryName("blue_armorboots").setCreativeTab(TabFivearmor.tab));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(helmet, 0, new ModelResourceLocation("kingsword:blue_armorhelmet", "inventory"));
		ModelLoader.setCustomModelResourceLocation(body, 0, new ModelResourceLocation("kingsword:blue_armorbody", "inventory"));
		ModelLoader.setCustomModelResourceLocation(legs, 0, new ModelResourceLocation("kingsword:blue_armorlegs", "inventory"));
		ModelLoader.setCustomModelResourceLocation(boots, 0, new ModelResourceLocation("kingsword:blue_armorboots", "inventory"));
	}
}
