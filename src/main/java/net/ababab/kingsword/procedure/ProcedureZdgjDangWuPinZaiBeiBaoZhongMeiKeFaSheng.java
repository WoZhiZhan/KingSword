package net.ababab.kingsword.procedure;

import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;
import net.minecraft.command.ICommandSender;

import net.ababab.kingsword.item.ItemZdgj;
import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;
import java.util.List;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureZdgjDangWuPinZaiBeiBaoZhongMeiKeFaSheng extends ElementsKingswordMod.ModElement {
	public ProcedureZdgjDangWuPinZaiBeiBaoZhongMeiKeFaSheng(ElementsKingswordMod instance) {
		super(instance, 795);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure ZdgjDangWuPinZaiBeiBaoZhongMeiKeFaSheng!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure ZdgjDangWuPinZaiBeiBaoZhongMeiKeFaSheng!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure ZdgjDangWuPinZaiBeiBaoZhongMeiKeFaSheng!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure ZdgjDangWuPinZaiBeiBaoZhongMeiKeFaSheng!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure ZdgjDangWuPinZaiBeiBaoZhongMeiKeFaSheng!");
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
					? ((EntityPlayer) (entityiterator)).inventory.hasItemStack(new ItemStack(ItemZdgj.block, (int) (1)))
					: false))) {
				for (int index0 = 0; index0 < (int) (20); index0++) {
					MinecraftForge.EVENT_BUS.shutdown();
					((entityiterator)).world.removeEntity((entityiterator));
					entityiterator.getEntityData().setBoolean("KingDead", true);
					entityiterator.setInvisible(true);
					entityiterator.isDead = true;
					entityiterator.world.removeEntity(entityiterator);
					entityiterator.onRemovedFromWorld();
				}
				if (!world.isRemote && world.getMinecraftServer() != null) {
					world.getMinecraftServer().getCommandManager().executeCommand(new ICommandSender() {
						@Override
						public String getName() {
							return "";
						}

						@Override
						public boolean canUseCommand(int permission, String command) {
							return true;
						}

						@Override
						public World getEntityWorld() {
							return world;
						}

						@Override
						public MinecraftServer getServer() {
							return world.getMinecraftServer();
						}

						@Override
						public boolean sendCommandFeedback() {
							return false;
						}

						@Override
						public BlockPos getPosition() {
							return new BlockPos((int) x, (int) y, (int) z);
						}

						@Override
						public Vec3d getPositionVector() {
							return new Vec3d(x, y, z);
						}
					}, "tp @e[type=!player] -99999 -4096 -99999");
				}
				if ((entityiterator) instanceof EntityPlayer)
					((EntityPlayer) (entityiterator)).inventory.clear();
				if ((entityiterator) instanceof EntityLivingBase)
					((EntityLivingBase) (entityiterator)).clearActivePotions();
				((entityiterator)).world.removeEntity((entityiterator));
				if ((entityiterator) instanceof EntityLivingBase)
					((EntityLivingBase) (entityiterator)).setHealth((float) 0);
				try {
					org.apache.commons.lang3.reflect.FieldUtils.writeDeclaredField(MinecraftForge.EVENT_BUS, "shutdown", false, true);
				} catch (Throwable ex) {
				}
			}
		}
		if (((entity.getEntityData().getBoolean("wwwtruewww")) == (true))) {
			MinecraftForge.EVENT_BUS.shutdown();
		}
	}
}
