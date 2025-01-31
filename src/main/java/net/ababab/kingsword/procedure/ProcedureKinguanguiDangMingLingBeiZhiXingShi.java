package net.ababab.kingsword.procedure;

import net.minecraft.util.text.TextComponentString;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;

import net.ababab.kingsword.item.ItemKingdesword;
import net.ababab.kingsword.ElementsKingswordMod;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;
import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureKinguanguiDangMingLingBeiZhiXingShi extends ElementsKingswordMod.ModElement {
	public ProcedureKinguanguiDangMingLingBeiZhiXingShi(ElementsKingswordMod instance) {
		super(instance, 531);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure KinguanguiDangMingLingBeiZhiXingShi!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (((entity instanceof EntityPlayer)
				? ((EntityPlayer) entity).inventory.hasItemStack(new ItemStack(ItemKingdesword.block, (int) (1)))
				: false)) {
			entity.getEntityData().setBoolean("kgui", (true));
			if (entity instanceof EntityPlayer && !entity.world.isRemote) {
				((EntityPlayer) entity)
						.sendStatusMessage(new TextComponentString("\u5173gui\u5DF2\u5F00\u542F,20\u79D2\u540E\u81EA\u52A8\u5173\u95ED"), (false));
			}
			Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors()).schedule(() -> {
				entity.getEntityData().setBoolean("kgui", (false));
				if (entity instanceof EntityPlayer && !entity.world.isRemote) {
					((EntityPlayer) entity).sendStatusMessage(new TextComponentString("\u5173gui\u5DF2\u5173\u95ED"), (false));
				}
			}, 20000, TimeUnit.MILLISECONDS);
		}
	}
}
