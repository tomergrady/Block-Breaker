package collision;
import geometry.Point;
import interfaces.Collidable;

/**
 * @author TOMER GRADY
 * 205660863
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;
    /**
     * create a CollsionInfo type.
     * @param collisionP point of collision
     * @param collisionO the object involved in the collision.
     */
    public CollisionInfo(Point collisionP, Collidable collisionO) {
        this.collisionObject = collisionO;
        this.collisionPoint = collisionP;
    }
    /**
     * @return the point at which the collision occurs.
     */
    public Point getCollisionPoint() {
        return this.collisionPoint;
    }

    /**
     * @return the collidable object involved in the collision
     */
    public Collidable getCollisionObject() {
        return this.collisionObject;
    }
}