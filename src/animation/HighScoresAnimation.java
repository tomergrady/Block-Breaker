package animation;

/**
 * Created by TOMER on 6/8/2017.
 */

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import constructions.HighScoresTable;
import interfaces.Animation;
import java.awt.Color;

/**
 * This class represents a High Scores Animation.
 */
public class HighScoresAnimation implements Animation {
    private KeyboardSensor keyboardSensor;
    private boolean close;
    private HighScoresTable table;
    /**
     * construct end screen object.
     * @param hst High Scores Table of the game.
     * @param keyboardSensor the keyboardSensor.
     */
    public HighScoresAnimation(HighScoresTable hst, KeyboardSensor keyboardSensor) {
        this.keyboardSensor = keyboardSensor;
        this.table =  hst;
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
        surface.drawText(280, 190, "High Scores Table", 30);
        for (int i = 0; i < this.table.getHighScores().size(); i++) {
            surface.drawText(280, 230 + 32 * i,
                    this.table.getHighScores().get(i).getName(), 30);
            surface.drawText(250, 233 + 32 * i,
                    Integer.toString(i + 1), 28);
            surface.drawText(264, 233 + 32 * i,
                    ".", 25);
//            surface.drawText(Integer.toString(i + 1), 30);
            surface.drawText(470, 230 + 32 * i,
                    Integer.toString(this.table.getHighScores().get(i).getScore()), 30);
            surface.setColor(Color.BLACK);
            surface.drawText(250, 550, "Press space to continue", 30);

        }
    }
    /**
     * this method return true if the screen has to be closed, false otherwise.
     * @return true if the screen has to be closed false otherwise.
     */
    public boolean shouldStop() {
        return this.close;
    }
}

