package net.ababab.kingsword.procedure;

import net.ababab.kingsword.ElementsKingswordMod;
import net.minecraft.client.gui.GameOver;

@ElementsKingswordMod.ModElement.Tag
public class Kingqz extends ElementsKingswordMod.ModElement {

	public Kingqz(ElementsKingswordMod instance) {
		super(instance, 26);
	}

	public GameOver guiGameOver= null;

	public Object setGui(GameOver gui) {
		return null;
	}

}

