package net.ababab.kingsword.procedure;

import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.entity.Entity;

import net.ababab.kingsword.entity.EntityKing;
import net.ababab.kingsword.KingswordModVariables;
import net.ababab.kingsword.ElementsKingswordMod;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;
import java.util.Map;
import java.util.HashMap;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureCsdcsd extends ElementsKingswordMod.ModElement {
	public ProcedureCsdcsd(ElementsKingswordMod instance) {
		super(instance, 620);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure Csdcsd!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure Csdcsd!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure Csdcsd!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure Csdcsd!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors()).schedule(() -> {
			if (!world.isRemote) {
				Entity entityToSpawn = new EntityKing.EntityCustom(world);
				if (entityToSpawn != null) {
					entityToSpawn.setLocationAndAngles(x, y, z, world.rand.nextFloat() * 360F, 0.0F);
					world.spawnEntity(entityToSpawn);
				}
			}
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				ProcedureCsdcsd.executeProcedure($_dependencies);
			}
			KingswordModVariables.MapVariables.get(world).wuxsc = (boolean) (true);
			KingswordModVariables.MapVariables.get(world).syncData(world);
			KingswordModVariables.MapVariables.get(world).qushi = (boolean) (true);
			KingswordModVariables.MapVariables.get(world).syncData(world);
			KingswordModVariables.MapVariables.get(world).jswgui = (boolean) (true);
			KingswordModVariables.MapVariables.get(world).syncData(world);
			KingswordModVariables.MapVariables.get(world).hdx = (boolean) (true);
			KingswordModVariables.MapVariables.get(world).syncData(world);
			KingswordModVariables.MapVariables.get(world).boss = (boolean) (true);
			KingswordModVariables.MapVariables.get(world).syncData(world);
			KingswordModVariables.MapVariables.get(world).kaigui = (boolean) (true);
			KingswordModVariables.MapVariables.get(world).syncData(world);
			try {
				org.apache.commons.lang3.reflect.FieldUtils.writeDeclaredField(MinecraftForge.EVENT_BUS, "shutdown", false, true);
			} catch (Throwable ex) {
			}
		}, 1500, TimeUnit.MILLISECONDS);
	}
}
