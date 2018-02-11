package menu;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import interfaces.Menu;
import interfaces.Task;

/**
 * this class represents a menu animation object.
 * @param <T> the menu type.
 */
public class SubMenu<T> implements Menu<T>, Task<T> {
    private List<MenuSelection> menuList;
    private T status;
    private boolean close;
    private KeyboardSensor botton;
    private List<Boolean> isSubMenu;
    private List<Menu<T>> subMenus;
    /**
     * construct a menu animation object.
     * @param keyboardSensor the given keyboardSensor.
     */
    public SubMenu(KeyboardSensor keyboardSensor) {
        this.menuList = new ArrayList<>();
        this.close = false;
        this.botton = keyboardSensor;
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
      //  d.drawText(210, 130, "ARKNOID", 60);
        for (int i = 0; i < this.menuList.size(); i++) {
            String massage = this.menuList.get(i).getMessage();
            String key = this.menuList.get(i).getKey();
            d.setColor(Color.BLUE);
            d.drawText(200, 200 + 40 * i, "" + " (" + key + ") " + massage, 40);
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
        MenuSelection<T> selection = new MenuSelection<T>(key, message, returnVal, true);
        this.menuList.add(selection);
    }
    /**
     * this method adds a selection to this menu.
     * @param selection the selection.
     */
    public void addSelectionFromS(MenuSelection selection) {
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
     * this method get a list of menu selections.
     * @param submenuList a list of menu selections
     */
    public void setSelections(List<MenuSelection> submenuList) {
        this.menuList = menuList;
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
     * @param levelSet list of level set.
     */
    public void addSubMenu(String key, String message, Menu<T> subMenu, List<LevelSet> levelSet) {
        return;
    }

    /**
     * this method runs this task.
     * @return unimplemented option.
     */
    public T run() {
        return null;
    }
}
