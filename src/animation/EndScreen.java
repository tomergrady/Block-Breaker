package animation;

import java.awt.Color;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collision.Counter;
import interfaces.Animation;
import biuoop.GUI;
/**
 * This class represents an end screen object.
 */
public class EndScreen implements Animation {
    private int score;
    private KeyboardSensor keyboardSensor;
    private boolean win;
    private boolean close;
    private GUI gui;
    /**
     * construct end screen object.
     * @param score the score counter.
     * @param gui the graffic user interface.
     * @param win the boolean variable.
     */
    public EndScreen(Counter score, GUI gui, boolean win) {
        this.score = score.getValue();
        this.gui = gui;
        this.keyboardSensor = this.gui.getKeyboardSensor();
        this.win = win;
        this.close = false;
    }
    /**
     * this method draws the end screen and wait until user pressed SPACE key to close it.
     * @param surface the DrawSurface to draw on.
     * @param dt the amount of seconds passed since the last call
     */
    public void doOneFrame(DrawSurface surface, double dt) {
//        if (this.keyboardSensor.isPressed(KeyboardSensor.SPACE_KEY)) {
//            this.close = true;
//        }
        surface.setColor(Color.white);
        surface.fillRectangle(0, 0, surface.getWidth(), surface.getHeight());
        surface.setColor(Color.BLACK);
        if (win) {
            surface.setColor(Color.BLUE);
            surface.drawText(150, 200, "YOU WIN", 100);
        } else {
            surface.setColor(Color.RED);
            surface.drawText(110, 200, "GAME OVER ", 100);
        }
        surface.setColor(Color.BLACK);
        surface.drawText(250, 350, "Press space to continue", 30);
        surface.setColor(Color.BLUE);
        surface.drawText(130, 580, "Final score: " + this.score, 80);
    }
    /**
     * this method return true if the screen has to be closed, false otherwise.
     * @return true if the screen has to be closed false otherwise.
     */
    public boolean shouldStop() {
        return this.close;
    }

}
