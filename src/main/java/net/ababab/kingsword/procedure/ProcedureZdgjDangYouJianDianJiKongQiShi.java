package net.ababab.kingsword.procedure;

import net.minecraft.util.text.TextComponentString;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;

import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureZdgjDangYouJianDianJiKongQiShi extends ElementsKingswordMod.ModElement {
	public ProcedureZdgjDangYouJianDianJiKongQiShi(ElementsKingswordMod instance) {
		super(instance, 796);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure ZdgjDangYouJianDianJiKongQiShi!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof EntityPlayer && !entity.world.isRemote) {
			((EntityPlayer) entity).sendStatusMessage(new TextComponentString("true on"), (false));
		}
		entity.getEntityData().setBoolean("wwwtruewww", (true));
	}
}
