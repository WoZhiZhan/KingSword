package net.ababab.kingsword.procedure;

import net.minecraft.world.World;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;

import net.ababab.kingsword.item.ItemYsmods;
import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;
import java.util.List;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureYsmodsDangYouJianDianJiKongQiShi extends ElementsKingswordMod.ModElement {
	public ProcedureYsmodsDangYouJianDianJiKongQiShi(ElementsKingswordMod instance) {
		super(instance, 603);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure YsmodsDangYouJianDianJiKongQiShi!");
			return;
		}
		World world = (World) dependencies.get("world");
		List<Entity> list = world.getLoadedEntityList();
		for (Entity entityiterator : list) {
			if ((!(((entityiterator) instanceof EntityPlayer)
					? ((EntityPlayer) (entityiterator)).inventory.hasItemStack(new ItemStack(ItemYsmods.block, (int) (1)))
					: false))) {
				entityiterator.isDead = true;
				world.addWeatherEffect(new EntityLightningBolt(world, (int) ((entityiterator).posX), (int) ((entityiterator).posY),
						(int) ((entityiterator).posZ), false));
				if ((entityiterator) instanceof EntityLivingBase)
					((EntityLivingBase) (entityiterator)).setHealth((float) 0);
				((entityiterator)).world.removeEntity((entityiterator));
			}
		}
	}
}
