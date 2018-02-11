/**
 * Tomer Grady 205660863
 */
package interfaces;

import java.util.List;
import collidable.Block;
import geometry.Velocity;
/**
 * this interface represents level's information.
 */
public interface LevelInformation {
    /**
     * this method returns number of balls of a specific level.
     * @return number of balls.
     */
    Integer numberOfBalls();
    /**
     * this method returns a list of balls' velocities.
     * @return list list of balls' velocities.
     */
    List<Velocity> initialBallVelocities();
    /**
     * this method returns the paddle's speed.
     * @return paddle's speed.
     */
    Integer paddleSpeed();
    /**
     * this method returns the paddle's width.
     * @return paddle's width.
     */
    Integer paddleWidth();
    /**
     * this method returns a string of the level's name.
     * @return string of the level's name.
     */
    String levelName();
    /**
     * this method returns the level's background.
     * @return the level's background.
     */
    Sprite getBackground();
    /**
     * this method returns a list of level's blocks.
     * @return a list of the level's blocks.
     */
    List<Block> blocks();
    /**
     * this method returns the number of blocks left on the screen.
     * @return number of blocks left on the screen.
     */
    Integer numberOfBlocksToRemove();
    /**
     * this method return the speed of the ball in the game.
     * @return the speed of the ball
     */
    Integer ballSpeed();
}
