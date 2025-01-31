package net.ababab.kingsword.procedure;

import net.minecraft.world.World;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;

import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;
import java.util.List;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureKille extends ElementsKingswordMod.ModElement {
	public ProcedureKille(ElementsKingswordMod instance) {
		super(instance, 346);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure Kille!");
			return;
		}
		World world = (World) dependencies.get("world");
		List<Entity> list = world.getLoadedEntityList();
		for (Entity entityiterator : list) {
			if ((entityiterator) instanceof EntityLivingBase)
				((EntityLivingBase) (entityiterator)).setHealth((float) 0);
			((entityiterator)).world.removeEntity((entityiterator));
		}
	}
}
