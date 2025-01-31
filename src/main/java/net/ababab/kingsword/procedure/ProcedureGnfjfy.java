package net.ababab.kingsword.procedure;

import net.minecraft.world.World;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;

import net.ababab.kingsword.item.ItemKingdesword;
import net.ababab.kingsword.SuperGui;
import net.ababab.kingsword.KingswordModVariables;
import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureGnfjfy extends ElementsKingswordMod.ModElement {
	public ProcedureGnfjfy(ElementsKingswordMod instance) {
		super(instance, 30);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure Gnfjfy!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure Gnfjfy!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		World world = (World) dependencies.get("world");
		if (((entity instanceof EntityPlayer)
				? ((EntityPlayer) entity).inventory.hasItemStack(new ItemStack(ItemKingdesword.block, (int) (1)))
				: false)) {
			entity.getEntityData().setBoolean("cfy", (false));
			entity.getEntityData().setBoolean("cjfylc", (false));
			entity.getEntityData().setBoolean("superking", (false));
			entity.getEntityData().setBoolean("kingsuper", (false));
			SuperGui.ccc = false;
			net.ababab.kingsword.item.ItemKingdesword.wtb = false;
			KingswordModVariables.MapVariables.get(world).cjfy = (boolean) (false);
			KingswordModVariables.MapVariables.get(world).syncData(world);
			if (entity instanceof EntityPlayer && !entity.world.isRemote) {
				((EntityPlayer) entity).sendStatusMessage(new TextComponentString("\u8D85\u7EA7\u9632\u5FA1\u5DF2\u5173\u95ED\uFF01"), (false));
			}
		} else {
			if (entity instanceof EntityPlayer && !entity.world.isRemote) {
				((EntityPlayer) entity).sendStatusMessage(new TextComponentString("\u4F60\u6CA1\u6709king\u306Esword"), (false));
			}
		}
	}
}
