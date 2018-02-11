package drawing;
import java.awt.Color;


import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;
import interfaces.Sprite;
import runs.GameLevel;
/**
 * This class represents a score indicator.
 */
public class LevelNameIndicator implements Sprite {
    private String levelName;
    private Rectangle rectangle;
    /**
     * Construct a Level name indicator from a string object.
     * @param name the given level name.
     */
    public LevelNameIndicator(String name) {
        this.levelName = name;
        this.rectangle = new Rectangle(new Point(0, 0), 800, 30);
    }
    /**
     * this method draws the score indicator on given DrawSurface.
     * @param surface the DrawSurface to draw on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        surface.drawText((int) (this.rectangle.getUpperLeft().getX()
                              + this.rectangle.getWidth() / 2 + 140),
                         (int) (this.rectangle.getUpperLeft().getY()
                              + this.rectangle.getHeight() / 2 + 5),
                         "LevelName: " + levelName, 15);
    }
    /**
     * this method notifies the score indicator that a time unit has passed.
     * @param dt the amount of seconds passed since the last call
     */
    public void timePassed(double dt) {
    }
    /**
     * this method adds the score indicator to a game.
     * @param gameLevel the game.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }
}