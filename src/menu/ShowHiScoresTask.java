package menu;

import biuoop.KeyboardSensor;
import animation.AnimationRunner;
import animation.HighScoresAnimation;
import animation.KeyPressStoppableAnimation;
import constructions.HighScoresTable;
import interfaces.Task;

/**
 * this class represents a ShowHiScoresTask object.
 */
public class ShowHiScoresTask implements Task<Void> {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private HighScoresTable hst;
    /**
     * constructs a ShowHiScoresTask object from an animationRunner,
     * a keyboardSensor and a highScoresTable.
     * @param animationRunner the given AnimationRunner.
     * @param keyboardSensor the given KeyboardSensor.
     * @param table the given HighScoresTable.
     */
    public ShowHiScoresTask(AnimationRunner animationRunner,
                            KeyboardSensor keyboardSensor,
                            HighScoresTable table) {
        this.animationRunner = animationRunner;
        this.keyboardSensor = keyboardSensor;
        this.hst = table;
    }
    /**
     * this method runs this task.
     * @return unimplemented option.
     */
    public Void run() {
        this.animationRunner.run(new KeyPressStoppableAnimation(
                new HighScoresAnimation(this.hst, this.keyboardSensor),
                this.keyboardSensor, "space"));
        return null;
    }
}

