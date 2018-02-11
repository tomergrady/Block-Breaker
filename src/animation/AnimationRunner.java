package animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import interfaces.Animation;

/**
 * this class represents an animation runner.
 * it contains the main graphic loop in the game.
 */
public class AnimationRunner {
    private GUI gui;
    private double framesPerSecond;
    private Sleeper sleeper;
    /**
     * construct an animation runner from a given gui object.
     * @param gui the given gui.
     * @param fps frames per second
     */
    public AnimationRunner(GUI gui, int fps) {
        this.gui = gui;
        this.sleeper = new Sleeper();
        this.framesPerSecond = fps;
    }
    /**
     * this function contains the main graphic loop of the game.
     * the function gets an animation object and uses the gui to show it.
     * @param animation the animation to show.
     */
    public void run(Animation animation) {
        double millisecondsPerFrame = 1000 / this.framesPerSecond;
        double dt = (double) 1 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis();
            DrawSurface d = this.gui.getDrawSurface();
            animation.doOneFrame(d, dt);
            this.gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = (long) millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
        return;
    }

    /**
     * this method returns the gui of the animation runner.
     * @return the gui of the animation runner.
     */
    public GUI getGui() {
        return this.gui;
    }
}
