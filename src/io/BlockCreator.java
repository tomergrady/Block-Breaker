package io;

import collidable.Block;

/**
 * Created by TOMER on 6/12/2017.
 */
public interface BlockCreator {
    /**
     * Create a block at the specified location.
     * @param xpos x position of the block.
     * @param ypos y position of the block
     * @return tne created block
     */
    Block create(int xpos, int ypos);
}