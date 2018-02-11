import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import biuoop.GUI;
import constructions.HighScoresTable;
import animation.AnimationRunner;
import interfaces.LevelInformation;
import interfaces.Menu;
import interfaces.Task;
import io.LevelSetsReader;
import io.LevelSpecificationReader;
import oldlevels.Level1;
import oldlevels.Level2;
import oldlevels.Level3;
import oldlevels.Level4;
import menu.ExitTask;
import menu.LevelSet;
import menu.MenuAnimation;
import menu.PlayGameTask;
import menu.ShowHiScoresTask;

/**
 *
 * @author TOMER GRADY
 * 205660863
 */
public class Ass6Game {
    /**
     *
     * @param args main arguments
     */
    public static void main(String[] args) {
        int startNumOfLives = 7;
        List<LevelSet> levelSet = null;
        GUI gui = new GUI("Arkanoid", 800, 600);
        AnimationRunner runner = new AnimationRunner(gui, 60);
        File file = new File("highScores");
        LevelSpecificationReader lsr = new LevelSpecificationReader();
        Menu<Task<Void>> subMenu = new MenuAnimation<>(gui.getKeyboardSensor(), runner);
        HighScoresTable hst = HighScoresTable.loadFromFile(file);

        try {
            if (args.length != 0) {
                levelSet = LevelSetsReader.fromReader(new InputStreamReader(ClassLoader.
                        getSystemClassLoader().getResourceAsStream(args[0])));
            } else {
                levelSet = LevelSetsReader.fromReader(new InputStreamReader(ClassLoader.
                        getSystemClassLoader().getResourceAsStream("level_sets.txt")));
            }
            for (LevelSet selection : levelSet) {
                subMenu.addSelection(selection.getKey(), selection.getMessage(), new PlayGameTask(gui, runner, hst,
                        selection.getPath(), startNumOfLives, file));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        Menu<Task<Void>> menu = new MenuAnimation<>(gui.getKeyboardSensor(), runner);
        menu.addSubMenu("s", "Start Game", subMenu, levelSet);

        menu.addSelection("h", "High Scores",
                new ShowHiScoresTask(runner, gui.getKeyboardSensor(), hst));
        menu.addSelection("q", "Quit", new ExitTask(gui));
        while (true) {
            runner.run(menu);
            Task<Void> task = menu.getStatus();
            task.run();
            menu = new MenuAnimation<>(gui.getKeyboardSensor(), runner);
            menu.addSubMenu("s", "Start Game", subMenu, levelSet);
//            menu.addSelection("s", "Start Game", (Task<Void>) subMenu);
            menu.addSelection("h", "High Scores",
                    new ShowHiScoresTask(runner, gui.getKeyboardSensor(), hst));
            menu.addSelection("q", "Quit", new ExitTask(gui));
        }
    }
    /**
     * this method set the levels list of the game by the user arguments.
     * @param args arguments from user
     * @return array of the levels for the game
     */
    public static ArrayList<LevelInformation> setLevels(String[] args) {
        ArrayList<LevelInformation> levels = new ArrayList<LevelInformation>();
        ArrayList<Integer> levelsNum = new ArrayList<Integer>();

        for (int i = 0; i < args.length; i++) {
            try {
                levelsNum.add(Integer.parseInt(args[i]));
            } catch (Exception e) {
                String a = "do nothing";
            }
        }
        // it's empty
        if (levelsNum.isEmpty()) {
            levels.add(new Level1());
            levels.add(new Level2());
            levels.add(new Level3());
            levels.add(new Level4());
            return levels;
            // check id the arguments between 1-4
        } else {
            for (int i = 0; i < levelsNum.size(); i++) {
                if (levelsNum.get(i) == 1) {
                    levels.add(new Level1());
                } else if (levelsNum.get(i) == 2) {
                    levels.add(new Level2());
                } else if (levelsNum.get(i) == 3) {
                    levels.add(new Level3());
                } else if (levelsNum.get(i) == 4) {
                    levels.add(new Level4());
                }
            }
            // if no arguments between 1 to 4
            if (levels.isEmpty()) {
                levels.add(new Level1());
                levels.add(new Level2());
                levels.add(new Level3());
                levels.add(new Level4());
            }
            return levels;
        }
    }
}