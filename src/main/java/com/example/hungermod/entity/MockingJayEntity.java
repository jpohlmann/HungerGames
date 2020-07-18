package com.example.hungermod.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.world.World;

public class MockingJayEntity extends ParrotEntity {
    public MockingJayEntity(EntityType<? extends ParrotEntity> type, World worldIn) {
        super(type, worldIn);
    }
}
