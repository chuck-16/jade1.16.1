package com.chuck.jademod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

public class MossyBricks extends Block {

    public MossyBricks() {

        super(Block.Properties.create(Material.ROCK)
                .hardnessAndResistance(2.0F, 6.0F));
    }
}
