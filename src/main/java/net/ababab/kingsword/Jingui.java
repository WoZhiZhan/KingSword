package net.ababab.kingsword;

import net.minecraft.client.gui.GameOver;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@ElementsKingswordMod.ModElement.Tag
public class Jingui extends ElementsKingswordMod.ModElement {

	public Jingui(ElementsKingswordMod instance) {
		super(instance, 26);
			MinecraftForge.EVENT_BUS.register(this);
	}

	public static boolean ccc = false;

	@SubscribeEvent
    public void nb(GuiOpenEvent g){
    	if (ccc){
        if (g.getGui() instanceof GameOver){
            g.setCanceled(true);
        }
    }
    }
}
