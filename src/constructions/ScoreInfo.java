package constructions;

/**
 * Created by TOMER on 6/8/2017.
 */
import java.io.Serializable;

/**
 * this class represents a score information in this game.
 */
public class ScoreInfo implements Serializable {
    private String name;
    private int score;
    /**
     * construct a scoreInfo object.
     * @param name the given name.
     * @param score the given score.
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }
    /**
     * returns the name from this scoreInfo.
     * @return the name.
     */
    public String getName() {
        return this.name;
    }
    /**
     * returns the score of this scoreInfo.
     * @return the score.
     */
    public int getScore() {
        return this.score;
    }
}
