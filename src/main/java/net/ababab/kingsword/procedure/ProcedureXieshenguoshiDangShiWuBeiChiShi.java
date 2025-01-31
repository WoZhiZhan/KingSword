package net.ababab.kingsword.procedure;

import net.minecraft.potion.PotionEffect;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;

import net.ababab.kingsword.potion.PotionXieshendezuzhou;
import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureXieshenguoshiDangShiWuBeiChiShi extends ElementsKingswordMod.ModElement {
	public ProcedureXieshenguoshiDangShiWuBeiChiShi(ElementsKingswordMod instance) {
		super(instance, 526);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure XieshenguoshiDangShiWuBeiChiShi!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof EntityLivingBase)
			((EntityLivingBase) entity).addPotionEffect(new PotionEffect(PotionXieshendezuzhou.potion, (int) 60, (int) 1));
	}
}
