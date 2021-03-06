package com.example.hungermod.init;

import com.example.hungermod.HungerMod;
import com.example.hungermod.entity.MockingJayEntity;
import com.example.hungermod.entity.TrackerJackerEntity;
import com.example.hungermod.entity.TurkeyEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Holds a list of all our {@link EntityType}s.
 * Suppliers that create EntityTypes are added to the DeferredRegister.
 * The DeferredRegister is then added to our mod event bus in our constructor.
 * When the EntityType Registry Event is fired by Forge and it is time for the mod to
 * register its EntityTypes, our EntityTypes are created and registered by the DeferredRegister.
 * The EntityType Registry Event will always be called after the Block and Item registries are filled.
 * Note: This supports registry overrides.
 *
 * @author Cadiboo
 */
public final class ModEntityTypes {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.ENTITIES, HungerMod.MODID);

    public static final String TRACKER_JACKER_NAME = "tracker_jacker";
    public static final RegistryObject<EntityType<TrackerJackerEntity>> TRACKER_JACKER = ENTITY_TYPES.register(TRACKER_JACKER_NAME, () ->
            EntityType.Builder.<TrackerJackerEntity>create(TrackerJackerEntity::new, EntityClassification.CREATURE)
                    .size(EntityType.BEE.getWidth(), EntityType.BEE.getHeight())
                    .build(new ResourceLocation(HungerMod.MODID, TRACKER_JACKER_NAME).toString())
    );
    public static final String TURKEY_NAME = "turkey";
    public static final RegistryObject<EntityType<TurkeyEntity>> TURKEY = ENTITY_TYPES.register(TURKEY_NAME, () ->
            EntityType.Builder.<TurkeyEntity>create(TurkeyEntity::new, EntityClassification.CREATURE)
                    .size(EntityType.CHICKEN.getWidth(), EntityType.CHICKEN.getHeight())
                    .build(new ResourceLocation(HungerMod.MODID, TURKEY_NAME).toString())
    );
    public static final String MOCKING_JAY_NAME = "mocking_jay";
    public static final RegistryObject<EntityType<MockingJayEntity>> MOCKING_JAY = ENTITY_TYPES.register(MOCKING_JAY_NAME, () ->
            EntityType.Builder.<MockingJayEntity>create(MockingJayEntity::new, EntityClassification.CREATURE)
                    .size(EntityType.PARROT.getWidth(), EntityType.PARROT.getHeight())
                    .build(new ResourceLocation(HungerMod.MODID, MOCKING_JAY_NAME).toString())
    );

}