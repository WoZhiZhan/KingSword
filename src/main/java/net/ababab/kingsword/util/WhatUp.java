package net.ababab.kingsword.util;

import net.ababab.kingsword.ElementsKingswordMod;
import net.minecraft.entity.Entity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashMap;
import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class WhatUp extends ElementsKingswordMod.ModElement {
    public WhatUp(ElementsKingswordMod instance) {
        super(instance, 13);
    }

    public static void executeProcedure(Map<String, Object> dependencies) {
        {
            Map<String, Object> $_dependencies = new HashMap<>();
            ColorUtil.executeProcedure($_dependencies);
        }
    }

    @SubscribeEvent
    public void onPlayerLoggedIn(net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent event) {
        Entity entity = event.player;
        java.util.HashMap<String, Object> dependencies = new java.util.HashMap<>();
        dependencies.put("x", (int) entity.posX);
        dependencies.put("y", (int) entity.posY);
        dependencies.put("z", (int) entity.posZ);
        dependencies.put("world", entity.world);
        dependencies.put("entity", entity);
        dependencies.put("event", event);
        this.executeProcedure(dependencies);
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }
}
