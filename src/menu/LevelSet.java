package menu;


import interfaces.LevelInformation;

import java.util.List;

/**
 * this class represents a menu selection object.
 */
public class LevelSet {
    private String key;
    private String message;
    private String path;
    private List<LevelInformation> levels;
    /**
     * construct a LevelSet object from a key, a message and a level set.
     * @param key the given key.
     * @param message the given message.
     * @param levels the given levels.
     */
    public LevelSet(String key, String message, List<LevelInformation> levels) {
        this.key = key;
        this.message = message;
        this.levels = levels;
    }
    /**
     * construct a LevelSet object from a key, a message and a level set.
     * @param key the given key.
     * @param message the given message.
     * @param path the given levels.
     */
    public LevelSet(String key, String message, String path) {
        this.key = key;
        this.message = message;
        this.path = path;
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
     * @return this LevelSet's levels.
     */
    public List<LevelInformation> getLevels() {
        return this.levels;
    }
    /**
     * this method return this menuSelection's level set.
     * @return this LevelSet's path.
     */
    public String getPath() {
        return this.path;
    }
}
