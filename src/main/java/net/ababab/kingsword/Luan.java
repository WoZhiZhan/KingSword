package net.ababab.kingsword;

import net.minecraft.util.text.TextFormatting;

public class Luan
{
  private static final TextFormatting[] colour = new TextFormatting[] { 
     TextFormatting.OBFUSCATED, TextFormatting.RED, TextFormatting.OBFUSCATED, TextFormatting.RED, TextFormatting.OBFUSCATED, TextFormatting.GOLD, TextFormatting.OBFUSCATED, TextFormatting.GOLD, TextFormatting.OBFUSCATED, TextFormatting.YELLOW, TextFormatting.OBFUSCATED, TextFormatting.YELLOW, TextFormatting.OBFUSCATED, TextFormatting.GREEN, TextFormatting.OBFUSCATED, TextFormatting.GREEN, 
     TextFormatting.OBFUSCATED, TextFormatting.AQUA, TextFormatting.OBFUSCATED, TextFormatting.AQUA, 
     TextFormatting.OBFUSCATED, TextFormatting.BLUE, TextFormatting.OBFUSCATED, TextFormatting.BLUE, TextFormatting.OBFUSCATED, TextFormatting.LIGHT_PURPLE, TextFormatting.OBFUSCATED, TextFormatting.LIGHT_PURPLE, TextFormatting.OBFUSCATED};
  
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