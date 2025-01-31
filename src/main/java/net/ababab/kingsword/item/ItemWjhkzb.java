
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
public class ItemWjhkzb extends ElementsKingswordMod.ModElement {
	@GameRegistry.ObjectHolder("kingsword:wjhkzbhelmet")
	public static final Item helmet = null;
	@GameRegistry.ObjectHolder("kingsword:wjhkzbbody")
	public static final Item body = null;
	@GameRegistry.ObjectHolder("kingsword:wjhkzblegs")
	public static final Item legs = null;
	@GameRegistry.ObjectHolder("kingsword:wjhkzbboots")
	public static final Item boots = null;
	public ItemWjhkzb(ElementsKingswordMod instance) {
		super(instance, 40);
	}

	@Override
	public void initElements() {
		ItemArmor.ArmorMaterial enuma = EnumHelper.addArmorMaterial("WJHKZB", "kingsword:q__", 1024, new int[]{10, 10, 10, 10}, 10,
				(net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY.getObject(new ResourceLocation("")), 10f);
		elements.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.HEAD).setUnlocalizedName("wjhkzbhelmet").setRegistryName("wjhkzbhelmet")
				.setCreativeTab(TabKingdwpl.tab));
		elements.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.CHEST).setUnlocalizedName("wjhkzbbody").setRegistryName("wjhkzbbody")
				.setCreativeTab(TabKingdwpl.tab));
		elements.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.LEGS).setUnlocalizedName("wjhkzblegs").setRegistryName("wjhkzblegs")
				.setCreativeTab(TabKingdwpl.tab));
		elements.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.FEET).setUnlocalizedName("wjhkzbboots").setRegistryName("wjhkzbboots")
				.setCreativeTab(TabKingdwpl.tab));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(helmet, 0, new ModelResourceLocation("kingsword:wjhkzbhelmet", "inventory"));
		ModelLoader.setCustomModelResourceLocation(body, 0, new ModelResourceLocation("kingsword:wjhkzbbody", "inventory"));
		ModelLoader.setCustomModelResourceLocation(legs, 0, new ModelResourceLocation("kingsword:wjhkzblegs", "inventory"));
		ModelLoader.setCustomModelResourceLocation(boots, 0, new ModelResourceLocation("kingsword:wjhkzbboots", "inventory"));
	}
}
