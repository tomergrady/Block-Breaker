package collision;
import collidable.Ball;
import collidable.Block;
import interfaces.HitListener;
import runs.GameLevel;

/**
 * this class represents a block remover.
 */
public class BlockRemover implements HitListener {
   private GameLevel gameLevel;
   private Counter remainingBlocks;
   /**
    * construct a block remover.
    * @param gameLevel a game to remove blocks from.
    * @param remainingBlocks the number of blocks to remove.
    */
   public BlockRemover(GameLevel gameLevel, Counter remainingBlocks) {
       this.gameLevel = gameLevel;
       this.remainingBlocks = remainingBlocks;
   }
   /**
    * the method removes from the game blocks that finished hit-points and
    * updates the number of blocks that left.
    * @param beingHit the block that is being hit.
    * @param hitter the hitting ball.
    */
   public void hitEvent(Block beingHit, Ball hitter) {
       if (beingHit.getHits() == 1) {
           beingHit.removeHitListener(this);
           beingHit.removeFromGame(this.gameLevel);
           this.remainingBlocks.decrease(1);
       }
   }
}
