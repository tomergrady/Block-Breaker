package io;
import java.awt.Color;

/**
 * Created by TOMER on 6/12/2017.
 * this class is a color parser.
 */
public class ColorParser {
    /**
     * this method covert a string to the color that is written in the string.
     * @param s the given string.
     * @param lineNum the given line number.
     * @return the color that is written in the string.
     * @throws Exception if there is no
     * valid color information in the string.
     */
    public static Color colorFromString(String s, int lineNum) throws Exception {
        if (s.startsWith("color(")) {
            s = s.substring("color(".length());
            if (s.startsWith("RGB(")) {
                s = s.substring("RGB(".length());
                s = s.replace("))", "");
                String[] parts = s.split(",");
                if (!(parts.length == 3)) {
                    throw new Exception("color not valid");
                }
                int r = Integer.valueOf(parts[0]);
                int g = Integer.valueOf(parts[1]);
                int b = Integer.valueOf(parts[2]);
                return new Color(r, g, b);
            } else {
                s = s.replace(")", "");
                if (s.equals("black")) {
                    return Color.BLACK;
                } else if (s.equals("blue")) {
                    return Color.BLUE;
                } else if (s.equals("cyan")) {
                    return Color.CYAN;
                } else if (s.equals("gray")) {
                    return Color.GRAY;
                } else if (s.equals("lightGray")) {
                    return Color.LIGHT_GRAY;
                } else if (s.equals("green")) {
                    return Color.GREEN;
                } else if (s.equals("orange")) {
                    return Color.ORANGE;
                } else if (s.equals("pink")) {
                    return Color.PINK;
                } else if (s.equals("red")) {
                    return Color.RED;
                } else if (s.equals("white")) {
                    return Color.WHITE;
                } else if (s.equals("yellow")) {
                    return Color.YELLOW;
                } else {
                    throw new Exception();
                }
            }
        }
        System.err.println("couldnt read the color");
        System.exit(0);
        return null;
        }
}
