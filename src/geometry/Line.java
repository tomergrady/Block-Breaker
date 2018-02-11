package geometry;
import java.util.ArrayList;

/**
 *
 * @author TOMER GRADY ID 205660863
 */
public class Line {
    /**
     * pointStart = first point of the line.
     */
    private Point pStart;
    /**
     * pointEnd - first point of the end.
     */
    private Point pEnd;
    /**
     * the slope of the line.
     */
    private double slope;

    /**
     * This constructor creates a line from 2 points.
     *
     * @param start
     *            first point
     * @param end
     *            second point
     */
    public Line(final Point start, final Point end) {
        this.pStart = start;
        this.pEnd = end;
        // get the slope of the line
        if (this.pStart.getX() != this.pEnd.getX()) {
            this.slope = (this.pStart.getY() - this.pEnd.getY()) / (this.pStart.getX() - this.pEnd.getX());
        } else {
            // double max value means that the slope is infinity.
            this.slope = Double.POSITIVE_INFINITY;
        }
    }

    /**
     * This constructor creates a line from 4 points that indicate 2 points.
     *
     * @param x1
     *            x value of point 1
     * @param y1
     *            y value of point 1
     * @param x2
     *            x value of point 2
     * @param y2
     *            y value of point 2
     */
    public Line(final double x1, final double y1, final double x2, final double y2) {
        this.pStart = new Point(x1, y1);
        this.pEnd = new Point(x2, y2);
        if (this.pStart.getX() != this.pEnd.getX()) {
            this.slope = (this.pStart.getY() - this.pEnd.getY()) / (this.pStart.getX() - this.pEnd.getX());
        } else {
            this.slope = Double.POSITIVE_INFINITY;
        }
    }

    /**
     * method returns the absolute length of the line.
     * @return the length of the line.
     */
    public double length() {
        return Math.abs(this.pStart.distance(this.pEnd));
    }

    /**
     * method returns the middle point of the line.
     * @return the middle point of the line
     */
    public Point middle() {
        return new Point((this.pStart.getX() + this.pEnd.getX()) / 2, (this.pStart.getY() + this.pEnd.getY()) / 2);
    }

    /**
     * method returns the start point of the line.
     * @return the start point of the line
     */
    public Point start() {
        return this.pStart;
    }

    /**
     * method starts the start point of the line.
     * @return the end point of the line
     */
    public Point end() {
        return this.pEnd;
    }

    /**
     * @param other
     *            line
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(final Line other) {
        if (this.intersectionWith(other) == null) {
            return false;
        }
        return true;
    }

    /**
     *
     * @param other
     *            line
     * @return the intersection point if the lines intersect, and null
     *         otherwise.
     */
    public Point intersectionWith(final Line other) {
        Double infinity = Double.POSITIVE_INFINITY;
        // points intersectP when x=.
        double bThis;
        double bOther;
        double xPoint;
        double yPoint;
        Point intersectP;
        // if the lines are parallel
        if (Math.abs(this.slope) == Math.abs(other.slope)) {
            if (this.pStart == this.pEnd) {
                if (other.checkPoint(this.pStart, other)) {
                    return this.pStart;
                }
            } else if (other.pStart == other.pEnd) {
                if (this.pStart.getX() == other.pStart.getX()) {
                    return other.pStart;
                }
            }
            return null;
        }
        // if the slope of one line is infinity (parallel to the Y pivot).
        if (Math.abs(this.slope) == infinity) {
            // b2 = y2 - m2*x.
            bOther = other.pStart.getY() - ((other.slope) * other.pStart.getX());
            yPoint = other.slope * this.pStart.getX() + bOther;
            intersectP = new Point(this.pStart.getX(), yPoint);
            if (this.checkPoint(intersectP, other)) {
                return intersectP;
            } else {
                return null;
            }
            // if the second slope's line is infinity
        } else if (Math.abs(other.slope) == infinity) {
            // b1 = y1 - m1*x
            bThis = this.pStart.getY() - ((this.slope) * this.pStart.getX());
            yPoint = this.slope * other.pStart.getX() + bThis;
            intersectP = new Point(other.pStart.getX(), yPoint);
            if (this.checkPoint(intersectP, other)) {
                return intersectP;
            } else {
                return null;
            }
        } else {
            bOther = other.pStart.getY() - ((other.slope) * other.pStart.getX());
            bThis = ((-(this.slope)) * this.pStart.getX()) + this.pStart.getY();
            xPoint = (bOther - bThis) / (this.slope - other.slope);
            yPoint = this.slope * xPoint + bThis;
            intersectP = new Point(xPoint, yPoint);
            if (this.checkPoint(intersectP, other)) {
                return intersectP;
            } else {
                return null;
            }
        }
    }

    /**
     * check if the point of intersection is in the limits of the lines.
     * @param p
     *            point to check if it's in the bounded
     * @param other
     *            line
     * @return true if the point is intersect between the lines of false
     *         otherwise
     */
    public boolean checkPoint(Point p, Line other) {
        // check if x value is in the limits of the lines.
        if (p.getX() < Math.min(this.pStart.getX(), this.pEnd.getX())
                || p.getX() > Math.max(this.pStart.getX(), this.pEnd.getX())
                || p.getX() < Math.min(other.pStart.getX(), other.pEnd.getX())
                || p.getX() > Math.max(other.pStart.getX(), other.pEnd.getX())) {
            return false;
        }
        // check if y value is in the limits of the lines.
        if (p.getY() < Math.min(this.pStart.getY(), this.pEnd.getY())
                || p.getY() > Math.max(this.pStart.getY(), this.pEnd.getY())
                || p.getY() < Math.min(other.pStart.getY(), other.pEnd.getY())
                || p.getY() > Math.max(other.pStart.getY(), other.pEnd.getY())) {
            return false;
        }
        return true;
    }

    /**
     * method checks if the lines are equal.
     * @param other
     *            line
     * @return true is the lines are equal, false otherwise
     */
    public boolean equals(final Line other) {
        if (other.pStart.equals(this.pStart) && (other.pEnd.equals(this.pEnd))) {
            return true;
        }
        if (other.pStart.equals(this.pEnd) && (other.pEnd.equals(this.pStart))) {
            return true;
        }
        return false;
    }

    /**
     * @param rect
     *            rectangle
     * @return the closest intersection point to the start of the line, if this
     *         line does not intersect with the rectangle null
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        ArrayList<Point> intersections = rect.intersectionPoints(this);
        if (intersections.isEmpty()) {
            return null;
        }
        Point closestP = intersections.remove(0);
        double distance = closestP.distance(this.pStart);
        while (!intersections.isEmpty()) {
            if (intersections.get(0).distance(this.pStart) < distance) {
                distance = intersections.get(0).distance(this.pStart);
                closestP = intersections.get(0);
            }
            intersections.remove(0);
        }
        return closestP;
    }
}
