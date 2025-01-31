
package net.ababab.kingsword.item;

import net.ababab.kingsword.ElementsKingswordMod;
import net.ababab.kingsword.creativetab.TabKingice;
import net.ababab.kingsword.procedure.ProcedureZdgjDangWuPinZaiBeiBaoZhongMeiKeFaSheng;
import net.ababab.kingsword.procedure.ProcedureZdgjDangYouJianDianJiKongQiShi;
import net.ababab.kingsword.util.FuckFont;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
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
public class ItemZdgj extends ElementsKingswordMod.ModElement {
	@GameRegistry.ObjectHolder("kingsword:zdgj")
	public static final Item block = null;
	public ItemZdgj(ElementsKingswordMod instance) {
		super(instance, 795);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(block, 0, new ModelResourceLocation("kingsword:zdgj", "inventory"));
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			setMaxDamage(0);
			maxStackSize = 64;
			setUnlocalizedName("zdgj");
			setRegistryName("zdgj");
			setCreativeTab(TabKingice.tab);
			MinecraftForge.EVENT_BUS.register(this);
		}

		@SubscribeEvent
  public void evt(RenderTooltipEvent.Color evt) {
    if (evt.getStack().getItem() == ItemZdgj.block)
      evt.setBackground(evt.getBackground() & 0xFF000000 | MathHelper.hsvToRGB(10.0F, 0.0F, 24.0F)); 
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
			list.add("\u628a\u7269\u54c1\u653e\u8fdb\u80cc\u5305\u5f00\u542f\u81ea\u52a8\u653b\u51fb");
			list.add("\u53f3\u952e\u5f00\u542f\u7279\u6b8a\u653b\u51fb");
		}

		@Override
		public String getItemStackDisplayName(ItemStack Stack)
 		{
    		return "\u81ea\u52a8\u653b\u51fb";
  		}

		@SideOnly(Side.CLIENT)
		public FontRenderer getFontRenderer(ItemStack stack)
 		{
  			return FuckFont.getFont();
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
				ProcedureZdgjDangYouJianDianJiKongQiShi.executeProcedure($_dependencies);
			}
			return ar;
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
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				ProcedureZdgjDangWuPinZaiBeiBaoZhongMeiKeFaSheng.executeProcedure($_dependencies);
			}
		}
	}
}
