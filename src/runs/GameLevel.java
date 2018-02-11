package runs;
import java.awt.Color;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import collidable.Ball;
import collidable.Block;
import collidable.GameEnvironment;
import collidable.Paddle;
import collidable.SpriteCollection;
import collision.BallRemover;
import collision.BlockRemover;
import collision.Counter;
import collision.ScoreTrackingListener;
import animation.AnimationRunner;
import animation.CountdownAnimation;
import drawing.LevelNameIndicator;
import drawing.LivesIndicator;
import drawing.PauseScreen;
import drawing.ScoreIndicator;
import geometry.Point;
import geometry.Rectangle;
import interfaces.Animation;
import interfaces.Collidable;
import interfaces.HitListener;
import interfaces.LevelInformation;
import interfaces.Sprite;

/**
 * @author TOMER 205660863
 *
 */
public class GameLevel implements Animation {
    private int paddleHight = 18;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter score;
    private Counter lives;
    private ScoreTrackingListener scoreListener;
    private ScoreIndicator scoredraw;
    private LivesIndicator livesDraw;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation levelInfo;
    private Paddle paddle;
    private int speedBall;
    private int numberBalls;
    private LevelNameIndicator nameDraw;
    /**
     * this is a constractor for game level.
     * @param level the level information of the game level
     * @param gui the graffic user interface.
     * @param animation the animation runner of the game
     * @param score the counter that indicated the score of the game
     * @param lives the counter thar indicated the lives that left in the game
     */
    public GameLevel(LevelInformation level, GUI gui, AnimationRunner animation, Counter score, Counter lives) {
        this.gui = gui;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.remainingBlocks = new Counter(level.numberOfBlocksToRemove());
        this.remainingBalls = new Counter(0);
        this.score = score;
        this.lives = lives;
        this.livesDraw = new LivesIndicator(this.lives);
        this.scoreListener = new ScoreTrackingListener(score);
        this.scoredraw = new ScoreIndicator(score);
        this.nameDraw = new LevelNameIndicator(level.levelName());
        this.running = true;
        this.runner = animation;
        this.keyboard = this.gui.getKeyboardSensor();
        this.levelInfo = level;
        this.speedBall = level.ballSpeed() - 30;
        this.numberBalls = level.numberOfBalls();
        }
    /**
     * constractor.
     */
    public GameLevel() {
        this.gui = new GUI("TEST", 800, 600);
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.remainingBlocks = new Counter(0);
        this.remainingBalls = new Counter(0);
        this.score = new Counter(0);
        this.lives = new Counter(4);
        this.scoreListener = new ScoreTrackingListener(score);
        this.scoredraw = new ScoreIndicator(score);
        //this.livesDraw = new LivesIndicator(lives);
        this.running = true;

        this.keyboard = this.gui.getKeyboardSensor();
        }
    /**
     * add collidable to the gameEnvironment.
     * @param c new collidable
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }
    /**
     * this method removes a given collidable from the game.
     * @param c the given collidable object.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }
    /**
     * this method adds new sprite to the sprite collection.
     * @param s new sprite
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }
    /**
     * this method removes a given sprite object from the game.
     * @param s the given sprite object.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }
    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle).
     * and add them to the game.
     */
    public void initialize() {
        this.sprites.addSprite(this.levelInfo.getBackground());

        // create the paddle.
        Rectangle paddleRect = new Rectangle(new Point(350, 570),
                this.levelInfo.paddleWidth(), paddleHight);
        this.paddle = new Paddle(paddleRect, Color.darkGray,
                this.gui, 800, 30, speedBall);
        initializeBlocks();
        paddle.addToGame(this);
        addBorders();
        this.livesDraw = new LivesIndicator(this.lives);
        this.addSprite(livesDraw);
        //this.livesDraw.addToGame(this);

    }

    /**
     * this method plays one turn, create the paddle and the balls and run the turn.
     */
    public void playOneTurn() {
        //create the balls.
        initializeBalls();
        this.paddle.setX(400 - (levelInfo.paddleWidth() / 2));
        if (this.remainingBlocks.getValue() > 0) {
            this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        }
        this.running = true;
        this.runner.run(this);
      //  this.running = true;
        }
    @Override
    /**
     * this method draw one turn
     * @param d drawsurface
     * @param dt the amount of seconds passed since the last call
     */
    public void doOneFrame(DrawSurface d, double dt) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed(dt);
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new PauseScreen(this.keyboard));
        }
        if (this.isLose()) {
            this.running = false;
        }
        if  (this.isWin()) {
            this.running = false;
        }
    }

    /**
     * create the borders and add to the game.
     */
    public void addBorders() {
        Block[] borders = new Block[4];
        // death block
        borders[0] = new Block(new Rectangle(new Point(0, 600), 800, 30), true, true);
        //other blocks
        borders[1] = new Block(new Rectangle(new Point(0, 27), 800, 33), true);
        borders[2] = new Block(new Rectangle(new Point(0, 30), 30, 670), true);
        borders[3] = new Block(new Rectangle(new Point(770, 30), 30, 670), true);
        for (int i = 0; i < borders.length; i++) {
            borders[i].addToGame(this);
        }
    }
    /**
     * this method check if the player win the game.
     * @return true if the player wins, false otherwise.
     */
    public boolean isWin() {
        return this.remainingBlocks.getValue() == 0;
    }
    /**
     * this method check if the player lose the game.
     * @return true if the player wins, false otherwise.
     */
    public boolean isLose() {
        return this.remainingBalls.getValue() == 0;
    }

    @Override
    /**
     * this method tells id the game should stop.
     * @return true is the game should stop, false othesise.
     */
    public boolean shouldStop() {
        return !this.running;
    }
    /**
     * This method initial the blocks in the game.
     */
    private void initializeBlocks() {
      BlockRemover blockRemover = new BlockRemover(this, this.remainingBlocks);
      for (Block block : levelInfo.blocks()) {
        block.addHitListener(blockRemover);
        block.addHitListener(this.scoreListener);
        block.addToGame(this);
      }
    }
    /**
     * initial the balls in the game level.
     */
    private void initializeBalls() {
      HitListener ballR = new BallRemover(this, this.remainingBalls);
      for (int i = 0; i < this.numberBalls; i++) {
          Ball ball = new Ball(new Point(400, 550), 5, Color.blue);
          ball.setVelocity(this.levelInfo.initialBallVelocities().get(i));
          ball.setGameEnv(this.environment);
          ball.addToGame(this);
          ball.addHitListener(ballR);
          this.remainingBalls.increase(1);
      }
    }
    /**
     * @return the current score of the game.
     */
    public Counter getScore() {
        return this.score;
    }
}