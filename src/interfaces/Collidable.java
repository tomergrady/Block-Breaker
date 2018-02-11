package interfaces;
import collidable.Ball;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;

/**
 * @author TOMER GRADY
 * 205660863
 */

public interface Collidable  {
    /**
     *
     * @return the "collision shape" of the object
     */
    Rectangle getCollisionRectangle();

    /**
     * @param collisionPoint
     *            point of the object that we collided with
     * @param currentVelocity
     *            velocity of the object that we collided with
     * @return new velocity expected after the hit (based on the force the
     *         object inflicted on us)
     * @param hitter the hitter ball
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
