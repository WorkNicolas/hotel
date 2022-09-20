package room;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author JCSJ
 * Represents a record in the `rooms` table
 */
public class RawInfo {
    protected int id;
    protected Type type;
    protected int size;
    protected String url;
    protected int rate;
    protected String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public RawInfo(int id, Type type, int size, String url, int rate, String name) {
        this.id = id;
        this.type = type;
        this.size = size;
        this.url = url;
        this.rate = rate;
        this.name = name;
    }

    public RawInfo(int id, Type type, int size, String url, int rate) {
        this(id, type, size, url, rate, "");
    }

    public RawInfo(ResultSet r) throws SQLException {
        this(
            r.getInt("id"), 
            Type.valueOf(r.getString("type")), 
            r.getInt("size"), 
            r.getString("url"),
            r.getInt("rate"),
            r.getString("name")
        );
    }
}
