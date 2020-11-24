package com.chuck.jademod.events;

import com.chuck.jademod.Jade;
import com.chuck.jademod.container.FletchingContainer;
import net.minecraft.block.AnvilBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.SmithingTableBlock;
import net.minecraft.inventory.container.*;
import net.minecraft.item.Items;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.NetworkHooks;

import java.awt.*;

@Mod.EventBusSubscriber(modid = Jade.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class Events {
    @SubscribeEvent
    public static void onRightClick (PlayerInteractEvent.RightClickBlock event){

        // Check if the player right clicks a soul soil
        if (event.getWorld().getBlockState(event.getPos()) == Blocks.field_235336_cN_.getDefaultState()){
            if (event.getPlayer().getHeldItemMainhand().getItem() == Items.WOODEN_HOE ||
                    event.getPlayer().getHeldItemMainhand().getItem() == Items.STONE_HOE ||
                    event.getPlayer().getHeldItemMainhand().getItem() == Items.GOLDEN_HOE||
                    event.getPlayer().getHeldItemMainhand().getItem() == Items.IRON_HOE||
                    event.getPlayer().getHeldItemMainhand().getItem() == Items.DIAMOND_HOE||
                    event.getPlayer().getHeldItemMainhand().getItem() == Items.field_234758_kU_){
                event.getWorld().setBlockState(event.getPos(), Blocks.SOUL_SAND.getDefaultState());
                event.getPlayer().getHeldItemMainhand().getItem().damageItem(event.getPlayer().getHeldItemMainhand(), 10, event.getPlayer(), (p_220043_1_) -> {
                            event.getPlayer().sendBreakAnimation(event.getPlayer().getActiveHand());
                        });
                event.getWorld().playSound(event.getPlayer(), event.getPos(), SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);

            }
        }

        // Check if the player right clicks a fletching table
        if (event.getWorld().getBlockState(event.getPos()) == Blocks.FLETCHING_TABLE.getDefaultState()){

        }
        }
    }

