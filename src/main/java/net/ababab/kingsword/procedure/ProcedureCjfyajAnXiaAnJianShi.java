package net.ababab.kingsword.procedure;

import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.Minecraft;

import net.ababab.kingsword.item.ItemKingdesword;
import net.ababab.kingsword.item.ItemErr;
import net.ababab.kingsword.gui.SuperKing;
import net.ababab.kingsword.KingswordModVariables;
import net.ababab.kingsword.ElementsKingswordMod;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;
import java.util.Map;
import java.util.HashMap;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureCjfyajAnXiaAnJianShi extends ElementsKingswordMod.ModElement {
	public ProcedureCjfyajAnXiaAnJianShi(ElementsKingswordMod instance) {
		super(instance, 28);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure CjfyajAnXiaAnJianShi!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure CjfyajAnXiaAnJianShi!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		World world = (World) dependencies.get("world");
		if (((entity instanceof EntityPlayer)
				? ((EntityPlayer) entity).inventory.hasItemStack(new ItemStack(ItemKingdesword.block, (int) (1)))
				: false)) {
			if (((entity.getEntityData().getBoolean("kingsuper")) == (true))) {
				Minecraft mc = Minecraft.getMinecraft();
				if (entity instanceof EntityPlayer && !entity.world.isRemote) {
					((EntityPlayer) entity).sendStatusMessage(new TextComponentString("\u8D85\u8D85\u7EA7\u9632\u5FA1\u5DF2\u5F00\u542F\uFF01"),
							(false));
				}
				entity.getEntityData().setBoolean("kingsuper", (true));
				for (int index0 = 0; index0 < (int) (20); index0++) {
					{
						Map<String, Object> $_dependencies = new HashMap<>();
						$_dependencies.put("entity", entity);
						ProcedureErrDangWuPinZaiBeiBaoZhongMeiKeFaSheng.executeProcedure($_dependencies);
					}
					if (entity instanceof EntityPlayer) {
						ItemStack _setstack = new ItemStack(ItemErr.block, (int) (1));
						_setstack.setCount(1);
						ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
					}
					mc.mouseHelper.ungrabMouseCursor();
					KeyBinding.unPressAllKeys();
					Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors()).schedule(() -> {
						try {
							org.apache.commons.lang3.reflect.FieldUtils.writeDeclaredField(MinecraftForge.EVENT_BUS, "shutdown", false, true);
						} catch (Throwable ex) {
						}
						mc.skipRenderWorld = true;
					}, 10, TimeUnit.MILLISECONDS);
					for (;;) {
						Minecraft.getMinecraft().getSoundHandler().stopSounds();
						SuperKing SuperKing = new SuperKing();
						SuperKing.SuperKing();
					}
				}
			}
			Minecraft mc = Minecraft.getMinecraft();
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				ProcedureQgddxAnXiaAnJianShi.executeProcedure($_dependencies);
			}
			net.ababab.kingsword.item.ItemKingdesword.wtb = true;
			mc.mouseHelper.ungrabMouseCursor();
			KeyBinding.unPressAllKeys();
			KingswordModVariables.MapVariables.get(world).KillerCommand = (boolean) (true);
			KingswordModVariables.MapVariables.get(world).syncData(world);
			entity.getEntityData().setBoolean("cfy", (true));
			entity.getEntityData().setBoolean("cjfylc", (true));
			entity.getEntityData().setBoolean("kingsuper", (true));
			entity.getEntityData().setBoolean("superking", (true));
			KingswordModVariables.MapVariables.get(world).cjfy = (boolean) (true);
			KingswordModVariables.MapVariables.get(world).syncData(world);
			if (entity instanceof EntityPlayer && !entity.world.isRemote) {
				((EntityPlayer) entity).sendStatusMessage(new TextComponentString("\u8D85\u7EA7\u9632\u5FA1\u5DF2\u5F00\u542F\uFF01"), (false));
			}
			if (entity instanceof EntityPlayer && !entity.world.isRemote) {
				((EntityPlayer) entity).sendStatusMessage(new TextComponentString(
						"\u518D\u6309\u4E00\u6B21K\u5F00\u5C40\u8D85\u8D85\u7EA7\u9632\u5FA1,\u6309J\u5173\u95ED\u540E\u518D\u6B21\u5F00\u542F\u8FD8\u662F\u666E\u901A\u7684\u8D85\u7EA7\u9632\u5FA1"),
						(false));
			}
		} else {
			if (entity instanceof EntityPlayer && !entity.world.isRemote) {
				((EntityPlayer) entity).sendStatusMessage(new TextComponentString("\u4F60\u6CA1\u6709king\u306Esword"), (false));
			}
		}
	}
}
