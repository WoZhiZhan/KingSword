
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
public class ItemBluePickaxe extends ElementsKingswordMod.ModElement {
	@GameRegistry.ObjectHolder("kingsword:blue_pickaxe")
	public static final Item block = null;
	public ItemBluePickaxe(ElementsKingswordMod instance) {
		super(instance, 743);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemPickaxe(EnumHelper.addToolMaterial("BLUE_PICKAXE", 5, 953, 11f, 1f, 36)) {
			{
				this.attackSpeed = -3f;
			}
			public Set<String> getToolClasses(ItemStack stack) {
				HashMap<String, Integer> ret = new HashMap<String, Integer>();
				ret.put("pickaxe", 5);
				return ret.keySet();
			}
		}.setUnlocalizedName("blue_pickaxe").setRegistryName("blue_pickaxe").setCreativeTab(TabFivearmor.tab));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(block, 0, new ModelResourceLocation("kingsword:blue_pickaxe", "inventory"));
	}
}
