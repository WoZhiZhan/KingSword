
package net.ababab.kingsword.keybind;

import net.ababab.kingsword.ElementsKingswordMod;
import net.ababab.kingsword.KingswordMod;
import net.ababab.kingsword.procedure.ProcedureAzazaz;
import net.ababab.kingsword.procedure.ProcedureKingdeswordDangYuanChengWuPinShiYongShi;
import net.ababab.kingsword.procedure.kd;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import java.util.HashMap;
import java.util.Map;

import static net.ababab.kingsword.item.ItemKingdesword.kill;
import static net.ababab.kingsword.item.ItemKingdesword.killWeather;

@ElementsKingswordMod.ModElement.Tag
public class KeyBindingXkill extends ElementsKingswordMod.ModElement {
	private KeyBinding keys;
	public KeyBindingXkill(ElementsKingswordMod instance) {
		super(instance, 170);
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		elements.addNetworkMessage(KeyBindingPressedMessageHandler.class, KeyBindingPressedMessage.class, Side.SERVER);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void init(FMLInitializationEvent event) {
		keys = new KeyBinding("key.mcreator.x", Keyboard.KEY_X, "key.categories.misc");
		ClientRegistry.registerKeyBinding(keys);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onKeyInput(InputEvent.KeyInputEvent event) {
		if (Minecraft.getMinecraft().currentScreen == null) {
			if (Keyboard.isKeyDown(keys.getKeyCode())) {
				KingswordMod.PACKET_HANDLER.sendToServer(new KeyBindingPressedMessage());
				pressAction(Minecraft.getMinecraft().player);
			}
		}
	}
	public static class KeyBindingPressedMessageHandler implements IMessageHandler<KeyBindingPressedMessage, IMessage> {
		@Override
		public IMessage onMessage(KeyBindingPressedMessage message, MessageContext context) {
			EntityPlayerMP entity = context.getServerHandler().player;
			entity.getServerWorld().addScheduledTask(() -> {
				pressAction(entity);
			});
			return null;
		}
	}

	public static class KeyBindingPressedMessage implements IMessage {
		@Override
		public void toBytes(io.netty.buffer.ByteBuf buf) {
		}

		@Override
		public void fromBytes(io.netty.buffer.ByteBuf buf) {
		}
	}
	private static void pressAction(EntityPlayer entity) {
		World world = entity.world;
		int x = (int) entity.posX;
		int y = (int) entity.posY;
		int z = (int) entity.posZ;
		if (!world.isBlockLoaded(new BlockPos(x, y, z)))
			return;
		{
			Map<String, Object> $_dependencies = new HashMap<>();
			$_dependencies.put("entity", entity);
			$_dependencies.put("world", world);
			ProcedureAzazaz.executeProcedure($_dependencies);
			ProcedureKingdeswordDangYuanChengWuPinShiYongShi.executeProcedure($_dependencies);
			kd.executeProcedure($_dependencies);
			for (int index1 = 0; index1 < (int) (5); index1++) {
				for (int i2 = 0; i2 < 90; i2++) {
					for (int i = 0; i < world.loadedEntityList.size(); i++) {
						Entity e = world.loadedEntityList.get(i);
						if (e != null && e != entity) {
							kill(e);
						}
					}
					for (int i = 0; i < world.weatherEffects.size(); i++) {
						Entity e = world.weatherEffects.get(i);
						if (e != null && e != entity) {
							kill(e);
						}
					}
					for (int i = 0; i < world.weatherEffects.size(); i++) {
						Entity e = world.weatherEffects.get(i);
						if (e != null && e != entity) {
							killWeather(e);
						}
					}
					for (int i = 0; i < world.loadedEntityList.size(); i++) {
						Entity e = world.loadedEntityList.get(i);
						if (e != null && e != entity) {
							killWeather(e);
						}
					}
				}
			}
		}
	}
}
