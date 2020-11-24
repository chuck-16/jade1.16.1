package com.chuck.jademod.container;

import com.chuck.jademod.util.StartupCommon;
import com.google.common.collect.Lists;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.SmithingRecipe;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.IntReferenceHolder;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class FletchingContainer extends Container {

    private final IWorldPosCallable worldPosCallable;
    /** The index of the selected recipe in the GUI. */
    private final IntReferenceHolder selectedRecipe = IntReferenceHolder.single();
    private final World world;
    private List<SmithingRecipe> recipes = Lists.newArrayList();
    /** The {@plainlink ItemStack} set in the input slot by the player. */
    private ItemStack itemStackInput = ItemStack.EMPTY;
    /**
     * Stores the game time of the last time the player took items from the the crafting result slot. This is used to
     * prevent the sound from being played multiple times on the same tick.
     */
    private long lastOnTake;
    final Slot inputInventorySlot;
    final Slot inputInventorySlotTwo;
    /** The inventory slot that stores the output of the crafting recipe. */
    final Slot outputInventorySlot;
    private Runnable inventoryUpdateListener = () -> {
    };
    public final IInventory inputInventory = new Inventory(2) {
        public void markDirty() {
            super.markDirty();
            FletchingContainer.this.onCraftMatrixChanged(this);
            FletchingContainer.this.inventoryUpdateListener.run();
        }
    };
    private final CraftResultInventory inventory = new CraftResultInventory();


    public FletchingContainer() {
        this(1, Minecraft.getInstance().player.inventory, IWorldPosCallable.DUMMY);
    }
    public FletchingContainer(int windowIdIn, PlayerInventory playerInventoryIn, final IWorldPosCallable worldPosCallableIn) {
        super(ContainerType.STONECUTTER, windowIdIn);
        this.worldPosCallable = worldPosCallableIn;
        this.world = playerInventoryIn.player.world;
        this.inputInventorySlot = this.addSlot(new Slot(this.inputInventory, 0, 27, 47));
        this.inputInventorySlotTwo = this.addSlot(new Slot(this.inputInventory, 1, 76, 47));

        this.outputInventorySlot = this.addSlot(new Slot(this.inventory, 1, 134, 47) {
            /**
             * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace fuel.
             */
            public boolean isItemValid(ItemStack stack) {
                return false;
            }

            public ItemStack onTake(PlayerEntity thePlayer, ItemStack stack) {
                ItemStack itemstack = FletchingContainer.this.inputInventorySlot.decrStackSize(1);
                if (!itemstack.isEmpty()) {
                    FletchingContainer.this.updateRecipeResultSlot();
                }

                stack.getItem().onCreated(stack, thePlayer.world, thePlayer);
                worldPosCallableIn.consume((p_216954_1_, p_216954_2_) -> {
                    long l = p_216954_1_.getGameTime();
                    if (FletchingContainer.this.lastOnTake != l) {
                        p_216954_1_.playSound((PlayerEntity)null, p_216954_2_, SoundEvents.ENTITY_VILLAGER_WORK_FLETCHER, SoundCategory.BLOCKS, 1.0F, 1.0F);
                        FletchingContainer.this.lastOnTake = l;
                    }

                });
                return super.onTake(thePlayer, stack);
            }
        });

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventoryIn, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for(int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(playerInventoryIn, k, 8 + k * 18, 142));
        }

        this.trackInt(this.selectedRecipe);
    }

    public FletchingContainer(int windowIdIn, PlayerInventory playerInventoryIn) {
        this(windowIdIn, playerInventoryIn, IWorldPosCallable.DUMMY);
    }


    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(this.worldPosCallable, playerIn, Blocks.FLETCHING_TABLE);
    }

    private void updateAvailableRecipes(IInventory inventoryIn, ItemStack stack) {
        this.recipes.clear();
        this.selectedRecipe.set(-1);
        this.outputInventorySlot.putStack(ItemStack.EMPTY);
        if (!stack.isEmpty()) {
            this.recipes = this.world.getRecipeManager().getRecipes(IRecipeType.field_234827_g_, inventoryIn, this.world);
        }

    }
    public ContainerType<?> getType() {
        return StartupCommon.fletchingContainerType;
    }

    @OnlyIn(Dist.CLIENT)
    public void setInventoryUpdateListener(Runnable listenerIn) {
        this.inventoryUpdateListener = listenerIn;
    }

    private void updateRecipeResultSlot() {
        if (!this.recipes.isEmpty() && this.func_241818_d_(this.selectedRecipe.get())) {
            SmithingRecipe fletchingRecipe = this.recipes.get(this.selectedRecipe.get());
            this.outputInventorySlot.putStack(fletchingRecipe.getCraftingResult(this.inputInventory));
        } else {
            this.outputInventorySlot.putStack(ItemStack.EMPTY);
        }

        this.detectAndSendChanges();
    }

    private boolean func_241818_d_(int p_241818_1_) {
        return p_241818_1_ >= 0 && p_241818_1_ < this.recipes.size();
    }

    public boolean canMergeSlot(ItemStack stack, Slot slotIn) {
        return slotIn.inventory != this.inventory && super.canMergeSlot(stack, slotIn);
    }

    /**
     * Handle when the stack in slot {@code index} is shift-clicked. Normally this moves the stack between the player
     * inventory and the other inventory(s).
     */
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            Item item = itemstack1.getItem();
            itemstack = itemstack1.copy();
            if (index == 1) {
                item.onCreated(itemstack1, playerIn.world, playerIn);
                if (!this.mergeItemStack(itemstack1, 2, 38, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(itemstack1, itemstack);
            } else if (index == 0) {
                if (!this.mergeItemStack(itemstack1, 2, 38, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (this.world.getRecipeManager().getRecipe(IRecipeType.field_234827_g_, new Inventory(itemstack1), this.world).isPresent()) {
                if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (index >= 2 && index < 29) {
                if (!this.mergeItemStack(itemstack1, 29, 38, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (index >= 29 && index < 38 && !this.mergeItemStack(itemstack1, 2, 29, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            }

            slot.onSlotChanged();
            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, itemstack1);
            this.detectAndSendChanges();
        }

        return itemstack;
    }

    /**
     * Called when the container is closed.
     */
    public void onContainerClosed(PlayerEntity playerIn) {
        super.onContainerClosed(playerIn);
        this.inventory.removeStackFromSlot(1);
        this.worldPosCallable.consume((p_217079_2_, p_217079_3_) -> {
            this.clearContainer(playerIn, playerIn.world, this.inputInventory);
        });
    }

}
