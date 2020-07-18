package com.example.hungermod.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.world.World;

public class TurkeyEntity extends ChickenEntity {
    public TurkeyEntity(EntityType<? extends ChickenEntity> type, World worldIn) {
        super(type, worldIn);
    }
}
