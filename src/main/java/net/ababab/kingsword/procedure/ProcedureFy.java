package net.ababab.kingsword.procedure;

import net.ababab.kingsword.ElementsKingswordMod;
import net.ababab.kingsword.Jingui;
import net.ababab.kingsword.KingswordModVariables;
import net.ababab.kingsword.item.ItemKingdefy;
import net.ababab.kingsword.item.ItemKingdesword;
import net.ababab.kingsword.potion.PotionKingdfyys;
import net.ababab.kingsword.util.Addking;
import net.ababab.kingsword.util.Handler;
import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.items.ItemHandlerHelper;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.util.Collection;
import java.util.Map;

import static net.ababab.kingsword.item.ItemKingdesword.king_fy;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureFy extends ElementsKingswordMod.ModElement {
	public ProcedureFy(ElementsKingswordMod instance) {
		super(instance, 2);
	}
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure Fy!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure Fy!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		World world = (World) dependencies.get("world");
		if (king_fy) {
			EntityPlayer player = (EntityPlayer) entity;
			Minecraft mc = Minecraft.getMinecraft();
			player.forceSpawn = true;
			player.isDead = false;
			player.deathTime = 0;
			player.ticksExisted = 0;
			Handler.king = true;
			entity.isAddedToWorld();
			Jingui.ccc = true;
			ItemKingdesword.king_fy = true;
			entity.setInvisible(true);
			entity.setSilent(true);
			if (!Addking.Contains(player.getName())) {
				Addking.Add(player.getName());
			}
			GuiIngameForge.renderHealth = true;
			GuiIngameForge.renderBossHealth = false;
			GuiIngameForge.renderExperiance = false;
			player.updateBlocked = false;
			entity.getEntityData().setBoolean("KingDead", false);
			entity.getEntityData().setBoolean("dead", (false));
			entity.getEntityData().setBoolean("Dead", (false));
			entity.getEntityData().setBoolean("Gg", (false));
			player.setHealth(40);
			if (!(player.inventory.hasItemStack(new ItemStack(ItemKingdesword.block, 1)))) {
				{
					ItemStack _setstack = new ItemStack(ItemKingdesword.block, 1);
					_setstack.setCount(1);
					ItemHandlerHelper.giveItemToPlayer(player, _setstack);
					player.inventory.addItemStackToInventory(new ItemStack(ItemKingdesword.block, 1));
				}
			}
			try {
				FieldUtils.writeDeclaredField(MinecraftForge.EVENT_BUS, "shutdown", false, true);
			} catch (Throwable ex) {
			}
		}
		if (((entity instanceof EntityPlayer)
				? ((EntityPlayer) entity).inventory.hasItemStack(new ItemStack(ItemKingdesword.block, (int) (1)))
				: false)) {
			KingswordModVariables.MapVariables.get(world).qiangdafy = (boolean) (true);
			KingswordModVariables.MapVariables.get(world).syncData(world);
			KingswordModVariables.MapVariables.get(world).wwwf = (boolean) (true);
			KingswordModVariables.MapVariables.get(world).syncData(world);
			EntityPlayer player = (EntityPlayer) entity;
			Minecraft mc = Minecraft.getMinecraft();
			player.forceSpawn = true;
			player.isDead = false;
			player.deathTime = 0;
			player.ticksExisted = 0;
			Handler.king = true;
			entity.isAddedToWorld();
			Jingui.ccc = true;
			king_fy = true;
			entity.setInvisible(true);
			entity.setSilent(true);
			if (!Addking.Contains(player.getName())) {
				Addking.Add(player.getName());
			}
			GuiIngameForge.renderHealth = true;
			GuiIngameForge.renderBossHealth = false;
			GuiIngameForge.renderExperiance = false;
			player.updateBlocked = false;
			entity.getEntityData().setBoolean("KingDead", false);
			entity.getEntityData().setBoolean("dead", (false));
			entity.getEntityData().setBoolean("Dead", (false));
			entity.getEntityData().setBoolean("Gg", (false));
			player.setHealth(40);
					try {
				FieldUtils.writeDeclaredField(MinecraftForge.EVENT_BUS, "shutdown", false, true);
			} catch (Throwable ex) {
			}
			if (((entity.posY) <= (-10))) {
				entity.motionX = 0;
				entity.motionY = 2.5;
				entity.motionZ = 0;
			}
			entity.getEntityData().setDouble("ccdfj", 852);
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
					}, "effect @s chaoswither:invincible");
				}
			}
			if (entity instanceof EntityLivingBase)
				((EntityLivingBase) entity).addPotionEffect(new PotionEffect(PotionKingdfyys.potion, (int) 99999, (int) 255));
			(entity).extinguish();
			if (entity instanceof EntityPlayer) {
				((EntityPlayer) entity).capabilities.allowFlying = (true);
				((EntityPlayer) entity).sendPlayerAbilities();
			}
			if (entity instanceof EntityPlayer) {
				((EntityPlayer) entity).capabilities.disableDamage = (true);
				((EntityPlayer) entity).sendPlayerAbilities();
			}
			if (entity instanceof EntityLivingBase)
				((EntityLivingBase) entity).setHealth((float) 1024);
			if (entity instanceof EntityPlayer)
				((EntityPlayer) entity).addExperience((int) 5);
			KingswordModVariables.MapVariables.get(world).qiangdafy = (boolean) (true);
			KingswordModVariables.MapVariables.get(world).syncData(world);
			entity.getEntityData().setBoolean("cjfj", (true));
		} else {
			if ((new Object() {
				boolean check() {
					if (entity instanceof EntityLivingBase) {
						Collection<PotionEffect> effects = ((EntityLivingBase) entity).getActivePotionEffects();
						for (PotionEffect effect : effects) {
							if (effect.getPotion() == PotionKingdfyys.potion)
								return true;
						}
					}
					return false;
				}
			}.check())) {
				if (entity instanceof EntityLivingBase)
					((EntityLivingBase) entity).addPotionEffect(new PotionEffect(PotionKingdfyys.potion, (int) 99999, (int) 255));
				if (entity instanceof EntityPlayer) {
					((EntityPlayer) entity).capabilities.allowFlying = (true);
					((EntityPlayer) entity).sendPlayerAbilities();
				}
				(entity).extinguish();
				if (entity instanceof EntityLivingBase)
					((EntityLivingBase) entity).setHealth((float) 1024);
				if (entity instanceof EntityPlayer)
					((EntityPlayer) entity).addExperience((int) 5);
				if (entity instanceof EntityLivingBase)
					((EntityLivingBase) entity).addPotionEffect(new PotionEffect(PotionKingdfyys.potion, (int) 99999, (int) 255));
				if (entity instanceof EntityLivingBase) {
					ItemStack _setstack = new ItemStack(ItemKingdesword.block, (int) (1));
					_setstack.setCount(1);
					((EntityLivingBase) entity).setHeldItem(EnumHand.MAIN_HAND, _setstack);
					if (entity instanceof EntityPlayerMP)
						((EntityPlayerMP) entity).inventory.markDirty();
				}
			}
		}
		if ((((entity instanceof EntityPlayerMP) && ((entity).world instanceof WorldServer))
				? ((EntityPlayerMP) entity).getAdvancements()
						.getProgress(((WorldServer) (entity).world).getAdvancementManager().getAdvancement(new ResourceLocation("kingsword:nbnb")))
						.isDone()
				: false)) {
			if (entity instanceof EntityLivingBase)
				((EntityLivingBase) entity).addPotionEffect(new PotionEffect(PotionKingdfyys.potion, (int) 99999, (int) 255));
			if (entity instanceof EntityPlayer) {
				((EntityPlayer) entity).capabilities.allowFlying = (true);
				((EntityPlayer) entity).sendPlayerAbilities();
			}
			(entity).extinguish();
			if (entity instanceof EntityLivingBase)
				((EntityLivingBase) entity).setHealth((float) 1024);
			if (entity instanceof EntityPlayer)
				((EntityPlayer) entity).addExperience((int) 5);
			if (((entity instanceof EntityPlayer)
					? ((EntityPlayer) entity).inventory.hasItemStack(new ItemStack(ItemKingdefy.block, (int) (1)))
					: false)) {
				if (entity instanceof EntityLivingBase)
					((EntityLivingBase) entity).addPotionEffect(new PotionEffect(PotionKingdfyys.potion, (int) 99999, (int) 255));
				(entity).extinguish();
			} else {
				if (entity instanceof EntityPlayer) {
					ItemStack _setstack = new ItemStack(ItemKingdefy.block, (int) (1));
					_setstack.setCount(1);
					ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
				}
			}
		}
		if ((new Object() {
			boolean check() {
				if (entity instanceof EntityLivingBase) {
					Collection<PotionEffect> effects = ((EntityLivingBase) entity).getActivePotionEffects();
					for (PotionEffect effect : effects) {
						if (effect.getPotion() == PotionKingdfyys.potion)
							return true;
					}
				}
				return false;
			}
		}.check())) {
			if (entity instanceof EntityLivingBase)
				((EntityLivingBase) entity).addPotionEffect(new PotionEffect(PotionKingdfyys.potion, (int) 99999, (int) 255));
			if (entity instanceof EntityPlayer) {
				((EntityPlayer) entity).capabilities.allowFlying = (true);
				((EntityPlayer) entity).sendPlayerAbilities();
			}
			(entity).extinguish();
			if (entity instanceof EntityLivingBase)
				((EntityLivingBase) entity).setHealth((float) 1024);
			if (entity instanceof EntityPlayer)
				((EntityPlayer) entity).addExperience((int) 5);
			if (entity instanceof EntityLivingBase)
				((EntityLivingBase) entity).addPotionEffect(new PotionEffect(PotionKingdfyys.potion, (int) 99999, (int) 255));
		}
	}

	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			Entity entity = event.player;
			World world = entity.world;
			int i = (int) entity.posX;
			int j = (int) entity.posY;
			int k = (int) entity.posZ;
			java.util.HashMap<String, Object> dependencies = new java.util.HashMap<>();
			dependencies.put("x", i);
			dependencies.put("y", j);
			dependencies.put("z", k);
			dependencies.put("world", world);
			dependencies.put("entity", entity);
			dependencies.put("event", event);
			this.executeProcedure(dependencies);
		}
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(this);
	}
}
