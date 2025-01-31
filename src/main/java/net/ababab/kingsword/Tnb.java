package net.ababab.kingsword;

import net.minecraft.util.text.TextFormatting;

public class Tnb
{
  private static final TextFormatting[] colour = new TextFormatting[] { 
      TextFormatting.BLACK, TextFormatting.GREEN, TextFormatting.YELLOW, TextFormatting.DARK_PURPLE, TextFormatting.STRIKETHROUGH, TextFormatting.RED, TextFormatting.BOLD, TextFormatting.ITALIC, TextFormatting.BLUE };
  
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