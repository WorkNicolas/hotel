package room;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.function.Consumer;

import db.Connector;

/**
 * Queries the db for available rooms
 */
public class Manager extends Connector {
    protected String TABLE_NAME = "rooms";
    public HashMap<String, RawInfo> infos = new HashMap<>();
    protected int limit;

    public Manager() throws SQLException {
        limit = 100;
        String creationCode = "CREATE TABLE IF NOT EXISTS `rooms` (`id` int NOT NULL AUTO_INCREMENT COMMENT 'Primary Key', `size` int NOT NULL, `type` enum('BASIC','STANDARD','SUITE') NOT NULL, `url` varchar(8000) NOT NULL,`available` tinyint(1) NOT NULL DEFAULT '1', PRIMARY KEY (`id`))";
        executeSQL(creationCode);
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

    public HashMap<String, RawInfo> fetchAvailable() {
        try {
            Connection conn = connect();
            var s = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE available = 1 LIMIT ?;");
            s.setInt(1, limit);
            ResultSet r = s.executeQuery();

            while (r.next()) {
                var id = r.getInt("id");
                if (infos.containsKey("" + id))
                    continue;

                infos.put("" + id,
                    new RawInfo(r)
                );
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return infos;
    }

    /**
     * @implNote `preview` field is excluded as writing files to a DB is bad practice.
     */
    public boolean add(RawInfo info) throws SQLException {
        var conn = connect();
        var s = conn.prepareStatement("INSERT INTO " + TABLE_NAME + "(type, size, url) VALUES(?, ?, ?)");
        s.setString(1, info.type.toString());
        s.setInt(2, info.size);
        s.setString(3, info.url);
        s.setInt(4, info.rate);
        return s.execute();
    }

    public static void main(String[] args) throws SQLException {
        Manager r = new Manager(10);
        try {
            Collection<RawInfo> in = r.fetchAvailable().values();
            in.forEach(new Consumer<RawInfo>() {

                @Override
                public void accept(RawInfo t) {
                    System.out.println(t.url);
                }
                
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}