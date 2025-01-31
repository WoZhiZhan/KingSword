package net.ababab.kingsword.procedure;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;

import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureShapedsDangWuPinZaiBeiBaoZhongMeiKeFaSheng extends ElementsKingswordMod.ModElement {
	public ProcedureShapedsDangWuPinZaiBeiBaoZhongMeiKeFaSheng(ElementsKingswordMod instance) {
		super(instance, 272);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure ShapedsDangWuPinZaiBeiBaoZhongMeiKeFaSheng!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof EntityPlayer) {
			((EntityPlayer) entity).capabilities.allowFlying = (true);
			((EntityPlayer) entity).sendPlayerAbilities();
		}
		if (entity instanceof EntityPlayer) {
			((EntityPlayer) entity).capabilities.disableDamage = (true);
			((EntityPlayer) entity).sendPlayerAbilities();
		}
		entity.getEntityData().setBoolean("az", (true));
		if (entity instanceof EntityLivingBase)
			((EntityLivingBase) entity).setHealth((float) 1024);
		if (((entity.posY) <= (-5))) {
			entity.motionX = 0;
			entity.motionY = 2;
			entity.motionZ = 0;
		}
	}
}
