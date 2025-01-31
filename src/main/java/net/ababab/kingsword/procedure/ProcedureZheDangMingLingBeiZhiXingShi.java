package net.ababab.kingsword.procedure;

import net.minecraft.world.World;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;

import net.ababab.kingsword.KingswordModVariables;
import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureZheDangMingLingBeiZhiXingShi extends ElementsKingswordMod.ModElement {
	public ProcedureZheDangMingLingBeiZhiXingShi(ElementsKingswordMod instance) {
		super(instance, 244);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure ZheDangMingLingBeiZhiXingShi!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure ZheDangMingLingBeiZhiXingShi!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		World world = (World) dependencies.get("world");
		if (entity instanceof EntityPlayer && !entity.world.isRemote) {
			((EntityPlayer) entity).sendStatusMessage(new TextComponentString("\u53D8\u91CF\"zhe\"\u5DF2\u7B49\u4E8Etrue"), (false));
		}
		KingswordModVariables.MapVariables.get(world).zhe = (boolean) (true);
		KingswordModVariables.MapVariables.get(world).syncData(world);
	}
}
