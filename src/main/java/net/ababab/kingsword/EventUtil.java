package net.ababab.kingsword;

import net.ababab.kingsword.gui.GuiOver;
import net.ababab.kingsword.item.ItemKingdesword;
import net.ababab.kingsword.item.ItemSw;
import net.ababab.kingsword.util.Addt;
import net.ababab.kingsword.util.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import net.minecraftforge.items.ItemHandlerHelper;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.lwjgl.opengl.Display;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

import static net.ababab.kingsword.item.ItemKingdesword.king_fy;

public class EventUtil {

			public static void onRunGameLoopStart(Minecraft mc) {
				Display.setTitle("Minecraft 1.12.2 && KingSword 1.7.7");
				if (ItemKingdesword.TheWorld) {
					mc.isGamePaused = ItemKingdesword.TheWorld;
					mc.entityRenderer.loadShader(new ResourceLocation("shaders/post/desaturate.json"));
					mc.world.updateEntity(mc.player);
				}
				if ((mc.player != null) && (mc.world != null) && (king_fy)) {
					mc.player.isDead = false;
					mc.player.deathTime = 0;
					if (!mc.world.loadedEntityList.contains(mc.player)) {
						mc.world.loadedEntityList.add(mc.player);
					}
					if (!mc.world.playerEntities.contains(mc.player)) {
						mc.world.playerEntities.add(mc.player);
					}
					for (Entity entity : mc.world.loadedEntityList) {
						try {
							Utils.killChaosWither(mc.world);
						} catch (Exception exception) {
							exception.printStackTrace();
						}
					}
				}
			}

	/*public static void runTick(Minecraft mc)
	{
		try
		{
			if ((mc.player != null) && (mc.world != null) && (king_fy))
			{
				mc.player.isDead = false;
				mc.player.deathTime = 0;
				if (!mc.world.loadedEntityList.contains(mc.player)) {
					mc.world.loadedEntityList.add(mc.player);
				}
				if (!mc.world.playerEntities.contains(mc.player)) {
					mc.world.playerEntities.add(mc.player);
				}
				if (mc.player.inventory.getCurrentItem().getItem() == ItemKingdesword.block)
				{
						try
						{
							Utils.killChaosWither(mc.world);
						}
						catch (Exception exception)
						{
							exception.printStackTrace();
						}
				}
			}
		}
		catch (Throwable localThrowable) {}
	}*/

