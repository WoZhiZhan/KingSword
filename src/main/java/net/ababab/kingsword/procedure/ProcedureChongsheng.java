package net.ababab.kingsword.procedure;

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
public class ProcedureChongsheng extends ElementsKingswordMod.ModElement {
	public ProcedureChongsheng(ElementsKingswordMod instance) {
		super(instance, 623);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure Chongsheng!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure Chongsheng!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure Chongsheng!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure Chongsheng!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure Chongsheng!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if (((KingswordModVariables.MapVariables.get(world).chongsheng) == (true))) {
			KingswordModVariables.MapVariables.get(world).chongsheng = (boolean) (true);
			KingswordModVariables.MapVariables.get(world).syncData(world);
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
					$_dependencies.put("entity", entity);
					$_dependencies.put("x", x);
					$_dependencies.put("y", y);
					$_dependencies.put("z", z);
					$_dependencies.put("world", world);
					ProcedureChongsheng.executeProcedure($_dependencies);
				}
			}, 1100, TimeUnit.MILLISECONDS);
		}
	}
}
