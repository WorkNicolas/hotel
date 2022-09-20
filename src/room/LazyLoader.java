package room;
import javax.swing.JLabel;

/**
 * @author JCSJ
 */
public class LazyLoader implements Runnable {
    protected JLabel label;
    protected RawInfo room;
    protected Thread thread;
    protected int width;
    protected int height;
    public LazyLoader(JLabel label, RawInfo room, int width, int height) {
        this.label = label;
        this.room = room;
        this.width = width;
        this.height = height;
    }
    @Override
    public void run() {
        Info upgraded = new Info(room);
        upgraded.preview = Helper.scale(upgraded.preview, width, height);
        label.setIcon(upgraded.preview);
    }
    
    /**
     * @implNote runs only once
     */
    public void start() {
        if (thread ==null) {
            thread = new Thread(this);
            thread.start();
        }
    }
}
