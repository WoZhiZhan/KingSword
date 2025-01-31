
package net.ababab.kingsword.item;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.event.ModelRegistryEvent;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.Item;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;

import net.ababab.kingsword.creativetab.TabFivearmor;
import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Set;
import java.util.HashMap;

@ElementsKingswordMod.ModElement.Tag
public class ItemGrennsPickaxe extends ElementsKingswordMod.ModElement {
	@GameRegistry.ObjectHolder("kingsword:grenns_pickaxe")
	public static final Item block = null;
	public ItemGrennsPickaxe(ElementsKingswordMod instance) {
		super(instance, 701);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemPickaxe(EnumHelper.addToolMaterial("GRENNS_PICKAXE", 4, 660, 9f, 0f, 28)) {
			{
				this.attackSpeed = -3f;
			}
			public Set<String> getToolClasses(ItemStack stack) {
				HashMap<String, Integer> ret = new HashMap<String, Integer>();
				ret.put("pickaxe", 4);
				return ret.keySet();
			}
		}.setUnlocalizedName("grenns_pickaxe").setRegistryName("grenns_pickaxe").setCreativeTab(TabFivearmor.tab));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(block, 0, new ModelResourceLocation("kingsword:grenns_pickaxe", "inventory"));
	}
}
