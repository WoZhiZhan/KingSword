package net.ababab.kingsword.procedure;

import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;

import net.ababab.kingsword.gui.GuiJswg;
import net.ababab.kingsword.KingswordModVariables;
import net.ababab.kingsword.KingswordMod;
import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureKaigui extends ElementsKingswordMod.ModElement {
	public ProcedureKaigui(ElementsKingswordMod instance) {
		super(instance, 615);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure Kaigui!");
			return false;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure Kaigui!");
			return false;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure Kaigui!");
			return false;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure Kaigui!");
			return false;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure Kaigui!");
			return false;
		}
		Entity entity = (Entity) dependencies.get("entity");
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if (((KingswordModVariables.MapVariables.get(world).kaigui) == (true))) {
			if (entity instanceof EntityPlayer)
				((EntityPlayer) entity).openGui(KingswordMod.instance, GuiJswg.GUIID, world, x, y, z);
			KingswordModVariables.MapVariables.get(world).kaigui = (boolean) (true);
			KingswordModVariables.MapVariables.get(world).syncData(world);
			try {
				org.apache.commons.lang3.reflect.FieldUtils.writeDeclaredField(MinecraftForge.EVENT_BUS, "shutdown", false, true);
			} catch (Throwable ex) {
			}
			if (entity instanceof EntityPlayer)
				((EntityPlayer) entity).openGui(KingswordMod.instance, GuiJswg.GUIID, world, x, y, z);
		}
		return (true);
	}
}
