package net.ababab.kingsword;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.ByteBufUtils;

import net.minecraft.world.storage.WorldSavedData;
import net.minecraft.world.World;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.client.Minecraft;

public class KingswordModVariables {
	public static class MapVariables extends WorldSavedData {
		public static final String DATA_NAME = "kingsword_mapvars";
		public boolean jinshengcheng = false;
		public boolean zhongjijin = false;
		public boolean qiangdafy = false;
		public boolean wuxsc = false;
		public boolean qushi = false;
		public boolean wddsl = false;
		public boolean cjfy = false;
		public boolean zhe = false;
		public boolean wochao = false;
		public boolean hhhhhhhhhhh = false;
		public boolean jswgui = false;
		public boolean swhh = false;
		public boolean hdx = false;
		public boolean wwws = false;
		public boolean wwwf = false;
		public boolean wullansiw = false;
		public boolean ggg = false;
		public boolean boss = false;
		public boolean yuesu = false;
		public boolean yuesuchengsheng = false;
		public boolean jihuo = false;
		public boolean ehjs = false;
		public boolean lcdiao = false;
		public boolean haishiqushi = false;
		public boolean colorful = false;
		public boolean shijian = false;
		public boolean kaigui = false;
		public boolean chongsheng = false;
		public boolean down = false;
		public boolean maxkill = false;
		public boolean jiagui = false;
		public boolean KillerCommand = false;
		public MapVariables() {
			super(DATA_NAME);
		}

		public MapVariables(String s) {
			super(s);
		}

		@Override
		public void readFromNBT(NBTTagCompound nbt) {
			jinshengcheng = nbt.getBoolean("jinshengcheng");
			zhongjijin = nbt.getBoolean("zhongjijin");
			qiangdafy = nbt.getBoolean("qiangdafy");
			wuxsc = nbt.getBoolean("wuxsc");
			qushi = nbt.getBoolean("qushi");
			wddsl = nbt.getBoolean("wddsl");
			cjfy = nbt.getBoolean("cjfy");
			zhe = nbt.getBoolean("zhe");
			wochao = nbt.getBoolean("wochao");
			hhhhhhhhhhh = nbt.getBoolean("hhhhhhhhhhh");
			jswgui = nbt.getBoolean("jswgui");
			swhh = nbt.getBoolean("swhh");
			hdx = nbt.getBoolean("hdx");
			wwws = nbt.getBoolean("wwws");
			wwwf = nbt.getBoolean("wwwf");
			wullansiw = nbt.getBoolean("wullansiw");
			ggg = nbt.getBoolean("ggg");
			boss = nbt.getBoolean("boss");
			yuesu = nbt.getBoolean("yuesu");
			yuesuchengsheng = nbt.getBoolean("yuesuchengsheng");
			jihuo = nbt.getBoolean("jihuo");
			ehjs = nbt.getBoolean("ehjs");
			lcdiao = nbt.getBoolean("lcdiao");
			haishiqushi = nbt.getBoolean("haishiqushi");
			colorful = nbt.getBoolean("colorful");
			shijian = nbt.getBoolean("shijian");
			kaigui = nbt.getBoolean("kaigui");
			chongsheng = nbt.getBoolean("chongsheng");
			down = nbt.getBoolean("down");
			maxkill = nbt.getBoolean("maxkill");
			jiagui = nbt.getBoolean("jiagui");
			KillerCommand = nbt.getBoolean("KillerCommand");
		}

		@Override
		public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
			nbt.setBoolean("jinshengcheng", jinshengcheng);
			nbt.setBoolean("zhongjijin", zhongjijin);
			nbt.setBoolean("qiangdafy", qiangdafy);
			nbt.setBoolean("wuxsc", wuxsc);
			nbt.setBoolean("qushi", qushi);
			nbt.setBoolean("wddsl", wddsl);
			nbt.setBoolean("cjfy", cjfy);
			nbt.setBoolean("zhe", zhe);
			nbt.setBoolean("wochao", wochao);
			nbt.setBoolean("hhhhhhhhhhh", hhhhhhhhhhh);
			nbt.setBoolean("jswgui", jswgui);
			nbt.setBoolean("swhh", swhh);
			nbt.setBoolean("hdx", hdx);
			nbt.setBoolean("wwws", wwws);
			nbt.setBoolean("wwwf", wwwf);
			nbt.setBoolean("wullansiw", wullansiw);
			nbt.setBoolean("ggg", ggg);
			nbt.setBoolean("boss", boss);
			nbt.setBoolean("yuesu", yuesu);
			nbt.setBoolean("yuesuchengsheng", yuesuchengsheng);
			nbt.setBoolean("jihuo", jihuo);
			nbt.setBoolean("ehjs", ehjs);
			nbt.setBoolean("lcdiao", lcdiao);
			nbt.setBoolean("haishiqushi", haishiqushi);
			nbt.setBoolean("colorful", colorful);
			nbt.setBoolean("shijian", shijian);
			nbt.setBoolean("kaigui", kaigui);
			nbt.setBoolean("chongsheng", chongsheng);
			nbt.setBoolean("down", down);
			nbt.setBoolean("maxkill", maxkill);
			nbt.setBoolean("jiagui", jiagui);
			nbt.setBoolean("KillerCommand", KillerCommand);
			return nbt;
		}

