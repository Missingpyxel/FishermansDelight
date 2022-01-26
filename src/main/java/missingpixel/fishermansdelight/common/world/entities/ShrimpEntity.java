package missingpixel.fishermansdelight.common.world.entities;

import missingpixel.fishermansdelight.FishermansDelight;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.entity.animal.Cod;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;

public class ShrimpEntity extends AbstractFish {
    public ShrimpEntity(EntityType<? extends AbstractFish> entityType, Level levelIn) {
        super(entityType, levelIn);
        this.setNoGravity(false);
    }

    private BlockPos jukebox;
    private boolean isPartying;
    private int musicTimer = 6;

    public ItemStack getBucketItemStack() {
        return new ItemStack(Items.COD_BUCKET);
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.COD_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.COD_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource p_28281_) {
        return SoundEvents.COD_HURT;
    }

    protected SoundEvent getFlopSound() {
        return SoundEvents.COD_FLOP;
    }

    public void setRecordPlayingNearby(BlockPos p_29395_, boolean p_29396_) {
        FishermansDelight.LOGGER.log(org.apache.logging.log4j.Level.INFO, "AMOGUS");
        this.jukebox = p_29395_;
        this.isPartying = p_29396_;
    }

    @Override
    public void aiStep() {
        if (this.jukebox == null || !this.jukebox.closerThan(this.position(), 5.5D) || !this.level.getBlockState(this.jukebox).is(Blocks.JUKEBOX)) {
            this.isPartying = false;
            this.jukebox = null;
        }

        super.aiStep();
    }

    @Override
    public void tick() {
        Vec3 og = this.getDeltaMovement();
        this.setDeltaMovement(og.x,og.y-0.02f, og.z);

        if(this.partyShrimp() && musicTimer == 0) {
            this.getLevel().addParticle(ParticleTypes.NOTE, this.getX(), this.getY() + 1.25f, this.getZ(), 0, 0.1f, 0);
        }

        this.musicTimer =  Math.floorMod(musicTimer - 1, 6);
        super.tick();
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Player.class, 8.0F, 1.6D, 1.4D, EntitySelector.NO_SPECTATORS::test));
        this.goalSelector.addGoal(4, new ShrimpEntity.FishSwimGoal(this));
    }

    static class FishSwimGoal extends RandomSwimmingGoal {
        private final ShrimpEntity fish;

        public FishSwimGoal(ShrimpEntity fishIn) {
            super(fishIn, 1.0D, 40);
            this.fish = fishIn;
        }

        public boolean canUse() {
            return this.fish.canRandomSwim() && super.canUse();
        }
    }

    public static AttributeSupplier.Builder prepareAttributes() {
        return LivingEntity.createLivingAttributes()
                .add(Attributes.MOVEMENT_SPEED, 2f)
                .add(Attributes.FOLLOW_RANGE, 15D)
                .add(Attributes.MAX_HEALTH,3D);
    }

    public boolean partyShrimp() {return this.isPartying;}
}
