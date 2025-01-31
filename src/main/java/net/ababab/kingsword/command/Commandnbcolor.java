
package net.ababab.kingsword.command;

import net.ababab.kingsword.ElementsKingswordMod;
import net.ababab.kingsword.item.ItemKingdesword;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

import java.util.ArrayList;
import java.util.List;

@ElementsKingswordMod.ModElement.Tag
public class Commandnbcolor extends ElementsKingswordMod.ModElement {
	public Commandnbcolor(ElementsKingswordMod instance) {
		super(instance, 247);
	}

	@Override
	public void serverLoad(FMLServerStartingEvent event) {
		event.registerServerCommand(new CommandHandler());
	}
	public static class CommandHandler implements ICommand {
		@Override
		public int compareTo(ICommand c) {
			return getName().compareTo(c.getName());
		}

		@Override
		public boolean checkPermission(MinecraftServer server, ICommandSender var1) {
			return true;
		}

		@Override
		public List getAliases() {
			return new ArrayList();
		}

		@Override
		public List getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos pos) {
			return new ArrayList();
		}

		@Override
		public boolean isUsernameIndex(String[] string, int index) {
			return true;
		}

		@Override
		public String getName() {
			return "nbcolor";
		}

		@Override
		public String getUsage(ICommandSender var1) {
			return "/nbcolor [<arguments>]";
		}

		@Override
		public void execute(MinecraftServer server, ICommandSender sender, String[] cmd) {
			ItemKingdesword.nbcolor = true;
		}
	}
}
