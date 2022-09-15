package reservation;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;

public class Stay {
    protected Date start;
    protected int length;
    public Stay(Date start, int length) {
        this.start = start;
        this.length = length;
    }
    public Date getStart() {
        return start;
    }
    public void setStart(Date start) {
        this.start = start;
    }
    public int getLength() {
        return length;
    }
    public void setEnd(int length) {
        this.length = length;
    }

    public Stay(ResultSet r) throws SQLException {
        this.start = r.getDate("startsAt");
        this.length = r.getInt("length");
    }
    /* 
     * Determines the status of the reservation by comparing start and end dates with the current date
     */
    public ReservationState getStatus() {
        var now = Date.valueOf(LocalDate.now());
        Calendar c = Calendar.getInstance();
        c.setTime(start);
        c.add(Calendar.DATE, length);
        var end = c.getTime();
        if (now.after(end)) {
            return ReservationState.DONE;
        } else if (start.before(now)) {
            return ReservationState.UPCOMING;
        } 

        return ReservationState.ONGOING;
    }
}