package geometry;
/**
 *
 * @author TOMER GRADY ID 205660863
 */
public class Velocity {

    private double dx;
    private double dy;

    /**
     *
     * @param dx
     *            the change in x line
     * @param dy
     *            the change in y line
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }


    /**
     * @return dx
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * @return dy value
     */
    public double getDy() {
        return this.dy;
    }

    /**
     *
     * @param p
     *            point with position (x,y).
     * @param dt the amount of seconds passed since the last call
     * @return point with a position (x+dx, y+dy)
     */
    public Point applyToPoint(Point p, double dt) {
        Point newP = new Point((p.getX() + (dt * this.dx)), (p.getY() + (dt * this.dy)));
        return newP;
    }

    /**
     *
     * @param angle
     *            angle of the ball
     * @param speed
     *            speed of the ball
     * @return new velocity by angle and speed
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.sin(Math.toRadians(angle)) * speed;
        double dy = Math.cos(Math.toRadians(angle)) * speed;
        return new Velocity(dx, -dy);
    }

    /**
     * set a new velocity.
     *
     * @param v
     *            the new velocity
     */
    public void setVelocity(Velocity v) {
        this.dx = v.getDx();
        this.dy = v.getDy();
    }
}