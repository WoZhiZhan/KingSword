package net.ababab.kingsword.procedure;

import net.minecraft.world.World;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;

import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;
import java.util.List;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureWatherShiTiShouShangShi extends ElementsKingswordMod.ModElement {
	public ProcedureWatherShiTiShouShangShi(ElementsKingswordMod instance) {
		super(instance, 413);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure WatherShiTiShouShangShi!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure WatherShiTiShouShangShi!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		World world = (World) dependencies.get("world");
		if (entity instanceof EntityLivingBase)
			((EntityLivingBase) entity).setHealth((float) 40);
		List<Entity> list = world.getLoadedEntityList();
		for (Entity entityiterator : list) {
			if ((entity instanceof EntityPlayer)) {
				for (int index0 = 0; index0 < (int) (100); index0++) {
					if (entity instanceof EntityLivingBase)
						((EntityLivingBase) entity).setHealth((float) (-10));
				}
			}
		}
	}
}
