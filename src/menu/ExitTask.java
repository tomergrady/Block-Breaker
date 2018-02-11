package menu;
import biuoop.GUI;
import interfaces.Task;

/**
 * Created by TOMER GRADY on 6/10/2017.
 * this class represents a ExitTask.
 */
public class ExitTask implements Task<Void> {
    private GUI gui;
    /**
     * construct an ExitTask from a given GUI object.
     * @param gui the given GUI.
     */
    public ExitTask(GUI gui) {
        this.gui = gui;
    }
    /**
     * this method runs this task.
     * @return unimplemented option.
     */
    public Void run() {
        this.gui.close();
        System.exit(0);
        return null;
    }
}