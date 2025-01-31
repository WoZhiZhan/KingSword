package net.ababab.kingsword.procedure;

import net.minecraft.world.World;
import net.minecraft.util.EnumHand;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;

import net.ababab.kingsword.item.ItemKingdesword;
import net.ababab.kingsword.KingswordModVariables;
import net.ababab.kingsword.ElementsKingswordMod;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureWwwfXianShiYouXiNeiDieJiaCeng extends ElementsKingswordMod.ModElement {
	public ProcedureWwwfXianShiYouXiNeiDieJiaCeng(ElementsKingswordMod instance) {
		super(instance, 392);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure WwwfXianShiYouXiNeiDieJiaCeng!");
			return false;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure WwwfXianShiYouXiNeiDieJiaCeng!");
			return false;
		}
		Entity entity = (Entity) dependencies.get("entity");
		World world = (World) dependencies.get("world");
		if (((KingswordModVariables.MapVariables.get(world).wwwf) == (true))) {
			for (int index0 = 0; index0 < (int) (10); index0++) {entity.isDead = false;
			((EntityLivingBase) entity).setHealth(40);
			((EntityPlayer) entity).deathTime = 0;
			((EntityPlayer) entity).ticksExisted = 0;
			entity.updateBlocked = false;
				if (entity instanceof EntityLivingBase)
					((EntityLivingBase) entity).setHealth((float) 40);
				((EntityLivingBase) entity).getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40);
			}
			if ((!((entity instanceof EntityPlayer)
					? ((EntityPlayer) entity).inventory.hasItemStack(new ItemStack(ItemKingdesword.block, (int) (1)))
					: false))) {
				if (entity instanceof EntityLivingBase) {
					ItemStack _setstack = new ItemStack(ItemKingdesword.block, (int) (1));
					_setstack.setCount(1);
					((EntityLivingBase) entity).setHeldItem(EnumHand.MAIN_HAND, _setstack);
					if (entity instanceof EntityPlayerMP)
						((EntityPlayerMP) entity).inventory.markDirty();
				}
			}
		}
		if (((KingswordModVariables.MapVariables.get(world).qiangdafy) == (true))) {
			for (int index1 = 0; index1 < (int) (10); index1++) {/* code */
				if (entity instanceof EntityLivingBase)
					((EntityLivingBase) entity).setHealth((float) 40);
					entity.updateBlocked = false;
				((EntityLivingBase) entity).getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40);
			}
			if ((!((entity instanceof EntityPlayer)
					? ((EntityPlayer) entity).inventory.hasItemStack(new ItemStack(ItemKingdesword.block, (int) (1)))
					: false))) {
				if (entity instanceof EntityLivingBase) {
					ItemStack _setstack = new ItemStack(ItemKingdesword.block, (int) (1));
					_setstack.setCount(1);
					((EntityLivingBase) entity).setHeldItem(EnumHand.MAIN_HAND, _setstack);
					if (entity instanceof EntityPlayerMP)
						((EntityPlayerMP) entity).inventory.markDirty();
				}
			}
		}
		return (true);
	}
}
