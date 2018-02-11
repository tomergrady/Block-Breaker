package geometry;

/**
*
* @author TOMER GRADY
* ID 205660863
*/
public class Point {
    /**
     * x value for a point.
     */
    private final double x;
    /**
     * y value for a point.
     */
    private final double y;
    /**
     *
     * @param myX x value
     * @param myY y value
     */
    public Point(double myX , double myY) {
        this.x = myX;
        this.y = myY;
    }

    /**
     * method return the distance between the point to another point.
     * @param other another point
     * @return the distance between the points
     */
    public double distance(Point other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     *
     * @param other another point
     * @return true it its the same points
     */
    public boolean equals(Point other) {
        return (this.x == other.x && this.y == other.y);
    }

    /**
     *
     * @return x value
     */
    public double getX() {
        return this.x;
    }

    /**
     *
     * @return y value.
     */
    public double getY() {
        return this.y;
    }
}
