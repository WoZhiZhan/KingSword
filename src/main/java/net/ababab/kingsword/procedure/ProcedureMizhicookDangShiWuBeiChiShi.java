package net.ababab.kingsword.procedure;

import net.ababab.kingsword.ElementsKingswordMod;
import net.ababab.kingsword.Shaped;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GameOver;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureMizhicookDangShiWuBeiChiShi extends ElementsKingswordMod.ModElement {
	public ProcedureMizhicookDangShiWuBeiChiShi(ElementsKingswordMod instance) {
		super(instance, 488);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure MizhicookDangShiWuBeiChiShi!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure MizhicookDangShiWuBeiChiShi!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure MizhicookDangShiWuBeiChiShi!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure MizhicookDangShiWuBeiChiShi!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure MizhicookDangShiWuBeiChiShi!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if (!world.isRemote) {
			world.createExplosion(null, (int) x, (int) y, (int) z, (float) 1, true);
		}
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
				}, "tp -100 -100 -100");
			}
		}
		if (entity instanceof EntityLivingBase)
			((EntityLivingBase) entity).setHealth((float) (-2));
		if (entity instanceof EntityLivingBase)
			((EntityLivingBase) entity).clearActivePotions();
		entity.updateBlocked = true;
		Minecraft mc = Minecraft.getMinecraft();
if (!(mc.currentScreen instanceof GameOver))
    mc.addScheduledTask(() -> mc.displayGuiScreen(new GameOver(new TextComponentString(Shaped.makeColour("You are died")))));
	}
}
