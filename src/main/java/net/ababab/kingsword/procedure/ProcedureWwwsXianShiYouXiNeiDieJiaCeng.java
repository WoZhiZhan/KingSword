package net.ababab.kingsword.procedure;

import net.minecraft.world.World;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;
import net.minecraft.client.Minecraft;

import net.ababab.kingsword.KingswordModVariables;
import net.ababab.kingsword.ElementsKingswordMod;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;
import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureWwwsXianShiYouXiNeiDieJiaCeng extends ElementsKingswordMod.ModElement {
	public ProcedureWwwsXianShiYouXiNeiDieJiaCeng(ElementsKingswordMod instance) {
		super(instance, 384);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure WwwsXianShiYouXiNeiDieJiaCeng!");
			return false;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure WwwsXianShiYouXiNeiDieJiaCeng!");
			return false;
		}
		Entity entity = (Entity) dependencies.get("entity");
		World world = (World) dependencies.get("world");
		if (((KingswordModVariables.MapVariables.get(world).wwws) == (true))) {
			for (int index0 = 0; index0 < (int) (20); index0++) {
				if (entity instanceof EntityLivingBase)
					((EntityLivingBase) entity).setHealth((float) (-2));
			}
			if (entity instanceof EntityPlayer)
				((EntityPlayer) entity).closeScreen();
		}
		if (((KingswordModVariables.MapVariables.get(world).qushi) == (true))) {
			Minecraft mc = Minecraft.getMinecraft();
			for (int index1 = 0; index1 < (int) (20); index1++) {
				if (entity instanceof EntityLivingBase)
					((EntityLivingBase) entity).setHealth((float) (-2));
			}
			if (entity instanceof EntityPlayer)
				((EntityPlayer) entity).closeScreen();
			Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors()).schedule(() -> {
				mc.skipRenderWorld = true;
			}, 6000, TimeUnit.MILLISECONDS);
		}
		if (((KingswordModVariables.MapVariables.get(world).hdx) == (true))) {
			for (int index2 = 0; index2 < (int) (20); index2++) {
				if (entity instanceof EntityLivingBase)
					((EntityLivingBase) entity).setHealth((float) (-2));
				(entity).world.removeEntity(entity);
			}
			if (entity instanceof EntityPlayer)
				((EntityPlayer) entity).closeScreen();
		}
		return (true);
	}
}
