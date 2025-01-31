
package net.ababab.kingsword.block;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.event.ModelRegistryEvent;

import net.minecraft.world.World;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.Item;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.Block;

import net.ababab.kingsword.creativetab.TabKingdwpl;
import net.ababab.kingsword.ElementsKingswordMod;

import java.util.List;

@ElementsKingswordMod.ModElement.Tag
public class BlockHyhyk extends ElementsKingswordMod.ModElement {
	@GameRegistry.ObjectHolder("kingsword:hyhyk")
	public static final Block block = null;
	public BlockHyhyk(ElementsKingswordMod instance) {
		super(instance, 140);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new BlockCustom().setRegistryName("hyhyk"));
		elements.items.add(() -> new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation("kingsword:hyhyk", "inventory"));
	}
	public static class BlockCustom extends Block {
		public BlockCustom() {
			super(Material.ROCK);
			setUnlocalizedName("hyhyk");
			setSoundType(SoundType.STONE);
			setHarvestLevel("pickaxe", 1);
			setHardness(3F);
			setResistance(10F);
			setLightLevel(0F);
			setLightOpacity(255);
			setCreativeTab(TabKingdwpl.tab);
		}

		@Override
		public void addInformation(ItemStack itemstack, World world, List<String> list, ITooltipFlag flag) {
			super.addInformation(itemstack, world, list, flag);
			list.add("\u6446\u6210\u5730\u72F1\u95E8\u7684\u6837\u5B50");
			list.add("\u7136\u540E\u7528\u6DF7\u5143\u6253\u706B\u77F3\u4F3C\u4E4E\u80FD\u6FC0\u6D3B");
			list.add("\u5982\u679Cforge\u7248\u672C\u8F83\u4F4E");
			list.add("\u53EF\u4EE5\u7528\u6B64\u65B9\u5757\u5408\u6210\u5FEB\u6377\u4F20\u9001\u9635");
		}
	}
}
