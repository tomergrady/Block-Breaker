package drawing;

import java.awt.Color;


import biuoop.DrawSurface;
import collision.Counter;
import geometry.Point;
import geometry.Rectangle;
import interfaces.Sprite;
import runs.GameLevel;
/**
 * @author TOMER GRADY 205660863
 * This class represents a score indicator.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;
    private Rectangle rectangle;
    /**
     * Construct a score indicator from a score counter object.
     * @param score the given score counter.
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
        this.rectangle = new Rectangle(new Point(0, 0), 800, 30);
    }
    /**
     * this method draws the score indicator on given DrawSurface.
     * @param surface the DrawSurface to draw on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.LIGHT_GRAY);
      //  surface.fillRectangle(0, 0, 800, 30);
        surface.setColor(Color.BLACK);
        surface.drawText((int) (this.rectangle.getUpperLeft().getX()
                              + this.rectangle.getWidth() / 2 - 30),
                         (int) (this.rectangle.getUpperLeft().getY()
                              + this.rectangle.getHeight() / 2 + 5),
                         "Score: "
                              + Integer.toString(this.score.getValue()), 15);
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