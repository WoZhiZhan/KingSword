package net.ababab.kingsword.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;

import java.lang.reflect.Field;

import static net.ababab.kingsword.item.ItemKingdesword.king_fy;

public class Utils {

    public static void killChaosWither(World world) {
        if (king_fy) {
            if (Loader.isModLoaded("chaoswither")) {
                try {
                    Class<?> type = Class.forName("com.chaoswither.chaoswither");
                    Field field = type.getDeclaredField("happymode");
                    field.setAccessible(true);
                    field.set(null, Boolean.valueOf(false));
                    Class<?> type2 = Class.forName("com.chaoswither.event.DetermineEvent");
                    Field field2 = type2.getDeclaredField("WITHERLIVE");
                    field2.setAccessible(true);
                    field2.set(null, Boolean.valueOf(false));
                    Class<?> type3 = Class.forName("net.minecraft.item.ItemStackLoader");
                    Field field3 = type3.getDeclaredField("NOITEM");
                    field3.setAccessible(true);
                    field3.set(null, Boolean.valueOf(false));
                    if (world.getWorldInfo().getPlayerNBTTagCompound().hasKey("hasChaosWither")) {
                        world.getWorldInfo().getPlayerNBTTagCompound().removeTag("hasChaosWither");
                    }
                } catch (ClassNotFoundException localClassNotFoundException) {
                } catch (IllegalArgumentException localIllegalArgumentException) {
                } catch (IllegalAccessException localIllegalAccessException) {
                } catch (NoSuchFieldException localNoSuchFieldException) {
                } catch (SecurityException localSecurityException) {
                }
            }
        }
    }

    public static boolean Kings(Object o)
    {
        if ((o instanceof EntityPlayer))
        {
            EntityPlayer player = (EntityPlayer)o;
            if (player.getEntityData().getBoolean("Kings")) {
                return true;
            }
            if (Addt.Players.contains(player.getDisplayNameString())) {
                return true;
            }
        }
        return false;
    }
}
