/**
 * Singleton for keeping App Major States
 */
public class Status extends Reactive<State> {
    public Status(State value) {
        super(value);
    }

    public static Status self = new Status(State.AUTH);
}