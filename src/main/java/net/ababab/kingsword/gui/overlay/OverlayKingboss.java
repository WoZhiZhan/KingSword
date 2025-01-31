
package net.ababab.kingsword.gui.overlay;

import com.google.common.collect.ImmutableMap;
import net.ababab.kingsword.Cai;
import net.ababab.kingsword.ElementsKingswordMod;
import net.ababab.kingsword.Nb;
import net.ababab.kingsword.procedure.ProcedureKingbossXianShiYouXiNeiDieJiaCeng;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@ElementsKingswordMod.ModElement.Tag
public class OverlayKingboss extends ElementsKingswordMod.ModElement {
	public OverlayKingboss(ElementsKingswordMod instance) {
		super(instance, 459);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void init(FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new GUIRenderEventClass());
	}
	public static class GUIRenderEventClass {
		@SubscribeEvent(priority = EventPriority.NORMAL)
		@SideOnly(Side.CLIENT)
		public void eventHandler(RenderGameOverlayEvent event) {
			if (!event.isCancelable() && event.getType() == RenderGameOverlayEvent.ElementType.HELMET) {
				int posX = (event.getResolution().getScaledWidth()) / 2;
				int posY = (event.getResolution().getScaledHeight()) / 2;
				EntityPlayer entity = Minecraft.getMinecraft().player;
				World world = entity.world;
				int x = (int) entity.posX;
				int y = (int) entity.posY;
				int z = (int) entity.posZ;
				if (ProcedureKingbossXianShiYouXiNeiDieJiaCeng.executeProcedure(ImmutableMap.of("world", world))) {
					GlStateManager.disableDepth();
					GlStateManager.depthMask(false);
					GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA,
							GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
					GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
					GlStateManager.disableAlpha();
					Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("kingsword:textures/azz.png"));
					Minecraft.getMinecraft().ingameGUI.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, event.getResolution().getScaledWidth(),
							event.getResolution().getScaledHeight(), event.getResolution().getScaledWidth(), event.getResolution().getScaledHeight());
					GlStateManager.depthMask(true);
					GlStateManager.enableDepth();
					GlStateManager.enableAlpha();
					GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
					GlStateManager.disableDepth();
					GlStateManager.depthMask(false);
					GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA,
							GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
					GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
					GlStateManager.disableAlpha();
					Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("kingsword:textures/boss.png"));
					Minecraft.getMinecraft().ingameGUI.drawModalRectWithCustomSizedTexture(posX + -88, posY + -141, 0, 0, 256, 256, 256, 256);
					GlStateManager.depthMask(true);
					GlStateManager.enableDepth();
					GlStateManager.enableAlpha();
					GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
					Minecraft.getMinecraft().fontRenderer.drawString(Nb.makeColour("King"), posX + -6, posY + -109, -12829636);
					Minecraft.getMinecraft().fontRenderer.drawString(
Cai.wozhizhan(
							"\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764"),
							posX + -688, posY + 78, -12829636);
					Minecraft.getMinecraft().fontRenderer.drawString(
Nb.makeColour(
							"\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764"),
							posX + -89, posY + -93, -12829636);
				}
			}
		}
	}
}
