
package net.ababab.kingsword.item;

import net.ababab.kingsword.*;
import net.ababab.kingsword.creativetab.TabKingdwpl;
import net.ababab.kingsword.procedure.*;
import net.ababab.kingsword.util.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@ElementsKingswordMod.ModElement.Tag
public class ItemKingdesword extends ElementsKingswordMod.ModElement {
	public static boolean TheWorld;
	public static boolean wtb = false;
	public static boolean king_fy = false;
	public static boolean nbcolor = false;
	public static boolean colors = false;
	@GameRegistry.ObjectHolder("kingsword:kingdesword")
	public static final Item block = null;
	public ItemKingdesword(ElementsKingswordMod instance) {
		super(instance, 1);
	}
		public static void kill(Entity ent){
			try {
				if (ent instanceof EntityLivingBase) {
					EntityLivingBase living = (EntityLivingBase) ent;
					living.deathTime = 20;
					living.hurtTime = 20;
					living.maxHurtTime = 20;
					living.clearActivePotions();
					living.getEntityWorld().loadedTileEntityList.remove(living);
					living.getActivePotionEffects().clear();
					living.setAir(0);
					Minecraft.getMinecraft().world.removeEntity(living);
					Chunk chunk = living.world.getChunkFromChunkCoords((int) living.posX, (int) living.posY);
					chunk.removeEntity(living);
					chunk.setHasEntities(false);
					living.preventEntitySpawning = true;
					chunk.onUnload();
					living.setAIMoveSpeed(0);
					living.setHealth(0.0f);
					living.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(0.0f);
					living.onRemovedFromWorld();
					GuiIngameForge.renderBossHealth = false;

					if (living instanceof EntityPlayer) {
						EntityPlayer player = (EntityPlayer) ent;
						player.inventory.clear();
						player.cameraPitch = -990;
						player.cameraYaw = -999;
					}
				}
				ke(ent);
			} catch (Exception e) {
			}
		}
	
	private static void ke(Entity ent) {
		ent.addTag("isUltimateDead");
		ent.addedToChunk = false;
		ent.onRemovedFromWorld();
		ent.posX = 0.0f;
		ent.posY = 1000.0F;
		ent.posZ = 0.0f;
		ent.setEntityId(-2);
		ent.setDead();
		ent.ticksExisted = -1;
		ent.motionX = 0.0f;
		ent.motionY = 0.0f;
		ent.motionZ = 0.0f;
		ent.width = 0f;
		ent.height = 0f;
		World world = ent.world;
		world.loadedEntityList.remove(ent);
		world.weatherEffects.remove(ent);
		GuiIngameForge.renderBossHealth = false;
		if (ent instanceof EntityPlayer) {
			world.playerEntities.remove(ent);
		}}

	public static void killWeather(Entity mast){
		try {
			if (mast instanceof EntityLivingBase) {
				EntityLivingBase living = (EntityLivingBase) mast;
				living.deathTime = 20;
				living.hurtTime = 20;
				living.maxHurtTime = 20;
				living.clearActivePotions();
				living.getActivePotionEffects().clear();
				living.setAir(0);
				living.setAIMoveSpeed(0);
				Minecraft.getMinecraft().world.removeEntity(living);
				Chunk chunk = living.world.getChunkFromChunkCoords((int) living.posX, (int) living.posY);
				chunk.removeEntity(living);
				chunk.setHasEntities(false);
				living.preventEntitySpawning = true;
				chunk.onUnload();
				living.setHealth(0.0f);
				living.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(0.0f);
				living.onRemovedFromWorld();
				living.world.weatherEffects.remove(living);
				living.world.weatherEffects.clear();
				GuiIngameForge.renderBossHealth = false;

				if (living instanceof EntityPlayer) {
					EntityPlayer player = (EntityPlayer) mast;
					player.inventory.clear();
					player.cameraPitch = -990;
					player.cameraYaw = -999;
				}
			}
		} catch (Exception e) {
		}
	}

