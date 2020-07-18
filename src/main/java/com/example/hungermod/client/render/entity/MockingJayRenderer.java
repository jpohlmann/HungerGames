package com.example.hungermod.client.render.entity;

import com.example.hungermod.HungerMod;
import com.example.hungermod.entity.MockingJayEntity;
import com.example.hungermod.entity.TurkeyEntity;
import com.example.hungermod.entity.model.MockingJayModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.ChickenModel;
import net.minecraft.client.renderer.entity.model.ParrotModel;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

/**
 * Handles rendering the tracker jacker.
 *
 * @author Justin and Jackson Pohlmann
 */
public class MockingJayRenderer extends MobRenderer<MockingJayEntity, MockingJayModel> {

    /**
     * Set the texture location for the tracker jacker
     */
    private static final ResourceLocation MOCKING_JAY_TEXTURE = new ResourceLocation(HungerMod.MODID, "textures/entity/mocking_jay/mocking_jay.png");

    public MockingJayRenderer(final EntityRendererManager manager) {
        super(manager, new MockingJayModel(), 0.3F);
    }

    @Override
    public ResourceLocation getEntityTexture(final MockingJayEntity entity) {
        return MOCKING_JAY_TEXTURE;
    }

    /**
     * Defines what float the third param in setRotationAngles of ModelBase is
     */
    public float handleRotationFloat(MockingJayEntity livingBase, float partialTicks) {
        float f = MathHelper.lerp(partialTicks, livingBase.oFlap, livingBase.flap);
        float f1 = MathHelper.lerp(partialTicks, livingBase.oFlapSpeed, livingBase.flapSpeed);
        return (MathHelper.sin(f) + 1.0F) * f1;
    }
}

