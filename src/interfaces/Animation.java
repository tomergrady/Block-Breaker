package interfaces;

import biuoop.DrawSurface;

/**
 * interdace for animation.
 * @author TOMER GRADY 205660863
 */
public interface Animation {
    /**
     * this method draws the the animation object to the screen.
     * @param d a draw surface.
     * @param dt the amount of seconds passed since the last call
     */
    void doOneFrame(DrawSurface d, double dt);
    /**
     * this method tells if the animation should stop.
     * @return true if the animation drawing should stop, false otherwise.
     */
    boolean shouldStop();
}