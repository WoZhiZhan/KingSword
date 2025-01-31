package net.ababab.kingsword.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public
class FuckFont
        extends FontRenderer
{
    private static FuckFont font;

    public static FuckFont getFont()
    {
        if (font == null)
        {
            Minecraft mc = Minecraft.getMinecraft();
            font = new FuckFont(mc.gameSettings, new ResourceLocation("textures/font/ascii.png"), mc.renderEngine, false);
            if (mc.gameSettings.language != null)
            {
                font.setUnicodeFlag(mc.isUnicode());
                font.setBidiFlag(mc.getLanguageManager().isCurrentLanguageBidirectional());
            }
        }
        return font;
    }

    private FuckFont(GameSettings gameSettingsIn, ResourceLocation location, TextureManager textureManagerIn, boolean unicode)
    {
        super(gameSettingsIn, location, textureManagerIn, unicode);
    }

    private static long milliTime()
    {
        return System.currentTimeMillis() & 0x3FFF;
    }

    public int drawStringWithShadow(String text, float x, float y, int color)
    {
        return drawColorFont(text, x, y, color);
    }

    public int drawColorFont(String string, float x, float y, int color)
    {
        string = TextFormatting.getTextWithoutFormattingCodes(string);

        float getColor = (float)milliTime() % 3601.0F;
        int drawColor = 0;
        for (int i = 0; i < string.length(); i++)
        {
            char c = string.charAt(i);
            String str = String.valueOf(c);

            drawColor = HSBtoRGB(getColor / 1000.0F, 0.7F, 0.9F);

            super.drawStringWithShadow(str, x, y, drawColor);

            x += getCharWidth(c);

            getColor += 11.0F;
            getColor %= 3601.0F;
        }
        return (int)x;
    }

    public static float[] RGBtoHSB(int r, int g, int b, float[] hsbvals)
    {
        if (hsbvals == null) {
            hsbvals = new float[3];
        }
        int cmax = r > g ? r : g;
        if (b > cmax) {
            cmax = b;
        }
        int cmin = r < g ? r : g;
        if (b < cmin) {
            cmin = b;
        }
        float brightness = cmax / 255.0F;
        float saturation;
        if (cmax != 0) {
            saturation = (cmax - cmin) / cmax;
        } else {
            saturation = 0.0F;
        }
        float hue;
        if (saturation == 0.0F)
        {
            hue = 0.0F;
        }
        else
        {
            float redc = (cmax - r) / (cmax - cmin);
            float greenc = (cmax - g) / (cmax - cmin);
            float bluec = (cmax - b) / (cmax - cmin);
            if (r == cmax)
            {
                hue = bluec - greenc;
            }
            else
            {
                if (g == cmax) {
                    hue = 2.0F + redc - bluec;
                } else {
                    hue = 4.0F + greenc - redc;
                }
            }
            hue /= 6.0F;
            if (hue < 0.0F) {
                hue += 1.0F;
            }
        }
        hsbvals[0] = hue;
        hsbvals[1] = saturation;
        hsbvals[2] = brightness;
        return hsbvals;
    }

    public static int HSBtoRGB(float hue, float saturation, float brightness)
    {
        int r = 0;int g = 0;int b = 0;
        if (saturation == 0.0F)
        {
            r = g = b = (int)(brightness * 255.0F + 0.5F);
        }
        else
        {
            float h = (hue - (float)Math.floor(hue)) * 6.0F;
            float f = h - (float)Math.floor(h);
            float p = brightness * (1.0F - saturation);
            float q = brightness * (1.0F - saturation * f);
            float t = brightness * (1.0F - saturation * (1.0F - f));
            switch ((int)h)
            {
                case 0:
                    r = (int)(brightness * 255.0F + 0.5F);
                    g = (int)(t * 255.0F + 0.5F);
                    b = (int)(p * 255.0F + 0.5F);
                    break;
                case 1:
                    r = (int)(q * 255.0F + 0.5F);
                    g = (int)(brightness * 255.0F + 0.5F);
                    b = (int)(p * 255.0F + 0.5F);
                    break;
                case 2:
                    r = (int)(p * 255.0F + 0.5F);
                    g = (int)(brightness * 255.0F + 0.5F);
                    b = (int)(t * 255.0F + 0.5F);
                    break;
                case 3:
                    r = (int)(p * 255.0F + 0.5F);
                    g = (int)(q * 255.0F + 0.5F);
                    b = (int)(brightness * 255.0F + 0.5F);
                    break;
                case 4:
                    r = (int)(t * 255.0F + 0.5F);
                    g = (int)(p * 255.0F + 0.5F);
                    b = (int)(brightness * 255.0F + 0.5F);
                    break;
                case 5:
                    r = (int)(brightness * 255.0F + 0.5F);
                    g = (int)(p * 255.0F + 0.5F);
                    b = (int)(q * 255.0F + 0.5F);
            }
        }
        return 0xFF000000 | r << 16 | g << 8 | b << 0;
    }
}
