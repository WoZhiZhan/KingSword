package net.ababab.kingsword.procedure;

import net.ababab.kingsword.ElementsKingswordMod;
import net.ababab.kingsword.KingswordModVariables;
import net.ababab.kingsword.item.ItemWulandead;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.client.GuiIngameForge;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureSwswsw extends ElementsKingswordMod.ModElement {
	public ProcedureSwswsw(ElementsKingswordMod instance) {
		super(instance, 394);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure Swswsw!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure Swswsw!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		World world = (World) dependencies.get("world");
		KingswordModVariables.MapVariables.get(world).wullansiw = (boolean) (true);
		KingswordModVariables.MapVariables.get(world).syncData(world);
		KingswordModVariables.MapVariables.get(world).hdx = (boolean) (true);
		KingswordModVariables.MapVariables.get(world).syncData(world);
		for (int index0 = 0; index0 < (int) (20); index0++) {
			EntityLivingBase living = (EntityLivingBase) entity;
			entity.isDead = true;
			entity.preventEntitySpawning = false;
			entity.addedToChunk = true;
			entity.onKillCommand();
			((EntityPlayer) entity).getHealth();
			((EntityLivingBase) entity).setHealth(0);
			entity.world.removeEntity(entity);
			((EntityPlayer) entity).deathTime = 999999;
			((EntityPlayer) entity).ticksExisted = 999999;
			living.getEntityData().setBoolean("Dead", true);
			GuiIngameForge.renderHealth = false;
			entity.updateBlocked = true;
			entity.onKillCommand();
			entity.onRemovedFromWorld();
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
					}, "clear");
				}
			}
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
					}, "advancement revoke @s everything");
				}
			}
			entity.attackEntityFrom(DamageSource.OUT_OF_WORLD, (float) 9999999);
			(entity).world.removeEntity(entity);
			if (entity instanceof EntityLivingBase)
				((EntityLivingBase) entity).setHealth((float) (-2));
			if (entity instanceof EntityLivingBase)
				((EntityLivingBase) entity).setHealth((float) (-2));
			(entity).world.removeEntity(entity);
			if (entity instanceof EntityPlayer)
				((EntityPlayer) entity).inventory.clear();
			if (entity instanceof EntityPlayer)
				((EntityPlayer) entity).getFoodStats().setFoodLevel((int) 0);
			((EntityLivingBase) entity).getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(0);
		}
		if (entity instanceof EntityPlayer) {
			((EntityPlayer) entity).capabilities.allowFlying = (false);
			((EntityPlayer) entity).sendPlayerAbilities();
		}
		if (entity instanceof EntityLivingBase) {
			ItemStack _setstack = new ItemStack(ItemWulandead.block, (int) (1));
			_setstack.setCount(1);
			((EntityLivingBase) entity).setHeldItem(EnumHand.MAIN_HAND, _setstack);
			if (entity instanceof EntityPlayerMP)
				((EntityPlayerMP) entity).inventory.markDirty();
		}
		if (entity instanceof EntityLivingBase) {
			ItemStack _setstack = new ItemStack(ItemWulandead.block, (int) (1));
			_setstack.setCount(1);
			((EntityLivingBase) entity).setHeldItem(EnumHand.OFF_HAND, _setstack);
			if (entity instanceof EntityPlayerMP)
				((EntityPlayerMP) entity).inventory.markDirty();
		}
	}
}
