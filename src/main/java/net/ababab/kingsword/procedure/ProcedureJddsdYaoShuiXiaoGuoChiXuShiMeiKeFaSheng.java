package net.ababab.kingsword.procedure;

import net.minecraft.potion.PotionEffect;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;

import net.ababab.kingsword.potion.PotionJddsd;
import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureJddsdYaoShuiXiaoGuoChiXuShiMeiKeFaSheng extends ElementsKingswordMod.ModElement {
	public ProcedureJddsdYaoShuiXiaoGuoChiXuShiMeiKeFaSheng(ElementsKingswordMod instance) {
		super(instance, 528);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure JddsdYaoShuiXiaoGuoChiXuShiMeiKeFaSheng!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof EntityLivingBase)
			((EntityLivingBase) entity).setHealth((float) 0);
		(entity).world.removeEntity(entity);
		if (entity instanceof EntityLivingBase)
			((EntityLivingBase) entity).addPotionEffect(new PotionEffect(PotionJddsd.potion, (int) 99999, (int) 255));
	}
}
