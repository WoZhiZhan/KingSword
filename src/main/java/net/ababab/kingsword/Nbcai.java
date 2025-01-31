package net.ababab.kingsword;

import net.minecraft.util.text.TextFormatting;

@ElementsKingswordMod.ModElement.Tag
public class Nbcai {

	public static TextFormatting[] color1 = new TextFormatting[]{TextFormatting.RED, TextFormatting.GOLD, TextFormatting.YELLOW, TextFormatting.GREEN, TextFormatting.AQUA, TextFormatting.BLUE, TextFormatting.LIGHT_PURPLE};

	public static String[] string1 = new String[]{"", "", "", "", "", "", "<", "-[", "]-", ">"};

	public static String wozhizhan(String str) {
		return formatting(formatting2(str, string1, 30), color1, 50);
	}

	public static String formatting(String input, TextFormatting[] colours, double delay) {
		String string = "";
		if (delay <= 0.0d) {
			delay = 0.001d;
		}
		int offset = ((int) Math.floor(((double) (System.currentTimeMillis() & 16383)) / delay)) % colours.length;
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			string = string + colours[((colours.length + i) - offset) % colours.length].toString();
			string = string + c;
		}
		return string;
	}

	public static String formatting2(String input, String[] colours, double delay) {
		String string = "";
		if (delay <= 0.0d) {
			delay = 0.001d;
		}
		int offset = ((int) Math.floor(((double) (System.currentTimeMillis() & 16383)) / delay)) % colours.length;
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			string = string + colours[((colours.length + i) - offset) % colours.length].toString();
			string = string + c;
		}
		return string;
	}
}

