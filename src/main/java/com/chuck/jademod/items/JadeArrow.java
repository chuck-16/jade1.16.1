package com.chuck.jademod.items;

import com.chuck.jademod.entities.EntityJadeArrow;
import com.chuck.jademod.util.RegisteryHandler;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.SpectralArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class JadeArrow extends ArrowItem {
    public JadeArrow() {
        super((new Item.Properties()).group(ItemGroup.COMBAT));
    }

    public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter) {
        return new EntityJadeArrow(worldIn, shooter);
    }
}
