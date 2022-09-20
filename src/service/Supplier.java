package service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import db.Connector;

/**
 * @author JCSJ
 */
public class Supplier extends Connector { 
    public final static String TABLE_NAME = "amenities";
    public static ArrayList<Amenity> fetchAvailable() throws SQLException {
        var conn = connect();
        var s = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE supply > 0");
        ResultSet rs = s.executeQuery();
        var items = asAmenities(rs);
        conn.close();
        return items;
    }

    /**
     * Helper for creating Amenities from the records
     */
    public static ArrayList<Amenity> asAmenities(ResultSet rs) {
        ArrayList<Amenity> a = new ArrayList<>();
        try {
            while(rs.next()) {
                a.add(new Amenity(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return a;
    }
    /**
     * @return the new record's id
     */
    public static int add(Amenity a) throws SQLException {
        var conn = connect();
        int new_id = 0;
        var s = conn.prepareStatement("INSERT INTO " + TABLE_NAME + "(name, type, price, supply) VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        s.setString(1, a.name);
        s.setString(2, a.type.toString());
        s.setFloat(3, a.price);
        s.setInt(4, a.supply);
        s.execute();
        var rs = s.getGeneratedKeys();
        if (rs.next()) {
            new_id = rs.getInt(1);
        }
        conn.close();

        return new_id;
    }

    public static void remove(int id) throws SQLException {
        var conn = connect();
        var s = conn.prepareStatement("DELETE FROM " + TABLE_NAME + " WHERE id = ?");
        s.setInt(1, id);
        s.executeUpdate();
        conn.close();
    }
    public static int alterSupply(Amenity a) throws SQLException {
        var conn = connect();
        var s = conn.prepareStatement("UPDATE " + TABLE_NAME + " SET supply = ? WHERE id = ?");
        s.setInt(1, a.supply);
        s.setInt(2, a.id);
        var status = s.executeUpdate();
        conn.close();
        return status;
    }
}
