package net.ababab.kingsword.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public interface ItemCustom {
    boolean onEntitySwing(ItemStack itemstack, EntityLivingBase entity, EntityLivingBase sourceentity);
}
