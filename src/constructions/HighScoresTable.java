package constructions;
/**
 * Created by TOMER on 6/8/2017.
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * this class represents a high score table object.
 */
public class HighScoresTable implements Serializable {
    private List<ScoreInfo> highScores;
    private int size;
    /**
     * construct a HighScoresTable object with the given size.
     * @param size the maximal number of scores.
     */
    public HighScoresTable(int size) {
        this.highScores = new ArrayList<ScoreInfo>();
        this.size = size;
    }
    /**
     * this method adds a score to the high score table according to the size of the table.
     * @param score the given score.
     */
    public void add(ScoreInfo score) {
        int rank = getRank(score.getScore());
        // if rank is bigger don't add it to the list
        if (rank > this.size) {
            return;
        }
        this.highScores.add(rank - 1, score);
        // remove the scores that out of the list
        while (this.highScores.size() > this.size) {
            this.highScores.remove(this.highScores.size() - 1);
        }
    }
    /**
     * this method returns the size of the table.
     * @return the size of the table.
     */
    public int size() {
        return highScores.size();
    }
    /**
     * this method returns the current high scores list.
     * @return the high scores list.
     */
    public List<ScoreInfo> getHighScores() {
        return this.highScores;
    }
    /**
     * this method return the rank of the current score.
     * @param score the given score.
     * @return the rank of the score.
     */
    public int
    getRank(int score) {
        int i;
        for (i = 0; i < this.highScores.size(); i++) {
            if (score > this.highScores.get(i).getScore()) {
                break;
            }
        }
        return i + 1;
    }
    /**
     * this method clears the scores' list.
     */
    public void clear() {
        this.highScores.clear();
    }
    /**
     * this method loads table data from file.
     * @param filename the given filename.
     * @throws IOException if there was an error with reading the file.
     */
    public void load(File filename) throws IOException {
        ObjectInputStream objectInputStream = null;
        HighScoresTable highScoresTable;
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(filename.getName()));
            highScoresTable = (HighScoresTable) objectInputStream.readObject();
            this.highScores = highScoresTable.highScores;
            this.size = highScoresTable.size;
        } catch (FileNotFoundException e) {
            System.err.println("Unable to find file: " + filename.getName());
            HighScoresTable emptyTable = new HighScoresTable(5);
            emptyTable.save(filename);
            this.highScores = emptyTable.highScores;
            this.size = emptyTable.size;
        } catch (ClassNotFoundException e) {
            System.err.println("Unable to find class for object in file: "
                    + filename.getName());
        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: "
                        + filename.getName());
            }
        }
    }
    /**
     * this method saves table data to the specified file.
     * @param filename the given filename.
     * @throws IOException if there was an error with writing to the file.
     */
    public void save(File filename) throws IOException {
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(filename.getName()));
            objectOutputStream.writeObject(this);
        } catch (IOException e) {
            System.err.println("Failed saving object");
        } finally {
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename.getName());
            }
        }
    }
    /**
     * this methods reads a table from file and return it.
     * @param filename the given filename.
     * @return a new table with the values according to the data in the file.
     * if the file does not exist, or there is a problem
     * with reading it, an empty table is returned.
     */
    public static HighScoresTable loadFromFile(File filename) {
        HighScoresTable table = new HighScoresTable(5);
        // if the file does not exist return an empty table.
        if (!filename.exists()) {
            return table;
        }
        try {
            table.load(filename);
        } catch (IOException e) {
            System.err.println("Failed closing file: " + filename.getName());
        }
        return table;
    }
}

