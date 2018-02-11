package io;

import collidable.Block;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.Map;
import java.util.TreeMap;
import javax.imageio.ImageIO;


/**
 * this class is a block definition reader.
 */
public class BlocksDefinitionReader {
    /**
     * this method returns a BlocksFromSymbolsFactory object.
     * @param reader the given reader.
     * @return a new BlocksFromSymbolsFactory object.
     * @throws Exception if an error occurs.
     */
    public static BlocksFromSymbolsFactory fromReader(Reader reader) throws Exception {
        BlocksFromSymbolsFactory blockFactory = new BlocksFromSymbolsFactory();
        LineNumberReader lineNumberReader = new LineNumberReader(reader);
        String line;
        int lineNum = lineNumberReader.getLineNumber();
        Map<String, String> blockMap = new TreeMap<String, String>();
        Map<String, String> defultMap = new TreeMap<String, String>();
        Map<String, String> tempMap = new TreeMap<String, String>();
        try {
            while ((line = lineNumberReader.readLine()) != null) {
                line = line.trim();
                if (!line.equals("") && !line.startsWith("#")) {
                    if (line.startsWith("default")) {
                        line = line.substring("default".length()).trim();
                        defultMap = parseFromLine(line, lineNum);
                    } else if (line.startsWith("bdef")) {
                        line = line.substring("bdef".length()).trim();
                        tempMap.clear();
                        tempMap = parseFromLine(line, lineNum);
                        blockMap.clear();
                        blockMap.putAll(defultMap);
                        // override
                        blockMap.putAll(tempMap);
                        if (blockMap.containsKey("symbol")
                                && blockMap.containsKey("width")
                                && blockMap.containsKey("height")
                                && blockMap.containsKey("hit_points")) {
                            String symbol = blockMap.get("symbol");
                            Block block = new Block();
                            try {
                                if ((Integer.valueOf(blockMap.get("width")) <= 0)
                                        || (Integer.valueOf(blockMap.get("height")) <= 0)
                                        || (Integer.valueOf(blockMap.get("hit_points")) <= 0)) {
                                    throw new Exception("block values less then 0");
                                }
                                block.setHeight(Integer.valueOf(blockMap.get("height")));
                                block.setWidth(Integer.valueOf(blockMap.get("width")));
                                block.setHits(Integer.valueOf(blockMap.get("hit_points")));
                            } catch (Exception e) {
                                System.out.println("error in block setting");
                                System.exit(0);
                            }
                            if (blockMap.containsKey("stroke")) {
                                block.setBorderColor(ColorParser.colorFromString(blockMap.get("stroke"), lineNum));
                                block.setBorder(true);
                            } else {
                                block.setBorder(false);
                            }
                            Map<Integer, Color> blockColors = new TreeMap<Integer, Color>();
                            Map<Integer, Image> blockImages = new TreeMap<Integer, Image>();
                            if (blockMap.containsKey("fill")) {
                                blockMap.put("fill-0", blockMap.get("fill"));
                                blockMap.remove("fill");
                            }
                            for (int i = 0; i <= Integer.valueOf(blockMap.get("hit_points")); i++) {
                                if (blockMap.containsKey("fill-" + i)) {
                                    String fillS = blockMap.get("fill-" + i);
                                    if (fillS.startsWith("image(")) {
                                        fillS = fillS.substring("image(".length());
                                        fillS = fillS.replace(")", "");
                                        Image image = ImageIO.read(ClassLoader.getSystemClassLoader().
                                                getResourceAsStream(fillS));
                                        blockImages.put(i, image);
                                    } else if (fillS.startsWith("color(")) {
                                        Color color = ColorParser.colorFromString(fillS, lineNum);
                                        blockColors.put(i, color);
                                    } else {
                                        System.err.println("wrong values BlocksDefinitionReader color");
                                    }
                                }
                            }
                            block.setColors(blockColors);
                            block.setImages(blockImages);
                            blockFactory.addBlock(symbol, block);
                        }
                    } else if (line.startsWith("sdef")) {
                        line = line.substring("sdef".length()).trim();
                        Map<String, String> spacerMap = parseFromLine(line, lineNum);
                        if (spacerMap.containsKey("symbol") && spacerMap.containsKey("width")) {
                            String symbol = spacerMap.get("symbol");
                            Integer width = Integer.valueOf(spacerMap.get("width"));
                            blockFactory.putSpaceWidth(symbol, width);
                        } else {
                            throw new Exception("must include: symbol and width");
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new Exception("BlocksDefinitionReader error");
        } finally {
            if (lineNumberReader != null) {
                try {
                    lineNumberReader.close();
                } catch (IOException e) {
                    throw new Exception("Error in closing file");
                }
            }
        }
        return blockFactory;
    }
    /**
     * this method gets a string and a line number and returns a map with the information.
     * @param line the given line.
     * @param lineNum the line number.
     * @return an organized map.
     * @throws Exception if the line is not valid.
     */
    private static Map<String, String> parseFromLine(String line, int lineNum) throws Exception {
        Map<String, String> returnVal = new TreeMap<String, String>();
        String[] pairs = line.split(" ");
        for (String pair : pairs) {
            String[] parts = pair.split(":");
            if (parts.length != 2) {
                throw new Exception("Parts of the line must be 2");
            }
            returnVal.put(parts[0], parts[1]);
        }
        return returnVal;
    }

}
