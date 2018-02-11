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
 * This class represents the third level.
 */
public class Level4 implements LevelInformation {
    private Sprite background = new Background4(); ///need to create background
    private List<Block> blocks = new ArrayList<Block>();
    private List<Velocity> initialBallVelocities = new ArrayList<Velocity>();
    private int speedBall = 100;
    /**
     * construct level 4.
     * creating background and blocks.
     * Initializing balls' speeds.
     */
    public Level4() {
        for (int i = 0; i < 15; ++i) {
            Block b = new Block(new Rectangle(new Point(720 - (i * 50), 100), 50, 30),
                    Color.MAGENTA, 2);
            this.blocks.add(b);
        }
        for (int i = 0; i < 15; ++i) {
            Block b = new Block(new Rectangle(new Point(720 - (i * 50), 130), 50, 30),
                    Color.blue, 1);
            this.blocks.add(b);
        }
        for (int i = 0; i < 15; ++i) {
            Block b = new Block(new Rectangle(new Point(720 - (i * 50), 160), 50, 30),
                    Color.yellow, 1);
            this.blocks.add(b);
        }
        for (int i = 0; i < 15; ++i) {
            Block b = new Block(new Rectangle(new Point(720 - (i * 50), 190), 50, 30),
                    Color.CYAN, 1);
            this.blocks.add(b);
        }
        for (int i = 0; i < 15; ++i) {
            Block b = new Block(new Rectangle(new Point(720 - (i * 50), 220), 50, 30),
                    Color.PINK, 1);
            this.blocks.add(b);
        }
        for (int i = 0; i < 15; ++i) {
            Block b = new Block(new Rectangle(new Point(720 - (i * 50), 250), 50, 30),
                    Color.orange.darker(), 1);
            this.blocks.add(b);
        }
    }
    /**
     * this method returns number of balls in level 3.
     * @return number of balls.
     */
    public Integer numberOfBalls() {
        return 3;
    }
    /**
     * this method returns a list of balls' velocities.
     * @return list list of balls' velocities.
     */
    public List<Velocity> initialBallVelocities() {
        this.initialBallVelocities.add((Velocity.fromAngleAndSpeed(20, this.speedBall)));
        this.initialBallVelocities.add((Velocity.fromAngleAndSpeed(-20, this.speedBall)));
        this.initialBallVelocities.add((Velocity.fromAngleAndSpeed(03, this.speedBall)));
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
        return 75;
    }
    /**
     * this method returns the paddle's height.
     * @return paddle's height.
     */
    public Integer paddleHeight() {
        return 15;
    }
    /**
     * this method returns a string of the level's name.
     * @return string of the level's name.
     */
    public String levelName() {
        return "Final Four";
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
     * this method returns the ball speed of the level
     */
    public Integer ballSpeed() {
        return this.speedBall;
    }
}
