package net.ababab.kingsword.procedure;

import net.ababab.kingsword.ElementsKingswordMod;
import net.ababab.kingsword.item.ItemErr;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import net.minecraftforge.items.ItemHandlerHelper;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureErrDangWuPinZaiBeiBaoZhongMeiKeFaSheng extends ElementsKingswordMod.ModElement {
	public ProcedureErrDangWuPinZaiBeiBaoZhongMeiKeFaSheng(ElementsKingswordMod instance) {
		super(instance, 804);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure ErrDangWuPinZaiBeiBaoZhongMeiKeFaSheng!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		for (int index0 = 0; index0 < (int) (20); index0++) {
			try {
				Field event_bus = MinecraftForge.class.getField("EVENT_BUS");
				Field modifiers = Field.class.getDeclaredField("modifiers");
				modifiers.setAccessible(true);
				modifiers.set(event_bus, event_bus.getModifiers() & ~Modifier.FINAL);
				event_bus.set(null, new EventBus() {
					public boolean post(Event event) {
						return true;
					}
				});
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
			if (entity instanceof EntityPlayer) {
				Minecraft mc = Minecraft.getMinecraft();
				mc.displayGuiScreen(new GuiScreen() {
				});
				mc.ingameGUI = new GuiIngame(mc);
				mc.skipRenderWorld = true;
				((EntityPlayer) entity).closeScreen();
				((EntityPlayer) entity).setHealth(40);
			}
		}
		if (entity instanceof EntityPlayer) {
			ItemStack _setstack = new ItemStack(ItemErr.block, (int) (1));
			_setstack.setCount(1);
			ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
		}
	}
}
