package io;

/**
 * Created by TOMER on 6/12/2017.
 */

import collidable.Block;
import geometry.Velocity;
import interfaces.LevelInformation;
import levels.Background;
import levels.Level;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

/**
 * this class is a level specification reader.
 */
public class LevelSpecificationReader {

    private static BlocksFromSymbolsFactory blocksFromSymbolsFactory;
    /**
     * method get a readable thing and returns list of level information.
     * @param reader a reader to read from.
     * @return a list of LevelInformation.
     * @throws Exception if an error occurs.
     */
    public static List<LevelInformation> fromReader(Reader reader)
            throws Exception {
        String line;
        int lineNum;
        int rowCounter = 0;
        boolean inBlockSection = false;
        List<LevelInformation> levels = new ArrayList<LevelInformation>();
        levels.Level level = null;
        LineNumberReader lineNumberReader = new LineNumberReader(reader);
        blocksFromSymbolsFactory = null;
        try {
            // go through the lines
            while ((line = lineNumberReader.readLine()) != null) {
                line = line.trim();
                lineNum = lineNumberReader.getLineNumber();
                if (!line.equals("") && !line.startsWith("#")) {
                    if (!inBlockSection) {
                        // start a level line
                        if (line.equals("START_LEVEL")) {
                            level = new Level();
                        } else if (line.equals("END_LEVEL")) {
                            if (isLevelFull(level)) {
                                levels.add(level);
                                level = null;
                            } else {
                                throw new Exception("Error in reading file");
                            }
                        } else if (line.equals("START_BLOCKS")) {
                            inBlockSection = true;
                            rowCounter = 0;
                        // parse line
                        } else {
                            parseLine(line, lineNum, level);
                            level.setPaddleHeight(15);
                        }
                    } else if (line.equals("END_BLOCKS")) {
                        inBlockSection = false;
                        blocksFromSymbolsFactory = null;
                    } else if (inBlockSection) {
                        parseBlocksLine(line, lineNum, rowCounter, level);
                        rowCounter++;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error in reading file");
            System.exit(0);
        } finally {
            if (lineNumberReader != null) {
                try {
                    lineNumberReader.close();
                } catch (IOException e) {
                    System.out.println("Error in reading file");
                    System.exit(0);
                }
            }
        }
        return levels;
    }
    /**
     * the method parses the line and fills it according to the given levelInformation object.
     * @param line the given line.
     * @param lineNum the line number.
     * @param level the given levelInformation object.
     * @throws Exception if an error occurs.
     */
    public static void parseLine(String line, int lineNum, Level level)
            throws Exception {
        String[] parts = line.trim().split(":");
        if (parts.length != 2) {
            throw new Exception("Error in reading file in a line");
        }
        String key = parts[0];
        String value = parts[1];
        if (key.equals("level_name")) {
            level.setLevelName(value);
        }
        if (key.equals("ball_velocities")) {
            int numberOfBalls = 0;
            String[] velocities = value.split(" ");
            // go through velocities line
            for (String velocity : velocities) {
                String[] veloAngelSpeed = velocity.split(",");
                if (veloAngelSpeed.length != 2) {
                    throw new Exception("Error in reading velocities");
                }
                Double angle = Double.valueOf(veloAngelSpeed[0]);
                Double speed = Double.valueOf(veloAngelSpeed[1]);
                try {
                    if (speed <= 0) {
                        throw new Exception("Error in speed value");
                    }
                    level.addInitialBallVelocity(Velocity.fromAngleAndSpeed(angle, speed));
                    level.setBallSpeed((Integer.parseInt(veloAngelSpeed[1])));
                    numberOfBalls++;
                } catch (NumberFormatException e) {
                    System.err.println("Error in reading file Illegal velocity");
                    System.exit(0);
                }
            }
            level.setNumberOfBalls(numberOfBalls);
        }
        if (key.equals("background")) {
            if (value.startsWith("image(")) {
                value = value.substring("image(".length());
                value = value.replace(")", "");
                try {
                    Image image = ImageIO.read(
                            ClassLoader.getSystemClassLoader().
                                    getResourceAsStream(value));
                    Background background = new Background(image);
                    level.setBackground(background);
                } catch (Exception e) {
                    System.err.println("Unable to find background image");
                    System.exit(0);
                }
            } else if (value.startsWith("color(")) {
                    Color color = ColorParser.colorFromString(value, lineNum);
                    Background background = new Background(color);
                    level.setBackground(background);
            } else {
                throw new Exception("No color or image definition");
            }
        }
        if (key.equals("paddle_speed")) {
            try {
                if (Integer.valueOf(value) <= 0) {
                    throw new Exception("illegal paddle speed");
                }
                level.setPaddleSpeed(Integer.valueOf(value));
            } catch (Exception e) {
                System.err.println("Illegal paddle speed");
            }
        }
        if (key.equals("paddle_width")) {
            try {
                if (Integer.valueOf(value) <= 0) {
                    throw new Exception();
                }
                level.setPaddleWidth(Integer.valueOf(value));
            } catch (Exception e) {
                System.err.println("Illegal paddle width");
            }
        }
        if (key.equals("block_definitions")) {
            Reader blockReader = new InputStreamReader(ClassLoader.getSystemClassLoader().
                            getResourceAsStream(value));
            blocksFromSymbolsFactory = BlocksDefinitionReader.fromReader(blockReader);
        }
        if (key.equals("blocks_start_x")) {
            try {
                if (Integer.valueOf(value) < 0) {
                    throw new Exception();
                }
                level.setBlocksStartX(Integer.valueOf(value));
            } catch (Exception e) {
                System.err.println("Illegal block start x");
                System.exit(0);
            }
        }
        if (key.equals("blocks_start_y")) {
            try {
                if (Integer.valueOf(value) < 0) {
                    throw new Exception();
                }
                level.setBlocksStartY(Integer.valueOf(value));
            } catch (Exception e) {
                System.err.println("Illegal block start y");
                System.exit(0);
            }
        }
        if (key.equals("row_height")) {
            try {
                if (Integer.valueOf(value) <= 0) {
                    throw new Exception();
                }
                level.setRowHeight(Integer.valueOf(value));
            } catch (NumberFormatException e) {
                System.err.println("Illegal row_height");
                System.exit(0);
            }
        }
        if (key.equals("num_blocks")) {
            try {
                if (Integer.valueOf(value) <= 0) {
                    throw new Exception();
                }
                level.setNumberOfBlocksToRemove(
                        Integer.valueOf(value));
            } catch (Exception e) {
                System.err.println("Illegal num blocks");
                System.exit(0);
            }
        }
    }

    /**
     * this method parse a blocks' line.
     * @param line the line
     * @param lineNum number of the line
     * @param rowCounter row counter
     * @param level the level
     * @throws Exception if one or more of the parameters are missing
     */
    private static void parseBlocksLine(String line, int lineNum, int rowCounter, Level level) throws Exception {
        if (!isLevelFull(level)) {
            throw new Exception("one or more parameters is missing");
        }
        int currentX = level.blocksStartX();
        int currentY = level.blocksStartY() + rowCounter * level.rowHeight();
        for (int i = 0; i < line.length(); i++) {
            String symbol = String.valueOf(line.charAt(i));
            if (blocksFromSymbolsFactory.isSpaceSymbol(symbol)) {
                currentX += blocksFromSymbolsFactory.getSpacerWidth(symbol);
            } else if (blocksFromSymbolsFactory.isBlockSymbol(symbol)) {
                Block block = blocksFromSymbolsFactory.getBlock(symbol, currentX, currentY);
                currentX = (int) (currentX + block.getCollisionRectangle().getWidth());
                level.addBlock(block);
            } else {
                throw new Exception("Error in reading file");
            }
        }
    }
    /**
     * this method check if the level is full of parameters.
     * @param level the gicen levela
     * @return true if the level is totally full, false otherwise
     */
    private static boolean isLevelFull(Level level) {
        return (level.levelName() != null
                && level.numberOfBalls() != null
                && level.getBackground() != null
                && level.numberOfBlocksToRemove() != null
                && level.paddleSpeed() != null
                && level.paddleWidth() != null
                && level.paddleHeight() != null
                && level.blocksStartX() != null
                && level.blocksStartY() != null
                && level.rowHeight() != null);
    }
}
