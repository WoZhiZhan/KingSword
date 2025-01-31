
package net.ababab.kingsword.item;

import net.ababab.kingsword.ElementsKingswordMod;
import net.ababab.kingsword.creativetab.TabDeadC;
import net.ababab.kingsword.procedure.ProcedureDpdpdpd;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ItemDpswwp extends ElementsKingswordMod.ModElement {
	@GameRegistry.ObjectHolder("kingsword:dpswwp")
	public static final Item block = null;
	public ItemDpswwp(ElementsKingswordMod instance) {
		super(instance, 37);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(block, 0, new ModelResourceLocation("kingsword:dpswwp", "inventory"));
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			setMaxDamage(0);
			maxStackSize = 64;
			setUnlocalizedName("dpswwp");
			setRegistryName("dpswwp");
			setCreativeTab(TabDeadC.tab);
			MinecraftForge.EVENT_BUS.register(this);
		}

		@SubscribeEvent
		public void evt(RenderTooltipEvent.Color evt) {
			float transcendF = Minecraft.getSystemTime() / 700.0F % 10;
			if (evt.getStack().getItem() == ItemDpswwp.block)
				evt.setBackground(evt.getBackground() & 0xFF000000 | MathHelper.hsvToRGB(transcendF, 0.6F, 1F));
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
		public void addInformation(ItemStack itemstack, World world, List<String> list, ITooltipFlag flag) {
			super.addInformation(itemstack, world, list, flag);
			list.add("\u53F3\u952E\u4F7F\u7528");
			list.add("\u8FD9\u4E2A\u4E0D\u4F1A\u7ED1\u5B9A\u5230\u80CC\u5305");
			list.add("\u5982\u679C\u4F60\u8BA4\u4E3A\u4F60\u8DB3\u591F\u5F3A\u5927\u53EF\u4EE5\u901D\u4E16");
		}

		@Override
		public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer entity, EnumHand hand) {
			ActionResult<ItemStack> ar = super.onItemRightClick(world, entity, hand);
			ItemStack itemstack = ar.getResult();
			int x = (int) entity.posX;
			int y = (int) entity.posY;
			int z = (int) entity.posZ;
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				ProcedureDpdpdpd.executeProcedure($_dependencies);
			}
			return ar;
		}
	}
}
