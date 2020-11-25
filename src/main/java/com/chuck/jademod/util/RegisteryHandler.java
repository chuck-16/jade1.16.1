package com.chuck.jademod.util;

import com.chuck.jademod.Jade;
import com.chuck.jademod.blocks.*;
import com.chuck.jademod.effects.ModEffect;
import com.chuck.jademod.effects.ModEffects;
import com.chuck.jademod.items.ItemBase;
import com.chuck.jademod.items.JadeArrow;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.item.*;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.SmithingRecipe;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

import static com.chuck.jademod.Jade.MOD_ID;


@Mod.EventBusSubscriber(modid = Jade.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class RegisteryHandler {

    public static DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
    public static DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
    public static DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, MOD_ID);

    public static void init(){
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        EFFECTS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
    // items
    public static final RegistryObject<Item> JADE_SHARD = ITEMS.register("jade_shard", ItemBase::new );
    public static final RegistryObject<Item> JADE_ARROW = ITEMS.register("jade_arrow", JadeArrow::new);
    // Blocks
    public static final RegistryObject<Block> JADE_CRYSTAL_BLOCK = BLOCKS.register("jade_crystal_block", JadeCrystalBlock::new);
    public static final RegistryObject<Block> JADE_ORE_BLOCK = BLOCKS.register("jade_ore_block", JadeOreBlock::new);
    public static final RegistryObject<Block> JADE_BLOCK = BLOCKS.register("jade_block", JadeBlock::new);
    public static final RegistryObject<Block> JADE_BLOCK_POLISHED = BLOCKS.register("jade_block_polished", JadeBlockPolished::new);


    public static final RegistryObject<Block> MOSSY_BRICKS = BLOCKS.register("mossy_brick", MossyBricks::new);
    public static final RegistryObject<Block> MOSSY_BRICK_WALL = BLOCKS.register("mossy_brick_wall", () -> new WallBlock(Block.Properties.from(Blocks.BRICK_WALL)));
    public static final RegistryObject<Block> MOSSY_BRICK_STAIR = BLOCKS.register("mossy_brick_stair", () -> new StairsBlock(() -> MOSSY_BRICKS.get().getDefaultState(), Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 6.0F)));
    public static final RegistryObject<Block> MOSSY_BRICK_SLAB = BLOCKS.register("mossy_brick_slab", MossyBrickSlab::new);
    // Blocks Items
    public static final RegistryObject<Item> JADE_CRYSTAL_BLOCK_ITEM = ITEMS.register("jade_crystal_block", () -> new BlockItemBase(JADE_CRYSTAL_BLOCK.get()));
    public static final RegistryObject<Item> JADE_ORE_BLOCK_ITEM = ITEMS.register("jade_ore_block", () -> new BlockItemBase(JADE_ORE_BLOCK.get()));
    public static final RegistryObject<Item> JADE_BLOCK_ITEM = ITEMS.register("jade_block", () -> new BlockItemBase(JADE_BLOCK.get()));
    public static final RegistryObject<Item> JADE_BLOCK_POLISHED_ITEM = ITEMS.register("jade_block_polished", () -> new BlockItemBase(JADE_BLOCK_POLISHED.get()));


    public static final RegistryObject<Item> MOSSY_BRICK_ITEM = ITEMS.register("mossy_brick", () -> new BlockItemBase(MOSSY_BRICKS.get()));
    public static final RegistryObject<Item> MOSSY_BRICK_STAIR_ITEM = ITEMS.register("mossy_brick_stair", () -> new BlockItemBase(MOSSY_BRICK_STAIR.get()));
    public static final RegistryObject<Item> MOSSY_BRICK_SLAB_ITEM = ITEMS.register("mossy_brick_slab", () -> new BlockItemBase(MOSSY_BRICK_SLAB.get()));
    public static final RegistryObject<Item> MOSSY_BRICK_WALL_ITEM = ITEMS.register("mossy_brick_wall", () -> new BlockItemBase(MOSSY_BRICK_WALL.get()));

    // Effects
    public static final RegistryObject<Effect> VISION = EFFECTS.register("vision", () -> new ModEffect(EffectType.BENEFICIAL, 2039713));
}

