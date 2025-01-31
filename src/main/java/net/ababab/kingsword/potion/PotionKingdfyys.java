
package net.ababab.kingsword.potion;

import net.ababab.kingsword.ElementsKingswordMod;
import net.ababab.kingsword.procedure.ProcedureKingdfyysYaoShuiXiaoGuoChiXuShiMeiKeFaSheng;
import net.ababab.kingsword.util.text.KingswordFontRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashMap;
import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class PotionKingdfyys extends ElementsKingswordMod.ModElement {
	@GameRegistry.ObjectHolder("kingsword:kingdfyys")
	public static final Potion potion = null;
	public PotionKingdfyys(ElementsKingswordMod instance) {
		super(instance, 20);
	}

	@Override
	public void initElements() {
		elements.potions.add(() -> new PotionCustom());
	}
	public static class PotionCustom extends Potion {
		private final ResourceLocation potionIcon;
		public PotionCustom() {
			super(false, -65434);
			setRegistryName("kingdfyys");
			setPotionName("effect.kingdfyys");
			potionIcon = new ResourceLocation("kingsword:textures/mob_effect/kingdfyys.png");
		}

		@Override
		public boolean isInstant() {
			return false;
		}

		@Override
		public boolean shouldRenderInvText(PotionEffect effect) {
			return true;
		}

		@Override
		public boolean shouldRenderHUD(PotionEffect effect) {
			return true;
		}

		public String getItemStackDisplayName(ItemStack stack) {
			return "King¤Î·ÀÓùÒ©Ë®";
		}

		public FontRenderer getFontRenderer(ItemStack stack)
		{
			return KingswordFontRenderer.getKingswordFontRenderer();
		}


		@Override
		public void performEffect(EntityLivingBase entity, int amplifier) {
			World world = entity.world;
			int x = (int) entity.posX;
			int y = (int) entity.posY;
			int z = (int) entity.posZ;
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				ProcedureKingdfyysYaoShuiXiaoGuoChiXuShiMeiKeFaSheng.executeProcedure($_dependencies);
			}
		}

		@SideOnly(Side.CLIENT)
		@Override
		public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc) {
			if (mc.currentScreen != null) {
				mc.getTextureManager().bindTexture(potionIcon);
				Gui.drawModalRectWithCustomSizedTexture(x + 6, y + 7, 0, 0, 18, 18, 18, 18);
			}
		}

		@SideOnly(Side.CLIENT)
		@Override
		public void renderHUDEffect(int x, int y, PotionEffect effect, Minecraft mc, float alpha) {
			mc.getTextureManager().bindTexture(potionIcon);
			Gui.drawModalRectWithCustomSizedTexture(x + 3, y + 3, 0, 0, 18, 18, 18, 18);
		}

		@Override
		public boolean isReady(int duration, int amplifier) {
			return true;
		}
	}
}
