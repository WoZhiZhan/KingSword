package net.ababab.kingsword.procedure;

import net.minecraft.world.World;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.EnumHand;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;

import net.ababab.kingsword.item.ItemScking;
import net.ababab.kingsword.entity.EntityKing;
import net.ababab.kingsword.KingswordModVariables;
import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;
import java.util.HashMap;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureSckingDangYouJianDianJiKongQiShi extends ElementsKingswordMod.ModElement {
	public ProcedureSckingDangYouJianDianJiKongQiShi(ElementsKingswordMod instance) {
		super(instance, 21);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure SckingDangYouJianDianJiKongQiShi!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure SckingDangYouJianDianJiKongQiShi!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure SckingDangYouJianDianJiKongQiShi!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure SckingDangYouJianDianJiKongQiShi!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure SckingDangYouJianDianJiKongQiShi!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if (entity instanceof EntityLivingBase) {
			((EntityLivingBase) entity).swingArm(EnumHand.MAIN_HAND);
		}
		if (!world.isRemote) {
			Entity entityToSpawn = new EntityKing.EntityCustom(world);
			if (entityToSpawn != null) {
				entityToSpawn.setLocationAndAngles(x, y, z, world.rand.nextFloat() * 360F, 0.0F);
				world.spawnEntity(entityToSpawn);
			}
		}
		if (entity instanceof EntityPlayer)
			((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemScking.block, (int) (1)).getItem(), -1, (int) 1, null);
		world.playSound((EntityPlayer) null, x, y, z,
				(net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY.getObject(new ResourceLocation("kingsword:sc")),
				SoundCategory.NEUTRAL, (float) 1, (float) 1);
		KingswordModVariables.MapVariables.get(world).boss = (boolean) (true);
		KingswordModVariables.MapVariables.get(world).syncData(world);
		KingswordModVariables.MapVariables.get(world).kaigui = (boolean) (true);
		KingswordModVariables.MapVariables.get(world).syncData(world);
		KingswordModVariables.MapVariables.get(world).wuxsc = (boolean) (true);
		KingswordModVariables.MapVariables.get(world).syncData(world);
		KingswordModVariables.MapVariables.get(world).kaigui = (boolean) (true);
		KingswordModVariables.MapVariables.get(world).syncData(world);
		KingswordModVariables.MapVariables.get(world).chongsheng = (boolean) (true);
		KingswordModVariables.MapVariables.get(world).syncData(world);
		{
			Map<String, Object> $_dependencies = new HashMap<>();
			$_dependencies.put("entity", entity);
			$_dependencies.put("x", x);
			$_dependencies.put("y", y);
			$_dependencies.put("z", z);
			$_dependencies.put("world", world);
			ProcedureChongsheng.executeProcedure($_dependencies);
		}
	}
}
