package net.ababab.kingsword.procedure;

import net.minecraft.world.World;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.command.ICommandSender;

import net.ababab.kingsword.block.BlockJihuofangkuai;
import net.ababab.kingsword.KingswordModVariables;
import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureEhz extends ElementsKingswordMod.ModElement {
	public ProcedureEhz(ElementsKingswordMod instance) {
		super(instance, 483);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure Ehz!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure Ehz!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure Ehz!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure Ehz!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure Ehz!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockJihuofangkuai.block.getDefaultState().getBlock())) {
			if (entity instanceof EntityPlayer && !entity.world.isRemote) {
				((EntityPlayer) entity).sendStatusMessage(new TextComponentString(
						"\u5DF2\u6FC0\u6D3B\uFF01\u628A\u672A\u77E5\u6076\u9B42\u306E\u6740\u624B\u62FF\u5E26\u5728\u80CC\u5305\u4E2D\u5373\u53EF\u6D88\u706D\u672A\u77E5\u6076\u9B42"),
						(false));
			}
			KingswordModVariables.MapVariables.get(world).ehjs = (boolean) (true);
			KingswordModVariables.MapVariables.get(world).syncData(world);
			{
				Entity _ent = entity;
				if (!_ent.world.isRemote && _ent.world.getMinecraftServer() != null) {
					_ent.world.getMinecraftServer().getCommandManager().executeCommand(new ICommandSender() {
						@Override
						public String getName() {
							return "";
						}

						@Override
						public boolean canUseCommand(int permission, String command) {
							return true;
						}

						@Override
						public World getEntityWorld() {
							return _ent.world;
						}

						@Override
						public MinecraftServer getServer() {
							return _ent.world.getMinecraftServer();
						}

						@Override
						public boolean sendCommandFeedback() {
							return false;
						}

						@Override
						public BlockPos getPosition() {
							return _ent.getPosition();
						}

						@Override
						public Vec3d getPositionVector() {
							return new Vec3d(_ent.posX, _ent.posY, _ent.posZ);
						}

						@Override
						public Entity getCommandSenderEntity() {
							return _ent;
						}
					}, "title @a title [{\"text\":\"\u5DF2\u6FC0\u6D3B\",\"color\":\"dark_red\",\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false}]");
				}
			}
		} else {
			if (entity instanceof EntityPlayer && !entity.world.isRemote) {
				((EntityPlayer) entity).sendStatusMessage(new TextComponentString("\u6B64\u65B9\u5757\u4E0D\u662F \u6FC0\u6D3B\u65B9\u5757"),
						(false));
			}
		}
	}
}
