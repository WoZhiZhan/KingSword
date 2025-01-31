
package net.ababab.kingsword.item;

import net.ababab.kingsword.ElementsKingswordMod;
import net.ababab.kingsword.Man;
import net.ababab.kingsword.procedure.ProcedureSqddeadDangWuPinZaiBeiBaoZhongMeiKeFaSheng;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Azaz;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ItemSqddead extends ElementsKingswordMod.ModElement {
	@GameRegistry.ObjectHolder("kingsword:sqddead")
	public static final Item block = null;
	public ItemSqddead(ElementsKingswordMod instance) {
		super(instance, 453);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(block, 0, new ModelResourceLocation("kingsword:sqddead", "inventory"));
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			setMaxDamage(0);
			maxStackSize = 64;
			setUnlocalizedName("sqddead");
			setRegistryName("sqddead");
			setCreativeTab(null);
		}

		@Override
		public int getItemEnchantability() {
			return 0;
		}

		@Override
		public int getMaxItemUseDuration(ItemStack itemstack) {
			return 0;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, IBlockState par2Block) {
			return 1F;
		}

		@Override
		@SideOnly(Side.CLIENT)
		public boolean hasEffect(ItemStack itemstack) {
			return true;
		}

		@Override
		public void addInformation(ItemStack itemstack, World world, List<String> list, ITooltipFlag flag) {
			super.addInformation(itemstack, world, list, flag);
			list.add(Azaz.makeColour("������"));
		}

		@Override
		public String getItemStackDisplayName(ItemStack Stack)
 		{
    		return Man.makeColour("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
  		}

		@Override
		public void onUpdate(ItemStack itemstack, World world, Entity entity, int slot, boolean par5) {
			super.onUpdate(itemstack, world, entity, slot, par5);
			int x = (int) entity.posX;
			int y = (int) entity.posY;
			int z = (int) entity.posZ;
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				ProcedureSqddeadDangWuPinZaiBeiBaoZhongMeiKeFaSheng.executeProcedure($_dependencies);
			}
		}
	}
}
