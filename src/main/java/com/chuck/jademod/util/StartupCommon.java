package com.chuck.jademod.util;

import com.chuck.jademod.blocks.FletchingTableBlock;
import com.chuck.jademod.container.FletchingContainer;
import com.chuck.jademod.entities.EntityJadeArrow;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.SmithingRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class StartupCommon {

    public static EntityType<EntityJadeArrow> jadeArrow;
    public static IRecipeSerializer<SmithingRecipe> fletchingSerializer;
    public static FletchingTableBlock fletchingBlock;
    public static ContainerType<FletchingContainer> fletchingContainerType;
    @SubscribeEvent
    public static void onEntityTypeRegistration(RegistryEvent.Register<EntityType<?>> entityTypeRegisterEvent) {
        jadeArrow = EntityType.Builder.<EntityJadeArrow>create(EntityJadeArrow::new, EntityClassification.MISC)
                .size(0.5F, 0.5F)
                .build("jade_arrow");
        jadeArrow.setRegistryName("jade_arrow");
        entityTypeRegisterEvent.getRegistry().register(jadeArrow);
    }

    @SubscribeEvent
    public static void onRecipeSerializerRegistration(RegistryEvent.Register<IRecipeSerializer<?>> event) {
        fletchingSerializer = new SmithingRecipe.Serializer();
        fletchingSerializer.setRegistryName(ResourceLocation.tryCreate("jade:fletching_upgrade_recipe"));
        event.getRegistry().register(fletchingSerializer);
    }

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event) {
        fletchingBlock = new FletchingTableBlock(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(2.5F).sound(SoundType.WOOD));
        fletchingBlock.setRegistryName("minecraft:fletching_table");
        event.getRegistry().register(fletchingBlock);
    }

    @SubscribeEvent
    public static void CraftingTypeRegister(RegistryEvent.Register<ContainerType<?>> event) {
        fletchingContainerType = new ContainerType<>(FletchingContainer::new);
        fletchingContainerType.setRegistryName("jade:fletching");
        event.getRegistry().register(fletchingContainerType);
    }


}