package drawing;

import java.awt.Color;
import biuoop.DrawSurface;
import collision.Counter;
import geometry.Point;
import geometry.Rectangle;
import interfaces.Sprite;
import runs.GameLevel;
/**
 * This class represents a lives indicator object.
 */
public class LivesIndicator implements Sprite {
    private Counter lives;
    private Rectangle rectangle;
    /**
     * construct a lives indicator from a given lives counter.
     * @param lives the given lives counter.
     */
    public LivesIndicator(Counter lives) {
       this.lives = lives;
       this.rectangle = new Rectangle(new Point(0, 0), 300, 30);
    }
    /**
     * this method draws the lives indicator on given DrawSurface.
     * @param surface the DrawSurface to draw on.
     */
    public void drawOn(DrawSurface surface) {
       surface.setColor(Color.BLACK);
       surface.drawText((int) (this.rectangle.getUpperLeft().getX()
                             + this.rectangle.getWidth() / 2),
                    (int) (this.rectangle.getUpperLeft().getY()
                        + this.rectangle.getHeight() / 2 + 5),
                          "Lives: " + Integer.toString(this.lives.getValue()), 15);
    }
    /**
     * this method notifies the lives indicator that a time unit has passed.
     * @param dt the amount of seconds passed since the last call
     */
    public void timePassed(double dt) {
    }
    /**
     * this method adds the lives indicator to a game.
     * @param gameLevel the game.
     */
    public void addToGame(GameLevel gameLevel) {
       gameLevel.addSprite(this);
    }
}
