package io;
/**
 * Created by TOMER on 6/17/2017.
 */
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import interfaces.LevelInformation;
import menu.LevelSet;

/**
 * this class represents a level set reader.
 */
public class LevelSetsReader {
    /**
     * this method read level set reader and returns a list of MenuSelection objects.
     * @param reader a reader to read from.
     * @return a list of LevelInformation objects.
     * @throws Exception if an error occurs.
     */
    public static List<LevelSet> fromReader(Reader reader) throws Exception {
        LineNumberReader lineNumberReader = new LineNumberReader(reader);
        List<LevelSet> returnVal = new ArrayList<LevelSet>();
        String line;
        String key = null;
        String message = null;
        List<LevelInformation> listOfLevels;
        LevelSpecificationReader lsr = new LevelSpecificationReader();
        try {
            while ((line = lineNumberReader.readLine()) != null) {
                if (lineNumberReader.getLineNumber() % 2 != 0) {
                    String[] parts = line.split(":");
                    if (parts.length == 2) {
                        key = parts[0];
                        message = parts[1];
                    } else {
                        throw new Exception("parts in level set must be two");
                    }
                } else {
                    //open the path and make a list out of it.
//                    listOfLevels = lsr.fromReader(new InputStreamReader(ClassLoader
//                            .getSystemClassLoader().getResourceAsStream(line)));

                    returnVal.add(new LevelSet(key, message, line));
                }
            }
        } catch (Exception e) {
            throw new Exception("Error in reading level set file");
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    throw new Exception("Error in closing file");
                }
            }
        }
        return returnVal;
    }
}

