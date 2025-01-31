
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
public class ItemJingzhuanArmor extends ElementsKingswordMod.ModElement {
	@GameRegistry.ObjectHolder("kingsword:jingzhuan_armorhelmet")
	public static final Item helmet = null;
	@GameRegistry.ObjectHolder("kingsword:jingzhuan_armorbody")
	public static final Item body = null;
	@GameRegistry.ObjectHolder("kingsword:jingzhuan_armorlegs")
	public static final Item legs = null;
	@GameRegistry.ObjectHolder("kingsword:jingzhuan_armorboots")
	public static final Item boots = null;
	public ItemJingzhuanArmor(ElementsKingswordMod instance) {
		super(instance, 133);
	}

	@Override
	public void initElements() {
		ItemArmor.ArmorMaterial enuma = EnumHelper.addArmorMaterial("JINGZHUAN_ARMOR", "kingsword:jingzhuan", 80, new int[]{9, 9, 9, 9}, 14,
				(net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY.getObject(new ResourceLocation("")), 10f);
		elements.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.HEAD).setUnlocalizedName("jingzhuan_armorhelmet")
				.setRegistryName("jingzhuan_armorhelmet").setCreativeTab(TabKingdwpl.tab));
		elements.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.CHEST).setUnlocalizedName("jingzhuan_armorbody")
				.setRegistryName("jingzhuan_armorbody").setCreativeTab(TabKingdwpl.tab));
		elements.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.LEGS).setUnlocalizedName("jingzhuan_armorlegs")
				.setRegistryName("jingzhuan_armorlegs").setCreativeTab(TabKingdwpl.tab));
		elements.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.FEET).setUnlocalizedName("jingzhuan_armorboots")
				.setRegistryName("jingzhuan_armorboots").setCreativeTab(TabKingdwpl.tab));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(helmet, 0, new ModelResourceLocation("kingsword:jingzhuan_armorhelmet", "inventory"));
		ModelLoader.setCustomModelResourceLocation(body, 0, new ModelResourceLocation("kingsword:jingzhuan_armorbody", "inventory"));
		ModelLoader.setCustomModelResourceLocation(legs, 0, new ModelResourceLocation("kingsword:jingzhuan_armorlegs", "inventory"));
		ModelLoader.setCustomModelResourceLocation(boots, 0, new ModelResourceLocation("kingsword:jingzhuan_armorboots", "inventory"));
	}
}
