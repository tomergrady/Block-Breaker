package io;

import collidable.Block;
import geometry.Point;
import geometry.Rectangle;
import interfaces.HitListener;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by TOMER on 6/13/2017.
 */
public class BlockSet implements BlockCreator {

    private Rectangle block;
    private java.awt.Color color;
    private int hitNum = -1;
    private List<HitListener> hitListeners = new ArrayList<HitListener>();
    private boolean hasBorder;
    private Map<Integer, Color> colors;
    private Map<Integer, Image> images;
    private Color borderColor;

    /**
     * constractor a block.
     */
    public BlockSet() {
        this.block = new Rectangle(new Point(0, 0), 1, 1);
        this.colors = new TreeMap<Integer, Color>();
        this.images = new TreeMap<Integer, Image>();
        this.borderColor = Color.BLACK;
        this.hasBorder = false;
        this.hitNum = 0;
        this.hasBorder = false;
    }

    @Override
    public Block create(int xpos, int ypos) {
        return null;
    }
    /**
     * this method sets the width of this block.
     * @param width the new width.
     */
    public void setWidth(int width) {
        this.block = new Rectangle(this.block.getUpperLeft(),
                width, this.block.getHeight());
    }
    /**
     * this method sets the height of this block.
     * @param height the new height.
     */
    public void setHeight(int height) {
        this.block = new Rectangle(this.block.getUpperLeft(),
                this.block.getWidth(), height);
    }
    /**
     * this method sets the number of hits of this block.
     * @param numberOfhits the number of hits.
     */
    public void setHits(int numberOfhits) {
        this.hitNum = numberOfhits;
    }
    /**
     * this method sets the color of this block's borders.
     * @param bordersColor the given color.
     */
    public void setBorderColor(Color bordersColor) {
        this.borderColor = bordersColor;
    }
    /**
     * this method sets the available colors of this block's filling.
     * @param filling the given colors.
     */
    public void setColors(Map<Integer, Color> filling) {
        this.colors = filling;
    }
    /**
     * this method sets the available images of this block's filling.
     * @param filling the given images.
     */
    public void setImages(Map<Integer, Image> filling) {
        this.images = filling;
    }
    /**
     * this method determines if this block has borders or not.
     * @param isHasBorder the wanted option.
     */
    public void setBorder(boolean isHasBorder) {
        this.hasBorder = isHasBorder;
    }
}
