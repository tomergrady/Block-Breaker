package menu;

import java.io.File;
import java.io.InputStreamReader;
import java.util.List;

import biuoop.GUI;
import animation.AnimationRunner;
import constructions.HighScoresTable;
import interfaces.LevelInformation;
import interfaces.Task;
import io.LevelSpecificationReader;
import runs.GameFlow;

/**
 * this class represents a StartGameTask object.
 */
public class PlayGameTask implements Task<Void> {
    private GUI gui;
    private AnimationRunner runner;
    private HighScoresTable table;
    private List<LevelInformation> levels;
    private int lives;
    private File highScoresFile;
    private String path = null;
    /**
     * construct a StartGameTask from a GUI, an animationRunner,
     * a highScoresTable, a levelInformation list,
     * a number of lives and a highScoresFile.
     * @param gui the given GUI.
     * @param animationRunner the given animationRunner.
     * @param table the given highScoresTable.
     * @param levels the given levelInformation list.
     * @param lives the number of lives.
     * @param highScoresFile the given highScores file.
     */
    public PlayGameTask(GUI gui, AnimationRunner animationRunner,
                         HighScoresTable table, List<LevelInformation> levels,
                         int lives, File highScoresFile) {
        this.gui = gui;
        this.runner = animationRunner;
        this.table = table;
        this.levels = levels;
        this.lives = lives;
        this.highScoresFile = highScoresFile;
    }
    /**
     * construct a StartGameTask from a GUI, an animationRunner,
     * a highScoresTable, a levelInformation list,
     * a number of lives and a highScoresFile.
     * @param gui the given GUI.
     * @param animationRunner the given animationRunner.
     * @param table the given highScoresTable.
     * @param path the given levelInformation path.
     * @param lives the number of lives.
     * @param highScoresFile the given highScores file.
     */
    public PlayGameTask(GUI gui, AnimationRunner animationRunner,
                        HighScoresTable table, String path,
                        int lives, File highScoresFile) {
        this.gui = gui;
        this.runner = animationRunner;
        this.table = table;
        this.path = path;
        this.lives = lives;
        this.highScoresFile = highScoresFile;
    }
    /**
     * this method runs this task.
     * @return unimplemented option.
     */
    public Void run() {
        GameFlow game = new GameFlow(runner, gui, this.lives, this.table, highScoresFile);
        LevelSpecificationReader lsr = new LevelSpecificationReader();
        List<LevelInformation> listOfLevels;
        //open the path and make a list out of it.
        if (this.path != null) {
            try {
                listOfLevels = lsr.fromReader(new InputStreamReader(ClassLoader
                        .getSystemClassLoader().getResourceAsStream(this.path)));
                game.runLevels(listOfLevels);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            game.runLevels(this.levels);
        }
        try {
            table.save(this.highScoresFile);
        } catch (Exception e) {
            System.out.println("run");;
        }
        return null;
    }
}

