package net.ababab.kingsword;

import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@ElementsKingswordMod.ModElement.Tag
public class SuperGui extends ElementsKingswordMod.ModElement {

	public SuperGui(ElementsKingswordMod instance) {
		super(instance, 26);
			MinecraftForge.EVENT_BUS.register(this);
	}

	public static boolean ccc = false;

	@SubscribeEvent
    public void nb(GuiOpenEvent g){
    	if (ccc){
            g.setCanceled(true);
        }
    }
    }
