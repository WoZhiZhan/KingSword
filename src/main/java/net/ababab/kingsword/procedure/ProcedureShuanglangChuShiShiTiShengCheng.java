package net.ababab.kingsword.procedure;

import net.minecraftforge.fml.common.FMLCommonHandler;

import net.minecraft.world.World;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;

import net.ababab.kingsword.KingswordModVariables;
import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureShuanglangChuShiShiTiShengCheng extends ElementsKingswordMod.ModElement {
	public ProcedureShuanglangChuShiShiTiShengCheng(ElementsKingswordMod instance) {
		super(instance, 197);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure ShuanglangChuShiShiTiShengCheng!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure ShuanglangChuShiShiTiShengCheng!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure ShuanglangChuShiShiTiShengCheng!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure ShuanglangChuShiShiTiShengCheng!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure ShuanglangChuShiShiTiShengCheng!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		KingswordModVariables.MapVariables.get(world).wddsl = (boolean) (true);
		KingswordModVariables.MapVariables.get(world).syncData(world);
		{
			MinecraftServer mcserv = FMLCommonHandler.instance().getMinecraftServerInstance();
			if (mcserv != null)
				mcserv.getPlayerList().sendMessage(new TextComponentString(
						"\u971C\u72FC:\u00A73\u6211\u662F\u65E0\u654C\u7684,\u522B\u60F3\u7740\u80FD\u6740\u6B7B\u6211,\u00A7e\u5982\u679C\u6211\u6CA1\u4E86,\u9000\u51FA\u6E38\u620F\u91CD\u65B0\u8FDB\u518D\u8BD5\u8BD5"));
		}
		{
			MinecraftServer mcserv = FMLCommonHandler.instance().getMinecraftServerInstance();
			if (mcserv != null)
				mcserv.getPlayerList().sendMessage(new TextComponentString(
						"\u971C\u72FC:\u00A7e\u6211\u662F\u65E0\u654C\u7684,\u522B\u60F3\u7740\u80FD\u6740\u6B7B\u6211,\u00A73\u5982\u679C\u6211\u6CA1\u4E86,\u9000\u51FA\u6E38\u620F\u91CD\u65B0\u8FDB\u518D\u8BD5\u8BD5"));
		}
		if (entity instanceof EntityLivingBase)
			((EntityLivingBase) entity).setHealth((float) 20);
		if (entity instanceof EntityLivingBase)
			((EntityLivingBase) entity).setHealth((float) 20);
		world.playSound((EntityPlayer) null, x, y, z,
				(net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY.getObject(new ResourceLocation("kingsword:zyzy")),
				SoundCategory.NEUTRAL, (float) 1, (float) 1);
	}
}
