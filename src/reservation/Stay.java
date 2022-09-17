package reservation;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Stay {
    protected Date start;
    protected Date end;
    public Stay(Date start, Date end) {
        this.start = start;
        this.end = end;
    }
    public Date getEnd() {
        return end;
    }
    public void setEnd(Date end) {
        this.end = end;
    }
    public Date getStart() {
        return start;
    }
    public void setStart(Date start) {
        this.start = start;
    }

    public Stay(ResultSet r) throws SQLException {
        this.start = r.getDate("start");
        this.end = r.getDate("end");
    }
    /* 
     * Determines the status of the reservation by comparing start and end dates with the current date
     */
    public ReservationState getStatus() {
        var now = Date.valueOf(LocalDate.now());
        if (now.after(end)) {
            return ReservationState.DONE;
        } else if (start.before(now)) {
            return ReservationState.UPCOMING;
        } 

        return ReservationState.ONGOING;
    }

    @Override
    public String toString() {
        return start.toString() + " --- " + end.toString();
    }
}