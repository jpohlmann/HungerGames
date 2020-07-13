package com.example.hungermod.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

/**
 * Block with Ethan's face
 *
 * @author Justin and Jackson Pohlmann
 */
public class Ethan extends HorizontalBlock {
    /**
     * Constructor
     *
     * @param properties
     */
    public Ethan(final Properties properties) {
        super(properties);
        // Set the default values for our blockstate properties
        this.setDefaultState(this.getDefaultState());
    }
}
