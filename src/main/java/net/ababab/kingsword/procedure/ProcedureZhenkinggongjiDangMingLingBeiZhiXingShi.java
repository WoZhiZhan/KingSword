package net.ababab.kingsword.procedure;

import net.minecraft.util.text.TextComponentString;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;

import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureZhenkinggongjiDangMingLingBeiZhiXingShi extends ElementsKingswordMod.ModElement {
	public ProcedureZhenkinggongjiDangMingLingBeiZhiXingShi(ElementsKingswordMod instance) {
		super(instance, 352);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure ZhenkinggongjiDangMingLingBeiZhiXingShi!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof EntityPlayer && !entity.world.isRemote) {
			((EntityPlayer) entity).sendStatusMessage(new TextComponentString("kinggod true"), (false));
		}
		entity.getEntityData().setBoolean("tack,kingnb", (true));
	}
}
