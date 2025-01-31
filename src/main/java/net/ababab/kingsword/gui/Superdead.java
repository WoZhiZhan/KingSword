package net.ababab.kingsword.gui;

import net.ababab.kingsword.util.Font;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.resources.I18n;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

public class Superdead extends Gui {
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

    public Superdead() {
        this.width = scaledResolution.getScaledWidth();
        this.height = scaledResolution.getScaledHeight();
        this.person = minecraft.gameSettings.thirdPersonView;
    }

    public void kill() {
        this.drawGradientRect(0, 0, this.width, this.height, 500, -2345);
        GL11.glPushMatrix();
        GL11.glScalef(2.0F, 2.0F, 2.0F);
        drawCenteredString(font, I18n.format("deathScreen.title"), this.width / 2 / 2, 30, 16777215);
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
