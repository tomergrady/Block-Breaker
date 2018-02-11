package collidable;
import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import biuoop.DrawSurface;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import interfaces.Collidable;
import interfaces.HitListener;
import interfaces.HitNotifier;
import interfaces.Sprite;
import runs.GameLevel;
/**
 *
 * @author TOMER GRADY
 * 205660863
 *
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle block;
    private java.awt.Color color;
    private int hitNum = -1;
    private List<HitListener> hitListeners = new ArrayList<HitListener>();
    private boolean isBoundry = false;
    private boolean hasBorder;
    private boolean isDeathBlock = false;
    private Map<Integer, Color> colors = new TreeMap<Integer, Color>();
    private Map<Integer, Image> images = new TreeMap<Integer, Image>();
    private Color borderColor;


    /**
     * constractor a block.
     */
    public Block() {
        this.block = new Rectangle(new Point(0, 0), 1, 1);
        this.borderColor = Color.BLACK;
        this.isBoundry = false;
        this.hasBorder = true;
        this.hitNum = 0;
    }
    /**
     * @param b
     *            rectangle
     * @param color the color of the block
     */
    public Block(Rectangle b, java.awt.Color color) {
        this.block = b;
        this.color = color;
    }
    /**
     * new block.
     * @param b rectangle
     * @param color color
     * @param hitNum number of hit that the block startwith
     */
    public Block(Rectangle b, java.awt.Color color, int hitNum) {
        this.block = b;
        this.color = color;
        this.hitNum = hitNum;
    }
    /**
     * new block.
     * @param b rectangle
     * @param color color
     * @param hitNum number of hit that the block startwith
     * @param hitL add the hitListener
     */
    public Block(Rectangle b, java.awt.Color color, int hitNum, HitListener hitL) {
        this.block = b;
        this.color = color;
        this.hitNum = hitNum;
        this.addHitListener(hitL);
    }
    /**
     * new block.
     * @param b rectangle
     * @param color color
     * @param hitNum number of hit that the block startwith
     * @param hitL add the hitListener
     * @param score the score game
     */
    public Block(Rectangle b, java.awt.Color color, int hitNum, HitListener hitL, HitListener score) {
        this.block = b;
        this.color = color;
        this.hitNum = hitNum;
        this.addHitListener(hitL);
        this.addHitListener(score);
    }
    /**
     *
     * @param b rectangle
     */
    public Block(Rectangle b) {
        this.block = b;
        this.color = Color.gray;
        this.hitListeners = new ArrayList<HitListener>();
    }
    /**
    *
    * @param b rectangle
     *@param isBundry boolean if the ball is the bunder.
    */
   public Block(Rectangle b, boolean isBundry) {
       this.block = b;
       this.colors.put(0, Color.decode("#12392b"));
       this.hitListeners = new ArrayList<HitListener>();
       this.isBoundry = true;
       this.hasBorder = false;
   }

   /**
   * constractor.
   * @param b rectangle
    *@param isBundry true is the block is boundy
    *@param deathBlock true if the block is deathBlock
   */
  public Block(Rectangle b, boolean isBundry, boolean deathBlock) {
      this.block = b;
      this.color = Color.darkGray;
      this.hitListeners = new ArrayList<HitListener>();
      this.isBoundry = true;
      this.hasBorder = false;
      this.isDeathBlock = true;
  }
    /**
     * construct a new block from a given block according to its' parameters,
     * a new hitListener object is generated anyway.
     * @param other the given block.
     */
    public Block(Block other) {
        this.block = other.block;
        this.colors = other.colors;
        this.images = other.images;
        this.borderColor = other.borderColor;
        this.hasBorder = other.hasBorder;
        this.hitNum = other.hitNum;
        this.hitListeners = new ArrayList<HitListener>();
    }
    /**
     * @return the block as a rectangle
     */

    public Rectangle getCollisionRectangle() {
        return this.block;
    }

    /**
     * change the velocity of the ball ball after the collision.
     *
     * @param collisionPoint
     *            point of collision
     * @param currentVelocity
     *            - the velocity of the collision
     * @param hitter the hitter ball
     * @return the new velocity after the collision
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        this.notifyHit(hitter);
        Line[] lines = this.block.rectangleLines();
        if (this.hitNum > 0) {
            this.hitNum--;
        }
        Point nextP = new Point(collisionPoint.getX() + currentVelocity.getDx(),
                collisionPoint.getY() + currentVelocity.getDy());
        Line myLine = new Line(collisionPoint, nextP);
        // if the collision is on the up/down size of the block
        if (lines[0].isIntersecting(myLine) || lines[1].isIntersecting(myLine)) {
            // if the collision is also on the left/right size of the block
            if (lines[2].isIntersecting(myLine) || lines[3].isIntersecting(myLine)) {
                return new Velocity((-1) * currentVelocity.getDx(), (-1) * currentVelocity.getDy());
            }
            return new Velocity(currentVelocity.getDx(), (-1) * currentVelocity.getDy());
        // if the collision is only on the left/right size of the block - change
        // the dx
        } else if (lines[2].isIntersecting(myLine) || lines[3].isIntersecting(myLine)) {
            return new Velocity((-1) * currentVelocity.getDx(), currentVelocity.getDy());
        }
        return currentVelocity;
    }
    /**
     * @param surface function to draw the block on the screen
     */
    public void drawOn(DrawSurface surface) {
//        surface.setColor(this.color);
        int x = (int) this.block.getUpperLeft().getX();
        int y  = (int) this.block.getUpperLeft().getY();
        int width = (int) this.block.getWidth();
        int height = (int) this.block.getHeight();
        if (this.images.containsKey(this.hitNum)) {
            surface.drawImage(x, y, this.images.get(this.hitNum));
        } else if (this.images.containsKey(0)) {
            surface.drawImage(x, y, this.images.get(0));
        } else if (this.colors.containsKey(this.hitNum)) {
            surface.setColor(this.colors.get(this.hitNum));
            surface.fillRectangle(x, y, width, height);
        } else if (this.colors.containsKey(0)) {
            surface.setColor(this.colors.get(0));
            surface.fillRectangle(x, y, width, height);
        } else if (this.color != null) {
            surface.setColor(this.color);
            surface.fillRectangle(x, y, width, height);
        }
        Line[] lines = this.block.rectangleLines();
        //  surface.setColor(java.awt.Color.BLACK);
        if (this.hasBorder) {
            surface.setColor(this.borderColor);
            for (int i = 0; i < lines.length; i++) {
                int startX = (int) lines[i].start().getX();
                int startY = (int) lines[i].start().getY();
                int endX = (int) lines[i].end().getX();
                int endY = (int) lines[i].end().getY();
                surface.drawLine(startX, startY, endX, endY);
            }
        }
        double middleX = this.block.getUpperLeft().getX() + ((this.block.getWidth() / 2));
        double middleY = this.block.getUpperLeft().getY() + ((this.block.getHeight() / 2));
//      surface.setColor(Color.WHITE);
//      surface.drawText((int) middleX, (int) middleY, String.valueOf(this.hitNum), 16);
    }
    /**
     * @return the color of the block
     */
    public Map<Integer, Color> getColor() {
        return this.colors;
        }
    /**
     * set the color to the block.
     * @param newColor the color of the block
     */
    public void setColor(java.awt.Color newColor) {
        this.color = newColor;
    }

    /**
     * animated block.
     * @param dt the amount of seconds passed since the last call
     */
    public void timePassed(double dt) {
        return;
    }
    /**
     * add the block to the game.
     * @param gameLevel our game
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
        gameLevel.addCollidable(this);
    }
    /**
     * this method removes the block from a game.
     * @param gameLevel the game.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }
    /**
     * this method returns the block's current number of hits.
     * @return the block's current number of hits.
     */
    public int getHits() {
        return this.hitNum;
    }
    /**
     * this method gets a ball that is hitting the block and notify
     * all the listeners about the hit.
     * @param hitter the hitting ball.
     */
    private void notifyHit(Ball hitter) {
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
    /**
     * this method adds a given hit listener to the block's hit listeners list.
     * @param hl the given hit listener.
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }
    /**
     * this method removes a given hit listener
     * from the block's hit listeners list.
     * @param hl the given hit listener.
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
    /**
     *
     * @return true id its a death block
     */
    public boolean isDeathB() {
        return this.isDeathBlock;
    }

    /**
     * this method sets the location of this block.
     * @param upperLeft the new location of the upper left corner of the block.
     */
    public void setUpperLeft(Point upperLeft) {
        this.block = new Rectangle(upperLeft, this.block.getWidth(),
                this.block.getHeight());
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
     * @param numberOfHits the number of hits.
     */
    public void setHits(int numberOfHits) {
        this.hitNum = numberOfHits;
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
     * @param hasBorderB the wanted option.
     */
    public void setBorder(boolean hasBorderB) {
        this.hasBorder = hasBorderB;
    }
    /**
     * this method sets the color of the border of the block.
     * @param bordersColor the given color.
     */
    public void setBorderColor(Color bordersColor) {
        this.borderColor = bordersColor;
        this.hasBorder = true;
    }
}