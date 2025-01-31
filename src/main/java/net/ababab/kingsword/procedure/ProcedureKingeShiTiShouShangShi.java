package net.ababab.kingsword.procedure;

import net.minecraft.world.World;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;

import net.ababab.kingsword.KingswordModVariables;
import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureKingeShiTiShouShangShi extends ElementsKingswordMod.ModElement {
	public ProcedureKingeShiTiShouShangShi(ElementsKingswordMod instance) {
		super(instance, 417);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure KingeShiTiShouShangShi!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure KingeShiTiShouShangShi!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		World world = (World) dependencies.get("world");
		if (entity instanceof EntityPlayer && !entity.world.isRemote) {
			((EntityPlayer) entity).sendStatusMessage(new TextComponentString("\u4F60\u6FC0\u6012\u4E86\u4ED6\uFF01"), (false));
		}
		KingswordModVariables.MapVariables.get(world).hdx = (boolean) (true);
		KingswordModVariables.MapVariables.get(world).syncData(world);
	}
}
