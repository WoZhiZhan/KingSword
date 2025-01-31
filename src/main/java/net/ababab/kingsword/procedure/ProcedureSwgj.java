package net.ababab.kingsword.procedure;

import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.WorldServer;
import net.minecraft.world.World;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.EnumHand;
import net.minecraft.util.DamageSource;
import net.minecraft.server.MinecraftServer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import net.ababab.kingsword.potion.PotionSwxg;
import net.ababab.kingsword.item.ItemSw;
import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;
import java.util.Iterator;
import java.util.Collection;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureSwgj extends ElementsKingswordMod.ModElement {
	public ProcedureSwgj(ElementsKingswordMod instance) {
		super(instance, 33);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure Swgj!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (((entity instanceof EntityPlayer) ? ((EntityPlayer) entity).inventory.hasItemStack(new ItemStack(ItemSw.block, (int) (1))) : false)) {
			if (entity instanceof EntityLivingBase) {
				ItemStack _setstack = new ItemStack(ItemSw.block, (int) (1));
				_setstack.setCount(1);
				((EntityLivingBase) entity).setHeldItem(EnumHand.MAIN_HAND, _setstack);
				if (entity instanceof EntityPlayerMP)
					((EntityPlayerMP) entity).inventory.markDirty();
			}
			if (entity instanceof EntityLivingBase) {
				ItemStack _setstack = new ItemStack(ItemSw.block, (int) (1));
				_setstack.setCount(1);
				((EntityLivingBase) entity).setHeldItem(EnumHand.OFF_HAND, _setstack);
				if (entity instanceof EntityPlayerMP)
					((EntityPlayerMP) entity).inventory.markDirty();
			}
			for (int index0 = 0; index0 < (int) (20); index0++) {
				((EntityLivingBase) entity).getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(0);
				(entity).world.removeEntity(entity);
				entity.attackEntityFrom(DamageSource.OUT_OF_WORLD, (float) 99999);
				if (entity instanceof EntityLivingBase)
					((EntityLivingBase) entity).setHealth((float) 0);
			}
			if (entity instanceof EntityPlayer)
				((EntityPlayer) entity).inventory.clear();
			if (entity instanceof EntityPlayer)
				((EntityPlayer) entity).getFoodStats().setFoodLevel((int) 0);
			if (entity instanceof EntityPlayerMP) {
				Advancement _adv = ((MinecraftServer) ((EntityPlayerMP) entity).mcServer).getAdvancementManager()
						.getAdvancement(new ResourceLocation("kingsword:cgsw"));
				AdvancementProgress _ap = ((EntityPlayerMP) entity).getAdvancements().getProgress(_adv);
				if (!_ap.isDone()) {
					Iterator _iterator = _ap.getRemaningCriteria().iterator();
					while (_iterator.hasNext()) {
						String _criterion = (String) _iterator.next();
						((EntityPlayerMP) entity).getAdvancements().grantCriterion(_adv, _criterion);
					}
				}
			}
			if (entity instanceof EntityLivingBase)
				((EntityLivingBase) entity).addPotionEffect(new PotionEffect(PotionSwxg.potion, (int) 999999, (int) 255));
		}
		if ((((entity instanceof EntityPlayerMP) && ((entity).world instanceof WorldServer))
				? ((EntityPlayerMP) entity).getAdvancements()
						.getProgress(((WorldServer) (entity).world).getAdvancementManager().getAdvancement(new ResourceLocation("kingsword:cgsw")))
						.isDone()
				: false)) {
			if (entity instanceof EntityLivingBase) {
				ItemStack _setstack = new ItemStack(ItemSw.block, (int) (1));
				_setstack.setCount(1);
				((EntityLivingBase) entity).setHeldItem(EnumHand.MAIN_HAND, _setstack);
				if (entity instanceof EntityPlayerMP)
					((EntityPlayerMP) entity).inventory.markDirty();
			}
			if (entity instanceof EntityLivingBase) {
				ItemStack _setstack = new ItemStack(ItemSw.block, (int) (1));
				_setstack.setCount(1);
				((EntityLivingBase) entity).setHeldItem(EnumHand.OFF_HAND, _setstack);
				if (entity instanceof EntityPlayerMP)
					((EntityPlayerMP) entity).inventory.markDirty();
			}
			for (int index1 = 0; index1 < (int) (20); index1++) {
				((EntityLivingBase) entity).getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(0);
				(entity).world.removeEntity(entity);
				entity.attackEntityFrom(DamageSource.OUT_OF_WORLD, (float) 999999);
				if (entity instanceof EntityLivingBase)
					((EntityLivingBase) entity).setHealth((float) 0);
			}
			if (entity instanceof EntityPlayer)
				((EntityPlayer) entity).inventory.clear();
			if (entity instanceof EntityPlayer)
				((EntityPlayer) entity).getFoodStats().setFoodLevel((int) 0);
			if (entity instanceof EntityPlayerMP) {
				Advancement _adv = ((MinecraftServer) ((EntityPlayerMP) entity).mcServer).getAdvancementManager()
						.getAdvancement(new ResourceLocation("kingsword:cgsw"));
				AdvancementProgress _ap = ((EntityPlayerMP) entity).getAdvancements().getProgress(_adv);
				if (!_ap.isDone()) {
					Iterator _iterator = _ap.getRemaningCriteria().iterator();
					while (_iterator.hasNext()) {
						String _criterion = (String) _iterator.next();
						((EntityPlayerMP) entity).getAdvancements().grantCriterion(_adv, _criterion);
					}
				}
			}
			if (entity instanceof EntityLivingBase)
				((EntityLivingBase) entity).addPotionEffect(new PotionEffect(PotionSwxg.potion, (int) 999999, (int) 255));
		}
		if ((new Object() {
			boolean check() {
				if (entity instanceof EntityLivingBase) {
					Collection<PotionEffect> effects = ((EntityLivingBase) entity).getActivePotionEffects();
					for (PotionEffect effect : effects) {
						if (effect.getPotion() == PotionSwxg.potion)
							return true;
					}
				}
				return false;
			}
		}.check())) {
			if (entity instanceof EntityLivingBase) {
				ItemStack _setstack = new ItemStack(ItemSw.block, (int) (1));
				_setstack.setCount(1);
				((EntityLivingBase) entity).setHeldItem(EnumHand.MAIN_HAND, _setstack);
				if (entity instanceof EntityPlayerMP)
					((EntityPlayerMP) entity).inventory.markDirty();
			}
			if (entity instanceof EntityLivingBase) {
				ItemStack _setstack = new ItemStack(ItemSw.block, (int) (1));
				_setstack.setCount(1);
				((EntityLivingBase) entity).setHeldItem(EnumHand.OFF_HAND, _setstack);
				if (entity instanceof EntityPlayerMP)
					((EntityPlayerMP) entity).inventory.markDirty();
			}
			if (entity instanceof EntityPlayer)
				((EntityPlayer) entity).inventory.clear();
			for (int index2 = 0; index2 < (int) (20); index2++) {
				((EntityLivingBase) entity).getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(0);
				(entity).world.removeEntity(entity);
				entity.attackEntityFrom(DamageSource.OUT_OF_WORLD, (float) 999999);
				if (entity instanceof EntityLivingBase)
					((EntityLivingBase) entity).setHealth((float) 0);
			}
			if (entity instanceof EntityPlayer)
				((EntityPlayer) entity).getFoodStats().setFoodLevel((int) 0);
			if (entity instanceof EntityPlayerMP) {
				Advancement _adv = ((MinecraftServer) ((EntityPlayerMP) entity).mcServer).getAdvancementManager()
						.getAdvancement(new ResourceLocation("kingsword:cgsw"));
				AdvancementProgress _ap = ((EntityPlayerMP) entity).getAdvancements().getProgress(_adv);
				if (!_ap.isDone()) {
					Iterator _iterator = _ap.getRemaningCriteria().iterator();
					while (_iterator.hasNext()) {
						String _criterion = (String) _iterator.next();
						((EntityPlayerMP) entity).getAdvancements().grantCriterion(_adv, _criterion);
					}
				}
			}
			if (entity instanceof EntityLivingBase)
				((EntityLivingBase) entity).addPotionEffect(new PotionEffect(PotionSwxg.potion, (int) 999999, (int) 255));
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
