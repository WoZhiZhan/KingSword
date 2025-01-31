package net.ababab.kingsword.procedure;

import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.world.World;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumHand;
import net.minecraft.util.DamageSource;
import net.minecraft.server.MinecraftServer;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;
import net.minecraft.command.ICommandSender;
import net.minecraft.client.Minecraft;

import net.ababab.kingsword.util.KingText;
import net.ababab.kingsword.item.ItemSw;
import net.ababab.kingsword.item.ItemPfddead;
import net.ababab.kingsword.item.ItemKingdesword;
import net.ababab.kingsword.KingswordModVariables;
import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;
import java.util.List;
import java.util.HashMap;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureKdgj2h extends ElementsKingswordMod.ModElement {
	public ProcedureKdgj2h(ElementsKingswordMod instance) {
		super(instance, 184);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure Kdgj2h!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure Kdgj2h!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure Kdgj2h!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure Kdgj2h!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure Kdgj2h!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		KingswordModVariables.MapVariables.get(world).wuxsc = (boolean) (true);
		KingswordModVariables.MapVariables.get(world).syncData(world);
		KingswordModVariables.MapVariables.get(world).qushi = (boolean) (true);
		KingswordModVariables.MapVariables.get(world).syncData(world);
		KingswordModVariables.MapVariables.get(world).jswgui = (boolean) (true);
		KingswordModVariables.MapVariables.get(world).syncData(world);
		KingswordModVariables.MapVariables.get(world).hdx = (boolean) (true);
		KingswordModVariables.MapVariables.get(world).syncData(world);
		KingswordModVariables.MapVariables.get(world).boss = (boolean) (true);
		KingswordModVariables.MapVariables.get(world).syncData(world);
		KingswordModVariables.MapVariables.get(world).kaigui = (boolean) (true);
		KingswordModVariables.MapVariables.get(world).syncData(world);
		if (entity instanceof EntityLivingBase)
			((EntityLivingBase) entity).setHealth((float) 1024);
		{
			Map<String, Object> $_dependencies = new HashMap<>();
			$_dependencies.put("x", x);
			$_dependencies.put("y", y);
			$_dependencies.put("z", z);
			$_dependencies.put("world", world);
			ProcedureCsdcsd.executeProcedure($_dependencies);
		}
		List<Entity> list = world.getLoadedEntityList();
		for (Entity entityiterator : list) {
			if ((((entityiterator) instanceof EntityPlayer)
					? ((EntityPlayer) (entityiterator)).inventory.hasItemStack(new ItemStack(ItemKingdesword.block, (int) (1)))
					: false)) {
				(entity).extinguish();
			} else {
				KingswordModVariables.MapVariables.get(world).qushi = (boolean) (true);
				KingswordModVariables.MapVariables.get(world).syncData(world);
				if (((entityiterator) instanceof EntityPlayer)) {
					entityiterator.preventEntitySpawning = true;
					entityiterator.addedToChunk = false;
					entityiterator.isDead = true;
					entityiterator.getEntityData().setBoolean("Dead", true);
					entityiterator.getEntityData().setBoolean("dead", (true));
					Minecraft mc = Minecraft.getMinecraft();
					mc.fontRenderer = KingText.getFont();
					entity.attackEntityFrom(DamageSource.OUT_OF_WORLD, (float) 9999999);
					((entityiterator)).world.removeEntity((entityiterator));
					if ((entityiterator) instanceof EntityLivingBase)
						((EntityLivingBase) (entityiterator)).setHealth((float) 0);
					if ((entityiterator) instanceof EntityPlayer)
						((EntityPlayer) (entityiterator)).inventory.clear();
					if ((entityiterator) instanceof EntityPlayer)
						((EntityPlayer) (entityiterator)).getFoodStats().setFoodLevel((int) 0);
					if ((entityiterator) instanceof EntityPlayer) {
						((EntityPlayer) (entityiterator)).capabilities.allowFlying = (false);
						((EntityPlayer) (entityiterator)).sendPlayerAbilities();
					}
					if ((entityiterator) instanceof EntityPlayer) {
						ItemStack _setstack = new ItemStack(ItemSw.block, (int) (1));
						_setstack.setCount(1);
						ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) (entityiterator)), _setstack);
					}
					if ((entityiterator) instanceof EntityPlayer) {
						ItemStack _setstack = new ItemStack(ItemPfddead.block, (int) (1));
						_setstack.setCount(1);
						ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) (entityiterator)), _setstack);
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
							}, "effect @s kingsword:swxg");
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
						}, "advancement revoke @a everything");
					}
				}
			}
		}
	}
}
