package oldlevels;

import java.awt.Color;
import biuoop.DrawSurface;
import interfaces.Sprite;
/**
 * Tomer Grady 205660863
 * This class represents the background of level 1.
 */
public class Background1 implements Sprite {

    /**
     * this method draws the background of the first level.
     * @param d the DrawSurface to draw on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.LIGHT_GRAY);
        d.setColor(Color.black);
        d.fillRectangle(30, 30, d.getWidth(), d.getHeight());
        d.setColor(Color.BLUE);
        d.drawCircle(400, 200, 60);
        d.drawCircle(400, 200, 90);
        d.drawCircle(400, 200, 120);
        d.drawLine(400, 100, 400, 300);
        d.drawLine(420, 200, 540, 200);
        d.drawLine(380, 200, 260, 200);
    }
    /**
     * this method notifies that a time unit has passed.
     * @param dt the amount of seconds passed since the last call
     */
    public void timePassed(double dt) {
    }
}
