package com.chuck.jademod.world.gen;

import com.chuck.jademod.Jade;
import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.registries.ForgeRegistries;

import static com.chuck.jademod.util.RegisteryHandler.JADE_CRYSTAL_BLOCK;
import static com.chuck.jademod.util.RegisteryHandler.JADE_ORE_BLOCK;

@Mod.EventBusSubscriber(modid = Jade.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class UndergroundDecorationHandler {

    @SubscribeEvent
    public static void generateCrystals(FMLLoadCompleteEvent event){

        for (Biome biome: ForgeRegistries.BIOMES){

            //Nether Gen
            if(biome.getCategory() == Biome.Category.NETHER){


            //End Gen
            } else if (biome.getCategory() == Biome.Category.THEEND){


            //Overworld Gen
            } else{
                genCrystal(biome, 2, 8, 5, 50, JADE_CRYSTAL_BLOCK);
                genOre(biome, 4, 8, 5, 50, OreFeatureConfig.FillerBlockType.NATURAL_STONE, JADE_ORE_BLOCK.get().getDefaultState(), 5);
            }
        }
    }

    private static void genCrystal(Biome biome, int count, int bottomOffset, int topOffset, int max, RegistryObject<Block> target) {
        BlockState JADE_CRYSTAL = target.get().getDefaultState();
        BlockClusterFeatureConfig JADE_CRYSTAL_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(JADE_CRYSTAL), SimpleBlockPlacer.field_236447_c_)).tries(30).whitelist(ImmutableSet.of(Blocks.STONE.getBlock())).func_227317_b_().build();
        CountRangeConfig range = new CountRangeConfig(count, bottomOffset, topOffset, max);
        ConfiguredPlacement config = Placement.COUNT_RANGE.configure(range);
        biome.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.RANDOM_PATCH.withConfiguration(JADE_CRYSTAL_CONFIG).withPlacement(config));
    }
    private static void genOre(Biome biome, int count, int bottomOffset, int topOffset, int max, OreFeatureConfig.FillerBlockType filler, BlockState defaultBlockstate, int size) {
        CountRangeConfig range = new CountRangeConfig(count, bottomOffset, topOffset, max);
        OreFeatureConfig feature = new OreFeatureConfig(filler, defaultBlockstate, size);
        ConfiguredPlacement config = Placement.COUNT_RANGE.configure(range);
        biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(feature).withPlacement(config));
    }
}