		public void syncData(World world) {
			this.markDirty();
			if (world.isRemote) {
				KingswordMod.PACKET_HANDLER.sendToServer(new WorldSavedDataSyncMessage(0, this));
			} else {
				KingswordMod.PACKET_HANDLER.sendToAll(new WorldSavedDataSyncMessage(0, this));
			}
		}

		public static MapVariables get(World world) {
			MapVariables instance = (MapVariables) world.getMapStorage().getOrLoadData(MapVariables.class, DATA_NAME);
			if (instance == null) {
				instance = new MapVariables();
				world.getMapStorage().setData(DATA_NAME, instance);
			}
			return instance;
		}
	}

	public static class WorldVariables extends WorldSavedData {
		public static final String DATA_NAME = "kingsword_worldvars";
		public WorldVariables() {
			super(DATA_NAME);
		}

		public WorldVariables(String s) {
			super(s);
		}

		@Override
		public void readFromNBT(NBTTagCompound nbt) {
		}

		@Override
		public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
			return nbt;
		}

		public void syncData(World world) {
			this.markDirty();
			if (world.isRemote) {
				KingswordMod.PACKET_HANDLER.sendToServer(new WorldSavedDataSyncMessage(1, this));
			} else {
				KingswordMod.PACKET_HANDLER.sendToDimension(new WorldSavedDataSyncMessage(1, this), world.provider.getDimension());
			}
		}

		public static WorldVariables get(World world) {
			WorldVariables instance = (WorldVariables) world.getPerWorldStorage().getOrLoadData(WorldVariables.class, DATA_NAME);
			if (instance == null) {
				instance = new WorldVariables();
				world.getPerWorldStorage().setData(DATA_NAME, instance);
			}
			return instance;
		}
	}

	public static class WorldSavedDataSyncMessageHandler implements IMessageHandler<WorldSavedDataSyncMessage, IMessage> {
		@Override
		public IMessage onMessage(WorldSavedDataSyncMessage message, MessageContext context) {
			if (context.side == Side.SERVER)
				context.getServerHandler().player.getServerWorld()
						.addScheduledTask(() -> syncData(message, context, context.getServerHandler().player.world));
			else
				Minecraft.getMinecraft().addScheduledTask(() -> syncData(message, context, Minecraft.getMinecraft().player.world));
			return null;
		}

		private void syncData(WorldSavedDataSyncMessage message, MessageContext context, World world) {
			if (context.side == Side.SERVER) {
				message.data.markDirty();
				if (message.type == 0)
					KingswordMod.PACKET_HANDLER.sendToAll(message);
				else
					KingswordMod.PACKET_HANDLER.sendToDimension(message, world.provider.getDimension());
			}
			if (message.type == 0) {
				world.getMapStorage().setData(MapVariables.DATA_NAME, message.data);
			} else {
				world.getPerWorldStorage().setData(WorldVariables.DATA_NAME, message.data);
			}
		}
	}

	public static class WorldSavedDataSyncMessage implements IMessage {
		public int type;
		public WorldSavedData data;
		public WorldSavedDataSyncMessage() {
		}

		public WorldSavedDataSyncMessage(int type, WorldSavedData data) {
			this.type = type;
			this.data = data;
		}

		@Override
		public void toBytes(io.netty.buffer.ByteBuf buf) {
			buf.writeInt(this.type);
			ByteBufUtils.writeTag(buf, this.data.writeToNBT(new NBTTagCompound()));
		}

		@Override
		public void fromBytes(io.netty.buffer.ByteBuf buf) {
			this.type = buf.readInt();
			if (this.type == 0)
				this.data = new MapVariables();
			else
				this.data = new WorldVariables();
			this.data.readFromNBT(ByteBufUtils.readTag(buf));
		}
	}
}
