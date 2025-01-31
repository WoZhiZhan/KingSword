
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
public class ItemRedsArmor extends ElementsKingswordMod.ModElement {
	@GameRegistry.ObjectHolder("kingsword:reds_armorhelmet")
	public static final Item helmet = null;
	@GameRegistry.ObjectHolder("kingsword:reds_armorbody")
	public static final Item body = null;
	@GameRegistry.ObjectHolder("kingsword:reds_armorlegs")
	public static final Item legs = null;
	@GameRegistry.ObjectHolder("kingsword:reds_armorboots")
	public static final Item boots = null;
	public ItemRedsArmor(ElementsKingswordMod instance) {
		super(instance, 690);
	}

	@Override
	public void initElements() {
		ItemArmor.ArmorMaterial enuma = EnumHelper.addArmorMaterial("REDS_ARMOR", "kingsword:reds", 30, new int[]{4, 12, 10, 4}, 18,
				(net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY.getObject(new ResourceLocation("")), 0f);
		elements.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.HEAD).setUnlocalizedName("reds_armorhelmet")
				.setRegistryName("reds_armorhelmet").setCreativeTab(TabFivearmor.tab));
		elements.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.CHEST).setUnlocalizedName("reds_armorbody")
				.setRegistryName("reds_armorbody").setCreativeTab(TabFivearmor.tab));
		elements.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.LEGS).setUnlocalizedName("reds_armorlegs")
				.setRegistryName("reds_armorlegs").setCreativeTab(TabFivearmor.tab));
		elements.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.FEET).setUnlocalizedName("reds_armorboots")
				.setRegistryName("reds_armorboots").setCreativeTab(TabFivearmor.tab));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(helmet, 0, new ModelResourceLocation("kingsword:reds_armorhelmet", "inventory"));
		ModelLoader.setCustomModelResourceLocation(body, 0, new ModelResourceLocation("kingsword:reds_armorbody", "inventory"));
		ModelLoader.setCustomModelResourceLocation(legs, 0, new ModelResourceLocation("kingsword:reds_armorlegs", "inventory"));
		ModelLoader.setCustomModelResourceLocation(boots, 0, new ModelResourceLocation("kingsword:reds_armorboots", "inventory"));
	}
}
