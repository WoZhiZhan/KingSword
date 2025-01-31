
package net.ababab.kingsword.item;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.event.ModelRegistryEvent;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.Item;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;

import net.ababab.kingsword.creativetab.TabFivearmor;
import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Set;
import java.util.HashMap;

@ElementsKingswordMod.ModElement.Tag
public class ItemYellowsHoe extends ElementsKingswordMod.ModElement {
	@GameRegistry.ObjectHolder("kingsword:yellows_hoe")
	public static final Item block = null;
	public ItemYellowsHoe(ElementsKingswordMod instance) {
		super(instance, 726);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemHoe(EnumHelper.addToolMaterial("YELLOWS_HOE", 5, 902, 10f, 0f, 35)) {
			public Set<String> getToolClasses(ItemStack stack) {
				HashMap<String, Integer> ret = new HashMap<String, Integer>();
				ret.put("hoe", 5);
				return ret.keySet();
			}
		}.setUnlocalizedName("yellows_hoe").setRegistryName("yellows_hoe").setCreativeTab(TabFivearmor.tab));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(block, 0, new ModelResourceLocation("kingsword:yellows_hoe", "inventory"));
	}
}
