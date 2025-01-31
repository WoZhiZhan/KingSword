package net.ababab.kingsword.procedure;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;

import net.ababab.kingsword.item.ItemKingdesword;
import net.ababab.kingsword.KingswordModVariables;
import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureFy7 extends ElementsKingswordMod.ModElement {
	public ProcedureFy7(ElementsKingswordMod instance) {
		super(instance, 216);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure Fy7!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure Fy7!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		World world = (World) dependencies.get("world");
		if (((entity instanceof EntityPlayer)
				? ((EntityPlayer) entity).inventory.hasItemStack(new ItemStack(ItemKingdesword.block, (int) (1)))
				: false)) {
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
			if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHealth() : -1) == 0)) {
				for (int index0 = 0; index0 < (int) (2); index0++) {
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
			if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHealth() : -1) != ((entity instanceof EntityLivingBase)
					? ((EntityLivingBase) entity).getMaxHealth()
					: -1))) {
				for (int index1 = 0; index1 < (int) (2); index1++) {
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
				}
			}
		}
		if (((KingswordModVariables.MapVariables.get(world).qiangdafy) == (true))) {
			if (((entity instanceof EntityPlayer)
					? ((EntityPlayer) entity).inventory.hasItemStack(new ItemStack(ItemKingdesword.block, (int) (1)))
					: false)) {
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
				if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHealth() : -1) == 0)) {
					for (int index2 = 0; index2 < (int) (2); index2++) {
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
				if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHealth() : -1) != ((entity instanceof EntityLivingBase)
						? ((EntityLivingBase) entity).getMaxHealth()
						: -1))) {
					for (int index3 = 0; index3 < (int) (2); index3++) {
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
					}
				}
			}
		}
	}

	@Override
	public void init(FMLInitializationEvent event) {
		this.executeProcedure(new java.util.HashMap<>());
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(this);
	}
}
