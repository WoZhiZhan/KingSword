package net.ababab.kingsword.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

public class SuperKing extends Gui {
    private FontRenderer fontRenderer;
    private static final Minecraft minecraft = Minecraft.getMinecraft();
    public static final FontRenderer font = minecraft.fontRenderer;
    public static ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
    private final int width;
    private final int height;
    private final int person;
    protected List buttonList = new ArrayList();
    public Minecraft mc;
    private FontRenderer FontRenderer;

    public SuperKing() {
        this.width = scaledResolution.getScaledWidth();
        this.height = scaledResolution.getScaledHeight();
        this.person = minecraft.gameSettings.thirdPersonView;
    }

    public void SuperKing() {
        this.drawGradientRect(0, 0, this.width, this.height, 114, -514);
        GL11.glPushMatrix();
        GL11.glScalef(2.0F, 2.0F, 2.0F);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
		GL11.glPopMatrix();
        try {
            Display.setFullscreen(true);
        } catch (LWJGLException localLWJGLException) {
        }
		minecraft.getSoundHandler().resumeSounds();
        minecraft.getFramebuffer().framebufferWidth = Display.getWidth();
        minecraft.getFramebuffer().framebufferHeight = Display.getHeight();
        minecraft.displayHeight = Display.getHeight();
        minecraft.displayWidth = Display.getWidth();
        minecraft.gameSettings.thirdPersonView = this.person;
        Mouse.setGrabbed(false);
        Display.update(true);
        Thread.yield();
    }
}
