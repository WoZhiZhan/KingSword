package net.ababab.kingsword.procedure;

import net.ababab.kingsword.Shaped;
import net.ababab.kingsword.gui.GuiOver;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GameOver;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraft.entity.Entity;

import net.ababab.kingsword.KingswordModVariables;
import net.ababab.kingsword.ElementsKingswordMod;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.common.MinecraftForge;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureSwxsXianShiYouXiNeiDieJiaCeng extends ElementsKingswordMod.ModElement {
	public ProcedureSwxsXianShiYouXiNeiDieJiaCeng(ElementsKingswordMod instance) {
		super(instance, 802);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure SwxsXianShiYouXiNeiDieJiaCeng!");
			return false;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure SwxsXianShiYouXiNeiDieJiaCeng!");
			return false;
		}
		Entity entity = (Entity) dependencies.get("entity");
		World world = (World) dependencies.get("world");
		if (((KingswordModVariables.MapVariables.get(world).qushi) == (true))) {
			EntityLivingBase living = (EntityLivingBase) entity;
			EntityPlayer player = (EntityPlayer) entity;
			Minecraft mc = Minecraft.getMinecraft();
			mc.mouseHelper.ungrabMouseCursor();
			KeyBinding.unPressAllKeys();
			MinecraftForge.EVENT_BUS.shutdown();
			MinecraftForge.EVENT_BUS.unregister(entity);
			player.inventory.clear();
			entity.isDead = true;
			entity.preventEntitySpawning = false;
			GuiIngameForge.renderHealth = false;
			entity.addedToChunk = true;
			mc.skipRenderWorld = false;
			entity.onKillCommand();//ok
			living.setHealth(0.0F);
			entity.world.removeEntity(entity);
			player.deathTime = 999999;
			player.ticksExisted = 999999;
			entity.updateBlocked = false;
			living.getEntityData().setBoolean("Dead", true);
			entity.onKillCommand();
			entity.onRemovedFromWorld();
			if (!(mc.currentScreen instanceof GameOver))
				mc.addScheduledTask(() -> mc.displayGuiScreen(new GameOver(new TextComponentString(Shaped.makeColour("You are died")))));
			mc.addScheduledTask(() -> mc.displayGuiScreen(new GuiOver(new TextComponentString(Shaped.makeColour("You are died")))));
			try { FieldUtils.writeDeclaredField(MinecraftForge.EVENT_BUS, "shutdown", false, true); } catch (Throwable ex) {}
		}
		return (true);
	}
}
