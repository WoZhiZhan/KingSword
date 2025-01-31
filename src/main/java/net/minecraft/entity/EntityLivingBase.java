package net.minecraft.entity;

import com.google.common.base.Objects;
import com.google.common.collect.Maps;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLadder;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentFrostWalker;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.ai.attributes.*;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.entity.passive.EntityFlying;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.*;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SPacketAnimation;
import net.minecraft.network.play.server.SPacketCollectItem;
import net.minecraft.network.play.server.SPacketEntityEquipment;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionUtils;
import net.minecraft.stats.StatList;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.PooledMutableBlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.event.entity.living.PotionColorCalculationEvent;
import net.minecraftforge.event.entity.living.PotionEvent.PotionAddedEvent;
import net.minecraftforge.event.entity.living.PotionEvent.PotionApplicableEvent;
import net.minecraftforge.event.entity.living.PotionEvent.PotionExpiryEvent;
import net.minecraftforge.event.entity.living.PotionEvent.PotionRemoveEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;
import net.minecraftforge.items.wrapper.EntityArmorInvWrapper;
import net.minecraftforge.items.wrapper.EntityHandsInvWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;
import java.util.*;

public abstract class EntityLivingBase extends Entity {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final UUID SPRINTING_SPEED_BOOST_ID = UUID.fromString("662A6B8D-DA3E-4C1C-8813-96EA6097278D");
    private static final AttributeModifier SPRINTING_SPEED_BOOST;
    public static final IAttribute SWIM_SPEED;
    protected static final DataParameter<Byte> HAND_STATES;
    public static final DataParameter<Float> HEALTH;
    private static final DataParameter<Integer> POTION_EFFECTS;
    private static final DataParameter<Boolean> HIDE_PARTICLES;
    private static final DataParameter<Integer> ARROW_COUNT_IN_ENTITY;
    private AbstractAttributeMap attributeMap;
    private final CombatTracker _combatTracker = new CombatTracker(this);
    private final Map<Potion, PotionEffect> activePotionsMap = Maps.newHashMap();
    private final NonNullList<ItemStack> handInventory;
    private final NonNullList<ItemStack> armorArray;
    public boolean isSwingInProgress;
    public EnumHand swingingHand;
    public int swingProgressInt;
    public int arrowHitTimer;
    public int hurtTime;
    public int maxHurtTime;
    public float attackedAtYaw;
    public int deathTime;
    public float prevSwingProgress;
    public float swingProgress;
    protected int ticksSinceLastSwing;
    public float prevLimbSwingAmount;
    public float limbSwingAmount;
    public float limbSwing;
    public int maxHurtResistantTime;
    public float prevCameraPitch;
    public float cameraPitch;
    public float randomUnused2;
    public float randomUnused1;
    public float renderYawOffset;
    public float prevRenderYawOffset;
    public float rotationYawHead;
    public float prevRotationYawHead;
    public float jumpMovementFactor;
    protected EntityPlayer attackingPlayer;
    protected int recentlyHit;
    public boolean dead;
    protected int idleTime;
    protected float prevOnGroundSpeedFactor;
    protected float onGroundSpeedFactor;
    protected float movedDistance;
    protected float prevMovedDistance;
    protected float unused180;
    protected int scoreValue;
    protected float lastDamage;
    protected boolean isJumping;
    public float moveStrafing;
    public float moveVertical;
    public float moveForward;
    public float randomYawVelocity;
    protected int newPosRotationIncrements;
    protected double interpTargetX;
    protected double interpTargetY;
    protected double interpTargetZ;
    protected double interpTargetYaw;
    protected double interpTargetPitch;
    private boolean potionsNeedUpdate;
    private EntityLivingBase revengeTarget;
    private int revengeTimer;
    private EntityLivingBase lastAttackedEntity;
    private int lastAttackedEntityTime;
    private float landMovementFactor;
    private int jumpTicks;
    private float absorptionAmount;
    protected ItemStack activeItemStack;
    protected int activeItemStackUseCount;
    protected int ticksElytraFlying;
    private BlockPos prevBlockpos;
    private DamageSource lastDamageSource;
    private long lastDamageStamp;
    private final IItemHandlerModifiable handHandler;
    private final IItemHandlerModifiable armorHandler;
    private final IItemHandler joinedHandler;

    public void onKillCommand() {
        this.attackEntityFrom(DamageSource.OUT_OF_WORLD, 3.4028235E38F);
    }

    public EntityLivingBase(World p_i1594_1_) {
        super(p_i1594_1_);
        this.handInventory = NonNullList.withSize(2, ItemStack.EMPTY);
        this.armorArray = NonNullList.withSize(4, ItemStack.EMPTY);
        this.maxHurtResistantTime = 20;
        this.jumpMovementFactor = 0.02F;
        this.potionsNeedUpdate = true;
        this.activeItemStack = ItemStack.EMPTY;
        this.handHandler = new EntityHandsInvWrapper(this);
        this.armorHandler = new EntityArmorInvWrapper(this);
        this.joinedHandler = new CombinedInvWrapper(new IItemHandlerModifiable[]{this.armorHandler, this.handHandler});
        this.applyEntityAttributes();
        this.setHealth(this.getMaxHealth());
        this.preventEntitySpawning = true;
        this.randomUnused1 = (float)((Math.random() + 1.0D) * 0.009999999776482582D);
        this.setPosition(this.posX, this.posY, this.posZ);
        this.randomUnused2 = (float)Math.random() * 12398.0F;
        this.rotationYaw = (float)(Math.random() * 6.283185307179586D);
        this.rotationYawHead = this.rotationYaw;
        this.stepHeight = 0.6F;
    }

    protected void entityInit() {
        this.dataManager.register(HAND_STATES, (byte)0);
        this.dataManager.register(POTION_EFFECTS, 0);
        this.dataManager.register(HIDE_PARTICLES, false);
        this.dataManager.register(ARROW_COUNT_IN_ENTITY, 0);
        this.dataManager.register(HEALTH, 1.0F);
    }

