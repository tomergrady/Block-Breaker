package animation;

import java.awt.Color;
import biuoop.DrawSurface;
import collidable.SpriteCollection;
import interfaces.Animation;
/**
 * This class represents a count down animation object.
 */
public class CountdownAnimation implements Animation {
    private boolean running;
    private long numOfMillis;
    private int counter;
    private int countFrom;
    private SpriteCollection gameScreen;
    private long initiationTime;
    /**
     * construct a count down animation.
     * @param numOfSeconds time of displaying the count down animation.
     * @param countFrom the number to count down from.
     * @param gameScreen the game's sprites.
     */
    public CountdownAnimation(double numOfSeconds,
            int countFrom, SpriteCollection gameScreen) {
        this.running = true;
        this.numOfMillis = (long) (numOfSeconds * 1000);
        this.countFrom = countFrom;
        this.counter = countFrom;
        this.gameScreen = gameScreen;
        this.initiationTime = System.currentTimeMillis();
    }
    /**
     * this method draws each frame of the animation
     * of the count down animation on a given DrawSurface.
     * when count down reaches 0 it changes the running member to false,
     * so the animation will stop.
     * @param d the DrawSurface to draw on.
     * @param dt the amount of seconds passed since the last call
     */
    public void doOneFrame(DrawSurface d, double dt) {
        if (this.counter == 0) {
            this.running = false;
        }
        d.setColor(Color.blue);
        d.fillRectangle(30, 30, d.getWidth() - 60, d.getHeight() - 60);
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.RED.darker());
        d.drawText(385, 450, Integer.toString(this.counter), 100);
        if (System.currentTimeMillis() - this.initiationTime
          > this.numOfMillis / this.countFrom) {
            this.initiationTime = System.currentTimeMillis();
            this.counter--;
        }
    }
    /**
     * this method returns true if count down animation has to stop,
     * false otherwise.
     * @return true if count down animation has to stop, false otherwise.
     */
    public boolean shouldStop() {
        return !this.running;
    }
}

