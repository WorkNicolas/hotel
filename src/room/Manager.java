package room;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import db.Connector;
import db.Fetcher;
import reservation.Stay;
/**
 * @author JCSJ
 * Queries the db for available rooms
 */
public class Manager extends Connector implements Fetcher<Stay, ArrayList<RawInfo>>{
    protected String TABLE_NAME = "rooms";
    protected int limit;
    private static Manager instance;
    public Manager() throws SQLException {
        limit = 100;
        String creationCode = "CREATE TABLE IF NOT EXISTS `rooms` (`id` int NOT NULL AUTO_INCREMENT COMMENT 'Primary Key', `size` int NOT NULL, `type` enum('BASIC','STANDARD','SUITE') NOT NULL, `url` varchar(8000) NOT NULL,`available` tinyint(1) NOT NULL DEFAULT '1', PRIMARY KEY (`id`))";
        executeSQL(creationCode);
    }
    public static Manager getManager() throws SQLException {
        if (instance == null)
            instance = new Manager();
        return instance;
    }
    public Manager(int limit) throws SQLException {
        this();
        this.limit = limit;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public ArrayList<RawInfo> fetch(Stay stay, int limit) {
        setLimit(limit);
        return fetch(stay);
    }

    /**
     * @implNote `preview` field is excluded as writing files to a DB is bad practice.
     */
    public boolean add(RawInfo info) throws SQLException {
        var conn = connect();
        var s = conn.prepareStatement("INSERT INTO " + TABLE_NAME + "(type, size, url, name) VALUES(?, ?, ?)");
        s.setString(1, info.type.toString());
        s.setInt(2, info.size);
        s.setString(3, info.url);
        s.setInt(4, info.rate);
        s.setString(5, info.name);
        return s.execute();
    }
    @Override
    public ArrayList<RawInfo> fetch(Stay stay) {
        ArrayList<RawInfo> rooms = new ArrayList<>();
        try {
            Connection conn = connect();
            var s = conn.prepareStatement("SELECT * FROM rooms WHERE id NOT IN (SELECT room_id FROM reservations WHERE (start BETWEEN ? AND ?) OR (end BETWEEN ? AND ?)) LIMIT ?");
            s.setDate(1, stay.getStart());
            s.setDate(2, stay.getEnd());
            s.setDate(3, stay.getStart());
            s.setDate(4, stay.getEnd());
            s.setInt(5, limit);
            ResultSet r = s.executeQuery();

            while (r.next()) {
                rooms.add(
                    new RawInfo(r)
                );
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rooms;
    }
}