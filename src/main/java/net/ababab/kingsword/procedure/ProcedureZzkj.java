package net.ababab.kingsword.procedure;

import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;

import net.ababab.kingsword.item.ItemZuizhongkingArmor;
import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureZzkj extends ElementsKingswordMod.ModElement {
	public ProcedureZzkj(ElementsKingswordMod instance) {
		super(instance, 548);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure Zzkj!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (((entity.getEntityData().getBoolean("wawa")) == (true))) {
			if (entity instanceof EntityLivingBase)
				((EntityLivingBase) entity).setHealth((float) 1024);
			if (entity instanceof EntityPlayer) {
				((EntityPlayer) entity).inventory.armorInventory.set(0, new ItemStack(ItemZuizhongkingArmor.boots, (int) (1)));
				if (entity instanceof EntityPlayerMP)
					((EntityPlayerMP) entity).inventory.markDirty();
			}
			/* @ItemStack */
			if (entity instanceof EntityPlayer) {
				((EntityPlayer) entity).inventory.armorInventory.set(1, new ItemStack(ItemZuizhongkingArmor.legs, (int) (1)));
				if (entity instanceof EntityPlayerMP)
					((EntityPlayerMP) entity).inventory.markDirty();
			}
			/* @ItemStack */
			if (entity instanceof EntityPlayer) {
				((EntityPlayer) entity).inventory.armorInventory.set(2, new ItemStack(ItemZuizhongkingArmor.body, (int) (1)));
				if (entity instanceof EntityPlayerMP)
					((EntityPlayerMP) entity).inventory.markDirty();
			}
			/* @ItemStack */
			if (entity instanceof EntityPlayer) {
				((EntityPlayer) entity).inventory.armorInventory.set(3, new ItemStack(ItemZuizhongkingArmor.helmet, (int) (1)));
				if (entity instanceof EntityPlayerMP)
					((EntityPlayerMP) entity).inventory.markDirty();
			}
			/* @ItemStack */
			if (entity instanceof EntityPlayer) {
				((EntityPlayer) entity).capabilities.allowFlying = (true);
				((EntityPlayer) entity).sendPlayerAbilities();
			}
			entity.isDead = false;
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
