package oldlevels;

import java.awt.Color;
import biuoop.DrawSurface;
import interfaces.Sprite;
/**
 * This class represents the background of the level 3.
 */
public class Background3 implements Sprite {
    /**
     * this method draws the background of level 3.
     * @param d the DrawSurface to draw on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.decode("#2EFE9A"));
        d.fillRectangle(0, 30, d.getWidth(), d.getHeight());
        d.setColor(Color.DARK_GRAY);
        d.fillRectangle(155, 180, 10, 240);
        d.fillRectangle(145, 410, 30, 40);
        d.setColor(Color.ORANGE);
        d.fillCircle(160, 180, 15);
        d.setColor(Color.RED.brighter());
        d.fillCircle(160, 180, 10);
        d.setColor(Color.WHITE);
        d.fillCircle(160, 180, 3);
        d.setColor(Color.DARK_GRAY.darker());
        d.fillRectangle(110, 450, 100, 150);
        d.setColor(Color.WHITE);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                d.fillRectangle(120 + 30 * i, 460 + 30 * j, 22, 25);
            }
        }

    }
    /**
     * this method notifies that a time unit has passed.
     * @param dt the amount of seconds passed since the last call
     */
    public void timePassed(double dt) {
    }
}