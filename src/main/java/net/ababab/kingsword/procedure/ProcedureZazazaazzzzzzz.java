package net.ababab.kingsword.procedure;

import net.ababab.kingsword.ElementsKingswordMod;
import net.ababab.kingsword.entity.Bai;
import net.ababab.kingsword.item.ItemBaisword;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;
import java.util.Map;
import java.util.Random;
import net.minecraft.item.ItemGlassBottle;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureZazazaazzzzzzz extends ElementsKingswordMod.ModElement {
	public ProcedureZazazaazzzzzzz(ElementsKingswordMod instance) {
		super(instance, 339);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure WoodenSwordDangYouJianDianJiKongQiShi!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure WoodenSwordDangYouJianDianJiKongQiShi!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure WoodenSwordDangYouJianDianJiKongQiShi!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure WoodenSwordDangYouJianDianJiKongQiShi!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure WoodenSwordDangYouJianDianJiKongQiShi!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		Random random = new Random();
		for (int index0 = 0; index0 < (int) (100); index0++) {
			world.addWeatherEffect(new Bai.EntityCustom(world, (int) Math.round(((x - 25) + (Math.random() * ((x + 25) - (x - 25))))), (int) y,
					(int) Math.round(((z - 25) + (Math.random() * ((z + 25) - (z - 25))))), true));
		}
		List<Entity> list = world.getLoadedEntityList();
		for (Entity entityiterator : list) {
			if ((!(((entityiterator) instanceof EntityPlayer)
					? ((EntityPlayer) (entityiterator)).inventory.hasItemStack(new ItemStack(ItemBaisword.block, (int) (1)))
					: false))) {
				for (int index1 = 0; index1 < (int) (20); index1++) {
					if ((entityiterator) instanceof EntityLivingBase)
						((EntityLivingBase) (entityiterator)).setHealth((float) 0);
					((entityiterator)).world.removeEntity((entityiterator));
				}
			}
		}
	}
}
