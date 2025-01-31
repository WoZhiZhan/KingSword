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
public class Font
  extends FontRenderer
{
  private static Font font;
  
  public static Font getFont()
  {
    if (font == null)
    {
      Minecraft mc = Minecraft.getMinecraft();
      font = new Font(mc.gameSettings, new ResourceLocation("textures/font/ascii.png"), mc.renderEngine, false);
      if (mc.gameSettings.language != null)
      {
        font.setUnicodeFlag(mc.isUnicode());
        font.setBidiFlag(mc.getLanguageManager().isCurrentLanguageBidirectional());
      }
    }
    return font;
  }
  
  private Font(GameSettings gameSettingsIn, ResourceLocation location, TextureManager textureManagerIn, boolean unicode)
  {
    super(gameSettingsIn, location, textureManagerIn, unicode);
  }
  
  private static long milliTime()
  {
    return System.nanoTime() / 1000000L;
  }
  
  private static double rangeRemap(double value, double low1, double high1, double low2, double high2)
  {
    return low2 + (value - low1) * (high2 - low2) / (high1 - low1);
  }
  
  public int drawStringWithShadow(String text, float x, float y, int color)
  {
    float huehuehue = (float)milliTime() / 700.0F % 1.0F;
    float huehuehueStep = (float)rangeRemap(Math.sin((float)milliTime() / 1200.0F) % 6.28318D, -0.9D, 2.5D, 0.025D, 0.15D);
    float posX = x;
    
    String drawText = TextFormatting.getTextWithoutFormattingCodes(text);
    for (int i = 0; i < drawText.length(); i++)
    {
      int c = color & 0xFF000000 | MathHelper.hsvToRGB(huehuehue, 0.8F, 1.0F);
      posX = super.drawStringWithShadow(String.valueOf(drawText.charAt(i)), posX, y, c);
      huehuehue += huehuehueStep;
      huehuehue %= 1.0F;
    }
    return (int)posX;
  }
}
