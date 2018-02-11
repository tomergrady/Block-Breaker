package interfaces;

/**
 * Created by TOMER GRADY on 6/10/2017.
 * this interface represent a generic task.
 * @param <T> the task type.
 */
public interface Task<T> {
    /**
     * this method runs this task.
     * @return unimplemented generic object.
     */
    T run();
}