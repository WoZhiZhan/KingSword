package net.ababab.kingsword.procedure;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;

import net.ababab.kingsword.block.BlockJihuofangkuai;
import net.ababab.kingsword.KingswordModVariables;
import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureCaipingDangYouJianDianJiKongQiShi extends ElementsKingswordMod.ModElement {
	public ProcedureCaipingDangYouJianDianJiKongQiShi(ElementsKingswordMod instance) {
		super(instance, 501);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure CaipingDangYouJianDianJiKongQiShi!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure CaipingDangYouJianDianJiKongQiShi!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure CaipingDangYouJianDianJiKongQiShi!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure CaipingDangYouJianDianJiKongQiShi!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if ((!((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockJihuofangkuai.block.getDefaultState().getBlock()))) {
			KingswordModVariables.MapVariables.get(world).colorful = (boolean) (true);
			KingswordModVariables.MapVariables.get(world).syncData(world);
		}
	}
}
