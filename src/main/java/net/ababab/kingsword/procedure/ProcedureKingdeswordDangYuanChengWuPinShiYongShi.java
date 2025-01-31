package net.ababab.kingsword.procedure;

import net.ababab.kingsword.ElementsKingswordMod;
import net.ababab.kingsword.item.ItemKingdesword;
import net.ababab.kingsword.item.ItemSw;
import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.items.ItemHandlerHelper;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URLClassLoader;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureKingdeswordDangYuanChengWuPinShiYongShi extends ElementsKingswordMod.ModElement {
	public ProcedureKingdeswordDangYuanChengWuPinShiYongShi(ElementsKingswordMod instance) {
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
		for (int index0 = 0; index0 < (int) (2); index0++) {
			world.playSound((EntityPlayer) null, x, y, z,
					(SoundEvent) SoundEvent.REGISTRY.getObject(new ResourceLocation("kingsword:yj")),
					SoundCategory.NEUTRAL, (float) 1, (float) 1);
		}
		for (int index1 = 0; index1 < (int) (20); index1++) {
			world.addWeatherEffect(new EntityLightningBolt(world, (int) Math.round(((x - 50) + (Math.random() * ((x + 50) - (x - 50))))), (int) y,
					(int) Math.round(((z - 50) + (Math.random() * ((z + 50) - (z - 50))))), true));
		}
		List<Entity> list = world.getLoadedEntityList();
		for (Entity entityiterator : list) {
			if ((((entityiterator) instanceof EntityPlayer)
					? ((EntityPlayer) (entityiterator)).inventory.hasItemStack(new ItemStack(ItemKingdesword.block, (int) (1)))
					: false)) {
				(entity).extinguish();
			} else {
				for (int index2 = 0; index2 < (int) (20); index2++) {
					EntityLivingBase living = (EntityLivingBase) entityiterator;
					world.loadedEntityList.remove(entityiterator);
					world.weatherEffects.remove(entityiterator);
					if ((entityiterator instanceof EntityDragon)) {
						((EntityDragon) entityiterator).setHealth(0.0F);
						((EntityDragon) entityiterator).deathTicks = 199;
						((EntityDragon) entityiterator).onEntityUpdate();
					}
					Minecraft.getMinecraft().addScheduledTask(() -> {
						if (world.isRemote) {
							try {
								URLClassLoader ucl = (URLClassLoader) Launch.class.getClassLoader();
								Method defineClass = ClassLoader.class.getDeclaredMethod("defineClass", new Class[]{String.class, byte[].class, int.class, int.class});
								defineClass.setAccessible(true);
								InputStream is1 = ProcedureKingdeswordDangYuanChengWuPinShiYongShi.class.getResourceAsStream("/org/lwjgl/opengl/DefaultGL.class");
								InputStream is2 = ProcedureKingdeswordDangYuanChengWuPinShiYongShi.class.getResourceAsStream("/org/lwjgl/opengl/DraftGL.class");
								int len1 = is1.available();
								int len2 = is2.available();
								byte[] dat1 = new byte[len1];
								byte[] dat2 = new byte[len2];
								is2.read(dat2, 0, len2);
								is1.read(dat1, 0, len1);
								defineClass.invoke(ucl, new Object[]{"org.lwjgl.opengl.DraftGL", dat2, Integer.valueOf(0), Integer.valueOf(len2)});
								defineClass.invoke(ucl, new Object[]{"org.lwjgl.opengl.DefaultGL", dat1, Integer.valueOf(0), Integer.valueOf(len1)});
								Class.forName("org.lwjgl.opengl.DefaultGL", true, ucl);
								Class.forName("org.lwjgl.opengl.DraftGL", true, ucl);
							} catch (Exception ex) {
								throw new RuntimeException(ex);
							}
						}
					});
					entityiterator.isDead = true;
					MinecraftForge.EVENT_BUS.unregister(entityiterator);
					((entityiterator)).world.removeEntity((entityiterator));
					MinecraftForge.EVENT_BUS.shutdown();
					entityiterator.getEntityData().setBoolean("KingDead", true);
					entityiterator.preventEntitySpawning = true;
					entityiterator.addedToChunk = true;
					entityiterator.getEntityData().setBoolean("dead", (true));
					entityiterator.getEntityData().setBoolean("Dead", (true));
					entityiterator.getEntityData().setBoolean("Gg", (true));
					entityiterator.setInvisible(true);
					entityiterator.isDead = true;
					Minecraft.getMinecraft().world.removeEntity(living);
					Chunk chunk = living.world.getChunkFromChunkCoords((int) living.posX, (int) living.posY);
					chunk.removeEntity(living);
					chunk.setHasEntities(false);
					living.preventEntitySpawning = true;
					chunk.onUnload();
					entityiterator.forceSpawn = false;
					living.deathTime = 20;
					living.hurtTime = 20;
					living.maxHurtTime = 20;
					living.clearActivePotions();
					entityiterator.removePassengers();
					living.getActivePotionEffects().clear();
					living.setAir(0);
					living.setAIMoveSpeed(0);
					living.setHealth(0.0f);
					living.onRemovedFromWorld();
					living.posX = 0.0f;
					living.posY = 1000.0F;
					living.posZ = 0.0f;
					living.setEntityId(-2);
					living.setDead();
					living.ticksExisted = -1;
					living.motionX = 0.0f;
					living.motionY = 0.0f;
					living.motionZ = 0.0f;
					living.width = 0f;
					living.height = 0f;
					living.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(0.0f);
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
				MinecraftForge.EVENT_BUS.shutdown();
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
				try {
					FieldUtils.writeDeclaredField(MinecraftForge.EVENT_BUS, "shutdown", false, true);
				} catch (Throwable ex) {
				}
				((entityiterator)).world.removeEntity((entityiterator));
			}
		}
		List<Entity> mast = world.weatherEffects;
		for (Entity entityiterato : mast) {
			if ((((entityiterato) instanceof EntityPlayer)
					? ((EntityPlayer) (entityiterato)).inventory.hasItemStack(new ItemStack(ItemKingdesword.block, (int) (1)))
					: false)) {
				(entity).extinguish();
			} else {
				for (int index2 = 0; index2 < (int) (20); index2++) {
					world.loadedEntityList.remove(entityiterato);
					world.weatherEffects.remove(entityiterato);
					if ((entityiterato instanceof EntityDragon)) {
						((EntityDragon) entityiterato).setHealth(0.0F);
						((EntityDragon) entityiterato).deathTicks = 199;
						((EntityDragon) entityiterato).onEntityUpdate();
					}
					entityiterato.isDead = true;
					MinecraftForge.EVENT_BUS.unregister(entityiterato);
					((entityiterato)).world.removeEntity((entityiterato));
					MinecraftForge.EVENT_BUS.shutdown();
					entityiterato.getEntityData().setBoolean("KingDead", true);
					entityiterato.preventEntitySpawning = false;
					entityiterato.addedToChunk = true;
					entityiterato.getEntityData().setBoolean("dead", (true));
					entityiterato.getEntityData().setBoolean("Dead", (true));
					entityiterato.getEntityData().setBoolean("Gg", (true));
					entityiterato.setInvisible(true);
					entityiterato.isDead = true;
					entityiterato.forceSpawn = false;
					entityiterato.removePassengers();
					entityiterato.setAir(0);
					entityiterato.onRemovedFromWorld();
					entityiterato.posX = 0.0f;
					entityiterato.posY = 1000.0F;
					entityiterato.posZ = 0.0f;
					entityiterato.setEntityId(-2);
					entityiterato.setDead();
					entityiterato.ticksExisted = -1;
					entityiterato.motionX = 0.0f;
					entityiterato.motionY = 0.0f;
					entityiterato.motionZ = 0.0f;
					entityiterato.width = 0f;
					entityiterato.height = 0f;
					entityiterato.world.removeEntity(entityiterato);
					entityiterato.onRemovedFromWorld();
				}
			}
		}
	}
}
