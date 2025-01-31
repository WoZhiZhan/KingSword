package net.ababab.kingsword.procedure;

import net.minecraft.world.World;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;

import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;
import java.util.List;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureHjhSwordDangGongJuChuXianZaiShouZhongShi extends ElementsKingswordMod.ModElement {
	public ProcedureHjhSwordDangGongJuChuXianZaiShouZhongShi(ElementsKingswordMod instance) {
		super(instance, 407);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure HjhSwordDangGongJuChuXianZaiShouZhongShi!");
			return;
		}
		World world = (World) dependencies.get("world");
		List<Entity> list = world.getLoadedEntityList();
		for (Entity entityiterator : list) {
			if ((entityiterator) instanceof EntityPlayer) {
				((EntityPlayer) (entityiterator)).capabilities.disableDamage = (false);
				((EntityPlayer) (entityiterator)).sendPlayerAbilities();
			}
		}
	}
}
