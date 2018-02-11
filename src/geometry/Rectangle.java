package geometry;
import java.util.ArrayList;

/**
 * @author TOMER
 * 205660863
 */

public class Rectangle {
    private Point upperLeft;
    private Point upperRight;
    private Point downLeft;
    private Point downRight;
    private double width = 0;
    private double height = 0;

    /**
     * Create a new rectangle with location and width/height.
     * @param upperLeft
     *            point of upper left
     * @param width
     *            of the rectangle
     * @param height
     *            of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.downLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + height);
        this.upperRight = new Point(this.upperLeft.getX() + width, this.upperLeft.getY());
        this.downRight = new Point(this.upperLeft.getX() + width, this.upperLeft.getY() + height);
    }

    /**
     * Create a new rectangle with 2 points.
     * @param leftDown the point of the left and down corner of the rectangle
     * @param rightUp the point of the up and right corner of the rectangle
     */
    public Rectangle(Point leftDown, Point rightUp) {
        this.downLeft = leftDown;
        this.upperRight = rightUp;
        this.width = Math.abs(rightUp.getX() - leftDown.getX());
        this.height = Math.abs(rightUp.getY() - leftDown.getY());
        this.upperLeft = new Point(leftDown.getX(), rightUp.getY());
        this.downRight = new Point(rightUp.getX(), leftDown.getY());
    }
    /**
     * method create the list of intersection points with a line.
     * @param line the line
     * @return a (possibly empty) List of intersection points with the specified
     *         line.
     */
    public java.util.ArrayList<Point> intersectionPoints(Line line) {
        ArrayList<Point> intersections = new ArrayList<Point>();
        Line[] lines = rectangleLines();
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].isIntersecting(line)) {
                intersections.add(lines[i].intersectionWith(line));
            }
        }
        return intersections;
    }

    /**
     * method create the array of the lines of the rectangle.
     * @return an array of lines of the rectangle
     */
    public Line[] rectangleLines() {
        Line[] lines = new Line[4];
        lines[0] = new Line(this.upperLeft, this.upperRight);
        lines[1] = new Line(this.downLeft, this.downRight);
        lines[2] = new Line(this.upperLeft, this.downLeft);
        lines[3] = new Line(this.upperRight, this.downRight);
        return lines;
    }

    /**
     *
     * @return the width and height of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * @return the upper-right point of the rectangle.
     */
    public Point getUpperRight() {
        return this.upperRight;
    }
}