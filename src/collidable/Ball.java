package collidable;

import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import collision.CollisionInfo;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import interfaces.Collidable;
import interfaces.HitListener;
import interfaces.HitNotifier;
import interfaces.Sprite;
import runs.GameLevel;

/**
 *
 * @author TOMER GRADY ID 205660863
 */
public class Ball implements Sprite, HitNotifier {

    private Point center;
    private double centerX, centerY;
    private int r;
    private java.awt.Color color;
    private Velocity velo;
    private Point leftDown;
    private Point rightUp;
    private Rectangle frame;
    private GameEnvironment dangers;
    private List<HitListener> hitListeners = new ArrayList<HitListener>();

    /**
     * @param center
     *            point that indicate the center
     * @param r
     *            radios of the ball
     * @param color
     *            color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.centerX = center.getX();
        this.centerY = center.getY();
        this.r = r;
        this.color = color;
        this.velo = new Velocity(0, 0);
        this.leftDown = new Point(0, 0);
        this.rightUp = new Point(600, 600);
        this.frame = new Rectangle(leftDown, rightUp);
        this.dangers = null;
    }

    /**
     * @param centerX
     *            x value of center point
     * @param centerY
     *            y value of center point
     * @param r
     *            radius
     * @param color
     *            color of the point
     */
    public Ball(int centerX, int centerY, int r, java.awt.Color color) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.center = new Point(this.centerX, this.centerY);
        this.r = r;
        this.color = color;
        this.velo = new Velocity(0, 0);
        this.leftDown = new Point(0, 0);
        this.rightUp = new Point(600, 600);
        this.frame = new Rectangle(leftDown, rightUp);
        this.dangers = null;
    }

    /**
     * construct for a ball.
     * @param centerX the x value of the center
     * @param centerY the y value of the center
     * @param r the raduis
     * @param color the color of the ball
     * @param ballR the hit listener
     */
    public Ball(int centerX, int centerY, int r, java.awt.Color color, HitListener ballR) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.center = new Point(this.centerX, this.centerY);
        this.r = r;
        this.color = color;
        this.velo = new Velocity(0, 0);
        this.leftDown = new Point(0, 0);
        this.rightUp = new Point(600, 600);
        this.frame = new Rectangle(leftDown, rightUp);
        this.dangers = null;
        this.addHitListener(ballR);
    }

    /**
     * @return x value of the center
     */
    public int getX() {
        return (int) this.centerX;
    }

    /**
     * @return y value of the center
     */
    public int getY() {
        return (int) this.centerY;
    }

    /**
     * @return the radius of the size
     */
    public int getSize() {
        return this.r;
    }
    /**
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * draw the ball on the given DrawSurface.
     *
     * @param surface
     *            surface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) this.centerX, (int) this.centerY, this.r);
    }

    /**
     * set velocity.
     * @param v
     *            velocity
     */
    public void setVelocity(Velocity v) {
        this.velo = v;
    }

    /**
     * set the velocity of the ball by x,y.
     * @param dx
     *            the change in x line
     * @param dy
     *            the change in y line
     */
    public void setVelocity(double dx, double dy) {
        Velocity newV = new Velocity(dx, dy);
        this.velo = newV;
    }

    /**
     * @return the velocity
     */
    public Velocity getVelocity() {
        return this.velo;
    }

    /**
     *
     * @param x1
     *            x left down point of the frame
     * @param y1
     *            y left down point of the frame
     * @param x2
     *            x right up point of the frame
     * @param y2
     *            y right up point of the frame
     */
    public void setFrame(double x1, double y1, double x2, double y2) {
        this.leftDown = new Point(x1, y1);
        this.rightUp = new Point(x2, y2);
        this.frame = new Rectangle(leftDown, rightUp);
    }

    /**
     * move one step function.
     * @param dt the amount of seconds passed since the last call.
     */
    public void moveOneStep(double dt) {
        Point nextPoint = this.getVelocity().applyToPoint(this.center, dt);
        // line to the next position
        Line trajectory = new Line(this.center, nextPoint);
        CollisionInfo boom = this.dangers.getClosestCollision(trajectory);
        if (boom == null) {
            this.center = nextPoint;
            this.centerX = this.center.getX();
            this.centerY = this.center.getY();
        } else {
            if (!(boom.getCollisionObject() instanceof Paddle)) {
                this.notifyHit((Block) boom.getCollisionObject());
            }
            Point pointBoom = boom.getCollisionPoint();
            double xNew = pointBoom.getX();
            double yNew = pointBoom.getY();
            // moving the collision point far from the the point
            if (this.velo.getDx() > 0) {
                xNew -= 0.00001;
            }
            if (this.velo.getDx() < 0) {
                xNew += 0.00001;
            }
            if (this.velo.getDy() > 0) {
                yNew -= 0.00001;
            }
            if (this.velo.getDy() < 0) {
                yNew += 0.00001;
            }

            // if the ball is inside the paddle
            if (boom.getCollisionObject() instanceof Paddle) {
                Paddle p = (Paddle) boom.getCollisionObject();
                if (this.insideRectangle(p.getCollisionRectangle())) {
                    yNew -= 2;
                }
            }
            // if the ball is inside a block
            if (boom.getCollisionObject() instanceof Block) {
                Block block = (Block) boom.getCollisionObject();
                if (this.insideRectangle(block.getCollisionRectangle())) {
                    yNew = boom.getCollisionObject().getCollisionRectangle().getUpperLeft().getY() + 1;
                }
            }
            this.centerY = yNew;
            this.center = new Point(xNew, yNew);
            Velocity newV = boom.getCollisionObject().hit(this, boom.getCollisionPoint(), this.getVelocity());
            this.setVelocity(newV);
        }
    }
    /**
     * time Passed calls move one step.
     * @param dt the amount of seconds passed since the last call
     */
    public void timePassed(double dt) {
        moveOneStep(dt);
    }

    /**
     * add new block to the Game environment.
     * @param danger
     *            new dangers
     */
    public void addDangers(Collidable danger) {
        this.dangers.addCollidable(danger);
    }

    /**
     * set game environment.
     * @param env
     *            new gameEnvironment
     */
    public void setGameEnv(GameEnvironment env) {
        this.dangers = env;
    }

    /**
     * this method add the ball to the game.
     * @param gameLevel the game that is played.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }
    /**
     * this method removes the ball from the game.
     * @param gameLevel the game that is played.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }

    /**
     * @param rect
     *            the rectangle
     * @return true if the ball is inside the rectangle
     */
    public boolean insideRectangle(Rectangle rect) {
        double x = this.center.getX();
        double y = this.center.getY();
        double xLeft = rect.getUpperLeft().getX();
        double yUp = rect.getUpperLeft().getY();
        double w = rect.getWidth();
        double h = rect.getHeight();
        if ((xLeft <= x && x <= xLeft + w) && (yUp <= y && y <= yUp + h)) {
            return true;
        }
        return false;
    }
    /**
     * this method adds a given hit listener to the balls's hit listeners list.
     * @param hl the given hit listener.
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }
    /**
     * this method removes a given hit listener
     * from the ball's hit listeners list.
     * @param hl the given hit listener.
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
    /**
     * this method gets a ball that is hitting the block and notify
     * all the listeners about the hit.
     * @param beingHit block the is being hit.
     */
    private void notifyHit(Block beingHit) {
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        for (HitListener hl : listeners) {
            hl.hitEvent(beingHit, this);
        }
    }
}
