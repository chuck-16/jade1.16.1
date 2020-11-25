package com.chuck.jademod.client.gui;

import com.chuck.jademod.container.FletchingContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


@OnlyIn(Dist.CLIENT)
public class FletchingScreen extends ContainerScreen<FletchingContainer> {

    // Location of the fletching table gui texture
    private static ResourceLocation FLETCHING_GUI_TEXTURE = new ResourceLocation("jade:textures/gui/container/fletching_table_screen.png");

    // Constructor
    public FletchingScreen(FletchingContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        // Set the width and height of the gui.  Should match the size of the texture!
        xSize = 176;
        ySize = 166;
    }

    @Override
    protected void func_230450_a_(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        drawGuiContainerBackgroundLayer(matrixStack, partialTicks, mouseX, mouseY);
    }

    private void drawGuiContainerBackgroundLayer(MatrixStack p_230450_1_, float p_230450_2_, int p_230450_3_, int p_230450_4_) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.getMinecraft().getTextureManager().bindTexture(FLETCHING_GUI_TEXTURE);   // this.minecraft.getTextureManager()

        // width and height are the size provided to the window when initialised after creation.
        // xSize, ySize are the expected size of the texture-? usually seems to be left as a default.
        // The code below is typical for vanilla containers, so I've just copied that- it appears to centre the texture within
        //  the available window

        int edgeSpacingX = (this.field_230708_k_ - this.xSize) / 2;
        int edgeSpacingY = (this.field_230709_l_ - this.ySize) / 2;
        this.func_238474_b_(p_230450_1_, edgeSpacingX, edgeSpacingY, 0, 0, this.xSize, this.ySize);
    }
}
