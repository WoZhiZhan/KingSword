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

@SideOnly(Side.CLIENT)
public class Fly
		extends FontRenderer {
	private static Fly font;

	public static Fly getFont() {
		if (font == null) {
			Minecraft mc = Minecraft.getMinecraft();
			font = new Fly(mc.gameSettings, new ResourceLocation("thread:textures/nm.png"), mc.renderEngine, false);
			if (mc.gameSettings.language != null) {
				font.setUnicodeFlag(mc.isUnicode());
				font.setBidiFlag(mc.getLanguageManager().isCurrentLanguageBidirectional());
			}
		}
		return font;
	}

	private Fly(GameSettings gameSettingsIn, ResourceLocation location, TextureManager textureManagerIn, boolean unicode) {
		super(gameSettingsIn, location, textureManagerIn, unicode);
	}

	private static long milliTime() {
		return System.nanoTime() / 1000000L;
	}

	private static double rangeRemap(double value, double low1, double high1, double low2, double high2) {
		return low2 + (value - low1) * (high2 - low2) / (high1 - low1);
	}

	public int drawStringWithShadow(String text, float x, float y, int color) {
		float huehuehueStep;
		float huehuehue;
		if (text == "King sword") {
			huehuehue = (float) milliTime() / 70.0F % 1.0F;
			huehuehueStep = (float) rangeRemap(Math.sin((float) milliTime() / 120.0F) % 10.0D, -5.0D, 60.0D, 58.0D, 56.0D);
		} else {
			huehuehue = (float) milliTime() / 700.0F % 1.0F;
			huehuehueStep = (float) rangeRemap(Math.sin((float) milliTime() / 12.0F) % 6.28318D, -0.9D, 2.5D, 0.025D, 0.15D);
		}
		float posX = x;

		String drawText = TextFormatting.getTextWithoutFormattingCodes(text);
		for (int i = 0; i < drawText.length(); i++) {
			int c = color & 0xFF000000 | MathHelper.hsvToRGB(huehuehue, 0.6F, 1.0F);
			float yOffset = (float) (Math.sin(i + (float) milliTime() / 3.0F) );
			float xOffset = (float) (Math.cos(i + (float) milliTime() / 3.0F) );
			posX = super.drawStringWithShadow(String.valueOf(drawText.charAt(i)), posX + xOffset, y + yOffset, c);
			huehuehue += huehuehueStep;
			huehuehue %= 10.0F;
		}
		return (int)posX;
	}
}
