package drawing;

import java.awt.Color;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import interfaces.Animation;
/**
 * This class represents a pause screen object.
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    /**
     * construct a pause screen object.
     * @param keyboard the keyboard sensor.
     */
    public PauseScreen(KeyboardSensor keyboard) {
        this.keyboard = keyboard;
        this.stop = false;
    }
    /**
     * this method draws the pause screen.
     * and checks if user pressed SPACE key to close it.
     * @param d the DrawSurface to draw on.
     * @param dt the amount of seconds passed since the last call
     */
    public void doOneFrame(DrawSurface d, double dt) {
        //create the screen view
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.RED);
        d.drawText(290, d.getHeight() / 2 - 100, "paused", 70);
        d.drawText(200, d.getHeight() / 2, "press space to continue", 40);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }
    /**
     * this method return true if the screen needs to be closed, false otherwise.
     * @return true if SPACE key was pressed and the screen has to be closed.
     * false otherwise.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
