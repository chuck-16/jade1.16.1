package com.chuck.jademod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class JadeBlockPolished extends Block {
    public JadeBlockPolished() {
        super(Block.Properties.create(Material.GLASS)
                .hardnessAndResistance(3.0f, 2.0f)
                .sound(SoundType.GLASS)
                .harvestTool(ToolType.PICKAXE)
                .harvestLevel(2)
        );
    }
}
