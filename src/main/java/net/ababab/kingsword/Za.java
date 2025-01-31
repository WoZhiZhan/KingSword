package net.ababab.kingsword;

import net.minecraft.util.text.TextFormatting;

public class Za
{
  private static final TextFormatting[] colour = { TextFormatting.BLACK, TextFormatting.GRAY, TextFormatting.DARK_RED, TextFormatting.LIGHT_PURPLE, TextFormatting.DARK_PURPLE, TextFormatting.YELLOW, TextFormatting.GREEN, TextFormatting.OBFUSCATED, TextFormatting.ITALIC, TextFormatting.DARK_AQUA, TextFormatting.LIGHT_PURPLE, TextFormatting.BLUE, TextFormatting.AQUA, TextFormatting.DARK_GREEN, TextFormatting.DARK_PURPLE, TextFormatting.DARK_BLUE, TextFormatting.DARK_GRAY, TextFormatting.AQUA, TextFormatting.GOLD, TextFormatting.RED };
  
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