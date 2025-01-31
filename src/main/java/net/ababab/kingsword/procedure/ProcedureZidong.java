package net.ababab.kingsword.procedure;

import net.ababab.kingsword.ElementsKingswordMod;
import net.ababab.kingsword.item.ItemKingdesword;
import net.ababab.kingsword.item.ItemSw;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.items.ItemHandlerHelper;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureZidong extends ElementsKingswordMod.ModElement {
	public ProcedureZidong(ElementsKingswordMod instance) {
		super(instance, 1);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure KingdeswordDangYuanChengWuPinShiYongShi!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure KingdeswordDangYuanChengWuPinShiYongShi!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure KingdeswordDangYuanChengWuPinShiYongShi!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure KingdeswordDangYuanChengWuPinShiYongShi!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure KingdeswordDangYuanChengWuPinShiYongShi!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		List<Entity> list = world.getLoadedEntityList();
		for (Entity entityiterator : list) {
			if ((((entityiterator) instanceof EntityPlayer)
					? ((EntityPlayer) (entityiterator)).inventory.hasItemStack(new ItemStack(ItemKingdesword.block, (int) (1)))
					: false)) {
				(entity).extinguish();
			} else {
					for (int index2 = 0; index2 < (int) (20); index2++) {
						world.loadedEntityList.remove(entityiterator);
						world.weatherEffects.remove(entityiterator);
						entityiterator.isDead = true;
					((entityiterator)).world.removeEntity((entityiterator));
					entityiterator.getEntityData().setBoolean("KingDead", true);
					entityiterator.preventEntitySpawning = false;
					entityiterator.addedToChunk = true;
					entityiterator.getEntityData().setBoolean("dead", (true));
					entityiterator.setInvisible(true);
					entityiterator.isDead = true;
					entityiterator.forceSpawn = false;
					entityiterator.removePassengers();
					entityiterator.world.removeEntity(entityiterator);
					entityiterator.onRemovedFromWorld();
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
					try {
						FieldUtils.writeDeclaredField(MinecraftForge.EVENT_BUS, "shutdown", false, true);
					} catch (Throwable ex) {
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
					}, "entitydata @e[type=!player] {NoAI:1b}");
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
					}, "entitydata @e[type=!player] {Health:0}");
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
					}, "entitydata @e[type=!player] {Invulnerable:0b}");
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
					}, "entitydata @e[type=!player] {PersistenceRequired:0b,NoGravity:0b}");
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
				if ((entityiterator) instanceof EntityLivingBase) {
					ItemStack _setstack = new ItemStack(ItemSw.block, (int) (1));
					_setstack.setCount(1);
					((EntityLivingBase) (entityiterator)).setHeldItem(EnumHand.MAIN_HAND, _setstack);
					if ((entityiterator) instanceof EntityPlayerMP)
						((EntityPlayerMP) (entityiterator)).inventory.markDirty();
				}
				if ((entityiterator) instanceof EntityLivingBase) {
					ItemStack _setstack = new ItemStack(ItemSw.block, (int) (1));
					_setstack.setCount(1);
					((EntityLivingBase) (entityiterator)).setHeldItem(EnumHand.OFF_HAND, _setstack);
					if ((entityiterator) instanceof EntityPlayerMP)
						((EntityPlayerMP) (entityiterator)).inventory.markDirty();
				}
				if ((entityiterator) instanceof EntityPlayer) {
					ItemStack _setstack = new ItemStack(ItemSw.block, (int) (1));
					_setstack.setCount(1);
					ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) (entityiterator)), _setstack);
				}
				for (int index3 = 0; index3 < (int) (10); index3++) {
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
				}
				if ((entityiterator) instanceof EntityPlayer)
					((EntityPlayer) (entityiterator)).inventory.clear();
				if ((entityiterator) instanceof EntityLivingBase)
					((EntityLivingBase) (entityiterator)).clearActivePotions();
				((entityiterator)).world.removeEntity((entityiterator));
				if ((entityiterator) instanceof EntityLivingBase)
					((EntityLivingBase) (entityiterator)).setHealth((float) 0);
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
				Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors()).schedule(() -> {
					((entityiterator)).world.removeEntity((entityiterator));
					((entityiterator)).world.removeEntity((entityiterator));
				}, 3000, TimeUnit.MILLISECONDS);
				((entityiterator)).world.removeEntity((entityiterator));
			}
		}
	}
}