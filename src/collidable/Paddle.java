package collidable;
import java.awt.Color;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import interfaces.Collidable;
import interfaces.Sprite;
import runs.GameLevel;

/**
 * @author TOMER GRADY
 * 205660863
 */

public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle paddle;
    private java.awt.Color color;
    private int widthOfScreen;
    private int widthOfBoarder;
    private int speedDx = 400;
    private int speedBall = 10;
    private double dtGame;

    /**
     * constractor.
     * @param rect rectangle
     * @param color color
     * @param gui gui
     * @param widthOfScreen width of screen
     * @param widthOfBoarder width of border
     * @param speedBall the speed of the bakk
     */
    public Paddle(Rectangle rect, java.awt.Color color, GUI gui, int widthOfScreen, int widthOfBoarder, int speedBall) {
        this.paddle = rect;
        this.color = color;
        this.keyboard = gui.getKeyboardSensor();
        this.widthOfScreen = widthOfScreen;
        this.widthOfBoarder = widthOfBoarder;
        this.speedBall = speedBall;
    }

    /**
     * move the paddle to the left.
     * @param dt the amount of seconds passed since the last call
     */
    public void moveLeft(double dt) {
        if (this.paddle.getUpperLeft().getX() > widthOfBoarder) {
            double newX = Math.max(paddle.getUpperLeft().getX() - (speedDx * dt), widthOfBoarder);
            paddle = new Rectangle(new Point(newX, paddle.getUpperLeft().getY()), paddle.getWidth(),
                    paddle.getHeight());
        }
    }

    /**
     * move the paddle to the right.
     * @param dt the amount of seconds passed since the last call
     */
    public void moveRight(double dt) {
        if (this.paddle.getUpperRight().getX() < widthOfScreen - widthOfBoarder) {
            double newX = Math.min(paddle.getUpperLeft().getX() + (speedDx * dt), widthOfScreen - widthOfBoarder);
            paddle = new Rectangle(new Point(newX, paddle.getUpperLeft().getY()), paddle.getWidth(),
                    paddle.getHeight());
        }
    }

    /**
     * sprite method of move the paddle.
     * @param dt the amount of seconds passed since the last call
     */
    public void timePassed(double dt) {
        this.dtGame = dt;
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft(dt);
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight(dt);
        }
    }

    /**
     * sprite method of draw the paddle.
     * @param d the function who paint the paddle
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.getColor());
        d.fillRectangle((int) this.paddle.getUpperLeft().getX(), (int) this.paddle.getUpperLeft().getY(),
                (int) this.paddle.getWidth(), (int) this.paddle.getHeight());
    }

    /**
     *
     * @return the color of the paddle
     */
    private Color getColor() {
        return this.color;
    }

    /**
     * @return the rectangle of the paddle
     */
    public Rectangle getCollisionRectangle() {
        return this.paddle;
    }
    /**
     * @param collisionPoint point of collision
     * @param currentVelocity the velocity of the vall
     * @param hitter the hitter ball
     * @return the velocity after the hit
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double xLeft = this.getCollisionRectangle().getUpperLeft().getX();
        double diffrence = (this.getCollisionRectangle().getWidth()) / 5;
        Velocity newV = currentVelocity;
        if (collisionPoint.getX() < xLeft + diffrence) {
            newV = Velocity.fromAngleAndSpeed(300, this.speedBall);
        } else if (collisionPoint.getX() < xLeft + (2 * diffrence)) {
            newV = Velocity.fromAngleAndSpeed(330, this.speedBall);
        } else if (collisionPoint.getX() < xLeft + (3 * diffrence)) {
            newV = new Velocity(currentVelocity.getDx(), (-1) * currentVelocity.getDy());
        } else if (collisionPoint.getX() < xLeft + (4 * diffrence)) {
            newV = Velocity.fromAngleAndSpeed(30, this.speedBall);
        } else if (collisionPoint.getX() <= xLeft + (5 * diffrence)) {
            newV = Velocity.fromAngleAndSpeed(60, this.speedBall);
        }
        if (currentVelocity.getDy() < 0) {
            newV = new Velocity(newV.getDx(), newV.getDy() * -1);
        }

        return newV;
    }

    /**
     * Add this paddle to the game.
     * @param g
     *            the game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
    /**
     * Add this paddle to the game.
     * @param g
     *            the game
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
        g.removeCollidable(this);
    }
    /**
     * this method returns the speed of the paddle.
     * @return the speed ot the paddle
     */
    public int paddleSpeed() {
        return speedDx;
    }
    /**
     * this method sets the speed of the paddle.
     * @param speed the speed ot the paddle
     */
    public void setPaddleSpeed(int speed) {
        this.speedDx = speed;
    }
    /**
     * set a new X value of the paddle.
     * @param newX the new x of the paddle
     */
    public void setX(double newX) {
        this.paddle = new Rectangle(new Point(newX, this.paddle.getUpperLeft().getY()),
                this.paddle.getWidth(), this.paddle.getHeight());
    }

}
