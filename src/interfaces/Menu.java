package interfaces;

import menu.LevelSet;

import java.util.List;

/**
 * Created by TOMER on 6/10/2017.
 * this interface represents a menu.
 * @param <T> the menu type.
 */
public interface Menu<T> extends Animation {
    /**
     * this method adds a selection to the menu.
     * @param key the key of the selection.
     * @param message the name of the selection.
     * @param returnVal the selection value according to the menu type.
     */
    void addSelection(String key, String message, T returnVal);
    /**
     * this method resets this menu.
     */
    void reset();
    /**
     * this method returns the selected value after the occurrence
     * of a selection event, according to the menu type.
     * @return the selected value.
     */
    T getStatus();
    /**
     * this method adds a sub-menu to the menu.
     * @param key the key of the selection.
     * @param message the name of the selection.
     * @param subMenu the given sub-menu.
     * @param levelSet list of levelSet objects
     */
    void addSubMenu(String key, String message, Menu<T> subMenu, List<LevelSet> levelSet);
}
