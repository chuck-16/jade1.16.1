package com.chuck.jademod.render.entities;

import com.chuck.jademod.entities.EntityJadeArrow;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class JadeArrowRenderer extends ArrowRenderer<EntityJadeArrow> {
    public static final ResourceLocation RES_JADE_ARROW = new ResourceLocation("jade:textures/entity/projectiles/jade_arrow.png");

    public JadeArrowRenderer(EntityRendererManager manager) {
        super(manager);
    }

    public ResourceLocation getEntityTexture(EntityJadeArrow entity) {
        return RES_JADE_ARROW;
    }

}
