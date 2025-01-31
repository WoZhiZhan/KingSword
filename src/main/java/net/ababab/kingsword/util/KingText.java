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
public class KingText
  extends FontRenderer
{
  public static KingText font;
  
  public static KingText getFont()
  {
    if (font == null)
    {
      Minecraft mc = Minecraft.getMinecraft();
      font = new KingText(mc.gameSettings, new ResourceLocation("godsword:textures/ascii.png"), mc.renderEngine, false);
      if (mc.gameSettings.language != null)
      {
        font.setUnicodeFlag(mc.isUnicode());
        font.setBidiFlag(mc.getLanguageManager().isCurrentLanguageBidirectional());
      }
    }
    return font;
  }
  
  public boolean first = false;
  
  public static KingText getInstanceAndRefresh()
  {
    font.first = true;
    return font;
  }
  
  private KingText(GameSettings gameSettingsIn, ResourceLocation location, TextureManager textureManagerIn, boolean unicode)
  {
    super(gameSettingsIn, location, textureManagerIn, unicode);
  }
  
  private static long milliTime()
  {
    return System.nanoTime() / 1000000L;
  }
  
  public int drawStringWithShadow(String text, float x, float y, int color)
  {
    float huehuehue = (float)milliTime() / 700.0F % 1.0F;
    float posX = x;
    String drawText = TextFormatting.getTextWithoutFormattingCodes(text);
    float huehuehueStep = 1.0F / (drawText.length() * 4);
    for (int i = 0; i < drawText.length(); i++)
    {
      float xOffset = (float)(Math.cos(i + (float)milliTime() / 300.0F) * 0.0D);
      posX = access$001(this, String.valueOf(drawText.charAt(i)), posX + xOffset, y + (float)(Math.sin(i + (float)milliTime() / 300.0F) * 0.0D), 0xFF000000 & color | MathHelper.hsvToRGB(huehuehue, 0.8F, 1.0F));
      huehuehue = (huehuehue + huehuehueStep) % 1.0F;
    }
    return (int)posX;
  }

  private float access$001(KingText kingText, String valueOf, float v, float v1, int i) {
      return 0;
  }
}
