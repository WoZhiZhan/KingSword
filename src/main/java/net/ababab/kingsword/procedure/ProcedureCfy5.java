package net.ababab.kingsword.procedure;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.util.EnumHand;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;

import net.ababab.kingsword.item.ItemKingdesword;
import net.ababab.kingsword.KingswordModVariables;
import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureCfy5 extends ElementsKingswordMod.ModElement {
	public ProcedureCfy5(ElementsKingswordMod instance) {
		super(instance, 224);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure Cfy5!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure Cfy5!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		World world = (World) dependencies.get("world");
		if (((KingswordModVariables.MapVariables.get(world).cjfy) == (true))) {
			if (((entity instanceof EntityPlayer)
					? ((EntityPlayer) entity).inventory.hasItemStack(new ItemStack(ItemKingdesword.block, (int) (1)))
					: false)) {
				if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHealth() : -1) == 0)) {
					entity.isAddedToWorld();
					entity.ticksExisted = 1;
					entity.preventEntitySpawning = false;
					entity.addedToChunk = true;
					entity.isDead = false;
					entity.setEntityId(63);
					entity.setInvisible(false);
					if (entity instanceof EntityLivingBase)
						((EntityLivingBase) entity).setHealth((float) 1024);
					((EntityLivingBase) entity).getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40);
					if (entity instanceof EntityPlayer)
						((EntityPlayer) entity).closeScreen();
					if (entity instanceof EntityLivingBase) {
						ItemStack _setstack = new ItemStack(ItemKingdesword.block, (int) (1));
						_setstack.setCount(1);
						((EntityLivingBase) entity).setHeldItem(EnumHand.MAIN_HAND, _setstack);
						if (entity instanceof EntityPlayerMP)
							((EntityPlayerMP) entity).inventory.markDirty();
					}
				}
				entity.isAddedToWorld();
				entity.ticksExisted = 1;
				entity.preventEntitySpawning = false;
				entity.addedToChunk = true;
				entity.isDead = false;
				entity.setEntityId(63);
				entity.setInvisible(false);
				if (entity instanceof EntityLivingBase)
					((EntityLivingBase) entity).setHealth((float) 1024);
				((EntityLivingBase) entity).getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40);
				if (entity instanceof EntityPlayer)
					((EntityPlayer) entity).closeScreen();
			}
		}
	}

	@SubscribeEvent
	public void onPlayerLoggedIn(net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent event) {
		Entity entity = event.player;
		java.util.HashMap<String, Object> dependencies = new java.util.HashMap<>();
		dependencies.put("x", (int) entity.posX);
		dependencies.put("y", (int) entity.posY);
		dependencies.put("z", (int) entity.posZ);
		dependencies.put("world", entity.world);
		dependencies.put("entity", entity);
		dependencies.put("event", event);
		this.executeProcedure(dependencies);
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(this);
	}
}
