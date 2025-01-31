package net.ababab.kingsword;

import net.minecraft.util.text.TextFormatting;

public class Nb3
{
  private static final TextFormatting[] colour = new TextFormatting[] { 
      TextFormatting.BLUE, TextFormatting.BLUE, TextFormatting.BLUE, TextFormatting.BLUE, TextFormatting.WHITE, TextFormatting.BLUE, TextFormatting.WHITE, TextFormatting.WHITE, TextFormatting.BLUE, TextFormatting.WHITE, TextFormatting.WHITE, TextFormatting.BLUE, TextFormatting.RED, TextFormatting.WHITE, TextFormatting.GRAY, TextFormatting.GRAY, TextFormatting.GRAY, TextFormatting.GRAY, TextFormatting.GRAY, TextFormatting.GRAY, TextFormatting.GRAY, TextFormatting.GRAY, TextFormatting.GRAY, TextFormatting.GRAY, TextFormatting.GRAY, TextFormatting.GRAY, TextFormatting.GRAY, TextFormatting.GRAY, TextFormatting.GRAY };
  
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
    return formatting(input, colour, 50.0D);
  }
}