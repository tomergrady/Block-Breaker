package collision;
import collidable.Ball;
import collidable.Block;
import interfaces.HitListener;
import runs.GameLevel;

/**
 * this class represents a ball remover.
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter numberOfBallsToRemove;
    /**
     * construct a ball remover.
     * @param gameLevel a game to remove balls from.
     * @param numberOfBallsToRemove the number of balls to remove.
     */
    public BallRemover(GameLevel gameLevel, Counter numberOfBallsToRemove) {
        this.gameLevel = gameLevel;
        this.numberOfBallsToRemove = numberOfBallsToRemove;
    }
    /**
     * this method is called whenever a ball is hitting the death region.
     * removes the ball from the game and updates the number of balls that left.
     * @param beingHit the block that is being hit (the death region).
     * @param hitter the hitting ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.isDeathB()) {
            hitter.removeFromGame(this.gameLevel);
            this.numberOfBallsToRemove.decrease(1);
            hitter.removeHitListener(this);
        }
    }
}
