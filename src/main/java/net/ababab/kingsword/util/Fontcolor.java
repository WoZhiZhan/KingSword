/**
 * This mod element is always locked. Enter your code in the methods below.
 * If you don't need some of these methods, you can remove them as they
 * are overrides of the base class ElementsKingswordMod.ModElement.
 *
 * You can register new events in this class too.
 *
 * As this class is loaded into mod element list, it NEEDS to extend
 * ModElement class. If you remove this extend statement or remove the
 * constructor, the compilation will fail.
 *
 * If you want to make a plain independent class, create it in
 * "Workspace" -> "Source" menu.
 *
 * If you change workspace package, modid or prefix, you will need
 * to manually adapt this file to these changes or remake it.
*/
package net.ababab.kingsword.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static net.ababab.kingsword.item.ItemKingdesword.colors;
import static net.ababab.kingsword.util.FuckFont.HSBtoRGB;

@SideOnly(Side.CLIENT)
public class Fontcolor
		extends FontRenderer {
	private static Fontcolor font;

	public static Fontcolor getFont() {
		if (font == null) {
			Minecraft mc = Minecraft.getMinecraft();
			font = new Fontcolor(mc.gameSettings, new ResourceLocation("thread:textures/nm.png"), mc.renderEngine, false);
			if (mc.gameSettings.language != null) {
				font.setUnicodeFlag(mc.isUnicode());
				font.setBidiFlag(mc.getLanguageManager().isCurrentLanguageBidirectional());
			}
		}
		return font;
	}

	private Fontcolor(GameSettings gameSettingsIn, ResourceLocation location, TextureManager textureManagerIn, boolean unicode) {
		super(gameSettingsIn, location, textureManagerIn, unicode);
	}

	public static long milliTime() {
		return System.nanoTime() / 1000000L;
	}

	private static double rangeRemap(double value, double low1, double high1, double low2, double high2) {
		return low2 + (value - low1) * (high2 - low2) / (high1 - low1);
	}

	public int drawStringWithShadow(String string, float x, float y, int color) {
		float huehuehueStep;
		float huehuehue;
		if (colors) {
			huehuehue = (float) milliTime() / 700.0F % 1.0F;
			huehuehueStep = (float) rangeRemap(Math.sin((float) milliTime() / 1200.0F) % 6.28318D, -0.9D, 2.5D, 0.025D, 0.15D);
			float posX = x;
			String drawText = TextFormatting.getTextWithoutFormattingCodes(string);
			for (int i = 0; i < drawText.length(); i++) {
				int c = color & 0xFF000000 | MathHelper.hsvToRGB(huehuehue, 0.7F, 0.9F);
				float yOffset = (float) (Math.sin(i + (float) milliTime() / 300.0F));
				float xOffset = (float) (Math.cos(i + (float) milliTime() / 300.0F));
				posX = super.drawStringWithShadow(String.valueOf(drawText.charAt(i)), posX + xOffset, y + yOffset, c);
				huehuehue += huehuehueStep;
				huehuehue %= 10.0F;
			}
		} else {
			string = TextFormatting.getTextWithoutFormattingCodes(string);

			float getColor = (float) milliTime() % 3601.0F;
			int drawColor = 0;
			for (int i = 0; i < string.length(); i++) {
				char c = string.charAt(i);
				String str = String.valueOf(c);
				drawColor = HSBtoRGB(getColor / 1000.0F, 0.7F, 0.9F);
				super.drawStringWithShadow(str, x, y, drawColor);
				x += getCharWidth(c);
				getColor += 11.0F;
				getColor %= 3601.0F;
			}
		}
		return (int) posX;
	}
}
