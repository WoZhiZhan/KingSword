package net.ababab.kingsword;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiOverlayDebug;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.FoodStats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class KingGuiIngameForge
		extends GuiIngameForge
{
	private static final int WHITE = 16777215;
	public static boolean renderVignette = true;
	public static boolean renderHelmet = true;
	public static boolean renderPortal = true;
	public static boolean renderHotbar = true;
	public static boolean renderCrosshairs = true;
	public static boolean renderBossHealth = true;
	public static boolean renderHealth = true;
	public static boolean renderArmor = true;
	public static boolean renderFood = true;
	public static boolean renderHealthMount = true;
	public static boolean renderAir = true;
	public static boolean renderExperiance = true;
	public static boolean renderJumpBar = true;
	public static boolean renderObjective = true;
	public static int left_height = 39;
	public static int right_height = 39;
	private ScaledResolution res = null;
	private FontRenderer fontrenderer = null;
	private RenderGameOverlayEvent eventParent;
	private GuiOverlayDebugForge debugOverlay;

	public KingGuiIngameForge(Minecraft p_i46325_1_) {
		super(p_i46325_1_);
	}

	public void renderGameOverlay(float partialTicks)
	{
		this.res = new ScaledResolution(this.mc);
		this.eventParent = new RenderGameOverlayEvent(partialTicks, this.res);
		int width = this.res.getScaledWidth();
		int height = this.res.getScaledHeight();
		renderHealthMount = this.mc.player.getRidingEntity() instanceof EntityLivingBase;
		renderFood = this.mc.player.getRidingEntity() == null;
		renderJumpBar = this.mc.player.isRidingHorse();

		right_height = 39;
		left_height = 39;
		if (pre(RenderGameOverlayEvent.ElementType.ALL)) {
			return;
		}
		this.fontrenderer = this.mc.fontRenderer;
		this.mc.entityRenderer.setupOverlayRendering();
		GlStateManager.enableBlend();
		if ((renderVignette) && (Minecraft.isFancyGraphicsEnabled()))
		{
			renderVignette(this.mc.player.getBrightness(), this.res);
		}
		else
		{
			GlStateManager.enableDepth();
			GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
		}
		if (renderHelmet) {
			renderHelmet(this.res, partialTicks);
		}
		if ((renderPortal) && (!this.mc.player.isPotionActive(MobEffects.NAUSEA))) {
			renderPortal(this.res, partialTicks);
		}
		if (renderHotbar) {
			renderHotbar(this.res, partialTicks);
		}
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.zLevel = -90.0F;
		this.rand.setSeed(this.updateCounter * 312871);
		if (renderCrosshairs) {
			renderCrosshairs(partialTicks);
		}
		if (renderBossHealth) {
			renderBossHealth();
		}
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		if ((this.mc.playerController.shouldDrawHUD()) && ((this.mc.getRenderViewEntity() instanceof EntityPlayer)))
		{
			if (renderHealth) {
				renderHealth(width, height);
			}
			if (renderArmor) {
				renderArmor(width, height);
			}
			if (renderFood) {
				renderHealth(width, height);
			}
			if (renderHealthMount) {
				renderHealthMount(width, height);
			}
			if (renderAir) {
				renderAir(width, height);
			}
		}
		renderSleepFade(width, height);
		if (renderJumpBar) {
			renderJumpBar(width, height);
		} else if (renderExperiance) {
			renderExperience(width, height);
		}
		renderToolHighlight(this.res);
		renderHUDText(width, height);
		renderFPSGraph();
		renderPotionIcons(this.res);
		renderRecordOverlay(width, height, partialTicks);
		renderSubtitles(this.res);
		renderTitle(width, height, partialTicks);


		Scoreboard scoreboard = this.mc.world.getScoreboard();
		ScoreObjective objective = null;
		ScorePlayerTeam scoreplayerteam = scoreboard.getPlayersTeam(this.mc.player.getName());
		if (scoreplayerteam != null)
		{
			int slot = scoreplayerteam.getColor().getColorIndex();
			if (slot >= 0) {
				objective = scoreboard.getObjectiveInDisplaySlot(3 + slot);
			}
		}
		ScoreObjective scoreobjective1 = objective != null ? objective : scoreboard.getObjectiveInDisplaySlot(1);
		if ((renderObjective) && (scoreobjective1 != null)) {
			renderScoreboard(scoreobjective1, this.res);
		}
		GlStateManager.enableBlend();
		GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
		GlStateManager.disableAlpha();

		renderChat(width, height);

		renderPlayerList(width, height);

		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.disableLighting();
		GlStateManager.enableAlpha();

		post(RenderGameOverlayEvent.ElementType.ALL);
	}

	public ScaledResolution getResolution()
	{
		return this.res;
	}

	protected void renderCrosshairs(float partialTicks)
	{
		if (pre(RenderGameOverlayEvent.ElementType.CROSSHAIRS)) {
			return;
		}
		bind(Gui.ICONS);
		GlStateManager.enableBlend();
		super.renderAttackIndicator(partialTicks, this.res);
		post(RenderGameOverlayEvent.ElementType.CROSSHAIRS);
	}

	protected void renderPotionIcons(ScaledResolution resolution)
	{
		if (pre(RenderGameOverlayEvent.ElementType.POTION_ICONS)) {
			return;
		}
		super.renderPotionEffects(resolution);
		post(RenderGameOverlayEvent.ElementType.POTION_ICONS);
	}

	protected void renderSubtitles(ScaledResolution resolution)
	{
		if (pre(RenderGameOverlayEvent.ElementType.SUBTITLES)) {
			return;
		}
		this.overlaySubtitle.renderSubtitles(this.res);
		post(RenderGameOverlayEvent.ElementType.SUBTITLES);
	}

	protected void renderBossHealth()
	{
		if (pre(RenderGameOverlayEvent.ElementType.BOSSHEALTH)) {
			return;
		}
		bind(Gui.ICONS);
		GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
		this.mc.mcProfiler.startSection("bossHealth");
		GlStateManager.enableBlend();
		this.overlayBoss.renderBossHealth();
		GlStateManager.disableBlend();
		this.mc.mcProfiler.endSection();
		post(RenderGameOverlayEvent.ElementType.BOSSHEALTH);
	}

	protected void renderVignette(float lightLevel, ScaledResolution scaledRes)
	{
		if (pre(RenderGameOverlayEvent.ElementType.VIGNETTE))
		{
			GlStateManager.enableDepth();
			GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
			return;
		}
		super.renderVignette(lightLevel, scaledRes);
		post(RenderGameOverlayEvent.ElementType.VIGNETTE);
	}

	private void renderHelmet(ScaledResolution res, float partialTicks)
	{
		if (pre(RenderGameOverlayEvent.ElementType.HELMET)) {
			return;
		}
		ItemStack itemstack = this.mc.player.inventory.armorItemInSlot(3);
		if ((this.mc.gameSettings.thirdPersonView == 0) && (!itemstack.isEmpty()))
		{
			Item item = itemstack.getItem();
			if (item == Item.getItemFromBlock(Blocks.PUMPKIN)) {
				renderPumpkinOverlay(res);
			} else {
				item.renderHelmetOverlay(itemstack, this.mc.player, res, partialTicks);
			}
		}
		post(RenderGameOverlayEvent.ElementType.HELMET);
	}

	protected void renderArmor(int width, int height)
	{
		if (pre(RenderGameOverlayEvent.ElementType.ARMOR)) {
			return;
		}
		this.mc.mcProfiler.startSection("armor");

		GlStateManager.enableBlend();
		int left = width / 2 - 91;
		int top = height - left_height;

		int level = ForgeHooks.getTotalArmorValue(this.mc.player);
		for (int i = 1; (level > 0) && (i < 20); i += 2)
		{
			if (i < level) {
				drawTexturedModalRect(left, top, 34, 9, 9, 9);
			} else if (i == level) {
				drawTexturedModalRect(left, top, 25, 9, 9, 9);
			} else if (i > level) {
				drawTexturedModalRect(left, top, 16, 9, 9, 9);
			}
			left += 8;
		}
		left_height += 10;

		GlStateManager.disableBlend();
		this.mc.mcProfiler.endSection();
		post(RenderGameOverlayEvent.ElementType.ARMOR);
	}

	protected void renderPortal(ScaledResolution res, float partialTicks)
	{
		if (pre(RenderGameOverlayEvent.ElementType.PORTAL)) {
			return;
		}
		float f1 = this.mc.player.prevTimeInPortal + (this.mc.player.timeInPortal - this.mc.player.prevTimeInPortal) * partialTicks;
		if (f1 > 0.0F) {
			renderPortal(f1, res);
		}
		post(RenderGameOverlayEvent.ElementType.PORTAL);
	}

	protected void renderHotbar(ScaledResolution res, float partialTicks)
	{
		if (pre(RenderGameOverlayEvent.ElementType.HOTBAR)) {
			return;
		}
		if (this.mc.playerController.isSpectator()) {
			this.spectatorGui.renderTooltip(res, partialTicks);
		} else {
			super.renderHotbar(res, partialTicks);
		}
		post(RenderGameOverlayEvent.ElementType.HOTBAR);
	}

	public void setOverlayMessage(ITextComponent component, boolean animateColor)
	{
		setOverlayMessage(component.getFormattedText(), animateColor);
	}

	protected void renderAir(int width, int height)
	{
		if (pre(RenderGameOverlayEvent.ElementType.AIR)) {
			return;
		}
		this.mc.mcProfiler.startSection("air");
		EntityPlayer player = (EntityPlayer)this.mc.getRenderViewEntity();
		GlStateManager.enableBlend();
		int left = width / 2 + 91;
		int top = height - right_height;
		if (player.isInsideOfMaterial(Material.WATER))
		{
			int air = player.getAir();
			int full = MathHelper.ceil((air - 2) * 10.0D / 300.0D);
			int partial = MathHelper.ceil(air * 10.0D / 300.0D) - full;
			for (int i = 0; i < full + partial; i++) {
				drawTexturedModalRect(left - i * 8 - 9, top, i < full ? 16 : 25, 18, 9, 9);
			}
			right_height += 10;
		}
		GlStateManager.disableBlend();
		this.mc.mcProfiler.endSection();
		post(RenderGameOverlayEvent.ElementType.AIR);
	}

	public void renderHealth(int width, int height)
	{
		bind(ICONS);
		if (pre(RenderGameOverlayEvent.ElementType.HEALTH)) {
			return;
		}
		this.mc.mcProfiler.startSection("health");
		GlStateManager.enableBlend();

		EntityPlayer player = (EntityPlayer)this.mc.getRenderViewEntity();
		int health = MathHelper.ceil(player.getHealth());
		boolean highlight = (this.healthUpdateCounter > this.updateCounter) && ((this.healthUpdateCounter - this.updateCounter) / 3L % 2L == 1L);
		if ((health < this.playerHealth) && (player.hurtResistantTime > 0))
		{
			this.lastSystemTime = Minecraft.getSystemTime();
			this.healthUpdateCounter = (this.updateCounter + 20);
		}
		else if ((health > this.playerHealth) && (player.hurtResistantTime > 0))
		{
			this.lastSystemTime = Minecraft.getSystemTime();
			this.healthUpdateCounter = (this.updateCounter + 10);
		}
		if (Minecraft.getSystemTime() - this.lastSystemTime > 1000L)
		{
			this.playerHealth = health;
			this.lastPlayerHealth = health;
			this.lastSystemTime = Minecraft.getSystemTime();
		}
		this.playerHealth = health;
		int healthLast = this.lastPlayerHealth;

		IAttributeInstance attrMaxHealth = player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);
		float healthMax = (float)attrMaxHealth.getAttributeValue();
		float absorb = MathHelper.ceil(player.getAbsorptionAmount());

		int healthRows = MathHelper.ceil((healthMax + absorb) / 2.0F / 10.0F);
		int rowHeight = Math.max(10 - (healthRows - 2), 3);

		this.rand.setSeed(this.updateCounter * 312871);

		int left = width / 2 - 91;
		int top = height - left_height;
		left_height += healthRows * rowHeight;
		if (rowHeight != 10) {
			left_height += 10 - rowHeight;
		}
		int regen = -1;
		if (player.isPotionActive(MobEffects.REGENERATION)) {
			regen = this.updateCounter % 25;
		}
		int TOP = 9 * (this.mc.world.getWorldInfo().isHardcoreModeEnabled() ? 5 : 0);
		int BACKGROUND = highlight ? 25 : 16;
		int MARGIN = 16;
		if (player.isPotionActive(MobEffects.POISON)) {
			MARGIN += 36;
		} else if (player.isPotionActive(MobEffects.WITHER)) {
			MARGIN += 72;
		}
		float absorbRemaining = absorb;
		for (int i = MathHelper.ceil((healthMax + absorb) / 2.0F) - 1; i >= 0; i--)
		{
			int row = MathHelper.ceil((i + 1) / 10.0F) - 1;
			int x = left + i % 10 * 8;
			int y = top - row * rowHeight;
			if (health <= 4) {
				y += this.rand.nextInt(2);
			}
			if (i == regen) {
				y -= 2;
			}
			drawTexturedModalRect(x, y, BACKGROUND, TOP, 9, 9);
			if (highlight) {
				if (i * 2 + 1 < healthLast) {
					drawTexturedModalRect(x, y, MARGIN + 54, TOP, 9, 9);
				} else if (i * 2 + 1 == healthLast) {
					drawTexturedModalRect(x, y, MARGIN + 63, TOP, 9, 9);
				}
			}
			if (absorbRemaining > 0.0F)
			{
				if ((absorbRemaining == absorb) && (absorb % 2.0F == 1.0F))
				{
					drawTexturedModalRect(x, y, MARGIN + 153, TOP, 9, 9);
					absorbRemaining -= 1.0F;
				}
				else
				{
					drawTexturedModalRect(x, y, MARGIN + 144, TOP, 9, 9);
					absorbRemaining -= 2.0F;
				}
			}
			else if (i * 2 + 1 < health) {
				drawTexturedModalRect(x, y, MARGIN + 36, TOP, 9, 9);
			} else if (i * 2 + 1 == health) {
				drawTexturedModalRect(x, y, MARGIN + 45, TOP, 9, 9);
			}
		}
		GlStateManager.disableBlend();
		this.mc.mcProfiler.endSection();
		post(RenderGameOverlayEvent.ElementType.HEALTH);
	}

	public void renderFood(int width, int height)
	{
		if (pre(RenderGameOverlayEvent.ElementType.FOOD)) {
			return;
		}
		this.mc.mcProfiler.startSection("food");

		EntityPlayer player = (EntityPlayer)this.mc.getRenderViewEntity();
		GlStateManager.enableBlend();
		int left = width / 2 + 91;
		int top = height - right_height;
		right_height += 10;
		boolean unused = false;

		FoodStats stats = this.mc.player.getFoodStats();
		int level = stats.getFoodLevel();
		for (int i = 0; i < 10; i++)
		{
			int idx = i * 2 + 1;
			int x = left - i * 8 - 9;
			int y = top;
			int icon = 16;
			byte background = 0;
			if (this.mc.player.isPotionActive(MobEffects.HUNGER))
			{
				icon += 36;
				background = 13;
			}
			if (unused) {
				background = 1;
			}
			if ((player.getFoodStats().getSaturationLevel() <= 0.0F) && (this.updateCounter % (level * 3 + 1) == 0)) {
				y = top + (this.rand.nextInt(3) - 1);
			}
			drawTexturedModalRect(x, y, 16 + background * 9, 27, 9, 9);
			if (idx < level) {
				drawTexturedModalRect(x, y, icon + 36, 27, 9, 9);
			} else if (idx == level) {
				drawTexturedModalRect(x, y, icon + 45, 27, 9, 9);
			}
		}
		GlStateManager.disableBlend();
		this.mc.mcProfiler.endSection();
		post(RenderGameOverlayEvent.ElementType.FOOD);
	}

	protected void renderSleepFade(int width, int height)
	{
		if (this.mc.player.getSleepTimer() > 0)
		{
			this.mc.mcProfiler.startSection("sleep");
			GlStateManager.disableDepth();
			GlStateManager.disableAlpha();
			int sleepTime = this.mc.player.getSleepTimer();
			float opacity = sleepTime / 100.0F;
			if (opacity > 1.0F) {
				opacity = 1.0F - (sleepTime - 100) / 10.0F;
			}
			int color = (int)(220.0F * opacity) << 24 | 0x101020;
			drawRect(0, 0, width, height, color);
			GlStateManager.enableAlpha();
			GlStateManager.enableDepth();
			this.mc.mcProfiler.endSection();
		}
	}

	protected void renderExperience(int width, int height)
	{
		bind(ICONS);
		if (pre(RenderGameOverlayEvent.ElementType.EXPERIENCE)) {
			return;
		}
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.disableBlend();
		if (this.mc.playerController.gameIsSurvivalOrAdventure())
		{
			this.mc.mcProfiler.startSection("expBar");
			int cap = this.mc.player.xpBarCap();
			int left = width / 2 - 91;
			if (cap > 0)
			{
				short barWidth = 182;
				int filled = (int)(this.mc.player.experience * (barWidth + 1));
				int top = height - 32 + 3;
				drawTexturedModalRect(left, top, 0, 64, barWidth, 5);
				if (filled > 0) {
					drawTexturedModalRect(left, top, 0, 69, filled, 5);
				}
			}
			this.mc.mcProfiler.endSection();
			if ((this.mc.playerController.gameIsSurvivalOrAdventure()) && (this.mc.player.experienceLevel > 0))
			{
				this.mc.mcProfiler.startSection("expLevel");
				boolean flag1 = false;
				int color = flag1 ? 16777215 : 8453920;
				String text = "" + this.mc.player.experienceLevel;
				int x = (width - this.fontrenderer.getStringWidth(text)) / 2;
				int y = height - 31 - 4;
				this.fontrenderer.drawString(text, x + 1, y, 0);
				this.fontrenderer.drawString(text, x - 1, y, 0);
				this.fontrenderer.drawString(text, x, y + 1, 0);
				this.fontrenderer.drawString(text, x, y - 1, 0);
				this.fontrenderer.drawString(text, x, y, color);
				this.mc.mcProfiler.endSection();
			}
		}
		GlStateManager.enableBlend();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

		post(RenderGameOverlayEvent.ElementType.EXPERIENCE);
	}

	protected void renderJumpBar(int width, int height)
	{
		bind(ICONS);
		if (pre(RenderGameOverlayEvent.ElementType.JUMPBAR)) {
			return;
		}
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.disableBlend();

		this.mc.mcProfiler.startSection("jumpBar");
		float charge = this.mc.player.getHorseJumpPower();
		int barWidth = 182;
		int x = width / 2 - 91;
		int filled = (int)(charge * 183.0F);
		int top = height - 32 + 3;

		drawTexturedModalRect(x, top, 0, 84, 182, 5);
		if (filled > 0) {
			drawTexturedModalRect(x, top, 0, 89, filled, 5);
		}
		GlStateManager.enableBlend();
		this.mc.mcProfiler.endSection();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		post(RenderGameOverlayEvent.ElementType.JUMPBAR);
	}

	protected void renderToolHighlight(ScaledResolution res)
	{
		if ((this.mc.gameSettings.heldItemTooltips) && (!this.mc.playerController.isSpectator()))
		{
			this.mc.mcProfiler.startSection("toolHighlight");
			if ((this.remainingHighlightTicks > 0) && (!this.highlightingItemStack.isEmpty()))
			{
				String name = this.highlightingItemStack.getDisplayName();
				if (this.highlightingItemStack.hasDisplayName()) {
					name = TextFormatting.ITALIC + name;
				}
				name = this.highlightingItemStack.getItem().getHighlightTip(this.highlightingItemStack, name);

				int opacity = (int)(this.remainingHighlightTicks * 256.0F / 10.0F);
				if (opacity > 255) {
					opacity = 255;
				}
				if (opacity > 0)
				{
					int y = res.getScaledHeight() - 59;
					if (!this.mc.playerController.shouldDrawHUD()) {
						y += 14;
					}
					GlStateManager.pushMatrix();
					GlStateManager.enableBlend();
					GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
					FontRenderer font = this.highlightingItemStack.getItem().getFontRenderer(this.highlightingItemStack);
					if (font != null)
					{
						int x = (res.getScaledWidth() - font.getStringWidth(name)) / 2;
						font.drawStringWithShadow(name, x, y, 0xFFFFFF | opacity << 24);
					}
					else
					{
						int x = (res.getScaledWidth() - this.fontrenderer.getStringWidth(name)) / 2;
						this.fontrenderer.drawStringWithShadow(name, x, y, 0xFFFFFF | opacity << 24);
					}
					GlStateManager.disableBlend();
					GlStateManager.popMatrix();
				}
			}
			this.mc.mcProfiler.endSection();
		}
		else if (this.mc.player.isSpectator())
		{
			this.spectatorGui.renderSelectedItem(res);
		}
	}

	protected void renderHUDText(int width, int height)
	{
		this.mc.mcProfiler.startSection("forgeHudText");
		OpenGlHelper.glBlendFunc(770, 771, 1, 0);
		ArrayList<String> listL = new ArrayList();
		ArrayList<String> listR = new ArrayList();
		if (this.mc.isDemo())
		{
			long time = this.mc.world.getTotalWorldTime();
			if (time >= 120500L) {
				listR.add(I18n.format("demo.demoExpired"));
			} else {
				listR.add(I18n.format("demo.remainingTime", new Object[] { StringUtils.ticksToElapsedTime((int)(120500L - time)) }));
			}
		}
		if ((this.mc.gameSettings.showDebugInfo) && (!pre(RenderGameOverlayEvent.ElementType.DEBUG)))
		{
			listL.addAll(this.debugOverlay.getLeft());
			listR.addAll(this.debugOverlay.getRight());
			post(RenderGameOverlayEvent.ElementType.DEBUG);
		}
		RenderGameOverlayEvent.Text event = new RenderGameOverlayEvent.Text(this.eventParent, listL, listR);
		int top;
		if (!MinecraftForge.EVENT_BUS.post(event))
		{
			top = 2;
			for (String msg : listL) {
				if (msg != null)
				{
					drawRect(1, top - 1, 2 + this.fontrenderer.getStringWidth(msg) + 1, top + this.fontrenderer.FONT_HEIGHT - 1, -1873784752);
					this.fontrenderer.drawString(msg, 2, top, 14737632);
					top += this.fontrenderer.FONT_HEIGHT;
				}
			}
			top = 2;
			for (String msg : listR) {
				if (msg != null)
				{
					int w = this.fontrenderer.getStringWidth(msg);
					int left = width - 2 - w;
					drawRect(left - 1, top - 1, left + w + 1, top + this.fontrenderer.FONT_HEIGHT - 1, -1873784752);
					this.fontrenderer.drawString(msg, left, top, 14737632);
					top += this.fontrenderer.FONT_HEIGHT;
				}
			}
		}
		this.mc.mcProfiler.endSection();
		post(RenderGameOverlayEvent.ElementType.TEXT);
	}

	protected void renderFPSGraph()
	{
		if ((this.mc.gameSettings.showDebugInfo) && (this.mc.gameSettings.showLagometer) && (!pre(RenderGameOverlayEvent.ElementType.FPS_GRAPH)))
		{
			this.debugOverlay.renderLagometer();
			post(RenderGameOverlayEvent.ElementType.FPS_GRAPH);
		}
	}

	protected void renderRecordOverlay(int width, int height, float partialTicks)
	{
		if (this.overlayMessageTime > 0)
		{
			this.mc.mcProfiler.startSection("overlayMessage");
			float hue = this.overlayMessageTime - partialTicks;
			int opacity = (int)(hue * 256.0F / 20.0F);
			if (opacity > 255) {
				opacity = 255;
			}
			if (opacity > 0)
			{
				GlStateManager.pushMatrix();
				GlStateManager.translate(width / 2, height - 68, 0.0F);
				GlStateManager.enableBlend();
				GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
				int color = this.animateOverlayMessageColor ? Color.HSBtoRGB(hue / 50.0F, 0.7F, 0.6F) & 0xFFFFFF : 16777215;
				this.fontrenderer.drawString(this.overlayMessage, -this.fontrenderer.getStringWidth(this.overlayMessage) / 2, -4, color | opacity << 24);
				GlStateManager.disableBlend();
				GlStateManager.popMatrix();
			}
			this.mc.mcProfiler.endSection();
		}
	}

	protected void renderTitle(int width, int height, float partialTicks)
	{
		if (this.titlesTimer > 0)
		{
			this.mc.mcProfiler.startSection("titleAndSubtitle");
			float age = this.titlesTimer - partialTicks;
			int opacity = 255;
			if (this.titlesTimer > this.titleFadeOut + this.titleDisplayTime)
			{
				float f3 = this.titleFadeIn + this.titleDisplayTime + this.titleFadeOut - age;
				opacity = (int)(f3 * 255.0F / this.titleFadeIn);
			}
			if (this.titlesTimer <= this.titleFadeOut) {
				opacity = (int)(age * 255.0F / this.titleFadeOut);
			}
			opacity = MathHelper.clamp(opacity, 0, 255);
			if (opacity > 8)
			{
				GlStateManager.pushMatrix();
				GlStateManager.translate(width / 2, height / 2, 0.0F);
				GlStateManager.enableBlend();
				GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
				GlStateManager.pushMatrix();
				GlStateManager.scale(4.0F, 4.0F, 4.0F);
				int l = opacity << 24 & 0xFF000000;
				getFontRenderer().drawString(this.displayedTitle, -getFontRenderer().getStringWidth(this.displayedTitle) / 2, -10.0F, 0xFFFFFF | l, true);
				GlStateManager.popMatrix();
				GlStateManager.pushMatrix();
				GlStateManager.scale(2.0F, 2.0F, 2.0F);
				getFontRenderer().drawString(this.displayedSubTitle, -getFontRenderer().getStringWidth(this.displayedSubTitle) / 2, 5.0F, 0xFFFFFF | l, true);
				GlStateManager.popMatrix();
				GlStateManager.disableBlend();
				GlStateManager.popMatrix();
			}
			this.mc.mcProfiler.endSection();
		}
	}

	protected Object renderChat(int width, int height)
	{
		this.mc.mcProfiler.startSection("chat");

		RenderGameOverlayEvent.Chat event = new RenderGameOverlayEvent.Chat(this.eventParent, 0, height - 48);
		if (MinecraftForge.EVENT_BUS.post(event)) {
			return null;
		}
		GlStateManager.pushMatrix();
		GlStateManager.translate(event.getPosX(), event.getPosY(), 0.0F);
		this.persistantChatGUI.drawChat(this.updateCounter);
		GlStateManager.popMatrix();

		post(RenderGameOverlayEvent.ElementType.CHAT);

		this.mc.mcProfiler.endSection();
		return null;
	}

	protected void renderPlayerList(int width, int height)
	{
		ScoreObjective scoreobjective = this.mc.world.getScoreboard().getObjectiveInDisplaySlot(0);
		NetHandlerPlayClient handler = this.mc.player.connection;
		if ((this.mc.gameSettings.keyBindPlayerList.isKeyDown()) && ((!this.mc.isIntegratedServerRunning()) || (handler.getPlayerInfoMap().size() > 1) || (scoreobjective != null)))
		{
			this.overlayPlayerList.updatePlayerList(true);
			if (pre(RenderGameOverlayEvent.ElementType.PLAYER_LIST)) {
				return;
			}
			this.overlayPlayerList.renderPlayerlist(width, this.mc.world.getScoreboard(), scoreobjective);
			post(RenderGameOverlayEvent.ElementType.PLAYER_LIST);
		}
		else
		{
			this.overlayPlayerList.updatePlayerList(false);
		}
	}

	protected void renderHealthMount(int width, int height)
	{
		EntityPlayer player = (EntityPlayer)this.mc.getRenderViewEntity();
		Entity tmp = player.getRidingEntity();
		if (!(tmp instanceof EntityLivingBase)) {
			return;
		}
		bind(ICONS);
		if (pre(RenderGameOverlayEvent.ElementType.HEALTHMOUNT)) {
			return;
		}
		boolean unused = false;
		int left_align = width / 2 + 91;

		this.mc.mcProfiler.endStartSection("mountHealth");
		GlStateManager.enableBlend();
		EntityLivingBase mount = (EntityLivingBase)tmp;
		int health = (int)Math.ceil(mount.getHealth());
		float healthMax = mount.getMaxHealth();
		int hearts = (int)(healthMax + 0.5F) / 2;
		if (hearts > 30) {
			hearts = 30;
		}
		int MARGIN = 52;
		int BACKGROUND = 52 + (unused ? 1 : 0);
		int HALF = 97;
		int FULL = 88;
		for (int heart = 0; hearts > 0; heart += 20)
		{
			int top = height - right_height;

			int rowCount = Math.min(hearts, 10);
			hearts -= rowCount;
			for (int i = 0; i < rowCount; i++)
			{
				int x = left_align - i * 8 - 9;
				drawTexturedModalRect(x, top, BACKGROUND, 9, 9, 9);
				if (i * 2 + 1 + heart < health) {
					drawTexturedModalRect(x, top, 88, 9, 9, 9);
				} else if (i * 2 + 1 + heart == health) {
					drawTexturedModalRect(x, top, 97, 9, 9, 9);
				}
			}
			right_height += 10;
		}
		GlStateManager.disableBlend();
		post(RenderGameOverlayEvent.ElementType.HEALTHMOUNT);
	}

	public boolean pre(RenderGameOverlayEvent.ElementType type)
	{
		return MinecraftForge.EVENT_BUS.post(new RenderGameOverlayEvent.Pre(this.eventParent, type));
	}

	public void post(RenderGameOverlayEvent.ElementType type)
	{
		MinecraftForge.EVENT_BUS.post(new RenderGameOverlayEvent.Post(this.eventParent, type));
	}

	private void bind(ResourceLocation res)
	{
		this.mc.getTextureManager().bindTexture(res);
	}

	private class GuiOverlayDebugForge
			extends GuiOverlayDebug
	{
		private Minecraft mc;

		private GuiOverlayDebugForge(Minecraft mc, Object o)
		{
			super(mc);
			this.mc = mc;
		}

		protected void renderDebugInfoLeft() {}

		protected void renderDebugInfoRight(ScaledResolution res) {}

		private List<String> getLeft()
		{
			List<String> ret = call();
			ret.add("");
			ret.add("Debug: Pie [shift]: " + (this.mc.gameSettings.showDebugProfilerChart ? "visible" : "hidden") + " FPS [alt]: " + (this.mc.gameSettings.showLagometer ? "visible" : "hidden"));
			ret.add("For help: press F3 + Q");
			return ret;
		}

		private List<String> getRight()
		{
			return getDebugInfoRight();
		}
	}
}
