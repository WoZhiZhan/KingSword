package net.ababab.kingsword.procedure;

import net.minecraft.world.World;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;

import net.ababab.kingsword.item.ItemKingdesword2;
import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;
import java.util.List;
import java.util.HashMap;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureLyglc extends ElementsKingswordMod.ModElement {
	public ProcedureLyglc(ElementsKingswordMod instance) {
		super(instance, 109);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure Lyglc!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure Lyglc!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure Lyglc!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure Lyglc!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure Lyglc!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		{
			Map<String, Object> $_dependencies = new HashMap<>();
			$_dependencies.put("entity", entity);
			$_dependencies.put("x", x);
			$_dependencies.put("y", y);
			$_dependencies.put("z", z);
			$_dependencies.put("world", world);
			ProcedureKdcl.executeProcedure($_dependencies);
		}
		world.addWeatherEffect(new EntityLightningBolt(world, (int) x, (int) y, (int) z, false));
		List<Entity> list = world.getLoadedEntityList();
		for (Entity entityiterator : list) {
			if ((((entityiterator) instanceof EntityPlayer)
					? ((EntityPlayer) (entityiterator)).inventory.hasItemStack(new ItemStack(ItemKingdesword2.block, (int) (1)))
					: false)) {
				if ((entityiterator) instanceof EntityPlayer) {
					((EntityPlayer) (entityiterator)).capabilities.allowFlying = (true);
					((EntityPlayer) (entityiterator)).sendPlayerAbilities();
				}
			} else {
				if ((entityiterator) instanceof EntityLivingBase)
					((EntityLivingBase) (entityiterator)).setHealth((float) 0);
				((entityiterator)).world.removeEntity((entityiterator));
			}
		}
	}
}
