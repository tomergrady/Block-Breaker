package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import interfaces.Animation;

/**
 * this class is an animation decorator that stops animations.
 */
public class KeyPressStoppableAnimation implements Animation {
    private Animation animation;
    private KeyboardSensor keyboardSensor;
    private String endKey;
    private boolean close;
    private boolean isAlreadyPressed;
    /**
     * constructs a Key Press Stoppable Animation.
     * @param animation the given animation.
     * @param keyboardSensor the given keyboard sensor.
     * @param endKey the key that stops the given animation.
     */
    public KeyPressStoppableAnimation(Animation animation,
                                      KeyboardSensor keyboardSensor, String endKey) {
        this.animation = animation;
        this.keyboardSensor = keyboardSensor;
        this.endKey = endKey;
        this.close = false;
        this.isAlreadyPressed = true;
    }
    /**
     * this method draws each frame of the animations
     * and checks if user pressed SPACE key to close it.
     * @param d the DrawSurface.
     * @param dt the dt value of the game.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        this.animation.doOneFrame(d, dt);
        if (this.keyboardSensor.isPressed(this.endKey)
                && !this.isAlreadyPressed) {
            this.close = true;
        }
        if (!this.keyboardSensor.isPressed(this.endKey)) {
            this.isAlreadyPressed = false;
        }
    }
    /**
     * this method tells if the animation drawing should stop.
     * @return true if the animation drawing should stop, false otherwise.
     */
    public boolean shouldStop() {
        return this.close;
    }
}
