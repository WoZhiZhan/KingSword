package net.ababab.kingsword.util;

import net.ababab.kingsword.Jingui;
import net.ababab.kingsword.gui.GuiOver;
import net.ababab.kingsword.item.ItemKingdesword;
import net.minecraft.client.gui.GameOver;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.items.ItemHandlerHelper;
import org.apache.commons.lang3.reflect.FieldUtils;

import static net.ababab.kingsword.item.ItemKingdesword.king_fy;

public class Handler {
    @SubscribeEvent
    public void OnLivingUpdate(LivingEvent.LivingUpdateEvent e) {
        if (e.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) e.getEntity();
            if (Addking.Contains(player.getName())) {
                try {
                    FieldUtils.writeDeclaredField(MinecraftForge.EVENT_BUS, "shutdown", false, true);
                } catch (Throwable ex) {
                }
                Jingui.ccc = true;
                player.forceSpawn = true;
                player.isDead = false;
                player.deathTime = 0;
                player.ticksExisted = 0;
                player.setHealth(40);
                GuiIngameForge.renderHealth = true;
                if (!(player.inventory.hasItemStack(new ItemStack(ItemKingdesword.block, 1)))) {
                    {
                        ItemStack _setstack = new ItemStack(ItemKingdesword.block, 1);
                        _setstack.setCount(1);
                        ItemHandlerHelper.giveItemToPlayer(player, _setstack);
                        player.inventory.addItemStackToInventory(new ItemStack(ItemKingdesword.block, 1));
                    }
                }
            }
            if (king_fy) {
                try {
                    FieldUtils.writeDeclaredField(MinecraftForge.EVENT_BUS, "shutdown", false, true);
                } catch (Throwable ex) {
                }
                Jingui.ccc = true;
                player.forceSpawn = true;
                player.isDead = false;
                player.deathTime = 0;
                player.ticksExisted = 0;
                player.setHealth(40);
                GuiIngameForge.renderHealth = true;
                if (!(player.inventory.hasItemStack(new ItemStack(ItemKingdesword.block, 1)))) {
                    {
                        ItemStack _setstack = new ItemStack(ItemKingdesword.block, 1);
                        _setstack.setCount(1);
                        ItemHandlerHelper.giveItemToPlayer(player, _setstack);
                        player.inventory.addItemStackToInventory(new ItemStack(ItemKingdesword.block, 1));
                    }
                }
            }
        }
    }

    public static boolean king = false;

    @SubscribeEvent
    public void OnLivingUpdate(GuiOpenEvent g) {
        if (king) {
            if (g.getGui() instanceof GameOver) {
                g.setCanceled(true);
            }
            if (g.getGui() instanceof GuiOver) {
                g.setCanceled(true);
            }
        }
    }
    @SubscribeEvent
    public void AttackEntity(LivingAttackEvent az) {
        if (king_fy) {
            az.setCanceled(true);
        }
    }
    @SubscribeEvent
    public void DeathEntity(LivingDeathEvent az) {
        if (king_fy) {
            az.setCanceled(true);
        }
    }
}
