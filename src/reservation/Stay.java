package reservation;
import java.sql.Date;

public class Stay {
    protected Date start;
    protected Date end;
    public Stay(Date start, Date end) {
        this.start = start;
        this.end = end;
    }
    public Date getStart() {
        return start;
    }
    public void setStart(Date start) {
        this.start = start;
    }
    public Date getEnd() {
        return end;
    }
    public void setEnd(Date end) {
        this.end = end;
    }
}