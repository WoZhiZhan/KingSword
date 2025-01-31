package net.ababab.kingsword.procedure;

import net.minecraftforge.fml.common.FMLCommonHandler;

import net.minecraft.world.World;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;

import net.ababab.kingsword.entity.EntityYueshuzhe;
import net.ababab.kingsword.KingswordModVariables;
import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;
import java.util.List;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureYuesuzheshashou extends ElementsKingswordMod.ModElement {
	public ProcedureYuesuzheshashou(ElementsKingswordMod instance) {
		super(instance, 476);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure Yuesuzheshashou!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure Yuesuzheshashou!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure Yuesuzheshashou!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure Yuesuzheshashou!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		world.addWeatherEffect(new EntityLightningBolt(world, (int) x, (int) y, (int) z, false));
		KingswordModVariables.MapVariables.get(world).yuesu = (boolean) (false);
		KingswordModVariables.MapVariables.get(world).syncData(world);
		KingswordModVariables.MapVariables.get(world).yuesuchengsheng = (boolean) (false);
		KingswordModVariables.MapVariables.get(world).syncData(world);
		List<Entity> list = world.getLoadedEntityList();
		for (Entity entityiterator : list) {
			if (((entityiterator) instanceof EntityYueshuzhe.EntityCustom)) {
				((entityiterator)).world.removeEntity((entityiterator));
				if ((entityiterator) instanceof EntityLivingBase)
					((EntityLivingBase) (entityiterator)).setHealth((float) 0);
			}
		}
		{
			MinecraftServer mcserv = FMLCommonHandler.instance().getMinecraftServerInstance();
			if (mcserv != null)
				mcserv.getPlayerList().sendMessage(new TextComponentString("\u00A74\u7EA6\u675F\u8005\u5DF2\u88AB\u6E05\u9664"));
		}
		world.playSound((EntityPlayer) null, x, y, z,
				(net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY.getObject(new ResourceLocation("kingsword:gj")),
				SoundCategory.NEUTRAL, (float) 1, (float) 1);
	}
}
