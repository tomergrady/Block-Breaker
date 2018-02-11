package oldlevels;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import collidable.Block;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import interfaces.LevelInformation;
import interfaces.Sprite;

/**
 * This class represents the first level.
 */
public class Level1 implements LevelInformation {
    private Sprite background;
    private List<Block> blocks = new ArrayList<Block>();
    private List<Velocity> initialBallVelocities = new ArrayList<Velocity>();
    private int speedBall = 100;
    /**
     * construct level 1.
     * creating background and blocks.
     * Initializing balls' speeds.
     */
    public Level1() {
        this.background = new Background1();
        Block block = new Block(new Rectangle(new Point(385, 185), 30, 30), Color.RED, 1);
        this.blocks.add(block);
        this.initialBallVelocities.add(Velocity.fromAngleAndSpeed(0, this.speedBall));
    }
    /**
     * this method returns number of balls in level 1.
     * @return number of balls.
     */
    public Integer numberOfBalls() {
        return 1;
    }
    /**
     * this method returns a list of balls' velocities.
     * @return list list of balls' velocities.
     */
    public List<Velocity> initialBallVelocities() {
        return this.initialBallVelocities;
    }
    /**
     * this method returns the paddle's speed.
     * @return paddle's speed.
     */
    public Integer paddleSpeed() {
        return 400;
    }
    /**
     * this method returns the paddle's width.
     * @return paddle's width.
     */
    public Integer paddleWidth() {
        return 85;
    }
    /**
     * this method returns a string of the level's name.
     * @return string of the level's name.
     */
    public String levelName() {
        return "Direct Hit";
    }
    /**
     * this method returns the level's background.
     * @return the level's background.
     */
    public Sprite getBackground() {
        return this.background;
    }
    /**
     * this method returns a list of the level's blocks.
     * @return a list of the level's blocks.
     */
    public List<Block> blocks() {
        return this.blocks;
    }
    /**
     * this method returns the number of blocks left on the screen.
     * @return number of blocks left on the screen.
     */
    public Integer numberOfBlocksToRemove() {
        return this.blocks.size();
    }
    @Override
    /**
     * this method returns the ball speed of the game
     */
    public Integer ballSpeed() {
        return this.speedBall;
    }
}
