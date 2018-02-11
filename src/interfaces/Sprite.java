package interfaces;
import biuoop.DrawSurface;

/**
 * @author TOMER GRADY
 * 205660863
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     * @param d the object that know how to paint
     */
    void drawOn(DrawSurface d);
    /**
     * notify the sprite that time has passed.
     * @param dt the amount of seconds passed since the last call
     */
    void timePassed(double dt);
}