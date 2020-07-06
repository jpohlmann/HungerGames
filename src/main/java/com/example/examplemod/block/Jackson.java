package com.example.hungermod.block;

import net.minecraft.block.HorizontalBlock;
import net.minecraft.util.Direction;

/**
 * Block with Jackson's face
 *
 * @author Justin and Jackson Pohlmann
 */
public class Jackson extends HorizontalBlock {
    /**
     * Constructor
     *
     * @param properties
     */
    public Jackson(final Properties properties) {
        super(properties);
        // Set the default values for our blockstate properties
        this.setDefaultState(this.getDefaultState());
    }

}
