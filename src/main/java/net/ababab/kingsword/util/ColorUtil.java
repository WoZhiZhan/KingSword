package net.ababab.kingsword.util;

import net.ababab.kingsword.ElementsKingswordMod;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static net.ababab.kingsword.item.ItemKingdesword.colors;

public class ColorUtil extends ElementsKingswordMod.ModElement {
    public ColorUtil(ElementsKingswordMod instance) {
        super(instance, 13);
    }

    public static void executeProcedure(Map<String, Object> dependencies) {
        colors = true;
        Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors()).schedule(() -> {
            colors = false;
            Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors()).schedule(() -> {
                {
                    Map<String, Object> $_dependencies = new HashMap<>();
                    ColorUtil.executeProcedure($_dependencies);
                }
            }, 2000, TimeUnit.MILLISECONDS);
        }, 2000, TimeUnit.MILLISECONDS);
    }
}
