package net.ababab.kingsword.procedure;

import net.ababab.kingsword.ElementsKingswordMod;
import net.ababab.kingsword.entity.EntityColorLightningBlot;
import net.ababab.kingsword.item.ItemWoodenSword;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;
import java.util.Map;
import java.util.Random;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureKdcl extends ElementsKingswordMod.ModElement {
	public ProcedureKdcl(ElementsKingswordMod instance) {
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
		for (int index0 = 0; index0 < (int) (65); index0++) {
			world.addWeatherEffect(new EntityColorLightningBlot.EntityCustom(world, (int) Math.round(((x - 50) + (Math.random() * ((x + 50) - (x - 50))))), (int) y,
					(int) Math.round(((z - 50) + (Math.random() * ((z + 50) - (z - 50))))), false));
		}
			}
		}