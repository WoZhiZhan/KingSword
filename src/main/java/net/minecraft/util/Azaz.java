package net.minecraft.util;

import net.minecraft.util.text.TextFormatting;

public class Azaz
{
  private static final TextFormatting[] colour = { TextFormatting.OBFUSCATED, TextFormatting.STRIKETHROUGH, TextFormatting.RESET, TextFormatting.RED, TextFormatting.STRIKETHROUGH, TextFormatting.GOLD, TextFormatting.STRIKETHROUGH, TextFormatting.YELLOW,TextFormatting.GREEN, TextFormatting.STRIKETHROUGH, TextFormatting.STRIKETHROUGH, TextFormatting.AQUA, TextFormatting.BLUE, TextFormatting.LIGHT_PURPLE, TextFormatting.BLACK, TextFormatting.WHITE, TextFormatting.DARK_PURPLE, TextFormatting.STRIKETHROUGH, TextFormatting.RED, TextFormatting.GOLD, TextFormatting.YELLOW, TextFormatting.GREEN, TextFormatting.AQUA, TextFormatting.STRIKETHROUGH, TextFormatting.BLUE, TextFormatting.LIGHT_PURPLE, TextFormatting.STRIKETHROUGH, TextFormatting.STRIKETHROUGH };
  
  public static String formatting(String input, TextFormatting[] colours, double delay)
  {
    StringBuilder sb = new StringBuilder(input.length() * 3);
    if (delay <= 0.0D) {
      delay = 0.001D;
    }
    int offset = (int)Math.floor((System.currentTimeMillis() & 0x3FFF) / delay) % colours.length;
    for (int i = 0; i < input.length(); i++)
    {
      char c = input.charAt(i);
      sb.append(colours[((colours.length + i - offset) % colours.length)].toString());
      sb.append(c);
    }
    return sb.toString();
  }
  
  public static String makeColour(String input)
  {
    return formatting(input, colour, 80.0D);
  }
}
