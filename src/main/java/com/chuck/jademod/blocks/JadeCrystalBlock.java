package com.chuck.jademod.blocks;

import com.chuck.jademod.util.RegisteryHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;

import java.util.Random;

import static net.minecraft.util.DamageSource.causeIndirectMagicDamage;

public class JadeCrystalBlock extends Block {
    private static final VoxelShape SHAPE = Block.makeCuboidShape(3.0d, 0.0d, 3.0d, 13.0d, 12.0d, 13.0d); // 1.0d == 1 pixel


    public JadeCrystalBlock() {
        super(Block.Properties.create(Material.ORGANIC)
                .hardnessAndResistance(4.0f, 1.0f)
                .sound(SoundType.GLASS)
                .harvestTool(ToolType.PICKAXE)
                .doesNotBlockMovement()
                .notSolid()
                .harvestLevel(2)
                .func_235838_a_((lightLevel) -> 10)
        );
    }
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        VoxelShape voxelshape = this.getShape(stateIn, worldIn, pos, ISelectionContext.dummy());
        Vector3d vector3d = voxelshape.getBoundingBox().getCenter();
        double d0 = (double)pos.getX() + vector3d.x;
        double d1 = (double)pos.getZ() + vector3d.z;

        if (rand.nextBoolean()) {
            worldIn.addParticle(ParticleTypes.HAPPY_VILLAGER, d0 + rand.nextDouble() , (double)pos.getY() + rand.nextDouble()*2, d1 + rand.nextDouble(), 0.0D, 0.0D, 0.0D);
        }
    }


    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        if (entityIn instanceof LivingEntity){
            LivingEntity livingentity = (LivingEntity)entityIn;
            livingentity.addPotionEffect(new EffectInstance(Effects.NAUSEA, 150, 1, false, false));
            livingentity.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 150, 4, false, false));
            livingentity.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 150, 4, false, false));
            //livingentity.addPotionEffect(new EffectInstance(Effects.INSTANT_DAMAGE, 1, 1, false, false));
            entityIn.attackEntityFrom(causeIndirectMagicDamage(entityIn, null), 4);
        }
    }

    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return state.isIn(Blocks.STONE) || state.isIn(Blocks.DIRT) || state.isIn(Blocks.COARSE_DIRT) || state.isIn(Blocks.PODZOL) || state.isIn(Blocks.ANDESITE) || state.isIn(Blocks.GRANITE)
                || state.isIn(Blocks.DIORITE) || state.isIn(Blocks.GRAVEL) || state.isIn(Blocks.GRASS_BLOCK) || state.isIn(Blocks.GRASS_PATH) || state.isIn(RegisteryHandler.JADE_BLOCK.get())
                || state.isIn(RegisteryHandler.JADE_BLOCK_POLISHED.get());
    }

    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        BlockPos blockpos = pos.down();
        return this.isValidGround(worldIn.getBlockState(blockpos), worldIn, blockpos);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }
}
