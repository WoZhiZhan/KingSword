package net.ababab.kingsword.procedure;

import net.ababab.kingsword.ElementsKingswordMod;
import net.ababab.kingsword.KingswordModVariables;
import net.ababab.kingsword.Shaped;
import net.ababab.kingsword.item.ItemDead;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GameOver;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureDead3 extends ElementsKingswordMod.ModElement {
	public ProcedureDead3(ElementsKingswordMod instance) {
		super(instance, 376);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure Dead3!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure Dead3!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		World world = (World) dependencies.get("world");
		if (((KingswordModVariables.MapVariables.get(world).swhh) == (true))) {
			if (entity instanceof EntityPlayer) {
				ItemStack _setstack = new ItemStack(ItemDead.block, (int) (1));
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
			}
			((EntityLivingBase) entity).getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(0);
		Minecraft mc = Minecraft.getMinecraft();
if (!(mc.currentScreen instanceof GameOver))
    mc.addScheduledTask(() -> mc.displayGuiScreen(new GameOver(new TextComponentString(Shaped.makeColour("You are died")))));
		}
	}

	@SubscribeEvent
	public void onPlayerRespawned(net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent event) {
		Entity entity = event.player;
		java.util.HashMap<String, Object> dependencies = new java.util.HashMap<>();
		dependencies.put("x", (int) entity.posX);
		dependencies.put("y", (int) entity.posY);
		dependencies.put("z", (int) entity.posZ);
		dependencies.put("world", entity.world);
		dependencies.put("entity", entity);
		dependencies.put("endconquered", event.isEndConquered());
		dependencies.put("event", event);
		this.executeProcedure(dependencies);
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(this);
	}
}
