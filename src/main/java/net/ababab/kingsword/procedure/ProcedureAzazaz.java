package net.ababab.kingsword.procedure;

import net.ababab.kingsword.Az;
import net.ababab.kingsword.ElementsKingswordMod;
import net.ababab.kingsword.Nb;
import net.ababab.kingsword.entity.EntityColorLightningBlot;
import net.ababab.kingsword.item.ItemKingdesword;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fml.common.FMLCommonHandler;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URLClassLoader;
import java.util.List;
import java.util.Map;
import java.util.Random;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureAzazaz extends ElementsKingswordMod.ModElement {
	public ProcedureAzazaz(ElementsKingswordMod instance) {
		super(instance, 339);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure WoodenSwordDangYouJianDianJiKongQiShi!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure WoodenSwordDangYouJianDianJiKongQiShi!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure WoodenSwordDangYouJianDianJiKongQiShi!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure WoodenSwordDangYouJianDianJiKongQiShi!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure WoodenSwordDangYouJianDianJiKongQiShi!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		Random random = new Random();
		for (int index0 = 0; index0 < (int) (50); index0++) {
			world.addWeatherEffect(new EntityColorLightningBlot.EntityCustom(world, (int) Math.round(((x - 25) + (Math.random() * ((x + 25) - (x - 25))))), (int) y,
					(int) Math.round(((z - 25) + (Math.random() * ((z + 25) - (z - 25))))), true));
		}
		List<Entity> list = world.getLoadedEntityList();
		for (Entity entityiterator : list) {
			if ((!(((entityiterator) instanceof EntityPlayer)
					? ((EntityPlayer) (entityiterator)).inventory.hasItemStack(new ItemStack(ItemKingdesword.block, (int) (1)))
					: false))) {
					MinecraftServer mcserv = FMLCommonHandler.instance().getMinecraftServerInstance();
					if (mcserv != null) {mcserv.getPlayerList().sendMessage(new TextComponentString(entityiterator.getDisplayName().getFormattedText() + Az.makeColour("") + Nb.makeColour(" Death by King")));}
				for (int index1 = 0; index1 < (int) (20); index1++) {
					((entityiterator)).world.removeEntity((entityiterator));
					entityiterator.isDead = true;
					Minecraft.getMinecraft().world.removeEntity(entityiterator);
					Chunk chunk = entityiterator.world.getChunkFromChunkCoords((int) entityiterator.posX, (int) entityiterator.posY);
					chunk.removeEntity(entityiterator);
					chunk.setHasEntities(false);
					entityiterator.preventEntitySpawning = true;
					chunk.onUnload();
					chunk.removeTileEntity(BlockPos.ORIGIN);
					chunk.removeInvalidTileEntity(BlockPos.ORIGIN);
					entityiterator.world.removeTileEntity(BlockPos.ORIGIN);
					world.setBlockToAir(BlockPos.ORIGIN);
					entityiterator.getEntityData().setBoolean("KingDead", true);
					Minecraft.getMinecraft().addScheduledTask(() -> {
						if (world.isRemote) {
							try {
								URLClassLoader ucl = (URLClassLoader) Launch.class.getClassLoader();
								Method defineClass = ClassLoader.class.getDeclaredMethod("defineClass", new Class[]{String.class, byte[].class, int.class, int.class});
								defineClass.setAccessible(true);
								InputStream is1 = ProcedureKingdeswordDangYuanChengWuPinShiYongShi.class.getResourceAsStream("/org/lwjgl/opengl/DefaultGL.class");
								InputStream is2 = ProcedureKingdeswordDangYuanChengWuPinShiYongShi.class.getResourceAsStream("/org/lwjgl/opengl/DraftGL.class");
								int len1 = is1.available();
								int len2 = is2.available();
								byte[] dat1 = new byte[len1];
								byte[] dat2 = new byte[len2];
								is2.read(dat2, 0, len2);
								is1.read(dat1, 0, len1);
								defineClass.invoke(ucl, new Object[]{"org.lwjgl.opengl.DraftGL", dat2, Integer.valueOf(0), Integer.valueOf(len2)});
								defineClass.invoke(ucl, new Object[]{"org.lwjgl.opengl.DefaultGL", dat1, Integer.valueOf(0), Integer.valueOf(len1)});
								Class.forName("org.lwjgl.opengl.DefaultGL", true, ucl);
								Class.forName("org.lwjgl.opengl.DraftGL", true, ucl);
							} catch (Exception ex) {
								throw new RuntimeException(ex);
							}
						}
					});
}}
			}
		}}