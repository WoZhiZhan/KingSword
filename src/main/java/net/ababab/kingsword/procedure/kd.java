package net.ababab.kingsword.procedure;

import net.ababab.kingsword.ElementsKingswordMod;
import net.ababab.kingsword.item.ItemKingdesword;
import net.ababab.kingsword.util.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

import java.util.List;
import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class kd extends ElementsKingswordMod.ModElement {
	public kd(ElementsKingswordMod instance) {
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
		List<Entity> list = world.getLoadedEntityList();
		for (Entity entityiterator : list) {
			if ((!(((entityiterator) instanceof EntityPlayer)
					? ((EntityPlayer) (entityiterator)).inventory.hasItemStack(new ItemStack(ItemKingdesword.block, (int) (1)))
					: false))) {
				for (int index1 = 0; index1 < (int) (20); index1++) {
					Minecraft mc = Minecraft.getMinecraft();
					((entityiterator)).world.removeEntity((entityiterator));
						entityiterator.isDead = true;
					Minecraft.getMinecraft().world.removeEntity(entityiterator);
					Chunk chunk = entityiterator.world.getChunkFromChunkCoords((int) entityiterator.posX, (int) entityiterator.posY);
					chunk.removeEntity(entityiterator);
					chunk.setHasEntities(false);
					entityiterator.preventEntitySpawning = true;
					chunk.onUnload();
					entityiterator.getEntityData().setBoolean("KingDead", true);
					for (Entity entityMax : mc.world.loadedEntityList) {
						try {
							Utils.killChaosWither(mc.world);
						} catch (Exception exception) {
							exception.printStackTrace();
						}
					}
}}
			}
		}}