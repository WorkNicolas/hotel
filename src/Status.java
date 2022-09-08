import java.util.Observable;

/**
 * Singleton for keeping App Major States
 */
public class Status extends Observable {
    private State s = State.auth;
    public static Status instance = new Status();
    private Status() {};
    public void setS(State s) {
        this.s = s;
        setChanged();
        notifyObservers(s);
        System.out.println(s);
    }
}