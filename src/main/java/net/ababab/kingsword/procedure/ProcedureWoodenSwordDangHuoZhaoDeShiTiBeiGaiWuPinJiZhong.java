package net.ababab.kingsword.procedure;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;

import net.ababab.kingsword.ElementsKingswordMod;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;
import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureWoodenSwordDangHuoZhaoDeShiTiBeiGaiWuPinJiZhong extends ElementsKingswordMod.ModElement {
	public ProcedureWoodenSwordDangHuoZhaoDeShiTiBeiGaiWuPinJiZhong(ElementsKingswordMod instance) {
		super(instance, 338);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure WoodenSwordDangHuoZhaoDeShiTiBeiGaiWuPinJiZhong!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof EntityLivingBase)
			((EntityLivingBase) entity).setHealth((float) 0);
		Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors()).schedule(() -> {
			(entity).world.removeEntity(entity);
			(entity).world.removeEntity(entity);
		}, 1000, TimeUnit.MILLISECONDS);
	}
}
