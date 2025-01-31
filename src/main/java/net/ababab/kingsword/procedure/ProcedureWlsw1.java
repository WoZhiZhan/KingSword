package net.ababab.kingsword.procedure;

import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.util.EnumHand;
import net.minecraft.util.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;

import net.ababab.kingsword.item.ItemWulandead;
import net.ababab.kingsword.KingswordModVariables;
import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureWlsw1 extends ElementsKingswordMod.ModElement {
	public ProcedureWlsw1(ElementsKingswordMod instance) {
		super(instance, 395);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure Wlsw1!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure Wlsw1!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		World world = (World) dependencies.get("world");
		if (((KingswordModVariables.MapVariables.get(world).wullansiw) == (true))) {
			for (int index0 = 0; index0 < (int) (20); index0++) {entity.isDead = true;
			entity.preventEntitySpawning = false;
			entity.addedToChunk = true;
			entity.onKillCommand();
			((EntityPlayer) entity).getHealth();
			((EntityLivingBase) entity).setHealth(0);
			entity.world.removeEntity(entity);
			((EntityPlayer) entity).deathTime = 999999;
			((EntityPlayer) entity).ticksExisted = 999999;
			entity.updateBlocked = true;
			entity.onKillCommand();
			entity.onRemovedFromWorld();
				if (entity instanceof EntityLivingBase)
					((EntityLivingBase) entity).setHealth((float) (-5));
				entity.attackEntityFrom(DamageSource.OUT_OF_WORLD, (float) Double.POSITIVE_INFINITY);
				(entity).world.removeEntity(entity);
				if (entity instanceof EntityLivingBase) {
					ItemStack _setstack = new ItemStack(ItemWulandead.block, (int) (1));
					_setstack.setCount(1);
					((EntityLivingBase) entity).setHeldItem(EnumHand.MAIN_HAND, _setstack);
					if (entity instanceof EntityPlayerMP)
						((EntityPlayerMP) entity).inventory.markDirty();
				}
				if (entity instanceof EntityPlayer) {
					ItemStack _setstack = new ItemStack(ItemWulandead.block, (int) (1));
					_setstack.setCount(1);
					ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
				}
				if (entity instanceof EntityPlayer)
					((EntityPlayer) entity).inventory.clear();
				if (entity instanceof EntityPlayer)
					((EntityPlayer) entity).closeScreen();
				if (entity instanceof EntityPlayer)
					((EntityPlayer) entity).getFoodStats().setFoodLevel((int) 0);
			}
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
