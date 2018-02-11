package interfaces;
import collidable.Ball;
import collidable.Block;

/**
 * this interface represents a hit listener.
 */
public interface HitListener {
    /**
     * this method is called whenever the block object is being hit.
     * @param beingHit the block that is being hit.
     * @param hitter the  ball.
     */
    void hitEvent(Block beingHit, Ball hitter);
}