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
public class Level2 implements LevelInformation {
    private Sprite background;
    private List<Block> blocks = new ArrayList<Block>();;
    private List<Velocity> initialBallVelocities = new ArrayList<Velocity>();
    private int speedBall = 100;
    /**
     * construct level 1.
     * creating background and blocks.
     * Initializing balls' speeds.
     */
    public Level2() {
        this.background = new Background2();
        Block block1 = new Block(new Rectangle(new Point(720, 250), 50, 30), Color.RED, 1);
        Block block2 = new Block(new Rectangle(new Point(670, 250), 50, 30), Color.RED, 1);
        Block block3 = new Block(new Rectangle(new Point(620, 250), 50, 30), Color.blue, 1);
        Block block4 = new Block(new Rectangle(new Point(570, 250), 50, 30), Color.blue, 1);
        Block block5 = new Block(new Rectangle(new Point(520, 250), 50, 30), Color.ORANGE, 1);
        Block block6 = new Block(new Rectangle(new Point(470, 250), 50, 30), Color.ORANGE, 1);
        Block block7 = new Block(new Rectangle(new Point(420, 250), 50, 30), Color.YELLOW, 1);
        Block block8 = new Block(new Rectangle(new Point(370, 250), 50, 30), Color.YELLOW, 1);
        Block block9 = new Block(new Rectangle(new Point(320, 250), 50, 30), Color.GREEN, 1);
        Block block10 = new Block(new Rectangle(new Point(270, 250), 50, 30), Color.GREEN, 1);
        Block block11 = new Block(new Rectangle(new Point(220, 250), 50, 30), Color.GREEN, 1);
        Block block12 = new Block(new Rectangle(new Point(170, 250), 50, 30), Color.PINK, 1);
        Block block13 = new Block(new Rectangle(new Point(120, 250), 50, 30), Color.PINK, 1);
        Block block14 = new Block(new Rectangle(new Point(75, 250), 45, 30), Color.CYAN, 1);
        Block block15 = new Block(new Rectangle(new Point(30, 250), 45, 30), Color.CYAN, 1);
        this.blocks.add(block1);
        this.blocks.add(block2);
        this.blocks.add(block3);
        this.blocks.add(block4);
        this.blocks.add(block5);
        this.blocks.add(block6);
        this.blocks.add(block7);
        this.blocks.add(block8);
        this.blocks.add(block9);
        this.blocks.add(block10);
        this.blocks.add(block11);
        this.blocks.add(block12);
        this.blocks.add(block13);
        this.blocks.add(block14);
        this.blocks.add(block15);

    }
    /**
     * this method returns number of balls in level 1.
     * @return number of balls.
     */
    public Integer numberOfBalls() {
        return 10;
    }
    /**
     * this method returns a list of balls' velocities.
     * @return list list of balls' velocities.
     */
    public List<Velocity> initialBallVelocities() {
        this.initialBallVelocities.add(Velocity.fromAngleAndSpeed(50, this.speedBall));
        this.initialBallVelocities.add(Velocity.fromAngleAndSpeed(40, this.speedBall));
        this.initialBallVelocities.add(Velocity.fromAngleAndSpeed(30, this.speedBall));
        this.initialBallVelocities.add(Velocity.fromAngleAndSpeed(20, this.speedBall));
        this.initialBallVelocities.add(Velocity.fromAngleAndSpeed(10, this.speedBall));
        this.initialBallVelocities.add(Velocity.fromAngleAndSpeed(-10, this.speedBall));
        this.initialBallVelocities.add(Velocity.fromAngleAndSpeed(-20, this.speedBall));
        this.initialBallVelocities.add(Velocity.fromAngleAndSpeed(-30, this.speedBall));
        this.initialBallVelocities.add(Velocity.fromAngleAndSpeed(-40, this.speedBall));
        this.initialBallVelocities.add(Velocity.fromAngleAndSpeed(-50, this.speedBall));
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
        return 700;
    }
    /**
     * this method returns a string of the level's name.
     * @return string of the level's name.
     */
    public String levelName() {
        return "Wide Easy";
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