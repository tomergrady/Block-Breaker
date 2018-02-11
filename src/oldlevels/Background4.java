package oldlevels;

import java.awt.Color;
import biuoop.DrawSurface;
import interfaces.Sprite;
/**
 * Created by TOMER Grady on 5/25/2017.
 * This class represents the background of the level 4.
 */
public class Background4 implements Sprite {
    /**
     * this method draws the background of level 4.
     * @param d the DrawSurface to draw on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.decode("#58ACFA"));

        d.fillRectangle(0, 30, d.getWidth(), d.getHeight());
        d.setColor(Color.WHITE);
        for (int i = 0; i < 12; i++) {
            d.drawLine(90 + i * 10, 470, 60 + i * 10, 550);
        }

        d.setColor(Color.decode("#D8D8D8"));
        d.fillCircle(100, 450, 25);
        d.fillCircle(120, 420, 30);
        d.setColor(Color.decode("#BDBDBD"));
        d.fillCircle(150, 430, 35);
        d.fillCircle(180, 450, 35);
        d.setColor(Color.WHITE);
        d.fillCircle(140, 470, 25);
        for (int i = 0; i < 10; i++) {
            d.drawLine(590 + i * 10, 470, 620 + i * 10, 550);
        }

        d.setColor(Color.decode("#D8D8D8"));
        d.fillCircle(600, 450, 25);
        d.fillCircle(620, 420, 30);
        d.setColor(Color.decode("#E6E6E6"));
        d.fillCircle(650, 430, 35);
        d.fillCircle(680, 450, 35);
        d.setColor(Color.decode("#A4A4A4"));
        d.fillCircle(640, 470, 25);
    }
    /**
     * this method notifies that a time unit has passed.
     * @param dt the amount of seconds passed since the last call
     */
    public void timePassed(double dt) {
    }
}