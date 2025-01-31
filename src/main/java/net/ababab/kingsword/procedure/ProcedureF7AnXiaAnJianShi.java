package net.ababab.kingsword.procedure;

import net.minecraft.world.World;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;

import net.ababab.kingsword.KingswordModVariables;
import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureF7AnXiaAnJianShi extends ElementsKingswordMod.ModElement {
	public ProcedureF7AnXiaAnJianShi(ElementsKingswordMod instance) {
		super(instance, 172);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure F7AnXiaAnJianShi!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure F7AnXiaAnJianShi!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		World world = (World) dependencies.get("world");
		if (((KingswordModVariables.MapVariables.get(world).jinshengcheng) == (true))) {
			KingswordModVariables.MapVariables.get(world).zhongjijin = (boolean) (true);
			KingswordModVariables.MapVariables.get(world).syncData(world);
			if (entity instanceof EntityPlayer && !entity.world.isRemote) {
				((EntityPlayer) entity).sendStatusMessage(new TextComponentString("\u8D85\u7EA7\u7981\u5DF2\u5F00\u542F"), (false));
			}
		} else {
			if (entity instanceof EntityPlayer && !entity.world.isRemote) {
				((EntityPlayer) entity).sendStatusMessage(new TextComponentString("\u7981\u751F\u6210\u8FD8\u672A\u5F00\u542F"), (false));
			}
		}
	}
}
