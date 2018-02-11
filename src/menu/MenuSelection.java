package menu;


/**
 * this class represents a menu selection object.
 * @param <T> task
 */
public class MenuSelection<T> {
    private String key;
    private String message;
    private T task;
    private boolean isSubmenu;
    /**
     * construct a menu selection object from a key, a message and a level set.
     * @param key the given key.
     * @param message the given message.
     * @param task the given task.
     * @param subMenu if it is a menu selection of subMenu
     */
    public MenuSelection(String key, String message, T task, boolean subMenu) {
        this.key = key;
        this.message = message;
        this.task = task;
        this.isSubmenu = isSubmenu;
    }
    /**
     * construct a menu selection object from a key, a message and a level set.
     * @param key the given key.
     * @param message the given message.
     * @param task the given task.
     */
    public MenuSelection(String key, String message, T task) {
        this.key = key;
        this.message = message;
        this.task = task;
        this.isSubmenu = true;
    }
    /**
     * this method return this menuSelection's key.
     * @return this menuSelection's key.
     */
    public String getKey() {
        return this.key;
    }
    /**
     * this method return this menuSelection's message.
     * @return this menuSelection's message.
     */
    public String getMessage() {
        return this.message;
    }
    /**
     * this method return this menuSelection's level set.
     * @return this menuSelection's task.
     */
    public T getTask() {
        return this.task;
    }
    /**
     * this method set the task tothe  level set.
     * @param givenTask the giceen task to set
     * @return null.
     */
    public Void setTask(T givenTask) {
        this.task = givenTask;
        return null;
    }
}
