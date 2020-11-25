package com.chuck.jademod.effects;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.math.AxisAlignedBB;

import java.util.List;

public class ModEffect extends Effect {
    private List<Entity> CloseEntities;
    private PlayerEntity player;
    private LivingEntity target;

    public ModEffect(EffectType typeIn, int liquidColorIn) {
        super(typeIn, liquidColorIn);
    }

    public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) {
        if (this == ModEffects.VISION){
            if (entityLivingBaseIn == Minecraft.getInstance().player){
                player = (PlayerEntity) entityLivingBaseIn;
                player.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 2));
                CloseEntities = entityLivingBaseIn.world.getEntitiesInAABBexcluding(entityLivingBaseIn, new AxisAlignedBB(player.getPosX()-20, player.getPosY(), player.getPosZ()-20, player.getPosX()+20, player.getPosY()+50, player.getPosZ()+20), EntityPredicates.IS_ALIVE);
                for (int i = 0; i <= CloseEntities.size(); i++){
                    target = (LivingEntity) CloseEntities.get(i);
                    target.addPotionEffect(new EffectInstance(Effects.GLOWING, 600));
                }
            }
        }

    }

}
