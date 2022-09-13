package room;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.function.Consumer;

import javax.swing.ImageIcon;
import db.Connector;

/**
 * Queries the db for available rooms
 */
public class Manager extends Connector {
    protected String TABLE_NAME = "rooms";
    public HashMap<String, Info> infos = new HashMap<>();
    protected int limit;

    public Manager() {
        limit = 100;
    }

    public Manager(int limit) {
        this.limit = limit;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public HashMap<String, Info> fetchAvailable() {
        Connection conn = null;
        try {
            conn = connect();
            var s = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE available = 1 LIMIT ?;");
            s.setInt(1, limit);
            ResultSet r = s.executeQuery();

            while (r.next()) {
                var id = r.getString("id");
                if (infos.containsKey(id))
                    continue;

                String url = r.getString("url");
                infos.put(id,
                    new Info(id,
                        Type.valueOf(r.getString("type")),
                        r.getInt("size"),
                        url,
                        imageIconFromURL(url) //FETCH image
                    )
                );
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        return infos;
    }

    /**
     * @implNote `preview` field is excluded as writing files to a DB is bad practice.
     */
    public boolean add(Info info) throws SQLException {
        var conn = connect();
        var s = conn.prepareStatement("INSERT INTO " + TABLE_NAME + "(type, size, url) VALUES(?, ?, ?)");
        s.setString(1, info.type().toString());
        s.setInt(2, info.size());
        s.setString(3, info.url());
        return s.execute();
    }

    public static ImageIcon imageIconFromURL(String urlStr) throws IOException {
        return new ImageIcon(
                new URL(urlStr));
    }

    public static void main(String[] args) {
        Manager r = new Manager();
        try {
            var in = r.fetchAvailable().values();
            in.forEach(new Consumer<Info>() {

                @Override
                public void accept(Info t) {
                    System.out.println(t.url());
                }
                
            });
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}