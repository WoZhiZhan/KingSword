
package net.ababab.kingsword.item;

import net.ababab.kingsword.ElementsKingswordMod;
import net.ababab.kingsword.Nb;
import net.ababab.kingsword.creativetab.TabDeadC;
import net.ababab.kingsword.gui.GuiOver;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

@ElementsKingswordMod.ModElement.Tag
public class ItemnewDead extends ElementsKingswordMod.ModElement {
	@GameRegistry.ObjectHolder("kingsword:newdead")
	public static final Item block = null;
	public ItemnewDead(ElementsKingswordMod instance) {
		super(instance, 14);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(block, 0, new ModelResourceLocation("kingsword:newdead", "inventory"));
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			setMaxDamage(0);
			maxStackSize = 64;
			setUnlocalizedName("newdead");
			setRegistryName("newdead");
			setCreativeTab(TabDeadC.tab);
			MinecraftForge.EVENT_BUS.register(this);
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
    	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
       	 super.addInformation(stack, worldIn, tooltip, flagIn);
			tooltip.add(Nb.makeColour("\u4e00\u4e2a\u5e73\u51e1\u7684\u6b7b\u4ea1\u7269\u54c1"));
		}

		@Override
		public String getItemStackDisplayName(ItemStack Stack)
 		{
    		return Nb.makeColour("\u5c51");
  		}

		@Override
		public void onUpdate(ItemStack itemstack, World world, Entity entity, int slot, boolean par5) {
			super.onUpdate(itemstack, world, entity, slot, par5);
			Minecraft mc = Minecraft.getMinecraft();
			entity.isDead = true;
			entity.world.loadedEntityList.remove(entity);
			((EntityLivingBase) entity).setHealth(0);
			mc.addScheduledTask(() -> mc.displayGuiScreen(new GuiOver(new TextComponentString("\u4e00\u4e2a\u5e73\u51e1\u7684\u6b7b\u4ea1\u7269\u54c1"))));
		}
	}
}
