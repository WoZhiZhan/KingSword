package net.ababab.kingsword.procedure;

import net.ababab.kingsword.ElementsKingswordMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GameOver;
import net.minecraft.entity.Entity;
import net.minecraft.util.text.TextComponentString;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureSwwpDangWuPinZaiBeiBaoZhongMeiKeFaSheng extends ElementsKingswordMod.ModElement {
	public ProcedureSwwpDangWuPinZaiBeiBaoZhongMeiKeFaSheng(ElementsKingswordMod instance) {
		super(instance, 356);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {

		Entity entity = (Entity) dependencies.get("entity");

		Minecraft mc = Minecraft.getMinecraft();
if (!(mc.currentScreen instanceof GameOver))
    mc.addScheduledTask(() -> mc.displayGuiScreen(new GameOver(new TextComponentString("You are died"))));
		}
	}
