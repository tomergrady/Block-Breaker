package collidable;
import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;
import interfaces.Sprite;

/**
 * @author TOMER GRADY 205660863
 *
 */
public class SpriteCollection {

    private List<Sprite> spriteColl;
    /**
     * contactor.
     */
    public SpriteCollection() {
        this.spriteColl = new ArrayList<Sprite>();

    }
    /**
     * this method adds sprite to the collection.
     * @param s
     *            new sprite object
     */
    public void addSprite(Sprite s) {
        this.spriteColl.add(s);
    }
    /**
     * this method removes a sprite object from collection.
     * @param s the given sprite object.
     */
    public void removeSprite(Sprite s) {
        this.spriteColl.remove(s);
    }

    /**
     * call timePassed() on all sprites.
     * @param dt the amount of seconds passed since the last call
     */
    public void notifyAllTimePassed(double dt) {
        ArrayList<Sprite> sprites = new ArrayList<>(this.spriteColl);
        for (int i = 0; i < sprites.size(); i++) {
            sprites.get(i).timePassed(dt);
        }
    }

    /**
     * call drawOn(d) on all sprites.
     * @param d drawsurface
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < this.spriteColl.size(); i++) {
            this.spriteColl.get(i).drawOn(d);
        }
    }
}