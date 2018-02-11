package runs;

import java.util.List;

import animation.AnimationRunner;
import animation.EndScreen;
import animation.KeyPressStoppableAnimation;
import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import collision.Counter;
import constructions.HighScoresTable;
import constructions.ScoreInfo;
import drawing.LevelNameIndicator;
import drawing.ScoreIndicator;
import interfaces.LevelInformation;
import java.io.File;
/**
 * this class manages the game handling.
 */
public class GameFlow {
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private Counter lives;
    private Counter score;
    private GUI gui;
    private boolean win;
    private HighScoresTable hst;
    private File file;
    /**
    * construct a gameFlow object.
    * @param ar an animation runner.
    * @param gui the graffic interface for the game
    * @param numOfLives the initial number of lives in this game.
     *@param hst the high score table of the game
     *@param file the file og the score table
    */
   public GameFlow(AnimationRunner ar, GUI gui, int numOfLives, HighScoresTable hst, File file) {
       this.animationRunner = ar;
       this.gui = gui;
       this.keyboardSensor = gui.getKeyboardSensor();
       this.lives = new Counter(numOfLives);
       this.score = new Counter(0);
       this.win = true;
       this.hst = hst;
       this.file = file;
   }
    /**
     * this method gets a list of levelInformation objects
     * and runs the appropriate levels.
     * @param levelsList the given list.
     */
    public void runLevels(List<LevelInformation> levelsList) {
        for (LevelInformation levelInfo : levelsList) {

            GameLevel level = new GameLevel(levelInfo, this.gui, this.animationRunner,  this.score, this.lives);
            level.initialize();
            ScoreIndicator si = new ScoreIndicator(this.score);
            LevelNameIndicator lni = new LevelNameIndicator(levelInfo.levelName());
            si.addToGame(level);
            lni.addToGame(level);
            while (this.lives.getValue() > 0) {
                level.playOneTurn();
                if (level.isWin()) {
                    this.score.increase(100);
                    break;
                } else {
                    this.score = level.getScore();
                    this.lives.decrease(1);
                }
            }
            if (this.lives.getValue() == 0) {
                this.win = false;
                break;
            }
        }
        int rank = this.hst.getRank(score.getValue());
  //      this.hst.clear();
        if (rank <= 5) {
            DialogManager dialog = this.gui.getDialogManager();
            String name = dialog.showQuestionDialog("Name", "What is your name?", "");
            ScoreInfo newScore = new ScoreInfo(name, this.score.getValue());
            this.hst.add(newScore);
        }
        try {
            this.hst.save(this.file);
        } catch (Exception e) {
            // do nothing
            int x = 1;
        }
        this.animationRunner.run(new KeyPressStoppableAnimation(
                new EndScreen(this.score, this.gui, this.win),
                this.keyboardSensor, "space"));
//        this.animationRunner.run(new KeyPressStoppableAnimation(
//                new HighScoresAnimation(this.hst, this.gui.getKeyboardSensor()),
//                this.keyboardSensor, "space"));
    }
}