package com.example.hungermod.entity;

import com.example.hungermod.entity.goal.JustinMeleeAttackGoal;
import net.minecraft.client.renderer.debug.BeeDebugRenderer;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import javax.sound.midi.Track;
import java.util.EnumSet;

/**
 * The properties and constructor for the tracker jacker.
 *
 * @author Justin and Jackson Pohlmann
 */
public class TrackerJackerEntity extends AnimalEntity {
    private float rollAmount;
    private float rollAmountO;
    private static final DataParameter<Byte> DATA_FLAGS_ID = EntityDataManager.createKey(TrackerJackerEntity.class, DataSerializers.BYTE);

    /**
     * Constructor for tracker jacker
     *
     * @param entityType
     * @param world
     */
    public TrackerJackerEntity(final EntityType<? extends TrackerJackerEntity> entityType, final World world) {
        super(entityType, world);
    }
    @Override
    /**
     * This is where we set the attributes for the tracker jacker (speed, health, etc).
     */
    protected void registerAttributes() {
        super.registerAttributes();
        final double baseSpeed = this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getBaseValue();
        final double baseHealth = this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue();
        // Multiply base health and base speed by one and a half
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(baseSpeed * 1.5D);
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(baseHealth * 1.5D);
    }
    public BeeEntity createChild(AgeableEntity ageable) {
        return EntityType.BEE.create(this.world);
    }
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new TrackerJackerEntity.StingGoal(this, (double)1.4F, true));
        this.goalSelector.addGoal(1, new TrackerJackerEntity.WanderGoal());
        this.targetSelector.addGoal(2, new TrackerJackerEntity.AttackPlayerGoal(this));
    }
    public float getBodyPitch(float p_226455_1_) {
        return MathHelper.lerp(p_226455_1_, this.rollAmountO, this.rollAmount);
    }

    public boolean attackEntityAsMob(Entity entityIn) {
        float damage = 1;
        DamageSource source = DamageSource.func_226252_a_(this);
        boolean flag = entityIn.attackEntityFrom(
            source,
            damage
        );
        if (flag) {
            this.applyEnchantments(this, entityIn);
            if (entityIn instanceof LivingEntity) {
                ((LivingEntity)entityIn).setBeeStingCount(((LivingEntity)entityIn).getBeeStingCount() + 1);
                int i = 0;
                if (this.world.getDifficulty() == Difficulty.NORMAL) {
                    i = 10;
                } else if (this.world.getDifficulty() == Difficulty.HARD) {
                    i = 18;
                }

                if (i > 0) {
                    ((LivingEntity)entityIn).addPotionEffect(new EffectInstance(Effects.POISON, i * 20, 0));
                }
            }

            this.setAttackTarget((LivingEntity)null);
            this.playSound(SoundEvents.ENTITY_BEE_STING, 1.0F, 1.0F);
        }

        return flag;
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void tick() {
        super.tick();

        this.updateBodyPitch();
    }
    public boolean isAngry()
    {
        return true;
    }

    public boolean hasStung() {
        return false;
    }
    private void updateBodyPitch() {
        this.rollAmountO = this.rollAmount;
        this.rollAmount = Math.max(0.0F, this.rollAmount - 0.24F);

    }

    static class AttackPlayerGoal extends NearestAttackableTargetGoal<PlayerEntity> {
        AttackPlayerGoal(TrackerJackerEntity beeIn) {
            super(beeIn, PlayerEntity.class, true);
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean shouldExecute() {
            return this.canSting() && super.shouldExecute();
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean shouldContinueExecuting() {
            boolean flag = this.canSting();
            if (flag && this.goalOwner.getAttackTarget() != null) {
                return super.shouldContinueExecuting();
            } else {
                this.target = null;
                return false;
            }
        }

        private boolean canSting() {
            return true;
        }
    }

    class StingGoal extends JustinMeleeAttackGoal {
        StingGoal(CreatureEntity creatureIn, double speedIn, boolean useLongMemory) {
            super(creatureIn, speedIn, useLongMemory);
        }

        /**
         * Always be stinging!
         */
        public boolean shouldExecute() {
            return TrackerJackerEntity.this.getAttackTarget() != null;
        }

        /**
         * Always be stinging!
         */
        public boolean shouldContinueExecuting() {
            return TrackerJackerEntity.this.getAttackTarget() != null;
        }
    }

    class WanderGoal extends Goal {
        WanderGoal() {
            this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean shouldExecute() {
            return TrackerJackerEntity.this.navigator.noPath() && TrackerJackerEntity.this.rand.nextInt(10) == 0;
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean shouldContinueExecuting() {
            return TrackerJackerEntity.this.navigator.func_226337_n_();
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting() {
            Vec3d vec3d = this.getRandomLocation();
            if (vec3d != null) {
                TrackerJackerEntity.this.navigator.setPath(TrackerJackerEntity.this.navigator.getPathToPos(new BlockPos(vec3d), 1), 1.0D);
            }

        }

        @Nullable
        private Vec3d getRandomLocation() {
            Vec3d vec3d;
            vec3d = TrackerJackerEntity.this.getLook(0.0F);

            int i = 8;
            Vec3d vec3d2 = RandomPositionGenerator.findAirTarget(TrackerJackerEntity.this, 8, 7, vec3d, ((float)Math.PI / 2F), 2, 1);
            return vec3d2 != null ? vec3d2 : RandomPositionGenerator.findGroundTarget(TrackerJackerEntity.this, 8, 4, -2, vec3d, (double)((float)Math.PI / 2F));
        }
    }
}
