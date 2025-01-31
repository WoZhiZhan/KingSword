package net.ababab.kingsword.procedure;

import net.minecraftforge.fml.common.FMLCommonHandler;

import net.minecraft.world.World;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.server.MinecraftServer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;

import net.ababab.kingsword.entity.EntityKing;
import net.ababab.kingsword.KingswordModVariables;
import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;
import java.util.List;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureSsqcc extends ElementsKingswordMod.ModElement {
	public ProcedureSsqcc(ElementsKingswordMod instance) {
		super(instance, 477);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure Ssqcc!");
			return;
		}
		World world = (World) dependencies.get("world");
		if (((KingswordModVariables.MapVariables.get(world).jihuo) == (true))) {
			KingswordModVariables.MapVariables.get(world).wuxsc = (boolean) (false);
			KingswordModVariables.MapVariables.get(world).syncData(world);
			KingswordModVariables.MapVariables.get(world).boss = (boolean) (false);
			KingswordModVariables.MapVariables.get(world).syncData(world);
			List<Entity> list = world.getLoadedEntityList();
			for (Entity entityiterator : list) {
				if (((entityiterator) instanceof EntityKing.EntityCustom)) {
					((entityiterator)).world.removeEntity((entityiterator));
					if ((entityiterator) instanceof EntityLivingBase)
						((EntityLivingBase) (entityiterator)).setHealth((float) 0);
				}
			}
			{
				MinecraftServer mcserv = FMLCommonHandler.instance().getMinecraftServerInstance();
				if (mcserv != null)
					mcserv.getPlayerList().sendMessage(new TextComponentString("\u00A7eKing\u5DF2\u88AB\u6E05\u9664"));
			}
		}
	}
}
