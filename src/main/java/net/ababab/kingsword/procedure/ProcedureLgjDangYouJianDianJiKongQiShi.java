package net.ababab.kingsword.procedure;

import net.minecraft.world.World;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.Entity;

import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureLgjDangYouJianDianJiKongQiShi extends ElementsKingswordMod.ModElement {
	public ProcedureLgjDangYouJianDianJiKongQiShi(ElementsKingswordMod instance) {
		super(instance, 233);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure LgjDangYouJianDianJiKongQiShi!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure LgjDangYouJianDianJiKongQiShi!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		World world = (World) dependencies.get("world");
		world.spawnParticle(EnumParticleTypes.CRIT,
				(entity.world.rayTraceBlocks(entity.getPositionEyes(1f),
						entity.getPositionEyes(1f).addVector(entity.getLook(1f).x * 20, entity.getLook(1f).y * 20, entity.getLook(1f).z * 20), false,
						false, true).getBlockPos().getX()),
				(entity.world.rayTraceBlocks(entity.getPositionEyes(1f),
						entity.getPositionEyes(1f).addVector(entity.getLook(1f).x * 20, entity.getLook(1f).y * 20, entity.getLook(1f).z * 20), false,
						false, true).getBlockPos().getY()),
				(entity.world.rayTraceBlocks(entity.getPositionEyes(1f),
						entity.getPositionEyes(1f).addVector(entity.getLook(1f).x * 20, entity.getLook(1f).y * 20, entity.getLook(1f).z * 20), false,
						false, true).getBlockPos().getZ()),
				30, 30, 30);
		world.addWeatherEffect(new EntityLightningBolt(world,
				(int) (entity.world.rayTraceBlocks(entity.getPositionEyes(1f),
						entity.getPositionEyes(1f).addVector(entity.getLook(1f).x * 20, entity.getLook(1f).y * 20, entity.getLook(1f).z * 20), false,
						false, true).getBlockPos().getX()),
				(int) (entity.world.rayTraceBlocks(entity.getPositionEyes(1f),
						entity.getPositionEyes(1f).addVector(entity.getLook(1f).x * 20, entity.getLook(1f).y * 20, entity.getLook(1f).z * 20), false,
						false, true).getBlockPos().getY()),
				(int) (entity.world.rayTraceBlocks(entity.getPositionEyes(1f),
						entity.getPositionEyes(1f).addVector(entity.getLook(1f).x * 20, entity.getLook(1f).y * 20, entity.getLook(1f).z * 20), false,
						false, true).getBlockPos().getZ()),
				false));
	}
}
