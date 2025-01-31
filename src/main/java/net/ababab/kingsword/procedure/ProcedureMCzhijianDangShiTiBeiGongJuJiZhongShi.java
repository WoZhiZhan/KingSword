package net.ababab.kingsword.procedure;

import net.minecraft.util.DamageSource;
import net.minecraft.entity.Entity;

import net.ababab.kingsword.ElementsKingswordMod;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;
import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureMCzhijianDangShiTiBeiGongJuJiZhongShi extends ElementsKingswordMod.ModElement {
	public ProcedureMCzhijianDangShiTiBeiGongJuJiZhongShi(ElementsKingswordMod instance) {
		super(instance, 232);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure MCzhijianDangShiTiBeiGongJuJiZhongShi!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		entity.attackEntityFrom(DamageSource.OUT_OF_WORLD, (float) Double.POSITIVE_INFINITY);
		Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors()).schedule(() -> {
			(entity).world.removeEntity(entity);
		}, 2000, TimeUnit.MILLISECONDS);
	}
}
