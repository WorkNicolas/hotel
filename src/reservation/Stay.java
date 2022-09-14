package reservation;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}