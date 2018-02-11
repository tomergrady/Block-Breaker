package collidable;
import java.util.List;

import collision.CollisionInfo;
import geometry.Line;
import geometry.Point;
import interfaces.Collidable;

import java.util.ArrayList;

/**
 *
 * @author TOMER GRADY
 * 205660863
 */
public class GameEnvironment {

    private List<Collidable> environment = new ArrayList<Collidable>();

    /**
     * this method add the given collidable to the environment.
     *
     * @param c
     *            the new collidable to be added
     */
    public void addCollidable(Collidable c) {
        this.environment.add(c);
    }
    /**
     * this method removes a given collidable object from the environment.
     * @param c the given collidable object.
     */
    public void removeCollidable(Collidable c) {
        this.environment.remove(c);
    }

    /**
     *
     * @param trajectory the line of the ball
     * @return null if this object will not collide with any of the collidables,
     *         else, return the information about the closest collision that is
     *         going to occur.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        double distance = Double.POSITIVE_INFINITY;
        Point closestP = null;
        Collidable object = null;
        Point collisionTemp = null;
        for (int i = 0; i < this.environment.size(); i++) {
            collisionTemp = trajectory
                    .closestIntersectionToStartOfLine(this.environment.get(i).getCollisionRectangle());
            if (collisionTemp != null) {
                if (trajectory.start().distance(collisionTemp) < distance) {
                    distance = trajectory.start().distance(collisionTemp);
                    closestP = trajectory
                            .closestIntersectionToStartOfLine(this.environment.get(i).getCollisionRectangle());
                    object = this.environment.get(i);
                }
            }
        }
        if (closestP == null) {
            return null;
        }
        return new CollisionInfo(closestP, object);
    }
}