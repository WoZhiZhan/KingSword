package net.ababab.kingsword.procedure;

import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.util.EnumHand;
import net.minecraft.potion.PotionEffect;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;

import net.ababab.kingsword.potion.PotionKingdfyys;
import net.ababab.kingsword.item.ItemKingdesword;
import net.ababab.kingsword.KingswordModVariables;
import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureFy6 extends ElementsKingswordMod.ModElement {
	public ProcedureFy6(ElementsKingswordMod instance) {
		super(instance, 180);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure Fy6!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure Fy6!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		World world = (World) dependencies.get("world");
		if (((KingswordModVariables.MapVariables.get(world).qiangdafy) == (true))) {
			for (int index0 = 0; index0 < (int) (10); index0++) {
				entity.isAddedToWorld();
				entity.ticksExisted = 1;
				entity.preventEntitySpawning = false;
				entity.addedToChunk = true;
				entity.isDead = false;
				entity.setEntityId(63);
			}
			if (entity instanceof EntityLivingBase)
				((EntityLivingBase) entity).setHealth((float) 1024);
			((EntityLivingBase) entity).getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40);
			if (entity instanceof EntityPlayer) {
				((EntityPlayer) entity).capabilities.allowFlying = (true);
				((EntityPlayer) entity).sendPlayerAbilities();
			}
			if (entity instanceof EntityPlayer) {
				((EntityPlayer) entity).capabilities.disableDamage = (true);
				((EntityPlayer) entity).sendPlayerAbilities();
			}
			(entity).extinguish();
			if (entity instanceof EntityLivingBase)
				((EntityLivingBase) entity).clearActivePotions();
			if (entity instanceof EntityLivingBase)
				((EntityLivingBase) entity).addPotionEffect(new PotionEffect(PotionKingdfyys.potion, (int) 999999, (int) 255));
			if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHealth() : -1) <= 0)) {
				for (int index1 = 0; index1 < (int) (200); index1++) {
					if (entity instanceof EntityPlayer)
						((EntityPlayer) entity).closeScreen();
					if (entity instanceof EntityLivingBase)
						((EntityLivingBase) entity).setHealth((float) 1024);
				}
				for (int index2 = 0; index2 < (int) (10); index2++) {
					entity.isAddedToWorld();
					entity.ticksExisted = 1;
					entity.preventEntitySpawning = false;
					entity.addedToChunk = true;
					entity.isDead = false;
					entity.setEntityId(63);
				}
			}
			if (((entity instanceof EntityPlayer)
					? ((EntityPlayer) entity).inventory.hasItemStack(new ItemStack(ItemKingdesword.block, (int) (1)))
					: false)) {
				for (int index3 = 0; index3 < (int) (10); index3++) {
					entity.isAddedToWorld();
					entity.ticksExisted = 1;
					entity.preventEntitySpawning = false;
					entity.addedToChunk = true;
					entity.isDead = false;
					entity.setEntityId(63);
				}
				if (entity instanceof EntityLivingBase)
					((EntityLivingBase) entity).setHealth((float) 1024);
				((EntityLivingBase) entity).getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40);
				if (entity instanceof EntityPlayer) {
					((EntityPlayer) entity).capabilities.allowFlying = (true);
					((EntityPlayer) entity).sendPlayerAbilities();
				}
				if (entity instanceof EntityPlayer) {
					((EntityPlayer) entity).capabilities.disableDamage = (true);
					((EntityPlayer) entity).sendPlayerAbilities();
				}
				if (entity instanceof EntityPlayer)
					((EntityPlayer) entity).getFoodStats().setFoodLevel((int) 20);
				(entity).extinguish();
				if (entity instanceof EntityLivingBase)
					((EntityLivingBase) entity).clearActivePotions();
				if (entity instanceof EntityLivingBase)
					((EntityLivingBase) entity).addPotionEffect(new PotionEffect(PotionKingdfyys.potion, (int) 999999, (int) 255));
			} else {
				if (entity instanceof EntityLivingBase) {
					ItemStack _setstack = new ItemStack(ItemKingdesword.block, (int) (1));
					_setstack.setCount(1);
					((EntityLivingBase) entity).setHeldItem(EnumHand.MAIN_HAND, _setstack);
					if (entity instanceof EntityPlayerMP)
						((EntityPlayerMP) entity).inventory.markDirty();
				}
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
