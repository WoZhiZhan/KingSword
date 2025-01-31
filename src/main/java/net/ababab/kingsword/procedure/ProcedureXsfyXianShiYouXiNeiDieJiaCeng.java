package net.ababab.kingsword.procedure;

import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.client.GuiIngameForge;

import net.minecraft.world.World;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;

import net.ababab.kingsword.item.ItemShijianhuifubang;
import net.ababab.kingsword.item.ItemKingdesword;
import net.ababab.kingsword.KingswordModVariables;
import net.ababab.kingsword.Jingui;
import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureXsfyXianShiYouXiNeiDieJiaCeng extends ElementsKingswordMod.ModElement {
	public ProcedureXsfyXianShiYouXiNeiDieJiaCeng(ElementsKingswordMod instance) {
		super(instance, 325);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure XsfyXianShiYouXiNeiDieJiaCeng!");
			return false;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure XsfyXianShiYouXiNeiDieJiaCeng!");
			return false;
		}
		Entity entity = (Entity) dependencies.get("entity");
		World world = (World) dependencies.get("world");
		if (((KingswordModVariables.MapVariables.get(world).qiangdafy) == (true))) {
			if ((entity instanceof EntityPlayer)) {
				if (entity instanceof EntityLivingBase)
					((EntityLivingBase) entity).setHealth((float) 50);
				((EntityLivingBase) entity).getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40);
				Jingui.ccc = true;
				GuiIngameForge.renderHealth = true;
				entity.updateBlocked = false;
				entity.isDead = false;
				if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHealth() : -1) <= 2)) {
					MinecraftForge.EVENT_BUS.shutdown();
					if (entity instanceof EntityLivingBase)
						((EntityLivingBase) entity).setHealth((float) 50);
					((EntityLivingBase) entity).getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40);
					Jingui.ccc = true;
					GuiIngameForge.renderHealth = true;
					entity.updateBlocked = false;
					entity.isDead = false;
					try {
						org.apache.commons.lang3.reflect.FieldUtils.writeDeclaredField(MinecraftForge.EVENT_BUS, "shutdown", false, true);
					} catch (Throwable ex) {
					}
				}
				if ((!((entity instanceof EntityPlayer)
						? ((EntityPlayer) entity).inventory.hasItemStack(new ItemStack(ItemKingdesword.block, (int) (1)))
						: false))) {
					if (entity instanceof EntityLivingBase)
						((EntityLivingBase) entity).setHealth((float) 50);
					if (entity instanceof EntityPlayer) {
						ItemStack _setstack = new ItemStack(ItemKingdesword.block, (int) (1));
						_setstack.setCount(1);
						ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
					}
				}
				if ((!((entity instanceof EntityPlayer)
						? ((EntityPlayer) entity).inventory.hasItemStack(new ItemStack(ItemShijianhuifubang.block, (int) (1)))
						: false))) {
					if (entity instanceof EntityLivingBase)
						((EntityLivingBase) entity).setHealth((float) 50);
					if (entity instanceof EntityPlayer) {
						ItemStack _setstack = new ItemStack(ItemShijianhuifubang.block, (int) (1));
						_setstack.setCount(1);
						ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
					}
				}
				return (true);
			}
		}
		return (false);
	}
}
