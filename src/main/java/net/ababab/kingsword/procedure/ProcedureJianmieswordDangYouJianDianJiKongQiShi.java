package net.ababab.kingsword.procedure;

import net.minecraft.world.World;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;

import net.ababab.kingsword.ElementsKingswordMod;
import net.minecraftforge.client.GuiIngameForge;

import java.util.List;
import java.util.Map;


import static net.ababab.kingsword.item.ItemKingdesword.kill;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureJianmieswordDangYouJianDianJiKongQiShi extends ElementsKingswordMod.ModElement {
	public ProcedureJianmieswordDangYouJianDianJiKongQiShi(ElementsKingswordMod instance) {
		super(instance, 631);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure JianmieswordDangYouJianDianJiKongQiShi!");
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
					for (int i2 = 0; i2 < 90; i2++) {
							EntityLivingBase entityLiving = (EntityLivingBase) entityiterator;
							for (int i = 0; i < world.loadedEntityList.size(); i++) {
								Entity e = world.loadedEntityList.get(i);
								if (e != null && e != entityLiving) {
									kill(e);
									GuiIngameForge.renderBossHealth = false;
								}
							}
						}
			}
		}
	}
