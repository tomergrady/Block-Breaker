package oldlevels;

import java.awt.Color;
import biuoop.DrawSurface;
import interfaces.Sprite;
/**
 * This class represents the background of level 2.
 */
public class Background2 implements Sprite {
    /**
     * this method draws the background of level 2.
     * @param d the DrawSurface to draw on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.white);
        d.fillRectangle(0, 30, d.getWidth(), d.getHeight());
        d.setColor(Color.yellow.brighter());
        d.fillCircle(150, 150, 40);
        int numberOfRays = 40;
        for (int i = 1; i <= numberOfRays; i++) {
          d.drawLine(150, 150, 740 / numberOfRays * i, 250);
          d.setColor(Color.yellow);
          d.fillCircle(150, 150, 50);
        }
    }
    /**
     * this method notifies that a time unit has passed.
     * @param dt the amount of seconds passed since the last call
     */
    public void timePassed(double dt) {
    }
}