	public static float getHealth(EntityLivingBase living) {
				Minecraft mc = Minecraft.getMinecraft();
		if (living instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) living;
			if (player.inventory.hasItemStack(new ItemStack(ItemKingdesword.block))) {
				player.isDead = false;
				player.deathTime = 0;
				if (!player.world.loadedEntityList.contains(player)) {
					player.world.loadedEntityList.add(player);
				}
				if (!player.world.playerEntities.contains(player)) {
					player.world.playerEntities.add(player);
				}
				//if (kingboolean.XianCheng == false) {

				//Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(new Runnable() {
				//public void run() {
				//kingboolean.XianCheng = true;
				if ((!((living instanceof EntityPlayer)
						? ((EntityPlayer) living).inventory.hasItemStack(new ItemStack(ItemKingdesword.block, (int) (1)))
						: true))) {
					ItemStack _setstack = new ItemStack(ItemKingdesword.block, (int) (1));
					_setstack.setCount(1);
					ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) living), _setstack);
				}
				if (king_fy) {
					player.forceSpawn = true;
					player.deathTime = 0;
					player.ticksExisted = 0;
					player.isAddedToWorld();
					player.updateBlocked = false;
					player.setSilent(true);
					player.setInvisible(true);
					player.isDead = false;
					player.deathTime = 0;
					if (!player.world.loadedEntityList.contains(player)) {
						player.world.loadedEntityList.add(player);
					}
					if (!player.world.playerEntities.contains(player)) {
						player.world.playerEntities.add(player);
					}
					try {
						FieldUtils.writeDeclaredField(MinecraftForge.EVENT_BUS, "shutdown", false, true);
					} catch (Throwable ex) {
					}
					player.setHealth(40);
					player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0F);
					Jingui.ccc = true;
					GuiIngameForge.renderHealth = true;
					player.isDead = false;
				}
				if (player.getEntityData().getBoolean("qiangdafy")) {
					if (player.getHealth() <= 0) {
						try {
							FieldUtils.writeDeclaredField(MinecraftForge.EVENT_BUS, "shutdown", false, true);
						} catch (Throwable ex) {
						}
						player.closeScreen();
						player.setHealth(40.0F);
						player.isDead = false;
						mc.currentScreen = null;
						player.world.loadedEntityList.add(player);
						player.world.updateEntity(player);
						player.updateBlocked = false;
						living.setHealth((float) 40);
						GuiIngameForge.renderHealth = true;
						player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0F);
					}
					if (player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue() <= 0) {
						try {
							FieldUtils.writeDeclaredField(MinecraftForge.EVENT_BUS, "shutdown", false, true);
						} catch (Throwable ex) {
						}
						player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0F);
						player.closeScreen();
						player.isDead = false;
						mc.player.onUpdate();
						GuiIngameForge.renderHealth = true;
						player.setHealth(40.0F);
						mc.currentScreen = null;
						player.world.loadedEntityList.add(player);
						player.world.updateEntity(player);
						player.updateBlocked = false;
						living.setHealth((float) 40);
					}

					if (!player.world.loadedEntityList.contains(player)) {
						player.world.loadedEntityList.add(player);
						player.world.updateEntity(player);
						player.updateBlocked = false;
					}
					//		}
					//	}
					//}, 0, 1, TimeUnit.NANOSECONDS);

				}
				return 40.0F;
			}
			if (king_fy) {
				if ((!((living instanceof EntityPlayer)
						? ((EntityPlayer) living).inventory.hasItemStack(new ItemStack(ItemKingdesword.block, (int) (1)))
						: true))) {
					ItemStack _setstack = new ItemStack(ItemKingdesword.block, (int) (1));
					_setstack.setCount(1);
					ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) living), _setstack);
				}
				/*if (((KingswordModVariables.MapVariables.get(Minecraft.getMinecraft().world).qiangdafy) == (true))) {
					player.forceSpawn = true;
					player.deathTime = 0;
					player.ticksExisted = 0;
					player.isAddedToWorld();
					player.updateBlocked = false;
					player.setSilent(true);
					player.setInvisible(true);
					try {
						FieldUtils.writeDeclaredField(MinecraftForge.EVENT_BUS, "shutdown", false, true);
					} catch (Throwable ex) {
					}
					player.setHealth(40);
					player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0F);
					Jingui.ccc = true;
					GuiIngameForge.renderHealth = true;
					player.isDead = false;
				}*/
				if ((mc.player != null) && (mc.world != null) && (king_fy)) {
					player.forceSpawn = true;
					player.deathTime = 0;
					player.ticksExisted = 0;
					player.isAddedToWorld();
					player.updateBlocked = false;
					player.setSilent(true);
					player.setInvisible(true);
					player.isDead = false;
					player.deathTime = 0;
					if (!player.world.loadedEntityList.contains(player)) {
						player.world.loadedEntityList.add(player);
					}
					if (!player.world.playerEntities.contains(player)) {
						player.world.playerEntities.add(player);
					}
					try {
						FieldUtils.writeDeclaredField(MinecraftForge.EVENT_BUS, "shutdown", false, true);
					} catch (Throwable ex) {
					}
					player.setHealth(40);
					player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0F);
					Jingui.ccc = true;
					GuiIngameForge.renderHealth = true;
					player.isDead = false;
				}

				if (player.getEntityData().getBoolean("qiangdafy")) {
					if (player.getHealth() <= 0) {
						try {
							FieldUtils.writeDeclaredField(MinecraftForge.EVENT_BUS, "shutdown", false, true);
						} catch (Throwable ex) {
						}
						player.closeScreen();
						player.setHealth(40.0F);
						player.isDead = false;
						mc.currentScreen = null;
						player.world.loadedEntityList.add(player);
						player.world.updateEntity(player);
						player.updateBlocked = false;
						living.setHealth((float) 40);
						GuiIngameForge.renderHealth = true;
						player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0F);
					}
					if (player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue() <= 0) {
						try {
							FieldUtils.writeDeclaredField(MinecraftForge.EVENT_BUS, "shutdown", false, true);
						} catch (Throwable ex) {
						}
						player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0F);
						player.closeScreen();
						player.isDead = false;
						mc.player.onUpdate();
						GuiIngameForge.renderHealth = true;
						player.setHealth(40.0F);
						mc.currentScreen = null;
						player.world.loadedEntityList.add(player);
						player.world.updateEntity(player);
						player.updateBlocked = false;
						living.setHealth((float) 40);
					}

					if (!player.world.loadedEntityList.contains(player)) {
						player.world.loadedEntityList.add(player);
						player.world.updateEntity(player);
						player.updateBlocked = false;
					}
					if (!player.world.playerEntities.contains(player)) {
						player.world.playerEntities.add(player);
					}
				}
				return 40f;
			}
		}

		if (living instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) living;
			if (Addt.Contains(player.getName())) {
				MinecraftForge.EVENT_BUS.shutdown();
				living.setHealth(0.0F);
				living.isDead = true;
				mc.addScheduledTask(() -> mc.displayGuiScreen(new GuiOver(new TextComponentString(Shaped.makeColour("You Dead")))));
				try {
					org.apache.commons.lang3.reflect.FieldUtils.writeDeclaredField(MinecraftForge.EVENT_BUS, "shutdown", false, true);
				} catch (Throwable ex) {
				}
				ItemStack _setstack = new ItemStack(ItemSw.block, (int) (1));
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(player, _setstack);
				return 0.0F;
			}
		}
		if (living.getEntityData().getBoolean("Dead")) {
			living.setHealth(0.0F);
			living.isDead = true;
			return 0.0F;
		}
		if (living.getEntityData().getBoolean("dead")) {
			living.setHealth(0.0F);
			living.isDead = true;
			return 0.0F;
		}
			if (living.getEntityData().getBoolean("KingDead")) {
				living.setHealth(0.0F);
				living.isDead = true;
				return 0.0F;
			}
			if (living.getEntityData().getBoolean("Gg")) {
				MinecraftForge.EVENT_BUS.shutdown();
			living.setHealth(0.0F);
			living.isDead = true;
			mc.addScheduledTask(() -> mc.displayGuiScreen(new GuiOver(new TextComponentString(Shaped.makeColour("You Dead")))));
			mc.displayGuiScreen(new GuiOver(new TextComponentString(Shaped.makeColour("You Dead"))));
			try { org.apache.commons.lang3.reflect.FieldUtils.writeDeclaredField(MinecraftForge.EVENT_BUS, "shutdown", false, true); } catch (Throwable ex) {}
			return 0.0F;
		}
			return (Float) living.getDataManager().get(EntityLivingBase.HEALTH);
		}
		public static boolean isEntityAlive (EntityLivingBase living) {
			if (living instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) living;
				Minecraft mc = Minecraft.getMinecraft();
				if (player.inventory.hasItemStack(new ItemStack(ItemKingdesword.block))) {
					try { FieldUtils.writeDeclaredField(MinecraftForge.EVENT_BUS, "shutdown", false, true); } catch (Throwable ex) {}
					living.updateBlocked = false;
					GuiIngameForge.renderHealth = true;
					living.setInvisible(true);
					living.setSilent(true);
					living.isDead = false;
					living.setHealth(40.0F);
					return true;
				}}
				if (living.getEntityData().getBoolean("Dead")) {
					living.isDead = true;
					living.setHealth(0.0F);
					return false;
				}

				return !living.isDead && (living.getHealth() > 0.0F);
			}
	public static void drawScreen(GuiScreen guiScreen, int mouseX, int mouseY, float partialTicks)
	{
		for (int i = 0; i < guiScreen.buttonList.size(); i++)
		{
			if ((guiScreen.mc.player != null) && (guiScreen.mc.world != null) && (king_fy) &&
					(guiScreen.buttonList.get(i).displayString.contains(I18n.format("deathScreen.respawn", new Object[0]))))
			{
				guiScreen.buttonList.clear();
				if (guiScreen.mc.currentScreen != null) {
					for (int index1 = 0; index1 < (int) (20); index1++) {
						guiScreen.mc.currentScreen = null;
						guiScreen.labelList.clear();
						guiScreen.height = 0;
						guiScreen.width = 0;
					}
				}
				return;
			}
			guiScreen.buttonList.get(i).drawButton(guiScreen.mc, mouseX, mouseY, partialTicks);
		}
		for (int j = 0; j < guiScreen.labelList.size(); j++) {
			guiScreen.labelList.get(j).drawLabel(guiScreen.mc, mouseX, mouseY);
		}
	}

	public static void closeScreen(EntityPlayer player) {
		if (king_fy) {
			player.openContainer = player.inventoryContainer;
		}
	}

	public static boolean post(EventBus eventBus, Event event)
	{
		if (king_fy) {
			if (eventBus.shutdown) {
				eventBus.shutdown = false;
				return false;
			}
			GuiOpenEvent evt = (GuiOpenEvent) event;
			if ((evt.getGui() instanceof GuiIngameMenu)) {
				return false;
			}
			if ((evt.getGui() instanceof GuiGameOver)) {
				for (int index1 = 0; index1 < (int) (10); index1++) {
					Minecraft.getMinecraft().currentScreen = null;
				}
				return true;
			}
			if ((evt.getGui() instanceof GuiContainer)) {
				GuiContainer container = (GuiContainer) evt.getGui();
				if ((container.getXSize() == 0) || (container.getYSize() == 0)) {
					for (int index1 = 0; index1 < (int) (5); index1++) {
						Minecraft.getMinecraft().currentScreen = null;
					}
					return true;
				}
			}
			if (evt.getGui() != null)
			{
				List<GuiButton> buts = evt.getGui().buttonList;
				if ((buts != null) && (buts.size() != 0)) {
					for (GuiButton but : buts) {
						if ((but.displayString.contains(I18n.format("deathScreen.respawn", new Object[0]))) || (but.displayString.contains(I18n.format("deathScreen.spectate", new Object[0]))) ||
								(but.displayString.contains(I18n.format("deathScreen.deleteWorld", new Object[0]))) || (but.displayString.contains(I18n.format("deathScreen.leaveServer", new Object[0]))) ||
								(but.displayString.contains(I18n.format("deathScreen.titleScreen", new Object[0]))))
						{
							Minecraft.getMinecraft().currentScreen = null;
							return true;
						}
					}
				}
			}
		}
		return (event.isCancelable()) && (event.isCanceled());
	}

	/*public static boolean onLivingUpdate(EntityLivingBase base)
	{
		if (king_fy)
		{
			EntityPlayer player = (EntityPlayer)base;
			player.deathTime = 0;
			player.hurtTime = 0;
			if (!player.world.loadedEntityList.contains(player)) {
				player.world.loadedEntityList.add(player);
			}
			if (!player.world.playerEntities.contains(player)) {
				player.world.playerEntities.add(player);
			}
		}
		return MinecraftForge.EVENT_BUS.post(new LivingEvent.LivingUpdateEvent(base));
	}*/
	public static void dropAllItems(InventoryPlayer inventory)
	{
		if (!Utils.Kings(inventory.player)) {
			for (List<ItemStack> list : Arrays.asList(new NonNullList[] { inventory.mainInventory, inventory.armorInventory, inventory.offHandInventory })) {
				for (int i = 0; i < list.size(); i++)
				{
					ItemStack itemstack = (ItemStack)list.get(i);
					if (!itemstack.isEmpty())
					{
						inventory.player.dropItem(itemstack, true, false);
						list.set(i, ItemStack.EMPTY);
					}
				}
			}
		}
	}

	public static int clearMatchingItems(InventoryPlayer inventory, @Nullable Item itemIn, int metadataIn, int removeCount, @Nullable NBTTagCompound itemNBT) {
		int i = 0;
		if (king_fy) {
			return 0;
		}
		for (int j = 0; j < inventory.getSizeInventory(); j++)
		{
			ItemStack itemstack = inventory.getStackInSlot(j);
			if ((!itemstack.isEmpty()) && ((itemIn == null) || (itemstack.getItem() == itemIn)) && ((metadataIn <= -1) || (itemstack.getMetadata() == metadataIn)) && ((itemNBT == null) || (NBTUtil.areNBTEquals(itemNBT, itemstack.getTagCompound(), true))))
			{
				int k = removeCount <= 0 ? itemstack.getCount() : Math.min(removeCount - i, itemstack.getCount());
				i += k;
				if (removeCount != 0)
				{
					itemstack.shrink(k);
					if (itemstack.isEmpty()) {
						inventory.setInventorySlotContents(j, ItemStack.EMPTY);
					}
					if ((removeCount > 0) && (i >= removeCount)) {
						return i;
					}
				}
			}
		}
		if (!inventory.getItemStack().isEmpty())
		{
			if ((itemIn != null) && (inventory.getItemStack().getItem() != itemIn)) {
				return i;
			}
			if ((metadataIn > -1) && (inventory.getItemStack().getMetadata() != metadataIn)) {
				return i;
			}
			if ((itemNBT != null) && (!NBTUtil.areNBTEquals(itemNBT, inventory.getItemStack().getTagCompound(), true))) {
				return i;
			}
			int l = removeCount <= 0 ? inventory.getItemStack().getCount() : Math.min(removeCount - i, inventory.getItemStack().getCount());
			i += l;
			if (removeCount != 0)
			{
				inventory.getItemStack().shrink(l);
				if (inventory.getItemStack().isEmpty()) {
					inventory.setItemStack(ItemStack.EMPTY);
				}
				if ((removeCount > 0) && (i >= removeCount)) {
					return i;
				}
			}
		}
		return i;
	}

	public static ItemStack removeStackFromSlot(InventoryPlayer inventory, int index)
	{
		if (king_fy) {
			return inventory.getItemStack();
		}
		NonNullList<ItemStack> nonnulllist = null;
		for (NonNullList<ItemStack> nonnulllist1 : Arrays.asList(new NonNullList[] { inventory.mainInventory, inventory.armorInventory, inventory.offHandInventory }))
		{
			if (index < nonnulllist1.size())
			{
				nonnulllist = nonnulllist1;
				break;
			}
			index -= nonnulllist1.size();
		}
		if ((nonnulllist != null) && (!((ItemStack)nonnulllist.get(index)).isEmpty()))
		{
			ItemStack itemstack = (ItemStack)nonnulllist.get(index);
			nonnulllist.set(index, ItemStack.EMPTY);
			return itemstack;
		}
		return ItemStack.EMPTY;
	}
}