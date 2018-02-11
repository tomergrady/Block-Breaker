package drawing;
import collidable.Ball;
import collidable.Block;
import interfaces.HitListener;

/**
 * This class prints information about blocks that were hit.
 */
public class PrintingHitListener implements HitListener {
    /**
     * this method prints information about blocks that were hit.
     * @param beingHit the block
     * @param hitter hitting ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block with " + beingHit.getHits() + " points was hit.");
    }
}