    protected void applyEntityAttributes() {
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.MAX_HEALTH);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ARMOR);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS);
        this.getAttributeMap().registerAttribute(SWIM_SPEED);
    }

    protected void updateFallState(double p_184231_1_, boolean p_184231_3_, IBlockState p_184231_4_, BlockPos p_184231_5_) {
        if (!this.isInWater()) {
            this.handleWaterMovement();
        }

        if (!this.world.isRemote && this.fallDistance > 3.0F && p_184231_3_) {
            float f = (float)MathHelper.ceil(this.fallDistance - 3.0F);
            if (!p_184231_4_.getBlock().isAir(p_184231_4_, this.world, p_184231_5_)) {
                double d0 = Math.min((double)(0.2F + f / 15.0F), 2.5D);
                int i = (int)(150.0D * d0);
                if (!p_184231_4_.getBlock().addLandingEffects(p_184231_4_, (WorldServer)this.world, p_184231_5_, p_184231_4_, this, i)) {
                    ((WorldServer)this.world).spawnParticle(EnumParticleTypes.BLOCK_DUST, this.posX, this.posY, this.posZ, i, 0.0D, 0.0D, 0.0D, 0.15000000596046448D, new int[]{Block.getStateId(p_184231_4_)});
                }
            }
        }

        super.updateFallState(p_184231_1_, p_184231_3_, p_184231_4_, p_184231_5_);
    }

    public boolean canBreatheUnderwater() {
        return false;
    }

    public void onEntityUpdate() {
        this.prevSwingProgress = this.swingProgress;
        super.onEntityUpdate();
        this.world.profiler.startSection("livingEntityBaseTick");
        boolean flag = this instanceof EntityPlayer;
        if (this.isEntityAlive()) {
            if (this.isEntityInsideOpaqueBlock()) {
                this.attackEntityFrom(DamageSource.IN_WALL, 1.0F);
            } else if (flag && !this.world.getWorldBorder().contains(this.getEntityBoundingBox())) {
                double d0 = this.world.getWorldBorder().getClosestDistance(this) + this.world.getWorldBorder().getDamageBuffer();
                if (d0 < 0.0D) {
                    double d1 = this.world.getWorldBorder().getDamageAmount();
                    if (d1 > 0.0D) {
                        this.attackEntityFrom(DamageSource.IN_WALL, (float)Math.max(1, MathHelper.floor(-d0 * d1)));
                    }
                }
            }
        }

        if (this.isImmuneToFire() || this.world.isRemote) {
            this.extinguish();
        }

        boolean flag1 = flag && ((EntityPlayer)this).capabilities.disableDamage;
        if (this.isEntityAlive()) {
            if (!this.isInsideOfMaterial(Material.WATER)) {
                this.setAir(300);
            } else {
                if (!this.canBreatheUnderwater() && !this.isPotionActive(MobEffects.WATER_BREATHING) && !flag1) {
                    this.setAir(this.decreaseAirSupply(this.getAir()));
                    if (this.getAir() == -20) {
                        this.setAir(0);

                        for(int i = 0; i < 8; ++i) {
                            float f2 = this.rand.nextFloat() - this.rand.nextFloat();
                            float f = this.rand.nextFloat() - this.rand.nextFloat();
                            float f1 = this.rand.nextFloat() - this.rand.nextFloat();
                            this.world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX + (double)f2, this.posY + (double)f, this.posZ + (double)f1, this.motionX, this.motionY, this.motionZ, new int[0]);
                        }

                        this.attackEntityFrom(DamageSource.DROWN, 2.0F);
                    }
                }

                if (!this.world.isRemote && this.isRiding() && this.getRidingEntity() != null && this.getRidingEntity().shouldDismountInWater(this)) {
                    this.dismountRidingEntity();
                }
            }

            if (!this.world.isRemote) {
                BlockPos blockpos = new BlockPos(this);
                if (!Objects.equal(this.prevBlockpos, blockpos)) {
                    this.prevBlockpos = blockpos;
                    this.frostWalk(blockpos);
                }
            }
        }

        if (this.isEntityAlive() && this.isWet()) {
            this.extinguish();
        }

        this.prevCameraPitch = this.cameraPitch;
        if (this.hurtTime > 0) {
            --this.hurtTime;
        }

        if (this.hurtResistantTime > 0 && !(this instanceof EntityPlayerMP)) {
            --this.hurtResistantTime;
        }

        if (this.getHealth() <= 0.0F) {
            this.onDeathUpdate();
        }

        if (this.recentlyHit > 0) {
            --this.recentlyHit;
        } else {
            this.attackingPlayer = null;
        }

        if (this.lastAttackedEntity != null && !this.lastAttackedEntity.isEntityAlive()) {
            this.lastAttackedEntity = null;
        }

        if (this.revengeTarget != null) {
            if (!this.revengeTarget.isEntityAlive()) {
                this.setRevengeTarget((EntityLivingBase)null);
            } else if (this.ticksExisted - this.revengeTimer > 100) {
                this.setRevengeTarget((EntityLivingBase)null);
            }
        }

        this.updatePotionEffects();
        this.prevMovedDistance = this.movedDistance;
        this.prevRenderYawOffset = this.renderYawOffset;
        this.prevRotationYawHead = this.rotationYawHead;
        this.prevRotationYaw = this.rotationYaw;
        this.prevRotationPitch = this.rotationPitch;
        this.world.profiler.endSection();
    }

    protected void frostWalk(BlockPos p_184594_1_) {
        int i = EnchantmentHelper.getMaxEnchantmentLevel(Enchantments.FROST_WALKER, this);
        if (i > 0) {
            EnchantmentFrostWalker.freezeNearby(this, this.world, p_184594_1_, i);
        }

    }

    public boolean isChild() {
        return false;
    }

    public void onDeathUpdate() {
        ++this.deathTime;
        if (this.deathTime == 20) {
            int k;
            if (!this.world.isRemote && (this.isPlayer() || this.recentlyHit > 0 && this.canDropLoot() && this.world.getGameRules().getBoolean("doMobLoot"))) {
                k = this.getExperiencePoints(this.attackingPlayer);
                k = ForgeEventFactory.getExperienceDrop(this, this.attackingPlayer, k);

                while(k > 0) {
                    int j = EntityXPOrb.getXPSplit(k);
                    k -= j;
                    this.world.spawnEntity(new EntityXPOrb(this.world, this.posX, this.posY, this.posZ, j));
                }
            }

            this.setDead();

            for(k = 0; k < 20; ++k) {
                double d2 = this.rand.nextGaussian() * 0.02D;
                double d0 = this.rand.nextGaussian() * 0.02D;
                double d1 = this.rand.nextGaussian() * 0.02D;
                this.world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d2, d0, d1, new int[0]);
            }
        }

    }

    protected boolean canDropLoot() {
        return !this.isChild();
    }

    protected int decreaseAirSupply(int p_70682_1_) {
        int i = EnchantmentHelper.getRespirationModifier(this);
        return i > 0 && this.rand.nextInt(i + 1) > 0 ? p_70682_1_ : p_70682_1_ - 1;
    }

    protected int getExperiencePoints(EntityPlayer p_70693_1_) {
        return 0;
    }

    protected boolean isPlayer() {
        return false;
    }

    public Random getRNG() {
        return this.rand;
    }

    @Nullable
    public EntityLivingBase getRevengeTarget() {
        return this.revengeTarget;
    }

    public int getRevengeTimer() {
        return this.revengeTimer;
    }

    public void setRevengeTarget(@Nullable EntityLivingBase p_70604_1_) {
        this.revengeTarget = p_70604_1_;
        this.revengeTimer = this.ticksExisted;
    }

    public EntityLivingBase getLastAttackedEntity() {
        return this.lastAttackedEntity;
    }

    public int getLastAttackedEntityTime() {
        return this.lastAttackedEntityTime;
    }

    public void setLastAttackedEntity(Entity p_130011_1_) {
        if (p_130011_1_ instanceof EntityLivingBase) {
            this.lastAttackedEntity = (EntityLivingBase)p_130011_1_;
        } else {
            this.lastAttackedEntity = null;
        }

        this.lastAttackedEntityTime = this.ticksExisted;
    }

    public int getIdleTime() {
        return this.idleTime;
    }

    protected void playEquipSound(ItemStack p_184606_1_) {
        if (!p_184606_1_.isEmpty()) {
            SoundEvent soundevent = SoundEvents.ITEM_ARMOR_EQUIP_GENERIC;
            Item item = p_184606_1_.getItem();
            if (item instanceof ItemArmor) {
                soundevent = ((ItemArmor)item).getArmorMaterial().getSoundEvent();
            } else if (item == Items.ELYTRA) {
                soundevent = SoundEvents.ITEM_ARMOR_EQIIP_ELYTRA;
            }

            this.playSound(soundevent, 1.0F, 1.0F);
        }

    }

    public void writeEntityToNBT(NBTTagCompound p_70014_1_) {
        p_70014_1_.setFloat("Health", this.getHealth());
        p_70014_1_.setShort("HurtTime", (short)this.hurtTime);
        p_70014_1_.setInteger("HurtByTimestamp", this.revengeTimer);
        p_70014_1_.setShort("DeathTime", (short)this.deathTime);
        p_70014_1_.setFloat("AbsorptionAmount", this.getAbsorptionAmount());
        EntityEquipmentSlot[] var2 = EntityEquipmentSlot.values();
        int var3 = var2.length;

        int var4;
        EntityEquipmentSlot entityequipmentslot1;
        ItemStack itemstack1;
        for(var4 = 0; var4 < var3; ++var4) {
            entityequipmentslot1 = var2[var4];
            itemstack1 = this.getItemStackFromSlot(entityequipmentslot1);
            if (!itemstack1.isEmpty()) {
                this.getAttributeMap().removeAttributeModifiers(itemstack1.getAttributeModifiers(entityequipmentslot1));
            }
        }

        p_70014_1_.setTag("Attributes", SharedMonsterAttributes.writeBaseAttributeMapToNBT(this.getAttributeMap()));
        var2 = EntityEquipmentSlot.values();
        var3 = var2.length;

        for(var4 = 0; var4 < var3; ++var4) {
            entityequipmentslot1 = var2[var4];
            itemstack1 = this.getItemStackFromSlot(entityequipmentslot1);
            if (!itemstack1.isEmpty()) {
                this.getAttributeMap().applyAttributeModifiers(itemstack1.getAttributeModifiers(entityequipmentslot1));
            }
        }

        if (!this.activePotionsMap.isEmpty()) {
            NBTTagList nbttaglist = new NBTTagList();
            Iterator var8 = this.activePotionsMap.values().iterator();

            while(var8.hasNext()) {
                PotionEffect potioneffect = (PotionEffect)var8.next();
                nbttaglist.appendTag(potioneffect.writeCustomPotionEffectToNBT(new NBTTagCompound()));
            }

            p_70014_1_.setTag("ActiveEffects", nbttaglist);
        }

        p_70014_1_.setBoolean("FallFlying", this.isElytraFlying());
    }

    public void readEntityFromNBT(NBTTagCompound p_70037_1_) {
        this.setAbsorptionAmount(p_70037_1_.getFloat("AbsorptionAmount"));
        if (p_70037_1_.hasKey("Attributes", 9) && this.world != null && !this.world.isRemote) {
            SharedMonsterAttributes.setAttributeModifiers(this.getAttributeMap(), p_70037_1_.getTagList("Attributes", 10));
        }

        if (p_70037_1_.hasKey("ActiveEffects", 9)) {
            NBTTagList nbttaglist = p_70037_1_.getTagList("ActiveEffects", 10);

            for(int i = 0; i < nbttaglist.tagCount(); ++i) {
                NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
                PotionEffect potioneffect = PotionEffect.readCustomPotionEffectFromNBT(nbttagcompound);
                if (potioneffect != null) {
                    this.activePotionsMap.put(potioneffect.getPotion(), potioneffect);
                }
            }
        }

        if (p_70037_1_.hasKey("Health", 99)) {
            this.setHealth(p_70037_1_.getFloat("Health"));
        }

        this.hurtTime = p_70037_1_.getShort("HurtTime");
        this.deathTime = p_70037_1_.getShort("DeathTime");
        this.revengeTimer = p_70037_1_.getInteger("HurtByTimestamp");
        if (p_70037_1_.hasKey("Team", 8)) {
            String s = p_70037_1_.getString("Team");
            boolean flag = this.world.getScoreboard().addPlayerToTeam(this.getCachedUniqueIdString(), s);
            if (!flag) {
                LOGGER.warn("Unable to add mob to team \"" + s + "\" (that team probably doesn't exist)");
            }
        }

        if (p_70037_1_.getBoolean("FallFlying")) {
            this.setFlag(7, true);
        }

    }

    protected void updatePotionEffects() {
        Iterator iterator = this.activePotionsMap.keySet().iterator();

        try {
            while(iterator.hasNext()) {
                Potion potion = (Potion)iterator.next();
                PotionEffect potioneffect = (PotionEffect)this.activePotionsMap.get(potion);
                if (!potioneffect.onUpdate(this)) {
                    if (!this.world.isRemote && !MinecraftForge.EVENT_BUS.post(new PotionExpiryEvent(this, potioneffect))) {
                        iterator.remove();
                        this.onFinishedPotionEffect(potioneffect);
                    }
                } else if (potioneffect.getDuration() % 600 == 0) {
                    this.onChangedPotionEffect(potioneffect, false);
                }
            }
        } catch (ConcurrentModificationException var11) {
        }

        if (this.potionsNeedUpdate) {
            if (!this.world.isRemote) {
                this.updatePotionMetadata();
            }

            this.potionsNeedUpdate = false;
        }

        int i = (Integer)this.dataManager.get(POTION_EFFECTS);
        boolean flag1 = (Boolean)this.dataManager.get(HIDE_PARTICLES);
        if (i > 0) {
            boolean flag;
            if (this.isInvisible()) {
                flag = this.rand.nextInt(15) == 0;
            } else {
                flag = this.rand.nextBoolean();
            }

            if (flag1) {
                flag &= this.rand.nextInt(5) == 0;
            }

            if (flag && i > 0) {
                double d0 = (double)(i >> 16 & 255) / 255.0D;
                double d1 = (double)(i >> 8 & 255) / 255.0D;
                double d2 = (double)(i >> 0 & 255) / 255.0D;
                this.world.spawnParticle(flag1 ? EnumParticleTypes.SPELL_MOB_AMBIENT : EnumParticleTypes.SPELL_MOB, this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, d0, d1, d2, new int[0]);
            }
        }

    }

    protected void updatePotionMetadata() {
        if (this.activePotionsMap.isEmpty()) {
            this.resetPotionEffectMetadata();
            this.setInvisible(false);
        } else {
            Collection<PotionEffect> collection = this.activePotionsMap.values();
            PotionColorCalculationEvent event = new PotionColorCalculationEvent(this, PotionUtils.getPotionColorFromEffectList(collection), areAllPotionsAmbient(collection), collection);
            MinecraftForge.EVENT_BUS.post(event);
            this.dataManager.set(HIDE_PARTICLES, event.areParticlesHidden());
            this.dataManager.set(POTION_EFFECTS, event.getColor());
            this.setInvisible(this.isPotionActive(MobEffects.INVISIBILITY));
        }

    }

    public static boolean areAllPotionsAmbient(Collection<PotionEffect> p_184593_0_) {
        Iterator var1 = p_184593_0_.iterator();

        PotionEffect potioneffect;
        do {
            if (!var1.hasNext()) {
                return true;
            }

            potioneffect = (PotionEffect)var1.next();
        } while(potioneffect.getIsAmbient());

        return false;
    }

    protected void resetPotionEffectMetadata() {
        this.dataManager.set(HIDE_PARTICLES, false);
        this.dataManager.set(POTION_EFFECTS, 0);
    }

    public void clearActivePotions() {
        if (!this.world.isRemote) {
            Iterator iterator = this.activePotionsMap.values().iterator();

            while(iterator.hasNext()) {
                PotionEffect effect = (PotionEffect)iterator.next();
                if (!MinecraftForge.EVENT_BUS.post(new PotionRemoveEvent(this, effect))) {
                    this.onFinishedPotionEffect(effect);
                    iterator.remove();
                }
            }
        }

    }

    public Collection<PotionEffect> getActivePotionEffects() {
        return this.activePotionsMap.values();
    }

    public Map<Potion, PotionEffect> getActivePotionMap() {
        return this.activePotionsMap;
    }

    public boolean isPotionActive(Potion p_70644_1_) {
        return this.activePotionsMap.containsKey(p_70644_1_);
    }

    @Nullable
    public PotionEffect getActivePotionEffect(Potion p_70660_1_) {
        return (PotionEffect)this.activePotionsMap.get(p_70660_1_);
    }

    public void addPotionEffect(PotionEffect p_70690_1_) {
        if (this.isPotionApplicable(p_70690_1_)) {
            PotionEffect potioneffect = (PotionEffect)this.activePotionsMap.get(p_70690_1_.getPotion());
            MinecraftForge.EVENT_BUS.post(new PotionAddedEvent(this, potioneffect, p_70690_1_));
            if (potioneffect == null) {
                this.activePotionsMap.put(p_70690_1_.getPotion(), p_70690_1_);
                this.onNewPotionEffect(p_70690_1_);
            } else {
                potioneffect.combine(p_70690_1_);
                this.onChangedPotionEffect(potioneffect, true);
            }
        }

    }

    public boolean isPotionApplicable(PotionEffect p_70687_1_) {
        PotionApplicableEvent event = new PotionApplicableEvent(this, p_70687_1_);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.getResult() != Result.DEFAULT) {
            return event.getResult() == Result.ALLOW;
        } else {
            if (this.getCreatureAttribute() == EnumCreatureAttribute.UNDEAD) {
                Potion potion = p_70687_1_.getPotion();
                if (potion == MobEffects.REGENERATION || potion == MobEffects.POISON) {
                    return false;
                }
            }

            return true;
        }
    }

    public boolean isEntityUndead() {
        return this.getCreatureAttribute() == EnumCreatureAttribute.UNDEAD;
    }

    @Nullable
    public PotionEffect removeActivePotionEffect(@Nullable Potion p_184596_1_) {
        return (PotionEffect)this.activePotionsMap.remove(p_184596_1_);
    }

    public void removePotionEffect(Potion p_184589_1_) {
        if (!MinecraftForge.EVENT_BUS.post(new PotionRemoveEvent(this, p_184589_1_))) {
            PotionEffect potioneffect = this.removeActivePotionEffect(p_184589_1_);
            if (potioneffect != null) {
                this.onFinishedPotionEffect(potioneffect);
            }

        }
    }

    protected void onNewPotionEffect(PotionEffect p_70670_1_) {
        this.potionsNeedUpdate = true;
        if (!this.world.isRemote) {
            p_70670_1_.getPotion().applyAttributesModifiersToEntity(this, this.getAttributeMap(), p_70670_1_.getAmplifier());
        }

    }

    protected void onChangedPotionEffect(PotionEffect p_70695_1_, boolean p_70695_2_) {
        this.potionsNeedUpdate = true;
        if (p_70695_2_ && !this.world.isRemote) {
            Potion potion = p_70695_1_.getPotion();
            potion.removeAttributesModifiersFromEntity(this, this.getAttributeMap(), p_70695_1_.getAmplifier());
            potion.applyAttributesModifiersToEntity(this, this.getAttributeMap(), p_70695_1_.getAmplifier());
        }

    }

    protected void onFinishedPotionEffect(PotionEffect p_70688_1_) {
        this.potionsNeedUpdate = true;
        if (!this.world.isRemote) {
            p_70688_1_.getPotion().removeAttributesModifiersFromEntity(this, this.getAttributeMap(), p_70688_1_.getAmplifier());
        }

    }

    public void heal(float p_70691_1_) {
        p_70691_1_ = ForgeEventFactory.onLivingHeal(this, p_70691_1_);
        if (!(p_70691_1_ <= 0.0F)) {
            float f = this.getHealth();
            if (f > 0.0F) {
                this.setHealth(f + p_70691_1_);
            }

        }
    }

    public final float getHealth() {
        return (Float)this.dataManager.get(HEALTH);
    }

    public void setHealth(float p_70606_1_) {
        this.dataManager.set(HEALTH, MathHelper.clamp(p_70606_1_, 0.0F, this.getMaxHealth()));
    }

    public boolean attackEntityFrom(DamageSource p_70097_1_, float p_70097_2_) {
        if (!ForgeHooks.onLivingAttack(this, p_70097_1_, p_70097_2_)) {
            return false;
        } else if (this.isEntityInvulnerable(p_70097_1_)) {
            return false;
        } else if (this.world.isRemote) {
            return false;
        } else {
            this.idleTime = 0;
            if (this.getHealth() <= 0.0F) {
                return false;
            } else if (p_70097_1_.isFireDamage() && this.isPotionActive(MobEffects.FIRE_RESISTANCE)) {
                return false;
            } else {
                float f = p_70097_2_;
                if ((p_70097_1_ == DamageSource.ANVIL || p_70097_1_ == DamageSource.FALLING_BLOCK) && !this.getItemStackFromSlot(EntityEquipmentSlot.HEAD).isEmpty()) {
                    this.getItemStackFromSlot(EntityEquipmentSlot.HEAD).damageItem((int)(p_70097_2_ * 4.0F + this.rand.nextFloat() * p_70097_2_ * 2.0F), this);
                    p_70097_2_ *= 0.75F;
                }

                boolean flag = false;
                if (p_70097_2_ > 0.0F && this.canBlockDamageSource(p_70097_1_)) {
                    this.damageShield(p_70097_2_);
                    p_70097_2_ = 0.0F;
                    if (!p_70097_1_.isProjectile()) {
                        Entity entity = p_70097_1_.getImmediateSource();
                        if (entity instanceof EntityLivingBase) {
                            this.blockUsingShield((EntityLivingBase)entity);
                        }
                    }

                    flag = true;
                }

                this.limbSwingAmount = 1.5F;
                boolean flag1 = true;
                if ((float)this.hurtResistantTime > (float)this.maxHurtResistantTime / 2.0F) {
                    if (p_70097_2_ <= this.lastDamage) {
                        return false;
                    }

                    this.damageEntity(p_70097_1_, p_70097_2_ - this.lastDamage);
                    this.lastDamage = p_70097_2_;
                    flag1 = false;
                } else {
                    this.lastDamage = p_70097_2_;
                    this.hurtResistantTime = this.maxHurtResistantTime;
                    this.damageEntity(p_70097_1_, p_70097_2_);
                    this.maxHurtTime = 10;
                    this.hurtTime = this.maxHurtTime;
                }

                this.attackedAtYaw = 0.0F;
                Entity entity1 = p_70097_1_.getTrueSource();
                if (entity1 != null) {
                    if (entity1 instanceof EntityLivingBase) {
                        this.setRevengeTarget((EntityLivingBase)entity1);
                    }

                    if (entity1 instanceof EntityPlayer) {
                        this.recentlyHit = 100;
                        this.attackingPlayer = (EntityPlayer)entity1;
                    } else if (entity1 instanceof EntityTameable) {
                        EntityTameable entitywolf = (EntityTameable)entity1;
                        if (entitywolf.isTamed()) {
                            this.recentlyHit = 100;
                            this.attackingPlayer = null;
                        }
                    }
                }

                if (flag1) {
                    if (flag) {
                        this.world.setEntityState(this, (byte)29);
                    } else if (p_70097_1_ instanceof EntityDamageSource && ((EntityDamageSource)p_70097_1_).getIsThornsDamage()) {
                        this.world.setEntityState(this, (byte)33);
                    } else {
                        byte b0;
                        if (p_70097_1_ == DamageSource.DROWN) {
                            b0 = 36;
                        } else if (p_70097_1_.isFireDamage()) {
                            b0 = 37;
                        } else {
                            b0 = 2;
                        }

                        this.world.setEntityState(this, b0);
                    }

                    if (p_70097_1_ != DamageSource.DROWN && (!flag || p_70097_2_ > 0.0F)) {
                        this.markVelocityChanged();
                    }

                    if (entity1 != null) {
                        double d1 = entity1.posX - this.posX;

                        double d0;
                        for(d0 = entity1.posZ - this.posZ; d1 * d1 + d0 * d0 < 1.0E-4D; d0 = (Math.random() - Math.random()) * 0.01D) {
                            d1 = (Math.random() - Math.random()) * 0.01D;
                        }

                        this.attackedAtYaw = (float)(MathHelper.atan2(d0, d1) * 57.29577951308232D - (double)this.rotationYaw);
                        this.knockBack(entity1, 0.4F, d1, d0);
                    } else {
                        this.attackedAtYaw = (float)((int)(Math.random() * 2.0D) * 180);
                    }
                }

                if (this.getHealth() <= 0.0F) {
                    if (!this.checkTotemDeathProtection(p_70097_1_)) {
                        SoundEvent soundevent = this.getDeathSound();
                        if (flag1 && soundevent != null) {
                            this.playSound(soundevent, this.getSoundVolume(), this.getSoundPitch());
                        }

                        this.onDeath(p_70097_1_);
                    }
                } else if (flag1) {
                    this.playHurtSound(p_70097_1_);
                }

                boolean flag2 = !flag || p_70097_2_ > 0.0F;
                if (flag2) {
                    this.lastDamageSource = p_70097_1_;
                    this.lastDamageStamp = this.world.getTotalWorldTime();
                }

                if (this instanceof EntityPlayerMP) {
                    CriteriaTriggers.ENTITY_HURT_PLAYER.trigger((EntityPlayerMP)this, p_70097_1_, f, p_70097_2_, flag);
                }

                if (entity1 instanceof EntityPlayerMP) {
                    CriteriaTriggers.PLAYER_HURT_ENTITY.trigger((EntityPlayerMP)entity1, this, p_70097_1_, f, p_70097_2_, flag);
                }

                return flag2;
            }
        }
    }

    protected void blockUsingShield(EntityLivingBase p_190629_1_) {
        p_190629_1_.knockBack(this, 0.5F, this.posX - p_190629_1_.posX, this.posZ - p_190629_1_.posZ);
    }

    private boolean checkTotemDeathProtection(DamageSource p_190628_1_) {
        if (p_190628_1_.canHarmInCreative()) {
            return false;
        } else {
            ItemStack itemstack = null;
            EnumHand[] var3 = EnumHand.values();
            int var4 = var3.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                EnumHand enumhand = var3[var5];
                ItemStack itemstack1 = this.getHeldItem(enumhand);
                if (itemstack1.getItem() == Items.TOTEM_OF_UNDYING) {
                    itemstack = itemstack1.copy();
                    itemstack1.shrink(1);
                    break;
                }
            }

            if (itemstack != null) {
                if (this instanceof EntityPlayerMP) {
                    EntityPlayerMP entityplayermp = (EntityPlayerMP)this;
                    entityplayermp.addStat(StatList.getObjectUseStats(Items.TOTEM_OF_UNDYING));
                    CriteriaTriggers.USED_TOTEM.trigger(entityplayermp, itemstack);
                }

                this.setHealth(1.0F);
                this.clearActivePotions();
                this.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 900, 1));
                this.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 100, 1));
                this.world.setEntityState(this, (byte)35);
            }

            return itemstack != null;
        }
    }

    @Nullable
    public DamageSource getLastDamageSource() {
        if (this.world.getTotalWorldTime() - this.lastDamageStamp > 40L) {
            this.lastDamageSource = null;
        }

        return this.lastDamageSource;
    }

    protected void playHurtSound(DamageSource p_184581_1_) {
        SoundEvent soundevent = this.getHurtSound(p_184581_1_);
        if (soundevent != null) {
            this.playSound(soundevent, this.getSoundVolume(), this.getSoundPitch());
        }

    }

    private boolean canBlockDamageSource(DamageSource p_184583_1_) {
        if (!p_184583_1_.isUnblockable() && this.isActiveItemStackBlocking()) {
            Vec3d vec3d = p_184583_1_.getDamageLocation();
            if (vec3d != null) {
                Vec3d vec3d1 = this.getLook(1.0F);
                Vec3d vec3d2 = vec3d.subtractReverse(new Vec3d(this.posX, this.posY, this.posZ)).normalize();
                vec3d2 = new Vec3d(vec3d2.x, 0.0D, vec3d2.z);
                if (vec3d2.dotProduct(vec3d1) < 0.0D) {
                    return true;
                }
            }
        }

        return false;
    }

    public void renderBrokenItemStack(ItemStack p_70669_1_) {
        this.world.playSound((EntityPlayer)null, this.posX, this.posY, this.posZ, SoundEvents.ENTITY_ITEM_BREAK, this.getSoundCategory(), 0.8F, 0.8F + this.world.rand.nextFloat() * 0.4F);

        for(int i = 0; i < 5; ++i) {
            Vec3d vec3d = new Vec3d(((double)this.rand.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);
            vec3d = vec3d.rotatePitch(-this.rotationPitch * 0.017453292F);
            vec3d = vec3d.rotateYaw(-this.rotationYaw * 0.017453292F);
            double d0 = (double)(-this.rand.nextFloat()) * 0.6D - 0.3D;
            Vec3d vec3d1 = new Vec3d(((double)this.rand.nextFloat() - 0.5D) * 0.3D, d0, 0.6D);
            vec3d1 = vec3d1.rotatePitch(-this.rotationPitch * 0.017453292F);
            vec3d1 = vec3d1.rotateYaw(-this.rotationYaw * 0.017453292F);
            vec3d1 = vec3d1.addVector(this.posX, this.posY + (double)this.getEyeHeight(), this.posZ);
            if (this.world instanceof WorldServer) {
                ((WorldServer)this.world).spawnParticle(EnumParticleTypes.ITEM_CRACK, vec3d1.x, vec3d1.y, vec3d1.z, 0, vec3d.x, vec3d.y + 0.05D, vec3d.z, 0.0D, new int[]{Item.getIdFromItem(p_70669_1_.getItem()), p_70669_1_.getMetadata()});
            } else {
                this.world.spawnParticle(EnumParticleTypes.ITEM_CRACK, vec3d1.x, vec3d1.y, vec3d1.z, vec3d.x, vec3d.y + 0.05D, vec3d.z, new int[]{Item.getIdFromItem(p_70669_1_.getItem()), p_70669_1_.getMetadata()});
            }
        }

    }

    public void onDeath(DamageSource p_70645_1_) {
        if (!ForgeHooks.onLivingDeath(this, p_70645_1_)) {
            if (!this.dead) {
                Entity entity = p_70645_1_.getTrueSource();
                EntityLivingBase entitylivingbase = this.getAttackingEntity();
                if (this.scoreValue >= 0 && entitylivingbase != null) {
                    entitylivingbase.awardKillScore(this, this.scoreValue, p_70645_1_);
                }

                if (entity != null) {
                    entity.onKillEntity(this);
                }

                this.dead = true;
                this.getCombatTracker().reset();
                if (!this.world.isRemote) {
                    int i = ForgeHooks.getLootingLevel(this, entity, p_70645_1_);
                    this.captureDrops = true;
                    this.capturedDrops.clear();
                    if (this.canDropLoot() && this.world.getGameRules().getBoolean("doMobLoot")) {
                        boolean flag = this.recentlyHit > 0;
                        this.dropLoot(flag, i, p_70645_1_);
                    }

                    this.captureDrops = false;
                    if (!ForgeHooks.onLivingDrops(this, p_70645_1_, this.capturedDrops, i, this.recentlyHit > 0)) {
                        Iterator var7 = this.capturedDrops.iterator();

                        while(var7.hasNext()) {
                            EntityItem item = (EntityItem)var7.next();
                            this.world.spawnEntity(item);
                        }
                    }
                }

                this.world.setEntityState(this, (byte)3);
            }

        }
    }

    protected void dropLoot(boolean p_184610_1_, int p_184610_2_, DamageSource p_184610_3_) {
        this.dropFewItems(p_184610_1_, p_184610_2_);
        this.dropEquipment(p_184610_1_, p_184610_2_);
    }

    protected void dropEquipment(boolean p_82160_1_, int p_82160_2_) {
    }

    public void knockBack(Entity p_70653_1_, float p_70653_2_, double p_70653_3_, double p_70653_5_) {
        LivingKnockBackEvent event = ForgeHooks.onLivingKnockBack(this, p_70653_1_, p_70653_2_, p_70653_3_, p_70653_5_);
        if (!event.isCanceled()) {
            p_70653_2_ = event.getStrength();
            p_70653_3_ = event.getRatioX();
            p_70653_5_ = event.getRatioZ();
            if (this.rand.nextDouble() >= this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).getAttributeValue()) {
                this.isAirBorne = true;
                float f = MathHelper.sqrt(p_70653_3_ * p_70653_3_ + p_70653_5_ * p_70653_5_);
                this.motionX /= 2.0D;
                this.motionZ /= 2.0D;
                this.motionX -= p_70653_3_ / (double)f * (double)p_70653_2_;
                this.motionZ -= p_70653_5_ / (double)f * (double)p_70653_2_;
                if (this.onGround) {
                    this.motionY /= 2.0D;
                    this.motionY += (double)p_70653_2_;
                    if (this.motionY > 0.4000000059604645D) {
                        this.motionY = 0.4000000059604645D;
                    }
                }
            }

        }
    }

    @Nullable
    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
        return SoundEvents.ENTITY_GENERIC_HURT;
    }

    @Nullable
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_GENERIC_DEATH;
    }

    protected SoundEvent getFallSound(int p_184588_1_) {
        return p_184588_1_ > 4 ? SoundEvents.ENTITY_GENERIC_BIG_FALL : SoundEvents.ENTITY_GENERIC_SMALL_FALL;
    }

    protected void dropFewItems(boolean p_70628_1_, int p_70628_2_) {
    }

    public boolean isOnLadder() {
        int i = MathHelper.floor(this.posX);
        int j = MathHelper.floor(this.getEntityBoundingBox().minY);
        int k = MathHelper.floor(this.posZ);
        if (this instanceof EntityPlayer && ((EntityPlayer)this).isSpectator()) {
            return false;
        } else {
            BlockPos blockpos = new BlockPos(i, j, k);
            IBlockState iblockstate = this.world.getBlockState(blockpos);
            Block block = iblockstate.getBlock();
            return ForgeHooks.isLivingOnLadder(iblockstate, this.world, new BlockPos(i, j, k), this);
        }
    }

    private boolean canGoThroughtTrapDoorOnLadder(BlockPos p_184604_1_, IBlockState p_184604_2_) {
        if ((Boolean)p_184604_2_.getValue(BlockTrapDoor.OPEN)) {
            IBlockState iblockstate = this.world.getBlockState(p_184604_1_.down());
            if (iblockstate.getBlock() == Blocks.LADDER && iblockstate.getValue(BlockLadder.FACING) == p_184604_2_.getValue(BlockTrapDoor.FACING)) {
                return true;
            }
        }

        return false;
    }

    public boolean isEntityAlive() {
        return !this.isDead && this.getHealth() > 0.0F;
    }

    public void fall(float p_180430_1_, float p_180430_2_) {
        float[] ret = ForgeHooks.onLivingFall(this, p_180430_1_, p_180430_2_);
        if (ret != null) {
            p_180430_1_ = ret[0];
            p_180430_2_ = ret[1];
            super.fall(p_180430_1_, p_180430_2_);
            PotionEffect potioneffect = this.getActivePotionEffect(MobEffects.JUMP_BOOST);
            float f = potioneffect == null ? 0.0F : (float)(potioneffect.getAmplifier() + 1);
            int i = MathHelper.ceil((p_180430_1_ - 3.0F - f) * p_180430_2_);
            if (i > 0) {
                this.playSound(this.getFallSound(i), 1.0F, 1.0F);
                this.attackEntityFrom(DamageSource.FALL, (float)i);
                int j = MathHelper.floor(this.posX);
                int k = MathHelper.floor(this.posY - 0.20000000298023224D);
                int l = MathHelper.floor(this.posZ);
                IBlockState iblockstate = this.world.getBlockState(new BlockPos(j, k, l));
                if (iblockstate.getMaterial() != Material.AIR) {
                    SoundType soundtype = iblockstate.getBlock().getSoundType(iblockstate, this.world, new BlockPos(j, k, l), this);
                    this.playSound(soundtype.getFallSound(), soundtype.getVolume() * 0.5F, soundtype.getPitch() * 0.75F);
                }
            }

        }
    }

    @SideOnly(Side.CLIENT)
    public void performHurtAnimation() {
        this.maxHurtTime = 10;
        this.hurtTime = this.maxHurtTime;
        this.attackedAtYaw = 0.0F;
    }

    public int getTotalArmorValue() {
        IAttributeInstance iattributeinstance = this.getEntityAttribute(SharedMonsterAttributes.ARMOR);
        return MathHelper.floor(iattributeinstance.getAttributeValue());
    }

    protected void damageArmor(float p_70675_1_) {
    }

    protected void damageShield(float p_184590_1_) {
    }

    protected float applyArmorCalculations(DamageSource p_70655_1_, float p_70655_2_) {
        if (!p_70655_1_.isUnblockable()) {
            this.damageArmor(p_70655_2_);
            p_70655_2_ = CombatRules.getDamageAfterAbsorb(p_70655_2_, (float)this.getTotalArmorValue(), (float)this.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).getAttributeValue());
        }

        return p_70655_2_;
    }

    protected float applyPotionDamageCalculations(DamageSource p_70672_1_, float p_70672_2_) {
        if (p_70672_1_.isDamageAbsolute()) {
            return p_70672_2_;
        } else {
            int k;
            if (this.isPotionActive(MobEffects.RESISTANCE) && p_70672_1_ != DamageSource.OUT_OF_WORLD) {
                k = (this.getActivePotionEffect(MobEffects.RESISTANCE).getAmplifier() + 1) * 5;
                int j = 25 - k;
                float f = p_70672_2_ * (float)j;
                p_70672_2_ = f / 25.0F;
            }

            if (p_70672_2_ <= 0.0F) {
                return 0.0F;
            } else {
                k = EnchantmentHelper.getEnchantmentModifierDamage(this.getArmorInventoryList(), p_70672_1_);
                if (k > 0) {
                    p_70672_2_ = CombatRules.getDamageAfterMagicAbsorb(p_70672_2_, (float)k);
                }

                return p_70672_2_;
            }
        }
    }

    protected void damageEntity(DamageSource p_70665_1_, float p_70665_2_) {
        if (!this.isEntityInvulnerable(p_70665_1_)) {
            p_70665_2_ = ForgeHooks.onLivingHurt(this, p_70665_1_, p_70665_2_);
            if (p_70665_2_ <= 0.0F) {
                return;
            }

            p_70665_2_ = this.applyArmorCalculations(p_70665_1_, p_70665_2_);
            p_70665_2_ = this.applyPotionDamageCalculations(p_70665_1_, p_70665_2_);
            float f = p_70665_2_;
            p_70665_2_ = Math.max(p_70665_2_ - this.getAbsorptionAmount(), 0.0F);
            this.setAbsorptionAmount(this.getAbsorptionAmount() - (f - p_70665_2_));
            p_70665_2_ = ForgeHooks.onLivingDamage(this, p_70665_1_, p_70665_2_);
            if (p_70665_2_ != 0.0F) {
                float f1 = this.getHealth();
                this.getCombatTracker().trackDamage(p_70665_1_, f1, p_70665_2_);
                this.setHealth(f1 - p_70665_2_);
                this.setAbsorptionAmount(this.getAbsorptionAmount() - p_70665_2_);
            }
        }

    }

    public CombatTracker getCombatTracker() {
        return this._combatTracker;
    }

    @Nullable
    public EntityLivingBase getAttackingEntity() {
        if (this._combatTracker.getBestAttacker() != null) {
            return this._combatTracker.getBestAttacker();
        } else if (this.attackingPlayer != null) {
            return this.attackingPlayer;
        } else {
            return this.revengeTarget != null ? this.revengeTarget : null;
        }
    }

    public final float getMaxHealth() {
        return (float)this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getAttributeValue();
    }

    public final int getArrowCountInEntity() {
        return (Integer)this.dataManager.get(ARROW_COUNT_IN_ENTITY);
    }

    public final void setArrowCountInEntity(int p_85034_1_) {
        this.dataManager.set(ARROW_COUNT_IN_ENTITY, p_85034_1_);
    }

    private int getArmSwingAnimationEnd() {
        if (this.isPotionActive(MobEffects.HASTE)) {
            return 6 - (1 + this.getActivePotionEffect(MobEffects.HASTE).getAmplifier());
        } else {
            return this.isPotionActive(MobEffects.MINING_FATIGUE) ? 6 + (1 + this.getActivePotionEffect(MobEffects.MINING_FATIGUE).getAmplifier()) * 2 : 6;
        }
    }

    public void swingArm(EnumHand p_184609_1_) {
        ItemStack stack = this.getHeldItem(p_184609_1_);
        if (stack.isEmpty() || !stack.getItem().onEntitySwing(this, stack)) {
            if (!this.isSwingInProgress || this.swingProgressInt >= this.getArmSwingAnimationEnd() / 2 || this.swingProgressInt < 0) {
                this.swingProgressInt = -1;
                this.isSwingInProgress = true;
                this.swingingHand = p_184609_1_;
                if (this.world instanceof WorldServer) {
                    ((WorldServer)this.world).getEntityTracker().sendToTracking(this, new SPacketAnimation(this, p_184609_1_ == EnumHand.MAIN_HAND ? 0 : 3));
                }
            }

        }
    }

    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte p_70103_1_) {
        boolean flag = p_70103_1_ == 33;
        boolean flag1 = p_70103_1_ == 36;
        boolean flag2 = p_70103_1_ == 37;
        if (p_70103_1_ != 2 && !flag && !flag1 && !flag2) {
            if (p_70103_1_ == 3) {
                SoundEvent soundevent1 = this.getDeathSound();
                if (soundevent1 != null) {
                    this.playSound(soundevent1, this.getSoundVolume(), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
                }

                this.setHealth(0.0F);
                this.onDeath(DamageSource.GENERIC);
            } else if (p_70103_1_ == 30) {
                this.playSound(SoundEvents.ITEM_SHIELD_BREAK, 0.8F, 0.8F + this.world.rand.nextFloat() * 0.4F);
            } else if (p_70103_1_ == 29) {
                this.playSound(SoundEvents.ITEM_SHIELD_BLOCK, 1.0F, 0.8F + this.world.rand.nextFloat() * 0.4F);
            } else {
                super.handleStatusUpdate(p_70103_1_);
            }
        } else {
            this.limbSwingAmount = 1.5F;
            this.hurtResistantTime = this.maxHurtResistantTime;
            this.maxHurtTime = 10;
            this.hurtTime = this.maxHurtTime;
            this.attackedAtYaw = 0.0F;
            if (flag) {
                this.playSound(SoundEvents.ENCHANT_THORNS_HIT, this.getSoundVolume(), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
            }

            DamageSource damagesource;
            if (flag2) {
                damagesource = DamageSource.ON_FIRE;
            } else if (flag1) {
                damagesource = DamageSource.DROWN;
            } else {
                damagesource = DamageSource.GENERIC;
            }

            SoundEvent soundevent = this.getHurtSound(damagesource);
            if (soundevent != null) {
                this.playSound(soundevent, this.getSoundVolume(), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
            }

            this.attackEntityFrom(DamageSource.GENERIC, 0.0F);
        }

    }

    protected void outOfWorld() {
        this.attackEntityFrom(DamageSource.OUT_OF_WORLD, 4.0F);
    }

    protected void updateArmSwingProgress() {
        int i = this.getArmSwingAnimationEnd();
        if (this.isSwingInProgress) {
            ++this.swingProgressInt;
            if (this.swingProgressInt >= i) {
                this.swingProgressInt = 0;
                this.isSwingInProgress = false;
            }
        } else {
            this.swingProgressInt = 0;
        }

        this.swingProgress = (float)this.swingProgressInt / (float)i;
    }

    public IAttributeInstance getEntityAttribute(IAttribute p_110148_1_) {
        return this.getAttributeMap().getAttributeInstance(p_110148_1_);
    }

    public AbstractAttributeMap getAttributeMap() {
        if (this.attributeMap == null) {
            this.attributeMap = new AttributeMap();
        }

        return this.attributeMap;
    }

    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.UNDEFINED;
    }

    public ItemStack getHeldItemMainhand() {
        return this.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND);
    }

    public ItemStack getHeldItemOffhand() {
        return this.getItemStackFromSlot(EntityEquipmentSlot.OFFHAND);
    }

    public ItemStack getHeldItem(EnumHand p_184586_1_) {
        if (p_184586_1_ == EnumHand.MAIN_HAND) {
            return this.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND);
        } else if (p_184586_1_ == EnumHand.OFF_HAND) {
            return this.getItemStackFromSlot(EntityEquipmentSlot.OFFHAND);
        } else {
            throw new IllegalArgumentException("Invalid hand " + p_184586_1_);
        }
    }

    public void setHeldItem(EnumHand p_184611_1_, ItemStack p_184611_2_) {
        if (p_184611_1_ == EnumHand.MAIN_HAND) {
            this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, p_184611_2_);
        } else {
            if (p_184611_1_ != EnumHand.OFF_HAND) {
                throw new IllegalArgumentException("Invalid hand " + p_184611_1_);
            }

            this.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, p_184611_2_);
        }

    }

    public boolean hasItemInSlot(EntityEquipmentSlot p_190630_1_) {
        return !this.getItemStackFromSlot(p_190630_1_).isEmpty();
    }

    public abstract Iterable<ItemStack> getArmorInventoryList();

    public abstract ItemStack getItemStackFromSlot(EntityEquipmentSlot var1);

    public abstract void setItemStackToSlot(EntityEquipmentSlot var1, ItemStack var2);

    public void setSprinting(boolean p_70031_1_) {
        super.setSprinting(p_70031_1_);
        IAttributeInstance iattributeinstance = this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
        if (iattributeinstance.getModifier(SPRINTING_SPEED_BOOST_ID) != null) {
            iattributeinstance.removeModifier(SPRINTING_SPEED_BOOST);
        }

        if (p_70031_1_) {
            iattributeinstance.applyModifier(SPRINTING_SPEED_BOOST);
        }

    }

    protected float getSoundVolume() {
        return 1.0F;
    }

    protected float getSoundPitch() {
        return this.isChild() ? (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.5F : (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F;
    }

    protected boolean isMovementBlocked() {
        return this.getHealth() <= 0.0F;
    }

    public void dismountEntity(Entity p_110145_1_) {
        double d1;
        double d4;
        if (!(p_110145_1_ instanceof EntityBoat) && !(p_110145_1_ instanceof AbstractHorse)) {
            d1 = p_110145_1_.posX;
            double d13 = p_110145_1_.getEntityBoundingBox().minY + (double)p_110145_1_.height;
            double d14 = p_110145_1_.posZ;
            EnumFacing enumfacing1 = p_110145_1_.getAdjustedHorizontalFacing();
            if (enumfacing1 != null) {
                EnumFacing enumfacing = enumfacing1.rotateY();
                int[][] aint1 = new int[][]{{0, 1}, {0, -1}, {-1, 1}, {-1, -1}, {1, 1}, {1, -1}, {-1, 0}, {1, 0}, {0, 1}};
                d4 = Math.floor(this.posX) + 0.5D;
                double d6 = Math.floor(this.posZ) + 0.5D;
                double d7 = this.getEntityBoundingBox().maxX - this.getEntityBoundingBox().minX;
                double d8 = this.getEntityBoundingBox().maxZ - this.getEntityBoundingBox().minZ;
                AxisAlignedBB axisalignedbb = new AxisAlignedBB(d4 - d7 / 2.0D, p_110145_1_.getEntityBoundingBox().minY, d6 - d8 / 2.0D, d4 + d7 / 2.0D, Math.floor(p_110145_1_.getEntityBoundingBox().minY) + (double)this.height, d6 + d8 / 2.0D);
                int[][] var20 = aint1;
                int var21 = aint1.length;

                for(int var22 = 0; var22 < var21; ++var22) {
                    int[] aint = var20[var22];
                    double d9 = (double)(enumfacing1.getFrontOffsetX() * aint[0] + enumfacing.getFrontOffsetX() * aint[1]);
                    double d10 = (double)(enumfacing1.getFrontOffsetZ() * aint[0] + enumfacing.getFrontOffsetZ() * aint[1]);
                    double d11 = d4 + d9;
                    double d12 = d6 + d10;
                    AxisAlignedBB axisalignedbb1 = axisalignedbb.offset(d9, 0.0D, d10);
                    if (!this.world.collidesWithAnyBlock(axisalignedbb1)) {
                        if (this.world.getBlockState(new BlockPos(d11, this.posY, d12)).isSideSolid(this.world, new BlockPos(d11, this.posY, d12), EnumFacing.UP)) {
                            this.setPositionAndUpdate(d11, this.posY + 1.0D, d12);
                            return;
                        }

                        BlockPos blockpos = new BlockPos(d11, this.posY - 1.0D, d12);
                        if (this.world.getBlockState(blockpos).isSideSolid(this.world, blockpos, EnumFacing.UP) || this.world.getBlockState(blockpos).getMaterial() == Material.WATER) {
                            d1 = d11;
                            d13 = this.posY + 1.0D;
                            d14 = d12;
                        }
                    } else if (!this.world.collidesWithAnyBlock(axisalignedbb1.offset(0.0D, 1.0D, 0.0D)) && this.world.getBlockState(new BlockPos(d11, this.posY + 1.0D, d12)).isSideSolid(this.world, new BlockPos(d11, this.posY + 1.0D, d12), EnumFacing.UP)) {
                        d1 = d11;
                        d13 = this.posY + 2.0D;
                        d14 = d12;
                    }
                }
            }

            this.setPositionAndUpdate(d1, d13, d14);
        } else {
            d1 = (double)(this.width / 2.0F + p_110145_1_.width / 2.0F) + 0.4D;
            float f;
            if (p_110145_1_ instanceof EntityBoat) {
                f = 0.0F;
            } else {
                f = 1.5707964F * (float)(this.getPrimaryHand() == EnumHandSide.RIGHT ? -1 : 1);
            }

            float f1 = -MathHelper.sin(-this.rotationYaw * 0.017453292F - 3.1415927F + f);
            float f2 = -MathHelper.cos(-this.rotationYaw * 0.017453292F - 3.1415927F + f);
            double d2 = Math.abs(f1) > Math.abs(f2) ? d1 / (double)Math.abs(f1) : d1 / (double)Math.abs(f2);
            double d3 = this.posX + (double)f1 * d2;
            d4 = this.posZ + (double)f2 * d2;
            this.setPosition(d3, p_110145_1_.posY + (double)p_110145_1_.height + 0.001D, d4);
            if (this.world.collidesWithAnyBlock(this.getEntityBoundingBox())) {
                this.setPosition(d3, p_110145_1_.posY + (double)p_110145_1_.height + 1.001D, d4);
                if (this.world.collidesWithAnyBlock(this.getEntityBoundingBox())) {
                    this.setPosition(p_110145_1_.posX, p_110145_1_.posY + (double)this.height + 0.001D, p_110145_1_.posZ);
                }
            }
        }

    }

    @SideOnly(Side.CLIENT)
    public boolean getAlwaysRenderNameTagForRender() {
        return this.getAlwaysRenderNameTag();
    }

    protected float getJumpUpwardsMotion() {
        return 0.42F;
    }

    protected void jump() {
        this.motionY = (double)this.getJumpUpwardsMotion();
        if (this.isPotionActive(MobEffects.JUMP_BOOST)) {
            this.motionY += (double)((float)(this.getActivePotionEffect(MobEffects.JUMP_BOOST).getAmplifier() + 1) * 0.1F);
        }

        if (this.isSprinting()) {
            float f = this.rotationYaw * 0.017453292F;
            this.motionX -= (double)(MathHelper.sin(f) * 0.2F);
            this.motionZ += (double)(MathHelper.cos(f) * 0.2F);
        }

        this.isAirBorne = true;
        ForgeHooks.onLivingJump(this);
    }

    protected void handleJumpWater() {
        this.motionY += 0.03999999910593033D * this.getEntityAttribute(SWIM_SPEED).getAttributeValue();
    }

    protected void handleJumpLava() {
        this.motionY += 0.03999999910593033D * this.getEntityAttribute(SWIM_SPEED).getAttributeValue();
    }

    protected float getWaterSlowDown() {
        return 0.8F;
    }

    public void travel(float p_191986_1_, float p_191986_2_, float p_191986_3_) {
        double d0;
        double d6;
        double d8;
        if (this.isServerWorld() || this.canPassengerSteer()) {
            float f7;
            float f8;
            float f9;
            if (!this.isInWater() || this instanceof EntityPlayer && ((EntityPlayer)this).capabilities.isFlying) {
                if (!this.isInLava() || this instanceof EntityPlayer && ((EntityPlayer)this).capabilities.isFlying) {
                    if (this.isElytraFlying()) {
                        if (this.motionY > -0.5D) {
                            this.fallDistance = 1.0F;
                        }

                        Vec3d vec3d = this.getLookVec();
                        float f = this.rotationPitch * 0.017453292F;
                        d6 = Math.sqrt(vec3d.x * vec3d.x + vec3d.z * vec3d.z);
                        d8 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
                        double d1 = vec3d.lengthVector();
                        float f4 = MathHelper.cos(f);
                        f4 = (float)((double)f4 * (double)f4 * Math.min(1.0D, d1 / 0.4D));
                        this.motionY += -0.08D + (double)f4 * 0.06D;
                        double d11;
                        if (this.motionY < 0.0D && d6 > 0.0D) {
                            d11 = this.motionY * -0.1D * (double)f4;
                            this.motionY += d11;
                            this.motionX += vec3d.x * d11 / d6;
                            this.motionZ += vec3d.z * d11 / d6;
                        }

                        if (f < 0.0F) {
                            d11 = d8 * (double)(-MathHelper.sin(f)) * 0.04D;
                            this.motionY += d11 * 3.2D;
                            this.motionX -= vec3d.x * d11 / d6;
                            this.motionZ -= vec3d.z * d11 / d6;
                        }

                        if (d6 > 0.0D) {
                            this.motionX += (vec3d.x / d6 * d8 - this.motionX) * 0.1D;
                            this.motionZ += (vec3d.z / d6 * d8 - this.motionZ) * 0.1D;
                        }

                        this.motionX *= 0.9900000095367432D;
                        this.motionY *= 0.9800000190734863D;
                        this.motionZ *= 0.9900000095367432D;
                        this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
                        if (this.collidedHorizontally && !this.world.isRemote) {
                            d11 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
                            double d3 = d8 - d11;
                            float f5 = (float)(d3 * 10.0D - 3.0D);
                            if (f5 > 0.0F) {
                                this.playSound(this.getFallSound((int)f5), 1.0F, 1.0F);
                                this.attackEntityFrom(DamageSource.FLY_INTO_WALL, f5);
                            }
                        }

                        if (this.onGround && !this.world.isRemote) {
                            this.setFlag(7, false);
                        }
                    } else {
                        float f6 = 0.91F;
                        PooledMutableBlockPos blockpos$pooledmutableblockpos = PooledMutableBlockPos.retain(this.posX, this.getEntityBoundingBox().minY - 1.0D, this.posZ);
                        if (this.onGround) {
                            IBlockState underState = this.world.getBlockState(blockpos$pooledmutableblockpos);
                            f6 = underState.getBlock().getSlipperiness(underState, this.world, blockpos$pooledmutableblockpos, this) * 0.91F;
                        }

                        f7 = 0.16277136F / (f6 * f6 * f6);
                        if (this.onGround) {
                            f8 = this.getAIMoveSpeed() * f7;
                        } else {
                            f8 = this.jumpMovementFactor;
                        }

                        this.moveRelative(p_191986_1_, p_191986_2_, p_191986_3_, f8);
                        f6 = 0.91F;
                        if (this.onGround) {
                            IBlockState underState = this.world.getBlockState(blockpos$pooledmutableblockpos.setPos(this.posX, this.getEntityBoundingBox().minY - 1.0D, this.posZ));
                            f6 = underState.getBlock().getSlipperiness(underState, this.world, blockpos$pooledmutableblockpos, this) * 0.91F;
                        }

                        if (this.isOnLadder()) {
                            f9 = 0.15F;
                            this.motionX = MathHelper.clamp(this.motionX, -0.15000000596046448D, 0.15000000596046448D);
                            this.motionZ = MathHelper.clamp(this.motionZ, -0.15000000596046448D, 0.15000000596046448D);
                            this.fallDistance = 0.0F;
                            if (this.motionY < -0.15D) {
                                this.motionY = -0.15D;
                            }

                            boolean flag = this.isSneaking() && this instanceof EntityPlayer;
                            if (flag && this.motionY < 0.0D) {
                                this.motionY = 0.0D;
                            }
                        }

                        this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
                        if (this.collidedHorizontally && this.isOnLadder()) {
                            this.motionY = 0.2D;
                        }

                        if (this.isPotionActive(MobEffects.LEVITATION)) {
                            this.motionY += (0.05D * (double)(this.getActivePotionEffect(MobEffects.LEVITATION).getAmplifier() + 1) - this.motionY) * 0.2D;
                        } else {
                            blockpos$pooledmutableblockpos.setPos(this.posX, 0.0D, this.posZ);
                            if (!this.world.isRemote || this.world.isBlockLoaded(blockpos$pooledmutableblockpos) && this.world.getChunkFromBlockCoords(blockpos$pooledmutableblockpos).isLoaded()) {
                                if (!this.hasNoGravity()) {
                                    this.motionY -= 0.08D;
                                }
                            } else if (this.posY > 0.0D) {
                                this.motionY = -0.1D;
                            } else {
                                this.motionY = 0.0D;
                            }
                        }

                        this.motionY *= 0.9800000190734863D;
                        this.motionX *= (double)f6;
                        this.motionZ *= (double)f6;
                        blockpos$pooledmutableblockpos.release();
                    }
                } else {
                    d0 = this.posY;
                    this.moveRelative(p_191986_1_, p_191986_2_, p_191986_3_, 0.02F);
                    this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
                    this.motionX *= 0.5D;
                    this.motionY *= 0.5D;
                    this.motionZ *= 0.5D;
                    if (!this.hasNoGravity()) {
                        this.motionY -= 0.02D;
                    }

                    if (this.collidedHorizontally && this.isOffsetPositionInLiquid(this.motionX, this.motionY + 0.6000000238418579D - this.posY + d0, this.motionZ)) {
                        this.motionY = 0.30000001192092896D;
                    }
                }
            } else {
                d0 = this.posY;
                f7 = this.getWaterSlowDown();
                f8 = 0.02F;
                f9 = (float)EnchantmentHelper.getDepthStriderModifier(this);
                if (f9 > 3.0F) {
                    f9 = 3.0F;
                }

                if (!this.onGround) {
                    f9 *= 0.5F;
                }

                if (f9 > 0.0F) {
                    f7 += (0.54600006F - f7) * f9 / 3.0F;
                    f8 += (this.getAIMoveSpeed() - f8) * f9 / 3.0F;
                }

                this.moveRelative(p_191986_1_, p_191986_2_, p_191986_3_, f8);
                this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
                this.motionX *= (double)f7;
                this.motionY *= 0.800000011920929D;
                this.motionZ *= (double)f7;
                if (!this.hasNoGravity()) {
                    this.motionY -= 0.02D;
                }

                if (this.collidedHorizontally && this.isOffsetPositionInLiquid(this.motionX, this.motionY + 0.6000000238418579D - this.posY + d0, this.motionZ)) {
                    this.motionY = 0.30000001192092896D;
                }
            }
        }

        this.prevLimbSwingAmount = this.limbSwingAmount;
        d0 = this.posX - this.prevPosX;
        d6 = this.posZ - this.prevPosZ;
        d8 = this instanceof EntityFlying ? this.posY - this.prevPosY : 0.0D;
        float f10 = MathHelper.sqrt(d0 * d0 + d8 * d8 + d6 * d6) * 4.0F;
        if (f10 > 1.0F) {
            f10 = 1.0F;
        }

        this.limbSwingAmount += (f10 - this.limbSwingAmount) * 0.4F;
        this.limbSwing += this.limbSwingAmount;
    }

    public float getAIMoveSpeed() {
        return this.landMovementFactor;
    }

    public void setAIMoveSpeed(float p_70659_1_) {
        this.landMovementFactor = p_70659_1_;
    }

    public boolean attackEntityAsMob(Entity p_70652_1_) {
        this.setLastAttackedEntity(p_70652_1_);
        return false;
    }

    public boolean isPlayerSleeping() {
        return false;
    }

    public void onUpdate() {
        if (!ForgeHooks.onLivingUpdate(this)) {
            super.onUpdate();
            this.updateActiveHand();
            if (!this.world.isRemote) {
                int i = this.getArrowCountInEntity();
                if (i > 0) {
                    if (this.arrowHitTimer <= 0) {
                        this.arrowHitTimer = 20 * (30 - i);
                    }

                    --this.arrowHitTimer;
                    if (this.arrowHitTimer <= 0) {
                        this.setArrowCountInEntity(i - 1);
                    }
                }

                EntityEquipmentSlot[] var2 = EntityEquipmentSlot.values();
                int var3 = var2.length;

                for(int var4 = 0; var4 < var3; ++var4) {
                    EntityEquipmentSlot entityequipmentslot = var2[var4];
                    ItemStack itemstack;
                    switch(entityequipmentslot.getSlotType()) {
                    case HAND:
                        itemstack = (ItemStack)this.handInventory.get(entityequipmentslot.getIndex());
                        break;
                    case ARMOR:
                        itemstack = (ItemStack)this.armorArray.get(entityequipmentslot.getIndex());
                        break;
                    default:
                        continue;
                    }

                    ItemStack itemstack1 = this.getItemStackFromSlot(entityequipmentslot);
                    if (!ItemStack.areItemStacksEqual(itemstack1, itemstack)) {
                        if (!ItemStack.areItemStacksEqualUsingNBTShareTag(itemstack1, itemstack)) {
                            ((WorldServer)this.world).getEntityTracker().sendToTracking(this, new SPacketEntityEquipment(this.getEntityId(), entityequipmentslot, itemstack1));
                        }

                        MinecraftForge.EVENT_BUS.post(new LivingEquipmentChangeEvent(this, entityequipmentslot, itemstack, itemstack1));
                        if (!itemstack.isEmpty()) {
                            this.getAttributeMap().removeAttributeModifiers(itemstack.getAttributeModifiers(entityequipmentslot));
                        }

                        if (!itemstack1.isEmpty()) {
                            this.getAttributeMap().applyAttributeModifiers(itemstack1.getAttributeModifiers(entityequipmentslot));
                        }

                        switch(entityequipmentslot.getSlotType()) {
                        case HAND:
                            this.handInventory.set(entityequipmentslot.getIndex(), itemstack1.isEmpty() ? ItemStack.EMPTY : itemstack1.copy());
                            break;
                        case ARMOR:
                            this.armorArray.set(entityequipmentslot.getIndex(), itemstack1.isEmpty() ? ItemStack.EMPTY : itemstack1.copy());
                        }
                    }
                }

                if (this.ticksExisted % 20 == 0) {
                    this.getCombatTracker().reset();
                }

                if (!this.glowing) {
                    boolean flag = this.isPotionActive(MobEffects.GLOWING);
                    if (this.getFlag(6) != flag) {
                        this.setFlag(6, flag);
                    }
                }
            }

            this.onLivingUpdate();
            double d0 = this.posX - this.prevPosX;
            double d1 = this.posZ - this.prevPosZ;
            float f3 = (float)(d0 * d0 + d1 * d1);
            float f4 = this.renderYawOffset;
            float f5 = 0.0F;
            this.prevOnGroundSpeedFactor = this.onGroundSpeedFactor;
            float f = 0.0F;
            if (f3 > 0.0025000002F) {
                f = 1.0F;
                f5 = (float)Math.sqrt((double)f3) * 3.0F;
                float f1 = (float)MathHelper.atan2(d1, d0) * 57.295776F - 90.0F;
                float f2 = MathHelper.abs(MathHelper.wrapDegrees(this.rotationYaw) - f1);
                if (95.0F < f2 && f2 < 265.0F) {
                    f4 = f1 - 180.0F;
                } else {
                    f4 = f1;
                }
            }

            if (this.swingProgress > 0.0F) {
                f4 = this.rotationYaw;
            }

            if (!this.onGround) {
                f = 0.0F;
            }

            this.onGroundSpeedFactor += (f - this.onGroundSpeedFactor) * 0.3F;
            this.world.profiler.startSection("headTurn");
            f5 = this.updateDistance(f4, f5);
            this.world.profiler.endSection();
            this.world.profiler.startSection("rangeChecks");

            while(this.rotationYaw - this.prevRotationYaw < -180.0F) {
                this.prevRotationYaw -= 360.0F;
            }

            while(this.rotationYaw - this.prevRotationYaw >= 180.0F) {
                this.prevRotationYaw += 360.0F;
            }

            while(this.renderYawOffset - this.prevRenderYawOffset < -180.0F) {
                this.prevRenderYawOffset -= 360.0F;
            }

            while(this.renderYawOffset - this.prevRenderYawOffset >= 180.0F) {
                this.prevRenderYawOffset += 360.0F;
            }

            while(this.rotationPitch - this.prevRotationPitch < -180.0F) {
                this.prevRotationPitch -= 360.0F;
            }

            while(this.rotationPitch - this.prevRotationPitch >= 180.0F) {
                this.prevRotationPitch += 360.0F;
            }

            while(this.rotationYawHead - this.prevRotationYawHead < -180.0F) {
                this.prevRotationYawHead -= 360.0F;
            }

            while(this.rotationYawHead - this.prevRotationYawHead >= 180.0F) {
                this.prevRotationYawHead += 360.0F;
            }

            this.world.profiler.endSection();
            this.movedDistance += f5;
            if (this.isElytraFlying()) {
                ++this.ticksElytraFlying;
            } else {
                this.ticksElytraFlying = 0;
            }

        }
    }

    protected float updateDistance(float p_110146_1_, float p_110146_2_) {
        float f = MathHelper.wrapDegrees(p_110146_1_ - this.renderYawOffset);
        this.renderYawOffset += f * 0.3F;
        float f1 = MathHelper.wrapDegrees(this.rotationYaw - this.renderYawOffset);
        boolean flag = f1 < -90.0F || f1 >= 90.0F;
        if (f1 < -75.0F) {
            f1 = -75.0F;
        }

        if (f1 >= 75.0F) {
            f1 = 75.0F;
        }

        this.renderYawOffset = this.rotationYaw - f1;
        if (f1 * f1 > 2500.0F) {
            this.renderYawOffset += f1 * 0.2F;
        }

        if (flag) {
            p_110146_2_ *= -1.0F;
        }

        return p_110146_2_;
    }

    public void onLivingUpdate() {
        if (this.jumpTicks > 0) {
            --this.jumpTicks;
        }

        if (this.newPosRotationIncrements > 0 && !this.canPassengerSteer()) {
            double d0 = this.posX + (this.interpTargetX - this.posX) / (double)this.newPosRotationIncrements;
            double d1 = this.posY + (this.interpTargetY - this.posY) / (double)this.newPosRotationIncrements;
            double d2 = this.posZ + (this.interpTargetZ - this.posZ) / (double)this.newPosRotationIncrements;
            double d3 = MathHelper.wrapDegrees(this.interpTargetYaw - (double)this.rotationYaw);
            this.rotationYaw = (float)((double)this.rotationYaw + d3 / (double)this.newPosRotationIncrements);
            this.rotationPitch = (float)((double)this.rotationPitch + (this.interpTargetPitch - (double)this.rotationPitch) / (double)this.newPosRotationIncrements);
            --this.newPosRotationIncrements;
            this.setPosition(d0, d1, d2);
            this.setRotation(this.rotationYaw, this.rotationPitch);
        } else if (!this.isServerWorld()) {
            this.motionX *= 0.98D;
            this.motionY *= 0.98D;
            this.motionZ *= 0.98D;
        }

        if (Math.abs(this.motionX) < 0.003D) {
            this.motionX = 0.0D;
        }

        if (Math.abs(this.motionY) < 0.003D) {
            this.motionY = 0.0D;
        }

        if (Math.abs(this.motionZ) < 0.003D) {
            this.motionZ = 0.0D;
        }

        this.world.profiler.startSection("ai");
        if (this.isMovementBlocked()) {
            this.isJumping = false;
            this.moveStrafing = 0.0F;
            this.moveForward = 0.0F;
            this.randomYawVelocity = 0.0F;
        } else if (this.isServerWorld()) {
            this.world.profiler.startSection("newAi");
            this.updateEntityActionState();
            this.world.profiler.endSection();
        }

        this.world.profiler.endSection();
        this.world.profiler.startSection("jump");
        if (this.isJumping) {
            if (this.isInWater()) {
                this.handleJumpWater();
            } else if (this.isInLava()) {
                this.handleJumpLava();
            } else if (this.onGround && this.jumpTicks == 0) {
                this.jump();
                this.jumpTicks = 10;
            }
        } else {
            this.jumpTicks = 0;
        }

        this.world.profiler.endSection();
        this.world.profiler.startSection("travel");
        this.moveStrafing *= 0.98F;
        this.moveForward *= 0.98F;
        this.randomYawVelocity *= 0.9F;
        this.updateElytra();
        this.travel(this.moveStrafing, this.moveVertical, this.moveForward);
        this.world.profiler.endSection();
        this.world.profiler.startSection("push");
        this.collideWithNearbyEntities();
        this.world.profiler.endSection();
    }

    private void updateElytra() {
        boolean flag = this.getFlag(7);
        if (flag && !this.onGround && !this.isRiding()) {
            ItemStack itemstack = this.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
            if (itemstack.getItem() == Items.ELYTRA && ItemElytra.isUsable(itemstack)) {
                flag = true;
                if (!this.world.isRemote && (this.ticksElytraFlying + 1) % 20 == 0) {
                    itemstack.damageItem(1, this);
                }
            } else {
                flag = false;
            }
        } else {
            flag = false;
        }

        if (!this.world.isRemote) {
            this.setFlag(7, flag);
        }

    }

    protected void updateEntityActionState() {
    }

    protected void collideWithNearbyEntities() {
        List<Entity> list = this.world.getEntitiesInAABBexcluding(this, this.getEntityBoundingBox(), EntitySelectors.getTeamCollisionPredicate(this));
        if (!list.isEmpty()) {
            int i = this.world.getGameRules().getInt("maxEntityCramming");
            int j;
            if (i > 0 && list.size() > i - 1 && this.rand.nextInt(4) == 0) {
                j = 0;

                for(int k = 0; k < list.size(); ++k) {
                    if (!((Entity)list.get(k)).isRiding()) {
                        ++j;
                    }
                }

                if (j > i - 1) {
                    this.attackEntityFrom(DamageSource.CRAMMING, 6.0F);
                }
            }

            for(j = 0; j < list.size(); ++j) {
                Entity entity = (Entity)list.get(j);
                this.collideWithEntity(entity);
            }
        }

    }

    protected void collideWithEntity(Entity p_82167_1_) {
        p_82167_1_.applyEntityCollision(this);
    }

    public void dismountRidingEntity() {
        Entity entity = this.getRidingEntity();
        super.dismountRidingEntity();
        if (entity != null && entity != this.getRidingEntity() && !this.world.isRemote) {
            this.dismountEntity(entity);
        }

    }

    public void updateRidden() {
        super.updateRidden();
        this.prevOnGroundSpeedFactor = this.onGroundSpeedFactor;
        this.onGroundSpeedFactor = 0.0F;
        this.fallDistance = 0.0F;
    }

    @SideOnly(Side.CLIENT)
    public void setPositionAndRotationDirect(double p_180426_1_, double p_180426_3_, double p_180426_5_, float p_180426_7_, float p_180426_8_, int p_180426_9_, boolean p_180426_10_) {
        this.interpTargetX = p_180426_1_;
        this.interpTargetY = p_180426_3_;
        this.interpTargetZ = p_180426_5_;
        this.interpTargetYaw = (double)p_180426_7_;
        this.interpTargetPitch = (double)p_180426_8_;
        this.newPosRotationIncrements = p_180426_9_;
    }

    public void setJumping(boolean p_70637_1_) {
        this.isJumping = p_70637_1_;
    }

    public void onItemPickup(Entity p_71001_1_, int p_71001_2_) {
        if (!p_71001_1_.isDead && !this.world.isRemote) {
            EntityTracker entitytracker = ((WorldServer)this.world).getEntityTracker();
            if (p_71001_1_ instanceof EntityItem || p_71001_1_ instanceof EntityArrow || p_71001_1_ instanceof EntityXPOrb) {
                entitytracker.sendToTracking(p_71001_1_, new SPacketCollectItem(p_71001_1_.getEntityId(), this.getEntityId(), p_71001_2_));
            }
        }

    }

    public boolean canEntityBeSeen(Entity p_70685_1_) {
        return this.world.rayTraceBlocks(new Vec3d(this.posX, this.posY + (double)this.getEyeHeight(), this.posZ), new Vec3d(p_70685_1_.posX, p_70685_1_.posY + (double)p_70685_1_.getEyeHeight(), p_70685_1_.posZ), false, true, false) == null;
    }

    public Vec3d getLook(float p_70676_1_) {
        if (p_70676_1_ == 1.0F) {
            return this.getVectorForRotation(this.rotationPitch, this.rotationYawHead);
        } else {
            float f = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * p_70676_1_;
            float f1 = this.prevRotationYawHead + (this.rotationYawHead - this.prevRotationYawHead) * p_70676_1_;
            return this.getVectorForRotation(f, f1);
        }
    }

    @SideOnly(Side.CLIENT)
    public float getSwingProgress(float p_70678_1_) {
        float f = this.swingProgress - this.prevSwingProgress;
        if (f < 0.0F) {
            ++f;
        }

        return this.prevSwingProgress + f * p_70678_1_;
    }

    public boolean isServerWorld() {
        return !this.world.isRemote;
    }

    public boolean canBeCollidedWith() {
        return !this.isDead;
    }

    public boolean canBePushed() {
        return this.isEntityAlive() && !this.isOnLadder();
    }

    protected void markVelocityChanged() {
        this.velocityChanged = this.rand.nextDouble() >= this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).getAttributeValue();
    }

    public float getRotationYawHead() {
        return this.rotationYawHead;
    }

    public void setRotationYawHead(float p_70034_1_) {
        this.rotationYawHead = p_70034_1_;
    }

    public void setRenderYawOffset(float p_181013_1_) {
        this.renderYawOffset = p_181013_1_;
    }

    public float getAbsorptionAmount() {
        return this.absorptionAmount;
    }

    public void setAbsorptionAmount(float p_110149_1_) {
        if (p_110149_1_ < 0.0F) {
            p_110149_1_ = 0.0F;
        }

        this.absorptionAmount = p_110149_1_;
    }

    public void sendEnterCombat() {
    }

    public void sendEndCombat() {
    }

    protected void markPotionsDirty() {
        this.potionsNeedUpdate = true;
    }

    public void curePotionEffects(ItemStack p_curePotionEffects_1_) {
        if (!this.world.isRemote) {
            Iterator iterator = this.activePotionsMap.values().iterator();

            while(iterator.hasNext()) {
                PotionEffect effect = (PotionEffect)iterator.next();
                if (effect.isCurativeItem(p_curePotionEffects_1_) && !MinecraftForge.EVENT_BUS.post(new PotionRemoveEvent(this, effect))) {
                    this.onFinishedPotionEffect(effect);
                    iterator.remove();
                    this.potionsNeedUpdate = true;
                }
            }

        }
    }

    public boolean shouldRiderFaceForward(EntityPlayer p_shouldRiderFaceForward_1_) {
        return this instanceof EntityPig;
    }

    public abstract EnumHandSide getPrimaryHand();

    public boolean isHandActive() {
        return ((Byte)this.dataManager.get(HAND_STATES) & 1) > 0;
    }

    public EnumHand getActiveHand() {
        return ((Byte)this.dataManager.get(HAND_STATES) & 2) > 0 ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND;
    }

    protected void updateActiveHand() {
        if (this.isHandActive()) {
            ItemStack itemstack = this.getHeldItem(this.getActiveHand());
            if (ForgeHooks.canContinueUsing(this.activeItemStack, itemstack)) {
                this.activeItemStack = itemstack;
            }

            if (itemstack == this.activeItemStack) {
                if (!this.activeItemStack.isEmpty()) {
                    this.activeItemStackUseCount = ForgeEventFactory.onItemUseTick(this, this.activeItemStack, this.activeItemStackUseCount);
                    if (this.activeItemStackUseCount > 0) {
                        this.activeItemStack.getItem().onUsingTick(this.activeItemStack, this, this.activeItemStackUseCount);
                    }
                }

                if (this.getItemInUseCount() <= 25 && this.getItemInUseCount() % 4 == 0) {
                    this.updateItemUse(this.activeItemStack, 5);
                }

                if (--this.activeItemStackUseCount <= 0 && !this.world.isRemote) {
                    this.onItemUseFinish();
                }
            } else {
                this.resetActiveHand();
            }
        }

    }

    public void setActiveHand(EnumHand p_184598_1_) {
        ItemStack itemstack = this.getHeldItem(p_184598_1_);
        if (!itemstack.isEmpty() && !this.isHandActive()) {
            int duration = ForgeEventFactory.onItemUseStart(this, itemstack, itemstack.getMaxItemUseDuration());
            if (duration <= 0) {
                return;
            }

            this.activeItemStack = itemstack;
            this.activeItemStackUseCount = duration;
            if (!this.world.isRemote) {
                int i = 1;
                if (p_184598_1_ == EnumHand.OFF_HAND) {
                    i |= 2;
                }

                this.dataManager.set(HAND_STATES, (byte)i);
            }
        }

    }

    public void notifyDataManagerChange(DataParameter<?> p_184206_1_) {
        super.notifyDataManagerChange(p_184206_1_);
        if (HAND_STATES.equals(p_184206_1_) && this.world.isRemote) {
            if (this.isHandActive() && this.activeItemStack.isEmpty()) {
                this.activeItemStack = this.getHeldItem(this.getActiveHand());
                if (!this.activeItemStack.isEmpty()) {
                    this.activeItemStackUseCount = this.activeItemStack.getMaxItemUseDuration();
                }
            } else if (!this.isHandActive() && !this.activeItemStack.isEmpty()) {
                this.activeItemStack = ItemStack.EMPTY;
                this.activeItemStackUseCount = 0;
            }
        }

    }

    protected void updateItemUse(ItemStack p_184584_1_, int p_184584_2_) {
        if (!p_184584_1_.isEmpty() && this.isHandActive()) {
            if (p_184584_1_.getItemUseAction() == EnumAction.DRINK) {
                this.playSound(SoundEvents.ENTITY_GENERIC_DRINK, 0.5F, this.world.rand.nextFloat() * 0.1F + 0.9F);
            }

            if (p_184584_1_.getItemUseAction() == EnumAction.EAT) {
                for(int i = 0; i < p_184584_2_; ++i) {
                    Vec3d vec3d = new Vec3d(((double)this.rand.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);
                    vec3d = vec3d.rotatePitch(-this.rotationPitch * 0.017453292F);
                    vec3d = vec3d.rotateYaw(-this.rotationYaw * 0.017453292F);
                    double d0 = (double)(-this.rand.nextFloat()) * 0.6D - 0.3D;
                    Vec3d vec3d1 = new Vec3d(((double)this.rand.nextFloat() - 0.5D) * 0.3D, d0, 0.6D);
                    vec3d1 = vec3d1.rotatePitch(-this.rotationPitch * 0.017453292F);
                    vec3d1 = vec3d1.rotateYaw(-this.rotationYaw * 0.017453292F);
                    vec3d1 = vec3d1.addVector(this.posX, this.posY + (double)this.getEyeHeight(), this.posZ);
                    if (p_184584_1_.getHasSubtypes()) {
                        this.world.spawnParticle(EnumParticleTypes.ITEM_CRACK, vec3d1.x, vec3d1.y, vec3d1.z, vec3d.x, vec3d.y + 0.05D, vec3d.z, new int[]{Item.getIdFromItem(p_184584_1_.getItem()), p_184584_1_.getMetadata()});
                    } else {
                        this.world.spawnParticle(EnumParticleTypes.ITEM_CRACK, vec3d1.x, vec3d1.y, vec3d1.z, vec3d.x, vec3d.y + 0.05D, vec3d.z, new int[]{Item.getIdFromItem(p_184584_1_.getItem())});
                    }
                }

                this.playSound(SoundEvents.ENTITY_GENERIC_EAT, 0.5F + 0.5F * (float)this.rand.nextInt(2), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
            }
        }

    }

    protected void onItemUseFinish() {
        if (!this.activeItemStack.isEmpty() && this.isHandActive()) {
            this.updateItemUse(this.activeItemStack, 16);
            ItemStack activeItemStackCopy = this.activeItemStack.copy();
            ItemStack itemstack = this.activeItemStack.onItemUseFinish(this.world, this);
            itemstack = ForgeEventFactory.onItemUseFinish(this, activeItemStackCopy, this.getItemInUseCount(), itemstack);
            this.setHeldItem(this.getActiveHand(), itemstack);
            this.resetActiveHand();
        }

    }

    public ItemStack getActiveItemStack() {
        return this.activeItemStack;
    }

    public int getItemInUseCount() {
        return this.activeItemStackUseCount;
    }

    public int getItemInUseMaxCount() {
        return this.isHandActive() ? this.activeItemStack.getMaxItemUseDuration() - this.getItemInUseCount() : 0;
    }

    public void stopActiveHand() {
        if (!this.activeItemStack.isEmpty() && !ForgeEventFactory.onUseItemStop(this, this.activeItemStack, this.getItemInUseCount())) {
            this.activeItemStack.onPlayerStoppedUsing(this.world, this, this.getItemInUseCount());
        }

        this.resetActiveHand();
    }

    public void resetActiveHand() {
        if (!this.world.isRemote) {
            this.dataManager.set(HAND_STATES, (byte)0);
        }

        this.activeItemStack = ItemStack.EMPTY;
        this.activeItemStackUseCount = 0;
    }

    public boolean isActiveItemStackBlocking() {
        if (this.isHandActive() && !this.activeItemStack.isEmpty()) {
            Item item = this.activeItemStack.getItem();
            if (item.getItemUseAction(this.activeItemStack) != EnumAction.BLOCK) {
                return false;
            } else {
                return item.getMaxItemUseDuration(this.activeItemStack) - this.activeItemStackUseCount >= 5;
            }
        } else {
            return false;
        }
    }

    public boolean isElytraFlying() {
        return this.getFlag(7);
    }

    @SideOnly(Side.CLIENT)
    public int getTicksElytraFlying() {
        return this.ticksElytraFlying;
    }

    public boolean attemptTeleport(double p_184595_1_, double p_184595_3_, double p_184595_5_) {
        double d0 = this.posX;
        double d1 = this.posY;
        double d2 = this.posZ;
        this.posX = p_184595_1_;
        this.posY = p_184595_3_;
        this.posZ = p_184595_5_;
        boolean flag = false;
        BlockPos blockpos = new BlockPos(this);
        World world = this.world;
        Random random = this.getRNG();
        boolean flag1;
        if (world.isBlockLoaded(blockpos)) {
            flag1 = false;

            while(!flag1 && blockpos.getY() > 0) {
                BlockPos blockpos1 = blockpos.down();
                IBlockState iblockstate = world.getBlockState(blockpos1);
                if (iblockstate.getMaterial().blocksMovement()) {
                    flag1 = true;
                } else {
                    --this.posY;
                    blockpos = blockpos1;
                }
            }

            if (flag1) {
                this.setPositionAndUpdate(this.posX, this.posY, this.posZ);
                if (world.getCollisionBoxes(this, this.getEntityBoundingBox()).isEmpty() && !world.containsAnyLiquid(this.getEntityBoundingBox())) {
                    flag = true;
                }
            }
        }

        if (!flag) {
            this.setPositionAndUpdate(d0, d1, d2);
            return false;
        } else {
            flag1 = true;

            for(int j = 0; j < 128; ++j) {
                double d6 = (double)j / 127.0D;
                float f = (random.nextFloat() - 0.5F) * 0.2F;
                float f1 = (random.nextFloat() - 0.5F) * 0.2F;
                float f2 = (random.nextFloat() - 0.5F) * 0.2F;
                double d3 = d0 + (this.posX - d0) * d6 + (random.nextDouble() - 0.5D) * (double)this.width * 2.0D;
                double d4 = d1 + (this.posY - d1) * d6 + random.nextDouble() * (double)this.height;
                double d5 = d2 + (this.posZ - d2) * d6 + (random.nextDouble() - 0.5D) * (double)this.width * 2.0D;
                world.spawnParticle(EnumParticleTypes.PORTAL, d3, d4, d5, (double)f, (double)f1, (double)f2, new int[0]);
            }

            if (this instanceof EntityCreature) {
                ((EntityCreature)this).getNavigator().clearPath();
            }

            return true;
        }
    }

    public boolean canBeHitWithPotion() {
        return true;
    }

    @Nullable
    public <T> T getCapability(Capability<T> p_getCapability_1_, @Nullable EnumFacing p_getCapability_2_) {
        if (p_getCapability_1_ == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (p_getCapability_2_ == null) {
                return (T) this.joinedHandler;
            }

            if (p_getCapability_2_.getAxis().isVertical()) {
                return (T) this.handHandler;
            }

            if (p_getCapability_2_.getAxis().isHorizontal()) {
                return (T) this.armorHandler;
            }
        }

        return super.getCapability(p_getCapability_1_, p_getCapability_2_);
    }

    public boolean hasCapability(Capability<?> p_hasCapability_1_, @Nullable EnumFacing p_hasCapability_2_) {
        return p_hasCapability_1_ == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(p_hasCapability_1_, p_hasCapability_2_);
    }

    public boolean attackable() {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public void setPartying(BlockPos p_191987_1_, boolean p_191987_2_) {
    }

    public void moveRelative(float p_191958_1_, float p_191958_2_, float p_191958_3_, float p_191958_4_) {
        float f = p_191958_1_ * p_191958_1_ + p_191958_2_ * p_191958_2_ + p_191958_3_ * p_191958_3_;
        if (f >= 1.0E-4F) {
            f = MathHelper.sqrt(f);
            if (f < 1.0F) {
                f = 1.0F;
            }

            f = p_191958_4_ / f;
            p_191958_1_ *= f;
            p_191958_2_ *= f;
            p_191958_3_ *= f;
            if (this.isInWater() || this.isInLava()) {
                p_191958_1_ *= (float)this.getEntityAttribute(SWIM_SPEED).getAttributeValue();
                p_191958_2_ *= (float)this.getEntityAttribute(SWIM_SPEED).getAttributeValue();
                p_191958_3_ *= (float)this.getEntityAttribute(SWIM_SPEED).getAttributeValue();
            }

            float f1 = MathHelper.sin(this.rotationYaw * 0.017453292F);
            float f2 = MathHelper.cos(this.rotationYaw * 0.017453292F);
            this.motionX += (double)(p_191958_1_ * f2 - p_191958_3_ * f1);
            this.motionY += (double)p_191958_2_;
            this.motionZ += (double)(p_191958_3_ * f2 + p_191958_1_ * f1);
        }

    }

    static {
        SPRINTING_SPEED_BOOST = (new AttributeModifier(SPRINTING_SPEED_BOOST_ID, "Sprinting speed boost", 0.30000001192092896D, 2)).setSaved(false);
        SWIM_SPEED = (new RangedAttribute((IAttribute)null, "forge.swimSpeed", 1.0D, 0.0D, 1024.0D)).setShouldWatch(true);
        HAND_STATES = EntityDataManager.createKey(EntityLivingBase.class, DataSerializers.BYTE);
        HEALTH = EntityDataManager.createKey(EntityLivingBase.class, DataSerializers.FLOAT);
        POTION_EFFECTS = EntityDataManager.createKey(EntityLivingBase.class, DataSerializers.VARINT);
        HIDE_PARTICLES = EntityDataManager.createKey(EntityLivingBase.class, DataSerializers.BOOLEAN);
        ARROW_COUNT_IN_ENTITY = EntityDataManager.createKey(EntityLivingBase.class, DataSerializers.VARINT);
    }
}
