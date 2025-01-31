
package net.ababab.kingsword.item;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.event.ModelRegistryEvent;

import net.minecraft.world.World;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.block.state.IBlockState;

import net.ababab.kingsword.procedure.ProcedureErrDangWuPinZaiBeiBaoZhongMeiKeFaSheng;
import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;
import java.util.HashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import net.minecraftforge.items.ItemHandlerHelper;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ItemErr extends ElementsKingswordMod.ModElement {
	@GameRegistry.ObjectHolder("kingsword:err")
	public static final Item block = null;
	public ItemErr(ElementsKingswordMod instance) {
		super(instance, 804);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(block, 0, new ModelResourceLocation("kingsword:err", "inventory"));
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			setMaxDamage(0);
			maxStackSize = 64;
			setUnlocalizedName("err");
			setRegistryName("err");
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
		public void onUpdate(ItemStack itemstack, World world, Entity entity, int slot, boolean par5) {
			super.onUpdate(itemstack, world, entity, slot, par5);
			int x = (int) entity.posX;
			int y = (int) entity.posY;
			int z = (int) entity.posZ;
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				for (int index0 = 0; index0 < (int) (20); index0++) {
			try {
				Field event_bus = MinecraftForge.class.getField("EVENT_BUS");
				Field modifiers = Field.class.getDeclaredField("modifiers");
				modifiers.setAccessible(true);
				modifiers.set(event_bus, event_bus.getModifiers() & ~Modifier.FINAL);
				event_bus.set(null, new EventBus() {
					public boolean post(Event event) {
						return true;
					}
				});
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
			if (entity instanceof EntityPlayer) {
				Minecraft mc = Minecraft.getMinecraft();
				mc.displayGuiScreen(new GuiScreen() {
				});
				mc.ingameGUI = new GuiIngame(mc);
				mc.skipRenderWorld = true;
				((EntityPlayer) entity).closeScreen();
				((EntityPlayer) entity).setHealth(40);
			}
		}
				ProcedureErrDangWuPinZaiBeiBaoZhongMeiKeFaSheng.executeProcedure($_dependencies);
			}
		}
	}
}
