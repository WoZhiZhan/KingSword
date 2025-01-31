package net.ababab.kingsword.gui.overlay;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface OverlayDeadovar {
    @SideOnly(Side.CLIENT)
    void init(FMLInitializationEvent event);
}
