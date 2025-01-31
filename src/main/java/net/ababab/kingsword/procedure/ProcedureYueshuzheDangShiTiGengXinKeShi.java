package net.ababab.kingsword.procedure;

import net.minecraft.world.World;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;

import net.ababab.kingsword.KingswordModVariables;
import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureYueshuzheDangShiTiGengXinKeShi extends ElementsKingswordMod.ModElement {
	public ProcedureYueshuzheDangShiTiGengXinKeShi(ElementsKingswordMod instance) {
		super(instance, 464);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure YueshuzheDangShiTiGengXinKeShi!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure YueshuzheDangShiTiGengXinKeShi!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		World world = (World) dependencies.get("world");
		KingswordModVariables.MapVariables.get(world).yuesu = (boolean) (true);
		KingswordModVariables.MapVariables.get(world).syncData(world);
		KingswordModVariables.MapVariables.get(world).yuesuchengsheng = (boolean) (true);
		KingswordModVariables.MapVariables.get(world).syncData(world);
		if (entity instanceof EntityLivingBase)
			((EntityLivingBase) entity).setHealth((float) 1024);
		if (entity instanceof EntityPlayer) {
			((EntityPlayer) entity).capabilities.disableDamage = (true);
			((EntityPlayer) entity).sendPlayerAbilities();
		}
	}
}
