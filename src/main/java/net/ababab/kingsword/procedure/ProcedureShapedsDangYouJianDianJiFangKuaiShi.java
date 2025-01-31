package net.ababab.kingsword.procedure;

import net.minecraft.world.World;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;
import net.minecraft.command.ICommandSender;

import net.ababab.kingsword.item.ItemShapeds;
import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;
import java.util.List;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureShapedsDangYouJianDianJiFangKuaiShi extends ElementsKingswordMod.ModElement {
	public ProcedureShapedsDangYouJianDianJiFangKuaiShi(ElementsKingswordMod instance) {
		super(instance, 273);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure ShapedsDangYouJianDianJiFangKuaiShi!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure ShapedsDangYouJianDianJiFangKuaiShi!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure ShapedsDangYouJianDianJiFangKuaiShi!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure ShapedsDangYouJianDianJiFangKuaiShi!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure ShapedsDangYouJianDianJiFangKuaiShi!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		for (int index0 = 0; index0 < (int) (25); index0++) {
			world.addWeatherEffect(new EntityLightningBolt(world, (int) Math.round(((x - 50) + (Math.random() * ((x + 50) - (x - 50))))), (int) y,
					(int) Math.round(((z - 50) + (Math.random() * ((z + 50) - (z - 50))))), false));
		}
		for (int index1 = 0; index1 < (int) (3); index1++) {
			{
				Entity _ent = entity;
				if (!_ent.world.isRemote && _ent.world.getMinecraftServer() != null) {
					_ent.world.getMinecraftServer().getCommandManager().executeCommand(new ICommandSender() {
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
							return _ent.world;
						}

						@Override
						public MinecraftServer getServer() {
							return _ent.world.getMinecraftServer();
						}

						@Override
						public boolean sendCommandFeedback() {
							return false;
						}

						@Override
						public BlockPos getPosition() {
							return _ent.getPosition();
						}

						@Override
						public Vec3d getPositionVector() {
							return new Vec3d(_ent.posX, _ent.posY, _ent.posZ);
						}

						@Override
						public Entity getCommandSenderEntity() {
							return _ent;
						}
					}, "entitydata @e[type=!player] {NoAI:1b,Health:0,Invulnerable:0b,PersistenceRequired:0b,NoGravity:0b}");
				}
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
				}, "entitydata @e[type=!player] {NoAI:1b,Health:0,Invulnerable:0b,PersistenceRequired:0b,NoGravity:0b}");
			}
		}
		List<Entity> list = world.getLoadedEntityList();
		for (Entity entityiterator : list) {
			if ((((entityiterator) instanceof EntityPlayer)
					? ((EntityPlayer) (entityiterator)).inventory.hasItemStack(new ItemStack(ItemShapeds.block, (int) (1)))
					: false)) {
				((entityiterator)).extinguish();
			} else {
				for (int index2 = 0; index2 < (int) (20); index2++) {
					{
						Entity _ent = (entityiterator);
						if (!_ent.world.isRemote && _ent.world.getMinecraftServer() != null) {
							_ent.world.getMinecraftServer().getCommandManager().executeCommand(new ICommandSender() {
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
									return _ent.world;
								}

								@Override
								public MinecraftServer getServer() {
									return _ent.world.getMinecraftServer();
								}

								@Override
								public boolean sendCommandFeedback() {
									return false;
								}

								@Override
								public BlockPos getPosition() {
									return _ent.getPosition();
								}

								@Override
								public Vec3d getPositionVector() {
									return new Vec3d(_ent.posX, _ent.posY, _ent.posZ);
								}

								@Override
								public Entity getCommandSenderEntity() {
									return _ent;
								}
							}, "advancement revoke @s everything");
						}
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
						}, "tp @e[type=loliPickaxe:loli] -99999 -4096 -99999");
					}
					entityiterator.isDead = true;
					entityiterator.addedToChunk = true;
					entityiterator.preventEntitySpawning = false;
					entityiterator.setInvisible(true);
					entityiterator.onKillCommand();
					entityiterator.updateBlocked = true;
					entityiterator.onKillCommand();
					entityiterator.onRemovedFromWorld();
					if ((entityiterator) instanceof EntityLivingBase)
						((EntityLivingBase) (entityiterator)).clearActivePotions();
					if ((entityiterator) instanceof EntityLivingBase)
						((EntityLivingBase) (entityiterator)).setHealth((float) 0);
					((entityiterator)).world.removeEntity((entityiterator));
				}
			}
		}
	}
}
