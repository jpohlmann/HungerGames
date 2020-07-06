package com.example.hungermod.init;

import com.example.hungermod.block.Ethan;
import com.example.hungermod.block.Jackson;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Define the blocks for our mod
 *
 * @author Justin and Jackson Pohlmann
 */
public final class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, com.example.hungermod.HungerGamesMod.MODID);
    public static final RegistryObject<Block> JACKSON = BLOCKS.register("jackson", () -> new Jackson(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F)));
    public static final RegistryObject<Block> ETHAN = BLOCKS.register("ethan", () -> new Ethan(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F)));
}
