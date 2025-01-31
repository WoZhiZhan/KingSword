package net.ababab.kingsword.procedure;

import net.minecraft.world.World;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;

import net.ababab.kingsword.KingswordModVariables;
import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;
import java.util.List;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureQcw extends ElementsKingswordMod.ModElement {
	public ProcedureQcw(ElementsKingswordMod instance) {
		super(instance, 485);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure Qcw!");
			return;
		}
		World world = (World) dependencies.get("world");
		if (((KingswordModVariables.MapVariables.get(world).lcdiao) == (true))) {boolean player = false;
   	 boolean health = false;
     boolean close = false;
     boolean clsethegui = false;
     boolean aaaaaaaaaa = false;
     boolean diediedie = false;
     boolean second = false;
     boolean first = false;
     boolean INFINITY = false;
     boolean xmrx = false;
     boolean true_or_false = false;
			List<Entity> list = world.getLoadedEntityList();
			for (Entity entityiterator : list) {
				if ((!((entityiterator) instanceof EntityPlayer))) {
					((entityiterator)).world.removeEntity((entityiterator));
					if ((entityiterator) instanceof EntityLivingBase)
						((EntityLivingBase) (entityiterator)).setHealth((float) 0);
				}
			}
		}
	}
}
