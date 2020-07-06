package com.example.hungermod.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

/**
 * The properties and constructor for the tracker jacker.
 *
 * @author Justin and Jackson Pohlmann
 */
public class TrackerJackerEntity extends BeeEntity {
    /**
     * Constructor for tracker jacker
     *
     * @param entityType
     * @param world
     */
    public TrackerJackerEntity(final EntityType<? extends TrackerJackerEntity> entityType, final World world) {
        super(entityType, world);
    }
    @Override
    /**
     * This is where we set the attributes for the tracker jacker (speed, health, etc).
     */
    protected void registerAttributes() {
        super.registerAttributes();
        this.setAggroed(true);

        final double baseSpeed = this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getBaseValue();
        final double baseHealth = this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue();
        // Multiply base health and base speed by one and a half
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(baseSpeed * 1.5D);
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(baseHealth * 1.5D);
    }

}
