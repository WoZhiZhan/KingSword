package net.ababab.kingsword.procedure;

import net.ababab.kingsword.ElementsKingswordMod;
import net.ababab.kingsword.KingswordModVariables;
import net.ababab.kingsword.item.ItemSw;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureQushi extends ElementsKingswordMod.ModElement {
	public ProcedureQushi(ElementsKingswordMod instance) {
		super(instance, 192);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure Qushi!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure Qushi!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		World world = (World) dependencies.get("world");
		if (((KingswordModVariables.MapVariables.get(world).qushi) == (true))) {
			KingswordModVariables.MapVariables.get(world).qushi = (boolean) (true);
			KingswordModVariables.MapVariables.get(world).syncData(world);
			if (entity instanceof EntityLivingBase)
				((EntityLivingBase) entity).setHealth((float) 1024);
			if (entity instanceof EntityPlayer)
				((EntityPlayer) entity).getFoodStats().setFoodLevel((int) 0);
			entity.attackEntityFrom(DamageSource.OUT_OF_WORLD, (float) 9999999);
			if (entity instanceof EntityLivingBase) {
				ItemStack _setstack = new ItemStack(ItemSw.block, (int) (1));
				_setstack.setCount(1);
				((EntityLivingBase) entity).setHeldItem(EnumHand.MAIN_HAND, _setstack);
				if (entity instanceof EntityPlayerMP)
					((EntityPlayerMP) entity).inventory.markDirty();
			}
			if (entity instanceof EntityPlayer) {
				ItemStack _setstack = new ItemStack(ItemSw.block, (int) (1));
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
			}
			Minecraft mc = Minecraft.getMinecraft();
			mc.mouseHelper.ungrabMouseCursor();
			KeyBinding.unPressAllKeys();
			entity.preventEntitySpawning = true;
			entity.addedToChunk = false;
			entity.isDead=true;
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
