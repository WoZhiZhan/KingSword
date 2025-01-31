
package net.ababab.kingsword.gui;

import net.ababab.kingsword.ElementsKingswordMod;
import net.ababab.kingsword.util.Font;
import net.minecraft.client.gui.*;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.Iterator;

@ElementsKingswordMod.ModElement.Tag
public class GuiOver extends GuiScreen {
	private int enableButtonsTimer;
	private final ITextComponent causeOfDeath;
	private FontRenderer FontRenderer;

	public GuiOver(@Nullable ITextComponent p_i46598_1_) {
		this.causeOfDeath = p_i46598_1_;
	}

	public void initGui() {
		this.buttonList.clear();
		this.enableButtonsTimer = 0;
		this.FontRenderer = Font.getFont();
		if (this.mc.world.getWorldInfo().isHardcoreModeEnabled()) {
			this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 72, I18n.format("deathScreen.spectate")));
			this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 96, I18n.format("deathScreen." + (this.mc.isIntegratedServerRunning() ? "deleteWorld" : "leaveServer"), new Object[0])));
		} else {
			this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 72, I18n.format("deathScreen.respawn")));
			this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 96, I18n.format("deathScreen.titleScreen")));
			if (this.mc.getSession() == null) {
				((GuiButton)this.buttonList.get(1)).enabled = false;
			}
		}

		GuiButton lvt_2_1_;
		for(Iterator var1 = this.buttonList.iterator(); var1.hasNext(); lvt_2_1_.enabled = false) {
			lvt_2_1_ = (GuiButton)var1.next();
		}

	}

	protected void keyTyped(char p_73869_1_, int p_73869_2_) throws IOException {
	}

	protected void actionPerformed(GuiButton p_146284_1_) throws IOException {
		switch(p_146284_1_.id) {
			case 0:
				this.mc.player.respawnPlayer();
				this.mc.displayGuiScreen((GuiScreen)null);
				break;
			case 1:
				if (this.mc.world.getWorldInfo().isHardcoreModeEnabled()) {
					this.mc.displayGuiScreen(new GuiMainMenu());
				} else {
					GuiYesNo lvt_2_1_ = new GuiYesNo(this, I18n.format("deathScreen.quit.confirm"), "", I18n.format("deathScreen.titleScreen"), I18n.format("deathScreen.respawn"), 0);
					this.mc.displayGuiScreen(lvt_2_1_);
					lvt_2_1_.setButtonDelay(20);
				}
		}

	}

	public void confirmClicked(boolean p_73878_1_, int p_73878_2_) {
		if (p_73878_1_) {
			if (this.mc.world != null) {
				this.mc.world.sendQuittingDisconnectingPacket();
			}

			this.mc.loadWorld((WorldClient)null);
			this.mc.displayGuiScreen(new GuiMainMenu());
		} else {
			this.mc.player.respawnPlayer();
			this.mc.displayGuiScreen((GuiScreen)null);
		}

	}

	public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
		boolean lvt_4_1_ = this.mc.world.getWorldInfo().isHardcoreModeEnabled();
		this.FontRenderer = Font.getFont();
		this.drawGradientRect(0, 0, this.width, this.height, 500, -2345);
		GlStateManager.pushMatrix();
		GlStateManager.scale(2.0F, 2.0F, 2.0F);
		this.drawCenteredString(this.fontRenderer, I18n.format(lvt_4_1_ ? "deathScreen.title.hardcore" : "deathScreen.title", new Object[0]), this.width / 2 / 2, 30, 16777215);
		GlStateManager.popMatrix();
		if (this.causeOfDeath != null) {
			this.drawCenteredString(this.fontRenderer, this.causeOfDeath.getFormattedText(), this.width / 2, 85, 16777215);
		}

		this.drawCenteredString(this.fontRenderer, I18n.format("deathScreen.score") + ": " + TextFormatting.YELLOW + this.mc.player.getScore(), this.width / 2, 100, 16777215);
		if (this.causeOfDeath != null && p_73863_2_ > 85 && p_73863_2_ < 85 + this.fontRenderer.FONT_HEIGHT) {
			ITextComponent lvt_5_1_ = this.getClickedComponentAt(p_73863_1_);
			if (lvt_5_1_ != null && lvt_5_1_.getStyle().getHoverEvent() != null) {
				this.handleComponentHover(lvt_5_1_, p_73863_1_, p_73863_2_);
			}
		}

		super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
	}

	@Nullable
	public ITextComponent getClickedComponentAt(int p_184870_1_) {
		if (this.causeOfDeath == null) {
			return null;
		} else {
			int lvt_2_1_ = this.mc.fontRenderer.getStringWidth(this.causeOfDeath.getFormattedText());
			int lvt_3_1_ = this.width / 2 - lvt_2_1_ / 2;
			int lvt_4_1_ = this.width / 2 + lvt_2_1_ / 2;
			int lvt_5_1_ = lvt_3_1_;
			if (p_184870_1_ >= lvt_3_1_ && p_184870_1_ <= lvt_4_1_) {
				Iterator var6 = this.causeOfDeath.iterator();

				ITextComponent lvt_7_1_;
				do {
					if (!var6.hasNext()) {
						return null;
					}

					lvt_7_1_ = (ITextComponent)var6.next();
					lvt_5_1_ += this.mc.fontRenderer.getStringWidth(GuiUtilRenderComponents.removeTextColorsIfConfigured(lvt_7_1_.getUnformattedComponentText(), false));
				} while(lvt_5_1_ <= p_184870_1_);

				return lvt_7_1_;
			} else {
				return null;
			}
		}
	}

	public boolean doesGuiPauseGame() {
		return false;
	}

	public void updateScreen() {
		super.updateScreen();
		++this.enableButtonsTimer;
		GuiButton lvt_2_1_;
		if (this.enableButtonsTimer == 20) {
			for(Iterator var1 = this.buttonList.iterator(); var1.hasNext(); lvt_2_1_.enabled = true) {
				lvt_2_1_ = (GuiButton)var1.next();
			}
		}

	}
}
