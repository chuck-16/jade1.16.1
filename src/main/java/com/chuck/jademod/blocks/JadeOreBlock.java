package com.chuck.jademod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.OreBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.ToolType;

public class JadeOreBlock extends OreBlock {
    public JadeOreBlock() {
        super(Block.Properties.create(Material.ROCK)
                .hardnessAndResistance(6.0f, 2.0f)
                .sound(SoundType.STONE)
                .harvestTool(ToolType.PICKAXE)
                .harvestLevel(2)
                .func_235838_a_((lightLevel) -> 4)
        );
    }
    @Override
    public int getExpDrop(BlockState state, IWorldReader reader, BlockPos pos, int fortune, int silktouch) {
        if (silktouch == 1){
            return 0;
        } else{
            return 2;
        }
    }
}
