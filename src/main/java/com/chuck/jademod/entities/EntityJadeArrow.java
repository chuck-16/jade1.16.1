package com.chuck.jademod.entities;

import com.chuck.jademod.util.RegisteryHandler;
import com.chuck.jademod.util.StartupCommon;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nonnull;


public class EntityJadeArrow extends AbstractArrowEntity {
    public EntityJadeArrow(World worldIn, LivingEntity shooter) {
        super(StartupCommon.jadeArrow, shooter, worldIn);
    }

    public EntityJadeArrow(EntityType<? extends EntityJadeArrow> entityJadeArrowEntityType, World world) {
        super(entityJadeArrowEntityType, world);
    }

    protected void arrowHit(LivingEntity living) {
        super.arrowHit(living);
        EffectInstance effectinstance = new EffectInstance(Effects.INSTANT_DAMAGE, 1, 1);
        living.addPotionEffect(effectinstance);

    }

    protected ItemStack getArrowStack() { return new ItemStack(RegisteryHandler.JADE_ARROW.get());}

    @Nonnull
    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    protected Item getDefaultItem() {
        return RegisteryHandler.JADE_ARROW.get();
    }

    public void tick() {
        super.tick();
        if (this.world.isRemote && !this.inGround) {
            this.world.addParticle(ParticleTypes.HAPPY_VILLAGER, this.getPosX(), this.getPosY(), this.getPosZ(), 0.0D, 0.0D, 0.0D);
        }

    }
}
