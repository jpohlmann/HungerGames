package com.example.hungermod.client.render.entity;

import com.example.hungermod.HungerMod;
import com.example.hungermod.entity.TrackerJackerEntity;
import com.example.hungermod.entity.model.TrackerJackerModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.BeeModel;
import net.minecraft.util.ResourceLocation;

/**
 * Handles rendering the tracker jacker.
 *
 * @author Justin and Jackson Pohlmann
 */
public class TrackerJackerRenderer extends MobRenderer<TrackerJackerEntity, TrackerJackerModel<TrackerJackerEntity>> {

    /**
     * Set the texture location for the tracker jacker
     */
    private static final ResourceLocation TRACKER_JACKER_TEXTURE = new ResourceLocation(HungerMod.MODID, "textures/entity/tracker_jacker/tracker_jacker.png");

    public TrackerJackerRenderer(final EntityRendererManager manager) {
        super(manager, new TrackerJackerModel<>(), 0.7F);
    }

    @Override
    public ResourceLocation getEntityTexture(final TrackerJackerEntity entity) {
        return TRACKER_JACKER_TEXTURE;
    }

}

