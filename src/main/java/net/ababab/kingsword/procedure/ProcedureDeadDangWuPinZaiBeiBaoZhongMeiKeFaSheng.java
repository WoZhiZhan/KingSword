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
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureDeadDangWuPinZaiBeiBaoZhongMeiKeFaSheng extends ElementsKingswordMod.ModElement {
	public ProcedureDeadDangWuPinZaiBeiBaoZhongMeiKeFaSheng(ElementsKingswordMod instance) {
		super(instance, 373);
	}

	public static void executeProcedure(Map<String, Object> dependencies){
		if(dependencies.get("entity")==null){
			System.err.println("Failed to load dependency entity for procedure DeadDangWuPinZaiBeiBaoZhongMeiKeFaSheng!");
			return;
		}
		if(dependencies.get("world")==null){
			System.err.println("Failed to load dependency world for procedure DeadDangWuPinZaiBeiBaoZhongMeiKeFaSheng!");
			return;
		}
            Entity entity =(Entity)dependencies.get("entity" );
            World world =(World)dependencies.get("world" );
		((EntityLivingBase) entity).getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(0);

		KingswordModVariables.MapVariables.get(world).swhh =(boolean)(true);
        KingswordModVariables.MapVariables.get(world).syncData(world);
Minecraft mc = Minecraft.getMinecraft();
if (!(mc.currentScreen instanceof GameOver))
    mc.addScheduledTask(() -> mc.displayGuiScreen(new GameOver(new TextComponentString(Shaped.makeColour("You are died")))));
if(entity instanceof EntityPlayer) {
	ItemStack _setstack = new ItemStack(ItemDead.block, (int)(1));
	_setstack.setCount(1);
	ItemHandlerHelper.giveItemToPlayer(((EntityPlayer)entity), _setstack);
}
	}
}
