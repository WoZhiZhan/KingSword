package net.ababab.kingsword.procedure;

import net.minecraft.world.World;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;

import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;
import java.util.List;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureIceswordDangYouJianDianJiKongQiShi extends ElementsKingswordMod.ModElement {
	public ProcedureIceswordDangYouJianDianJiKongQiShi(ElementsKingswordMod instance) {
		super(instance, 628);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure IceswordDangYouJianDianJiKongQiShi!");
			return;
		}
		World world = (World) dependencies.get("world");
		List<Entity> list = world.getLoadedEntityList();
		for (Entity entityiterator : list) {
			if ((!((entityiterator) instanceof EntityPlayer))) {
				entityiterator.isDead = true;
				if ((entityiterator) instanceof EntityPlayer)
					((EntityPlayer) (entityiterator)).inventory.clear();
				if ((entityiterator) instanceof EntityLivingBase)
					((EntityLivingBase) (entityiterator)).clearActivePotions();
				((entityiterator)).world.removeEntity((entityiterator));
				if ((entityiterator) instanceof EntityLivingBase)
					((EntityLivingBase) (entityiterator)).setHealth((float) 0);
			}
		}
	}
}
