package net.ababab.kingsword.entity;

import net.ababab.kingsword.ElementsKingswordMod;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityWeatherEffect;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Random;

@ElementsKingswordMod.ModElement.Tag
public class EntityLightningBlotFul extends ElementsKingswordMod.ModElement {
	public static final int ENTITYID = 15;
	public static final int ENTITYID_RANGED = 16;
	public EntityLightningBlotFul(ElementsKingswordMod instance) {
		super(instance, 90);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(EntityCustom.class, new EntityRenderFactory<EntityCustom>(RenderAbsoluteLightning.class));
	}
	private static class EntityRenderFactory<E extends Entity> implements IRenderFactory<E> {
		private final Class<? extends Render<E>> renderClass;
		public EntityRenderFactory(Class<? extends Render<E>> renderClass) {
			this.renderClass = renderClass;
		}

		@Override
		public Render<E> createRenderFor(RenderManager manager) {
			try {
				return renderClass.getConstructor(RenderManager.class).newInstance(manager);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	private static class RenderAbsoluteLightning extends Render<EntityCustom> {
		public RenderAbsoluteLightning(RenderManager renderManagerIn) {
			super(renderManagerIn);
		}

		/**
		 * Renders the desired {@code T} type Entity.
		 */
		public void doRender(EntityCustom entity, double x, double y, double z, float entityYaw, float partialTicks) {
			Tessellator tessellator = Tessellator.getInstance();
			BufferBuilder bufferbuilder = tessellator.getBuffer();
			GlStateManager.disableTexture2D();
			GlStateManager.disableLighting();
			GlStateManager.enableBlend();
			GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);
			double[] adouble = new double[8];
			double[] adouble1 = new double[8];
			double d0 = 0.0D;
			double d1 = 0.0D;
			Random random = new Random(entity.boltVertex);
			for (int i = 7; i >= 0; --i) {
				adouble[i] = d0;
				adouble1[i] = d1;
				d0 += (double) (random.nextInt(11) - 5);
				d1 += (double) (random.nextInt(11) - 5);
			}
			for (int k1 = 0; k1 < 4; k1++) {
				Random random1 = new Random(entity.boltVertex);
				for (int j = 0; j < 3; j++) {
					int k = 7;
					int l = 0;
					if (j > 0) {
						k = 7 - j;
					}
					if (j > 0) {
						l = k - 2;
					}
					double d2 = adouble[k] - d0;
					double d3 = adouble1[k] - d1;
					for (int i1 = k; i1 >= l; i1--) {
						double d4 = d2;
						double d5 = d3;
						if (j == 0) {
							d2 += (double) (random1.nextInt(11) - 5);
							d3 += (double) (random1.nextInt(11) - 5);
						} else {
							d2 += (double) (random1.nextInt(31) - 15);
							d3 += (double) (random1.nextInt(31) - 15);
						}
						bufferbuilder.begin(5, DefaultVertexFormats.POSITION_COLOR);
						float f = 0.5F;
						float f1 = 0.45F;
						float f2 = 0.45F;
						float f3 = 0.5F;
						double d6 = 0.1D + k1 * 0.2D;
						if (j == 0)
							d6 *= i1 * 0.1D + 1.0D;
						double d7 = 0.1D + k1 * 0.2D;
						if (j == 0)
							d7 *= (i1 - 1) * 0.1D + 1.0D;
						for (int j1 = 0; j1 < 5; j1++) {
							double d8 = x + 0.5D - d6;
							double d9 = z + 0.5D - d6;
							if (j1 == 1 || j1 == 2)
								d8 += d6 * 2.0D;
							if (j1 == 2 || j1 == 3)
								d9 += d6 * 2.0D;
							double d10 = x + 0.5D - d7;
							double d11 = z + 0.5D - d7;
							if (j1 == 1 || j1 == 2)
								d10 += d7 * 2.0D;
							if (j1 == 2 || j1 == 3)
								d11 += d7 * 2.0D;
							Random randCol = new Random();
							bufferbuilder.pos(d10 + d2, y + (i1 * 16), d11 + d3).color(randCol.nextFloat(), randCol.nextFloat(), randCol.nextFloat(), 0.5F).endVertex();
							bufferbuilder.pos(d8 + d4, y + ((i1 + 1) * 16), d9 + d5).color(randCol.nextFloat(), randCol.nextFloat(), randCol.nextFloat(), 0.5F).endVertex();
						}
						tessellator.draw();
					}
				}
			}
			GlStateManager.disableBlend();
			GlStateManager.enableLighting();
			GlStateManager.enableTexture2D();
		}

		/**
		 * Returns the location of an entity's texture. Doesn't seem to be called unless
		 * you call Render.bindEntityTexture.
		 */
		@Nullable
		protected ResourceLocation getEntityTexture(EntityCustom entity) {
			return null;
		}
	}

	public static class EntityCustom extends EntityWeatherEffect {
		/**
		 * Declares which state the lightning bolt is in. Whether it's in the air, hit
		 * the ground, etc.
		 */
		private int lightningState;
		/**
		 * A random long that is used to change the vertex of the lightning rendered in
		 * RenderLightningBolt
		 */
		public long boltVertex;
		/**
		 * Determines the time before the EntityLightningBolt is destroyed. It is a
		 * random integer decremented over time.
		 */
		private int boltLivingTime;
		private final boolean effectOnly;
		public EntityCustom(World worldIn, double x, double y, double z, boolean effectOnlyIn) {
			super(worldIn);
			this.setLocationAndAngles(x, y, z, 0.0F, 0.0F);
			this.lightningState = 2;
			this.boltVertex = this.rand.nextLong();
			this.boltLivingTime = this.rand.nextInt(3) + 1;
			this.effectOnly = effectOnlyIn;
			BlockPos blockpos = new BlockPos(this);
			if (!effectOnlyIn && !worldIn.isRemote && worldIn.getGameRules().getBoolean("doFireTick")
					&& (worldIn.getDifficulty() == EnumDifficulty.NORMAL || worldIn.getDifficulty() == EnumDifficulty.HARD)
					&& worldIn.isAreaLoaded(blockpos, 10)) {
				if (worldIn.getBlockState(blockpos).getMaterial() == Material.AIR && Blocks.FIRE.canPlaceBlockAt(worldIn, blockpos)) {
					worldIn.setBlockState(blockpos, Blocks.FIRE.getDefaultState());
				}
				for (int i = 0; i < 4; ++i) {
					BlockPos blockpos1 = blockpos.add(this.rand.nextInt(3) - 1, this.rand.nextInt(3) - 1, this.rand.nextInt(3) - 1);
					if (worldIn.getBlockState(blockpos1).getMaterial() == Material.AIR && Blocks.FIRE.canPlaceBlockAt(worldIn, blockpos1)) {
						worldIn.setBlockState(blockpos1, Blocks.FIRE.getDefaultState());
					}
				}
			}
		}

		public EntityCustom(World worldIn) {
			super(worldIn);
			this.lightningState = 2;
			this.boltVertex = this.rand.nextLong();
			this.boltLivingTime = this.rand.nextInt(3) + 1;
			effectOnly = true;
		}

		public SoundCategory getSoundCategory() {
			return SoundCategory.WEATHER;
		}

		/**
		 * Called to update the entity's position/logic.
		 */
		public void onUpdate() {
			super.onUpdate();
			if (this.lightningState == 2) {
				this.world.playSound((EntityPlayer) null, this.posX, this.posY, this.posZ, SoundEvents.ENTITY_LIGHTNING_THUNDER,
						SoundCategory.WEATHER, 10000.0F, 0.8F + this.rand.nextFloat() * 0.2F);
				this.world.playSound((EntityPlayer) null, this.posX, this.posY, this.posZ, SoundEvents.ENTITY_LIGHTNING_IMPACT, SoundCategory.WEATHER,
						2.0F, 0.5F + this.rand.nextFloat() * 0.2F);
			}
			--this.lightningState;
			if (this.lightningState < 0) {
				if (this.boltLivingTime == 0) {
					this.setDead();
				} else if (this.lightningState < -this.rand.nextInt(10)) {
					--this.boltLivingTime;
					this.lightningState = 1;
					if (!this.effectOnly && !this.world.isRemote) {
						this.boltVertex = this.rand.nextLong();
						BlockPos blockpos = new BlockPos(this);
						if (this.world.getGameRules().getBoolean("doFireTick") && this.world.isAreaLoaded(blockpos, 10)
								&& this.world.getBlockState(blockpos).getMaterial() == Material.AIR
								&& Blocks.FIRE.canPlaceBlockAt(this.world, blockpos)) {
							this.world.setBlockState(blockpos, Blocks.FIRE.getDefaultState());
						}
					}
				}
			}
		}

		protected void entityInit() {
		}

		/**
		 * (abstract) Protected helper method to read subclass entity data from NBT.
		 */
		protected void readEntityFromNBT(NBTTagCompound compound) {
		}

		/**
		 * (abstract) Protected helper method to write subclass entity data to NBT.
		 */
		protected void writeEntityToNBT(NBTTagCompound compound) {
		}
	}
}