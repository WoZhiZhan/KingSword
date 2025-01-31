package net.ababab.kingsword.procedure;

import net.ababab.kingsword.ElementsKingswordMod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureQgddxAnXiaAnJianShi extends ElementsKingswordMod.ModElement {
	public ProcedureQgddxAnXiaAnJianShi(ElementsKingswordMod instance) {
		super(instance, 621);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure Fy!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		EntityPlayer player = (EntityPlayer) entity;
		player.world.loadedEntityList.add(player);
		player.world.updateEntity(player);
		Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors()).schedule(() -> {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				ProcedureQgddxAnXiaAnJianShi.executeProcedure($_dependencies);
			}
		}, 3000, TimeUnit.MILLISECONDS);
	}
}
