package com.example.hungermod.client.render.entity;

import com.example.hungermod.HungerMod;
import com.example.hungermod.entity.TrackerJackerEntity;
import com.example.hungermod.entity.TurkeyEntity;
import com.example.hungermod.entity.model.TrackerJackerModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.ChickenModel;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

/**
 * Handles rendering the tracker jacker.
 *
 * @author Justin and Jackson Pohlmann
 */
public class TurkeyRenderer extends MobRenderer<TurkeyEntity, ChickenModel<TurkeyEntity>> {

    /**
     * Set the texture location for the tracker jacker
     */
    private static final ResourceLocation TURKEY_TEXTURE = new ResourceLocation(HungerMod.MODID, "textures/entity/turkey/turkey.png");

    public TurkeyRenderer(final EntityRendererManager manager) {
        super(manager, new ChickenModel<>(), 0.7F);
    }

    @Override
    public ResourceLocation getEntityTexture(final TurkeyEntity entity) {
        return TURKEY_TEXTURE;
    }

    /**
     * Defines what float the third param in setRotationAngles of ModelBase is
     */
    protected float handleRotationFloat(TurkeyEntity livingBase, float partialTicks) {
        float f = MathHelper.lerp(partialTicks, livingBase.oFlap, livingBase.wingRotation);
        float f1 = MathHelper.lerp(partialTicks, livingBase.oFlapSpeed, livingBase.destPos);
        return (MathHelper.sin(f) + 1.0F) * f1;
    }
}

