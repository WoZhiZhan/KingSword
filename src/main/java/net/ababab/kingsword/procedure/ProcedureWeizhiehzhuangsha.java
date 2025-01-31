package net.ababab.kingsword.procedure;

import net.minecraft.world.World;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;

import net.ababab.kingsword.KingswordModVariables;
import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;
import java.util.List;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureWeizhiehzhuangsha extends ElementsKingswordMod.ModElement {
	public ProcedureWeizhiehzhuangsha(ElementsKingswordMod instance) {
		super(instance, 481);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure Weizhiehzhuangsha!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure Weizhiehzhuangsha!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		World world = (World) dependencies.get("world");
		if (((KingswordModVariables.MapVariables.get(world).ehjs) == (true))) {
			if (entity instanceof EntityPlayer && !entity.world.isRemote) {
				((EntityPlayer) entity).sendStatusMessage(new TextComponentString("\u5DF2\u6E05\u9664\u672A\u77E5\u6076\u9B42"), (false));
			}
			boolean player = false;
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
