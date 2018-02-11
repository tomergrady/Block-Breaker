package collision;

import collidable.Ball;
import collidable.Block;
import interfaces.HitListener;

/**
 * This class represents a score tracking listener.
 */
public class ScoreTrackingListener implements HitListener {
   private Counter currentScore;
   /**
    * construct a score tracking listener from a counter object.
    * @param scoreCounter the given counter object.
    */
   public ScoreTrackingListener(Counter scoreCounter) {
      this.currentScore = scoreCounter;
   }
   /**
    * this method updates the game score when a block is being hit.
    * if the block is removed, the score is increased in 15 points, 5 otherwise.
    * @param beingHit the block that is being hit.
    * @param hitter the hitting ball.
    */
   public void hitEvent(Block beingHit, Ball hitter) {
       if (beingHit.getHits() >= 1) {
           this.currentScore.increase(5);
       }
       if (beingHit.getHits() == 1) {
           this.currentScore.increase(10);
       }
   }

}