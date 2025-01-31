
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
public class ItemLsjArmor extends ElementsKingswordMod.ModElement {
	@GameRegistry.ObjectHolder("kingsword:lsj_armorhelmet")
	public static final Item helmet = null;
	@GameRegistry.ObjectHolder("kingsword:lsj_armorbody")
	public static final Item body = null;
	@GameRegistry.ObjectHolder("kingsword:lsj_armorlegs")
	public static final Item legs = null;
	@GameRegistry.ObjectHolder("kingsword:lsj_armorboots")
	public static final Item boots = null;
	public ItemLsjArmor(ElementsKingswordMod instance) {
		super(instance, 78);
	}

	@Override
	public void initElements() {
		ItemArmor.ArmorMaterial enuma = EnumHelper.addArmorMaterial("LSJ_ARMOR", "kingsword:lsj", 180, new int[]{5, 10, 12, 7}, 18,
				(net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY.getObject(new ResourceLocation("")), 2.5f);
		elements.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.HEAD).setUnlocalizedName("lsj_armorhelmet")
				.setRegistryName("lsj_armorhelmet").setCreativeTab(TabKingdwpl.tab));
		elements.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.CHEST).setUnlocalizedName("lsj_armorbody")
				.setRegistryName("lsj_armorbody").setCreativeTab(TabKingdwpl.tab));
		elements.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.LEGS).setUnlocalizedName("lsj_armorlegs")
				.setRegistryName("lsj_armorlegs").setCreativeTab(TabKingdwpl.tab));
		elements.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.FEET).setUnlocalizedName("lsj_armorboots")
				.setRegistryName("lsj_armorboots").setCreativeTab(TabKingdwpl.tab));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(helmet, 0, new ModelResourceLocation("kingsword:lsj_armorhelmet", "inventory"));
		ModelLoader.setCustomModelResourceLocation(body, 0, new ModelResourceLocation("kingsword:lsj_armorbody", "inventory"));
		ModelLoader.setCustomModelResourceLocation(legs, 0, new ModelResourceLocation("kingsword:lsj_armorlegs", "inventory"));
		ModelLoader.setCustomModelResourceLocation(boots, 0, new ModelResourceLocation("kingsword:lsj_armorboots", "inventory"));
	}
}
