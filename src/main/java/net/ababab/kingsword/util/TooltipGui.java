package net.ababab.kingsword.util;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderTooltipEvent;

import javax.annotation.Nonnull;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TooltipGui {
        public static void drawHoveringText(@Nonnull ItemStack stack, List<String> textLines, int mouseX, int mouseY, int screenWidth, int screenHeight, int maxTextWidth, FontRenderer font)
        {
            if (!textLines.isEmpty())
            {
                RenderTooltipEvent.Pre event = new RenderTooltipEvent.Pre(stack, textLines, mouseX, mouseY, screenWidth, screenHeight, maxTextWidth, font);
                mouseX = event.getX();
                mouseY = event.getY();
                screenWidth = event.getScreenWidth();
                screenHeight = event.getScreenHeight();
                maxTextWidth = event.getMaxWidth();
                font = event.getFontRenderer();

                GlStateManager.disableRescaleNormal();
                RenderHelper.disableStandardItemLighting();
                GlStateManager.disableLighting();
                GlStateManager.disableDepth();
                int tooltipTextWidth = 0;
                for (String textLine : textLines)
                {
                    int textLineWidth = font.getStringWidth(textLine);
                    if (textLineWidth > tooltipTextWidth) {
                        tooltipTextWidth = textLineWidth;
                    }
                }
                boolean needsWrap = false;

                int titleLinesCount = 1;
                int tooltipX = mouseX + 12;
                if (tooltipX + tooltipTextWidth + 4 > screenWidth)
                {
                    tooltipX = mouseX - 16 - tooltipTextWidth;
                    if (tooltipX < 4)
                    {
                        if (mouseX > screenWidth / 2) {
                            tooltipTextWidth = mouseX - 12 - 8;
                        } else {
                            tooltipTextWidth = screenWidth - 16 - mouseX;
                        }
                        needsWrap = true;
                    }
                }
                if ((maxTextWidth > 0) && (tooltipTextWidth > maxTextWidth))
                {
                    tooltipTextWidth = maxTextWidth;
                    needsWrap = true;
                }
                if (needsWrap)
                {
                    int wrappedTooltipWidth = 0;
                    List<String> wrappedTextLines = new ArrayList();
                    for (int i = 0; i < textLines.size(); i++)
                    {
                        String textLine = (String)textLines.get(i);
                        List<String> wrappedLine = font.listFormattedStringToWidth(textLine, tooltipTextWidth);
                        if (i == 0) {
                            titleLinesCount = wrappedLine.size();
                        }
                        for (String line : wrappedLine)
                        {
                            int lineWidth = font.getStringWidth(line);
                            if (lineWidth > wrappedTooltipWidth) {
                                wrappedTooltipWidth = lineWidth;
                            }
                            wrappedTextLines.add(line);
                        }
                    }
                    tooltipTextWidth = wrappedTooltipWidth;
                    textLines = wrappedTextLines;
                    if (mouseX > screenWidth / 2) {
                        tooltipX = mouseX - 16 - tooltipTextWidth;
                    } else {
                        tooltipX = mouseX + 12;
                    }
                }
                int tooltipY = mouseY - 12;
                int tooltipHeight = 8;
                if (textLines.size() > 1)
                {
                    tooltipHeight += (textLines.size() - 1) * 10;
                    if (textLines.size() > titleLinesCount) {
                        tooltipHeight += 2;
                    }
                }
                if (tooltipY < 4) {
                    tooltipY = 4;
                } else if (tooltipY + tooltipHeight + 4 > screenHeight) {
                    tooltipY = screenHeight - tooltipHeight - 4;
                }
                int zLevel = 300;

                int backgroundColor = 0;
                int borderColorStart = 0;
                int borderColorEnd = 0;
                drawGradientRect(300, tooltipX - 3, tooltipY - 4, tooltipX + tooltipTextWidth + 3, tooltipY - 3, backgroundColor, backgroundColor);
                drawGradientRect(300, tooltipX - 3, tooltipY + tooltipHeight + 3, tooltipX + tooltipTextWidth + 3, tooltipY + tooltipHeight + 4, backgroundColor, backgroundColor);
                drawGradientRect(300, tooltipX - 3, tooltipY - 3, tooltipX + tooltipTextWidth + 3, tooltipY + tooltipHeight + 3, backgroundColor, backgroundColor);
                drawGradientRect(300, tooltipX - 4, tooltipY - 3, tooltipX - 3, tooltipY + tooltipHeight + 3, backgroundColor, backgroundColor);
                drawGradientRect(300, tooltipX + tooltipTextWidth + 3, tooltipY - 3, tooltipX + tooltipTextWidth + 4, tooltipY + tooltipHeight + 3, backgroundColor, backgroundColor);
                drawGradientRect(300, tooltipX - 3, tooltipY - 3 + 1, tooltipX - 3 + 1, tooltipY + tooltipHeight + 3 - 1, borderColorStart, borderColorEnd);
                drawGradientRect(300, tooltipX + tooltipTextWidth + 2, tooltipY - 3 + 1, tooltipX + tooltipTextWidth + 3, tooltipY + tooltipHeight + 3 - 1, borderColorStart, borderColorEnd);
                drawGradientRect(300, tooltipX - 3, tooltipY - 3, tooltipX + tooltipTextWidth + 3, tooltipY - 3 + 1, borderColorStart, borderColorStart);
                drawGradientRect(300, tooltipX - 3, tooltipY + tooltipHeight + 2, tooltipX + tooltipTextWidth + 3, tooltipY + tooltipHeight + 3, borderColorEnd, borderColorEnd);

                int tooltipTop = tooltipY;
                for (int lineNumber = 0; lineNumber < textLines.size(); lineNumber++)
                {
                    String line = (String)textLines.get(lineNumber);
                    font.drawStringWithShadow(line, tooltipX, tooltipY, -1);
                    if (lineNumber + 1 == titleLinesCount) {
                        tooltipY += 2;
                    }
                    tooltipY += 10;
                }
                GlStateManager.enableLighting();
                GlStateManager.enableDepth();
                RenderHelper.enableStandardItemLighting();
                GlStateManager.enableRescaleNormal();
            }
        }

        public static void drawGradientRect(int zLevel, int left, int top, int right, int bottom, int startColor, int endColor)
        {
            Random random = new Random();
            Color c = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256), random.nextInt(256));
            Color c2 = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256), random.nextInt(256));
            float startAlpha = (c.getRGB() >> 24 & 0xFF) / 255.0F;
            float startRed = (c.getRGB() >> 16 & 0xFF) / 255.0F;
            float startGreen = (c.getRGB() >> 8 & 0xFF) / 255.0F;
            float startBlue = (c.getRGB() & 0xFF) / 255.0F;
            float endAlpha = (c2.getRGB() >> 24 & 0xFF) / 255.0F;
            float endRed = (c2.getRGB() >> 16 & 0xFF) / 255.0F;
            float endGreen = (c2.getRGB() >> 8 & 0xFF) / 255.0F;
            float endBlue = (c2.getRGB() & 0xFF) / 255.0F;

            GlStateManager.disableTexture2D();
            GlStateManager.enableBlend();
            GlStateManager.disableAlpha();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            GlStateManager.shadeModel(7425);

            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder buffer = tessellator.getBuffer();
            buffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
            buffer.pos(right, top, zLevel).color(startRed, startGreen, startBlue, startAlpha).endVertex();
            buffer.pos(left, top, zLevel).color(startRed, startGreen, startBlue, startAlpha).endVertex();
            buffer.pos(left, bottom, zLevel).color(endRed, endGreen, endBlue, endAlpha).endVertex();
            buffer.pos(right, bottom, zLevel).color(endRed, endGreen, endBlue, endAlpha).endVertex();
            tessellator.draw();

            GlStateManager.shadeModel(7424);
            GlStateManager.disableBlend();
            GlStateManager.enableAlpha();
            GlStateManager.enableTexture2D();
        }
    }