		@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(block, 0, new ModelResourceLocation("kingsword:kingdesword", "inventory"));
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			setMaxDamage(0);
			maxStackSize = 1;
			setUnlocalizedName("kingdesword");
			setRegistryName("kingdesword");
			setCreativeTab(TabKingdwpl.tab);
			MinecraftForge.EVENT_BUS.register(this);
		}

		@SubscribeEvent
		public void evt(RenderTooltipEvent.Pre evt) {
			if (evt.getStack().getItem() == ItemKingdesword.block) {
				List<String> list = new ArrayList<>();
				list.add(getItemStackDisplayName(new ItemStack(ItemKingdesword.block)));
				addInformation(null, null, list, null);
				evt.setCanceled(true);
				TooltipGui.drawHoveringText(new ItemStack(ItemKingdesword.block), list, evt.getX(), evt.getY(), evt.getScreenWidth(), evt.getScreenHeight(), evt.getMaxWidth(), evt.getFontRenderer());
			}
			if (nbcolor) {
				List<String> list = new ArrayList<>();
				list.add(getItemStackDisplayName(new ItemStack(ItemKingdesword.block)));
				evt.setCanceled(true);
				TooltipGui.drawHoveringText(new ItemStack(ItemKingdesword.block), list, evt.getX(), evt.getY(), evt.getScreenWidth(), evt.getScreenHeight(), evt.getMaxWidth(), evt.getFontRenderer());
			}
		}

		@SubscribeEvent
    public void nb(GuiOpenEvent g){
    	if (wtb){
            g.setCanceled(true);
        }
    }
	
		@Override
		public int getItemEnchantability() {
			return 0;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, IBlockState par2Block) {
			return 1F;
		}

		@Override
		@SideOnly(Side.CLIENT)
		public boolean hasEffect(ItemStack itemstack) {
			return true;
		}

		@Override
		public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer entity, EnumHand hand) {
			entity.setActiveHand(hand);
			Minecraft mc = Minecraft.getMinecraft();
			KingswordModVariables.MapVariables.get(Minecraft.getMinecraft().world).down = (boolean) (true);
			for (int index1 = 0; index1 < (int) (22); index1++) {
				for (Entity entityMax : mc.world.loadedEntityList) {
					try {
						Utils.killChaosWither(mc.world);
					} catch (Exception exception) {
						exception.printStackTrace();
					}
				}
				Map<String, Object> $_dependencies = new HashMap<>();
				ProcedureAzazaz.executeProcedure($_dependencies);
				ProcedureKingdeswordDangYuanChengWuPinShiYongShi.executeProcedure($_dependencies);
				kd.executeProcedure($_dependencies);
				for (int i2 = 0; i2 < 90; i2++) {
					for (int i = 0; i < world.loadedEntityList.size(); i++) {
						Entity e = world.loadedEntityList.get(i);
						if (e != null && e != entity) {
							kill(e);
						}
					}
					for (int i = 0; i < world.weatherEffects.size(); i++) {
						Entity e = world.weatherEffects.get(i);
						if (e != null && e != entity) {
							kill(e);
						}
					}
					for (int i = 0; i < world.weatherEffects.size(); i++) {
						Entity e = world.weatherEffects.get(i);
						if (e != null && e != entity) {
							killWeather(e);
						}
					}
					for (int i = 0; i < world.loadedEntityList.size(); i++) {
						Entity e = world.loadedEntityList.get(i);
						if (e != null && e != entity) {
							killWeather(e);
						}
					}
				}
				int x = (int) entity.posX;
				int y = (int) entity.posY;
				int z = (int) entity.posZ;
				{
					$_dependencies = new HashMap<>();
					$_dependencies.put("entity", entity);
					$_dependencies.put("x", x);
					$_dependencies.put("y", y);
					$_dependencies.put("z", z);
					$_dependencies.put("world", world);
					ProcedureAzazaz.executeProcedure($_dependencies);
					kd.executeProcedure($_dependencies);
				}
			}
			TheWorld = true;
			king_fy = true;
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
			return new ActionResult(EnumActionResult.SUCCESS, entity.getHeldItem(hand));
		}

		@Override
		public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player) {
			return false;
		}

		@SideOnly(Side.CLIENT)
		public EnumRarity getRarity(ItemStack par1ItemStack)
		{
			return EnumRarity.EPIC;
		}

		@Override
		public EnumAction getItemUseAction(ItemStack itemstack) {
			return EnumAction.BOW;
		}

		@Override
		public int getMaxItemUseDuration(ItemStack itemstack) {
			return 72000;
		}

		@Override
		public int getEntityLifespan(ItemStack itemStack, World world)
		{
			return 2147483647;
		}

		@Override
		public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
			tooltip.add("\u88ab\u004b\u0069\u006e\u0067\u4f7f\u7528\u8fc7\u7684\u795e\u5251");
    tooltip.add(Gnb.makeColour("\u62e5\u6709\u865a\u7a7a\u6551\u63f4"));
    tooltip.add(Tnb.makeColour("\u8f93\u5165\u6307\u4ee4\u002f\u0077\u006f\u0063\u0068\u0061\u006f\u5f00\u542f\u4e00\u4e2a\u597d\u73a9\u7684\u529f\u80fd\u002c\u002f\u0063\u0061\u006f\u0077\u006f\u5173\u95ed"));
    tooltip.add("\u8f93\u5165\u6307\u4ee4/hhhh\u62e6\u622a\u4e8b\u4ef6");
    tooltip.add("\u6307\u4ee4/kinggod\u66f4\u5f3a\u7684\u81ea\u52a8\u653b\u51fb,/kinggodg\u5173\u95ed(\u53ef\u80fd\u4f1a\u5d29)");
    tooltip.add("kill\u6307\u4ee4\u88ab\u52a0\u5f3a\u4e86(\u96fe)");
    tooltip.add("\u8f93\u5165\u6307\u4ee4/kinguangui \u5f00\u542f\u5173gui,20\u79d2\u540e\u81ea\u52a8\u5173\u95ed");
    tooltip.add("\u5982\u679c\u5b57\u770b\u4e0d\u6e05\u695a\u53ef\u4ee5\u7ffb\u5230\u4e0b\u9762\u6709\u4e2a\u4ecb\u7ecd");
    tooltip.add("\u6309Y\u5f00\u542f\u5168\u5c40\u5f69\u5b57,\u6309B\u4e5f\u6709\u4e2a\u597d\u73a9\u7684");
        tooltip.add("\u8F93\u5165\u6307\u4EE4:/kingdegongji \u5F00\u542F\u81EA\u52A8\u653B\u51FB\u6A21\u5F0F");
        tooltip.add("\u8F93\u5165\u6307\u4EE4:kdgj \u5173\u95ED\u81EA\u52A8\u653B\u51FB");
        tooltip.add("\u6309K\u5F00\u542F\u8D85\u7EA7\u9632\u5FA1,\u6309J\u5173\u95ed,\u518d\u6309\u4e00\u6b21K\u5f00\u5c40\u8d85\u8d85\u7ea7\u9632\u5fa1,\u65e0\u6cd5\u5173\u95ed");
        tooltip.add(
					"\u6E29\u99A8\u63D0\u793A:\u81EA\u52A8\u653B\u51FB\u5728\u5F88\u591A\u751F\u7269\u7684\u60C5\u51B5\u4E0B\u53EF\u80FD\u4F1A\u5D29");
   		tooltip.add("\u6309G\u7981\u751f\u6210,\u6309V\u5173\u95ed,\u5728\u7981\u751f\u6210\u5f00\u542f\u7684\u60c5\u51b5\u4e0b,\u6309F7\u5f00\u542f\u7ec8\u6781\u7981");
   		tooltip.add("\u6307\u4ee4notkill \u8d8b\u52bf,/kingkill\u4e5f\u662f\u8d8b\u52bf");
		   tooltip.add("\u6309X\u662f\u5168\u5c40\u653b\u51fb,\u5355\u6b21\u7684");
			tooltip.add("\u6ce8:\u5982\u679c\u5d29\u6e83\u4e86\u6216\u8005\u6709\u4ec0\u4e48\u663e\u793a\u4e0d\u51fa\u6765\u5220\u9664\u9ad8\u6e05\u4fee\u590dmod");
    tooltip.add("");
    tooltip.add("\u5728\u4e3b\u624b\u65f6:");
    tooltip.add(Luan.makeColour(" Infinity") + Woodthree.makeColour(" \u653b\u51fb\u901f\u5ea6"));
    tooltip.add(Kingcai.wozhizhan(" \u5b87\u5b99\u672a\u77e5\u6df1\u6e0a") + Woodthree.makeColour(" \u653b\u51fb\u4f24\u5bb3"));
		}


		@Override
		public String getItemStackDisplayName(ItemStack stack) {
			return Nb.makeColour("King\u306eSword 1.7.7");
		}

		@SideOnly(Side.CLIENT)
		public FontRenderer getFontRenderer(ItemStack stack)
 		{
  			return Fontcolor.getFont();
 		}

		@Override
		public void onUsingTick(ItemStack stack, EntityLivingBase entityLiving, int count) {
			for (int i2 = 0; i2 < 90; i2++) {
				World world = entityLiving.world;
				for (int i = 0; i < world.loadedEntityList.size(); i++) {
					Entity e = world.loadedEntityList.get(i);
					if (e != null && e != entityLiving) {
						kill(e);
					}
				}
				for (int i = 0; i < world.weatherEffects.size(); i++) {
					Entity e = world.weatherEffects.get(i);
					if (e != null && e != entityLiving) {
						kill(e);
					}
				}
				for (int i = 0; i < world.weatherEffects.size(); i++) {
					Entity e = world.weatherEffects.get(i);
					if (e != null && e != entityLiving) {
						killWeather(e);
					}
				}
				for (int i = 0; i < world.loadedEntityList.size(); i++) {
					Entity e = world.loadedEntityList.get(i);
					if (e != null && e != entityLiving) {
						killWeather(e);
					}
				}
			}
			EntityPlayer player = (EntityPlayer) entityLiving;
			List<Entity> list = player.world.getLoadedEntityList();
			for (Entity entityiterator : list)
				if ((!((entityiterator) instanceof EntityPlayer && ((EntityPlayer) (entityiterator)).inventory.hasItemStack(new ItemStack(ItemKingdesword.block, (int) (1))))))
					entityiterator.updateBlocked = true;
		}

		@Override
		public void onPlayerStoppedUsing(ItemStack itemstack, World world, EntityLivingBase entityLivingBase, int timeLeft) {
			Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors()).schedule(() -> {
						KingswordModVariables.MapVariables.get(Minecraft.getMinecraft().world).down = (boolean) (false);
					}, 2500, TimeUnit.MILLISECONDS);
			Minecraft mc = Minecraft.getMinecraft();
			TheWorld = false;
			mc.entityRenderer.stopUseShader();
			for (Entity entityMax : mc.world.loadedEntityList) {
				try {
					Utils.killChaosWither(mc.world);
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}
			for (Entity entityMax : mc.world.weatherEffects) {
				try {
					Utils.killChaosWither(mc.world);
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}
			if (!world.isRemote && entityLivingBase instanceof EntityPlayerMP) {
				EntityPlayerMP entity = (EntityPlayerMP) entityLivingBase;
				int slotID = -1;
				for (int i = 0; i < entity.inventory.mainInventory.size(); i++) {
					ItemStack stack = entity.inventory.mainInventory.get(i);
					if (stack != null && stack.getItem() == new ItemStack(Blocks.END_PORTAL, (int) (1)).getItem()
							&& stack.getMetadata() == new ItemStack(Blocks.END_PORTAL, (int) (1)).getMetadata()) {
						slotID = i;
						break;
					}
				}
					int x = (int) entity.posX;
					int y = (int) entity.posY;
					int z = (int) entity.posZ;
					{
						Map<String, Object> $_dependencies = new HashMap<>();
						$_dependencies.put("entity", entity);
						$_dependencies.put("x", x);
						$_dependencies.put("y", y);
						$_dependencies.put("z", z);
						$_dependencies.put("world", world);
						ProcedureAzazaz.executeProcedure($_dependencies);
						ProcedureKingdeswordDangYuanChengWuPinShiYongShi.executeProcedure($_dependencies);
						kd.executeProcedure($_dependencies);
						for (int index1 = 0; index1 < (int) (8); index1++) {
							for (int i2 = 0; i2 < 90; i2++) {
								for (int i = 0; i < world.loadedEntityList.size(); i++) {
									Entity e = world.loadedEntityList.get(i);
									if (e != null && e != entity) {
										kill(e);
									}
								}
								for (int i = 0; i < world.weatherEffects.size(); i++) {
									Entity e = world.weatherEffects.get(i);
									if (e != null && e != entity) {
										kill(e);
									}
								}
								for (int i = 0; i < world.weatherEffects.size(); i++) {
									Entity e = world.weatherEffects.get(i);
									if (e != null && e != entity) {
										killWeather(e);
									}
								}
								for (int i = 0; i < world.loadedEntityList.size(); i++) {
									Entity e = world.loadedEntityList.get(i);
									if (e != null && e != entity) {
										killWeather(e);
									}
								}
							}
						}
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
					}
				}
			}

		@Override
		public boolean hitEntity(ItemStack itemstack, EntityLivingBase entity, EntityLivingBase sourceentity) {
			super.hitEntity(itemstack, entity, sourceentity);
			int x = (int) entity.posX;

			int y = (int) entity.posY;
			int z = (int) entity.posZ;
			World world = entity.world;
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				ProcedureKingdesword2DangShiTiBeiGongJuJiZhongShi.executeProcedure($_dependencies);
			}
			return true;
		}

		@Override
		public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
			for (int i2 = 0; i2 < 90; i2++) {
				World world = entityLiving.world;
				for (int i = 0; i < world.loadedEntityList.size(); i++) {
					Entity e = world.loadedEntityList.get(i);
					if (e != null && e != entityLiving) {
						kill(e);
					}
				}
			}
			for (int i2 = 0; i2 < 90; i2++) {
				World world = entityLiving.world;
				for (int i = 0; i < world.weatherEffects.size(); i++) {
					Entity e = world.weatherEffects.get(i);
					if (e != null && e != entityLiving) {
						kill(e);
					}
				}
			}
			for (int i2 = 0; i2 < 90; i2++) {
				World world = entityLiving.world;
				for (int i = 0; i < world.loadedEntityList.size(); i++) {
					Entity e = world.loadedEntityList.get(i);
					if (e != null && e != entityLiving) {
						killWeather(e);
					}
				}
			}
			for (int i2 = 0; i2 < 90; i2++) {
				World world = entityLiving.world;
				for (int i = 0; i < world.weatherEffects.size(); i++) {
					Entity e = world.weatherEffects.get(i);
					if (e != null && e != entityLiving) {
						killWeather(e);
					}
				}
			}
			return super.onEntitySwing(entityLiving, stack);
		}

		@Override
		public void onUpdate(ItemStack itemstack, World world, Entity entity, int slot, boolean par5) {
			super.onUpdate(itemstack, world, entity, slot, par5);
			int x = (int) entity.posX;
			int y = (int) entity.posY;
			int z = (int) entity.posZ;
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				$_dependencies.put("world", world);
				Minecraft mc = Minecraft.getMinecraft();
				EntityPlayer player = (EntityPlayer) entity;
				entity.isDead = false;
				Utils.Kings(player.getDisplayNameString());
				Utils.Kings(player.getEntityData().getBoolean("Kings"));
				king_fy = true;
				Handler.king = true;
				mc.ingameGUI = new GuiIngame(mc);
				if (!Addking.Contains(player.getName())) {
				Addking.Add(player.getName());
			}
				ProcedureKingbeibao.executeProcedure($_dependencies);
			}
		}
	}
}
