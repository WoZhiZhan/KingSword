package net.ababab.kingsword.procedure;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
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
import net.ababab.kingsword.Jingui;
import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureFy29 extends ElementsKingswordMod.ModElement {
	public ProcedureFy29(ElementsKingswordMod instance) {
		super(instance, 362);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure Fy29!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure Fy29!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		World world = (World) dependencies.get("world");
		if (((KingswordModVariables.MapVariables.get(world).qiangdafy) == (true))) {
			if ((entity instanceof EntityPlayer)) {
				Jingui.ccc = true;
				((EntityPlayer) entity).deathTime = 0;
				((EntityPlayer) entity).ticksExisted = 0;
				if (entity instanceof EntityLivingBase)
					((EntityLivingBase) entity).setHealth((float) 40);
				((EntityLivingBase) entity).getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40);
				if ((!((entity instanceof EntityPlayer)
						? ((EntityPlayer) entity).inventory.hasItemStack(new ItemStack(ItemKingdesword.block, (int) (1)))
						: false))) {
					((EntityLivingBase) entity).getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40);
					if (entity instanceof EntityLivingBase)
						((EntityLivingBase) entity).setHealth((float) 40);
					if (entity instanceof EntityLivingBase) {
						ItemStack _setstack = new ItemStack(ItemKingdesword.block, (int) (1));
						_setstack.setCount(1);
						((EntityLivingBase) entity).setHeldItem(EnumHand.MAIN_HAND, _setstack);
						if (entity instanceof EntityPlayerMP)
							((EntityPlayerMP) entity).inventory.markDirty();
					}
					if (entity instanceof EntityLivingBase)
						((EntityLivingBase) entity).clearActivePotions();
				}
			}
		}
	}

	@SubscribeEvent
	public void onEntityDeath(LivingDeathEvent event) {
		if (event != null && event.getEntity() != null) {
			Entity entity = event.getEntity();
			int i = (int) entity.posX;
			int j = (int) entity.posY;
			int k = (int) entity.posZ;
			World world = entity.world;
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
