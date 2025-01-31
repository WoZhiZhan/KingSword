package net.ababab.kingsword.util.text;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;

public class KingswordFontRenderer extends FontRenderer {

    public static final KingswordFontRenderer kingswordFontRenderer = new KingswordFontRenderer(Minecraft.getMinecraft().gameSettings, new ResourceLocation("minecraft:textures/font/ascii.png"), Minecraft.getMinecraft().renderEngine, false);

    static {
        ((IReloadableResourceManager)Minecraft.getMinecraft().getResourceManager()).registerReloadListener(kingswordFontRenderer);
    }

    public static KingswordFontRenderer getKingswordFontRenderer() {
        return kingswordFontRenderer;
    }

    public KingswordFontRenderer(GameSettings gameSettingsIn, ResourceLocation location, TextureManager textureManagerIn, boolean unicode) {
        super(gameSettingsIn, location, textureManagerIn, unicode);
    }

    @Override
    public int drawString(String text, float x, float y, int color, boolean dropShadow) {
         {

            float posX = x;
            float transcendF = Minecraft.getSystemTime() / 700.0F % 1;
            float transcendS = (float) MathUtil.rangeRemap(Math.sin((double)((float)Minecraft.getSystemTime() / 2000.0F)) % 6.28318D, -1.0D, 1.0D, 0.01D, 0.15D);
             String drawText = TextFormatting.getTextWithoutFormattingCodes(text);
             for (int i = 0; i < drawText.length(); i++)
             {
                int c = color & -16777216 | MathHelper.hsvToRGB(transcendF, 0.5F, 1F);
                posX = (float)(super.drawString((String.valueOf(drawText.charAt(i))), posX, y, c, true) -1);
                transcendF += transcendS;
                transcendF %= 1.0F;
            }
            return (int)posX;
        }
    }
}