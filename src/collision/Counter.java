package collision;
/**
 * this class represents a counter.
 */
public class Counter {
    private int count;
    /**
     * construct a counter from an initial value.
     * @param initialValue the given initial value.
     */
    public Counter(int initialValue) {
        this.count = initialValue;
    }
    /**
     * this method gets an integer and increases the counter accordingly.
     * @param number the given number.
     */
    public void increase(int number) {
        this.count += number;
    }
    /**
     * this method gets an integer and decreases the counter accordingly.
     * @param number the given number.
     */
    public void decrease(int number) {
        this.count -= number;
    }
    /**
     * this method returns the current value of the counter.
     * @return the current value of the counter.
     */
    public int getValue() {
        return this.count;
    }
}
