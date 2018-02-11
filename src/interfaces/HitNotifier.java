package interfaces;
/**
 * @author TOMER GRADY
 * 205660863
 * this interface represents a hit notifier.
 */
public interface HitNotifier {
    /**
     * this method adds a given hit listener to a list of hit listeners in this hit notifier.
     * @param hl the hit listener object.
     */
    void addHitListener(HitListener hl);
    /**
     * this method removes a given hit listener object
     * from the list of hit listeners in this hit notifier.
     * @param hl the hit listener object.
     */
    void removeHitListener(HitListener hl);
}