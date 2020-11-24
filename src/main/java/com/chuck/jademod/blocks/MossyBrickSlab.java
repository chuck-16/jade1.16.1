package com.chuck.jademod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class MossyBrickSlab extends Block {
    private static final VoxelShape SHAPE = Block.makeCuboidShape(0.0d, 0.0d, 0.0d, 16.0d, 8.0d, 16.0d); // 1.0d == 1 pixel
    public MossyBrickSlab() {

        super(Block.Properties.create(Material.ROCK)
                .hardnessAndResistance(2.0F, 6.0F));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }
}
