
package net.ababab.kingsword.item;

import net.minecraftforge.fml.common.registry.GameRegistry;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumActionResult;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.entity.player.EntityPlayer;

import net.ababab.kingsword.world.WorldXwddf;
import net.ababab.kingsword.procedure.ProcedureXwddfDangChuanSongMenHongFaQiBeiHongFa;
import net.ababab.kingsword.creativetab.TabKingdwpl;

import java.util.Map;
import java.util.HashMap;

public class ItemXwddf extends Item {
	@GameRegistry.ObjectHolder("kingsword:xwddf")
	public static final Item block = null;
	public ItemXwddf() {
		super();
		this.maxStackSize = 1;
		setMaxDamage(64);
		setCreativeTab(TabKingdwpl.tab);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer entity, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY,
			float hitZ) {
		pos = pos.offset(facing);
		ItemStack itemstack = entity.getHeldItem(hand);
		if (!entity.canPlayerEdit(pos, facing, itemstack)) {
			return EnumActionResult.FAIL;
		} else {
			if (world.isAirBlock(pos))
				WorldXwddf.portal.portalSpawn(world, pos);
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				ProcedureXwddfDangChuanSongMenHongFaQiBeiHongFa.executeProcedure($_dependencies);
			}
			itemstack.damageItem(1, entity);
			return EnumActionResult.SUCCESS;
		}
	}
}
