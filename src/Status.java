import java.util.concurrent.SubmissionPublisher;

/**
 * Singleton for keeping App Major States
 */
public class Status extends SubmissionPublisher<State> {
    protected State value;
    public Status(State value) {
        super();
        this.value = value;
    }
    public static Status self = new Status(State.auth);

    public State getValue() {
        return value;
    }
}