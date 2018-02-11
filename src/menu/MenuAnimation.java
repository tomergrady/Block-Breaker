package menu;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import animation.AnimationRunner;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import interfaces.Menu;
import interfaces.Task;

/**
 * this class represents a menu animation object.
 * @param <T> the menu type.
 */
public class MenuAnimation<T> implements Menu<T> {
    private List<MenuSelection> menuList;
    private T status;
    private boolean close;
    private KeyboardSensor botton;
    private List<MenuSelection> subMenuList;
    private AnimationRunner runner;
    private MenuAnimation<T> subMenuAnimation;
    private List<LevelSet> levelsInSubMenu;
    /**
     * construct a menu animation object.
     * @param keyboardSensor the given keyboardSensor.
     * @param runner the animation runner.
     */
    public MenuAnimation(KeyboardSensor keyboardSensor, AnimationRunner runner) {
        this.menuList = new ArrayList<>();
        this.subMenuList = new ArrayList<>();
        this.close = false;
        this.botton = keyboardSensor;
        this.runner = runner;
    }
    /**
     * this method draws the object to the screen.
     * @param d a draw surface.
     * @param dt the dt value.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.GREEN);
        d.drawText(210, 130, "ARKNOID", 60);
        for (int i = 0; i < this.menuList.size(); i++) {
            String massage = this.menuList.get(i).getMessage();
            String key = this.menuList.get(i).getKey();
            d.setColor(Color.BLUE);
            d.drawText(200, 250 + 40 * i, "" + " (" + key + ") " + massage, 40);
        }
        for (int i = 0; i < this.subMenuList.size(); i++) {
            String massage = this.subMenuList.get(i).getMessage();
            String key = this.subMenuList.get(i).getKey();
            d.setColor(Color.BLUE);
            d.drawText(200, 200 + 40 * i, "" + " (" + key + ") " + massage, 40);
        }
//        if (this.botton.isPressed("s")) {
//            this.runner.run(subMenuAnimation);
//        }
        for (int i = 0; i < this.subMenuList.size(); i++) {
            if (this.botton.isPressed(this.subMenuList.get(i).getKey())) {
                this.status = (T) this.subMenuList.get(i).getTask();
             //   System.out.println(this.status);
                this.runner.run(subMenuAnimation);
                Task<Void> task = (Task<Void>) this.subMenuAnimation.getStatus();
                task.run();
                this.subMenuAnimation.close = false;
   //             this.close = false;
            }
        }
        for (int i = 0; i < this.menuList.size(); i++) {
            if (this.botton.isPressed(this.menuList.get(i).getKey())) {
                this.status = (T) this.menuList.get(i).getTask();
                this.close = true;
            }
        }
    }
    /**
     * this method tells if the animation should stop.
     * @return true if the animation drawing should stop, false otherwise.
     */
    public boolean shouldStop() {
        return this.close;
    }
    /**
     * this method adds a selection to this menu.
     * @param key the key of the selection.
     * @param message the name of the selection.
     * @param returnVal the selection value according to the menu type.
     */
    public void addSelection(String key, String message, T returnVal) {
        MenuSelection<T> selection = new MenuSelection<T>(key, message, returnVal, false);
        this.menuList.add(selection);
    }
    /**
     * this method returns the selected value after the occurrence
     * of a selection event, according to the menu type.
     * @return the selected value.
     */
    public T getStatus() {
        return this.status;
    }
    /**
     * this method resets this menu.
     */
    public void reset() {
        this.status = null;
        this.close = false;
    }
    /**
     * this method adds a sub-menu to this menu.
     * @param key the key of the selection.
     * @param message the name of the selection.
     * @param subMenu the given sub-menu.
     * @param levelSet list for sub menu.
     */
    public void addSubMenu(String key, String message, Menu<T> subMenu, List<LevelSet> levelSet) {
        this.subMenuList.add(new MenuSelection<Menu<T>>(key, message, subMenu, true));
        this.subMenuAnimation = (MenuAnimation<T>) subMenu;
        this.levelsInSubMenu = levelSet;
    }
}
