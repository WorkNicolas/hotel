import java.util.concurrent.SubmissionPublisher;
/**
 * Publisher that holds a value
 * @author JCSJ
 */
public class Reactive<T> extends SubmissionPublisher<T> {
    protected T value;
    public Reactive(T value) {
        super();
        this.value = value;
    }
    public T getValue() {
        return value;
    }
}