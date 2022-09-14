package payment;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import auth.ContactInfo;
import db.Connector;
import reservation.Reservation;
import reservation.Stay;
import room.Info;

public class Hotelier extends Connector{
    protected static final String TABLE_NAME = "reservations";

    /**
     * @return n > 0 it is the newly created record's id. Otherwise it failed
     */
    public static int commit(Reservation r) throws SQLException {
        var conn = connect();
        var ss = conn.prepareStatement("SELECT id FROM users WHERE email = ?;");
        ss.setString(1, r.getTenant().getEmail());
        var resultSet = ss.executeQuery();
        if (!resultSet.next())
            return 0;
        int new_id = 0;
        {
            var tenant_id = resultSet.getInt("id");
            var s = conn.prepareStatement("INSERT INTO " + TABLE_NAME + "(room_id, startsAt, length, tenant_id) VALUES(?,?,?, ?)", Statement.RETURN_GENERATED_KEYS);
            s.setInt(1, r.getRoom().getId());
            s.setDate(2, r.getStay().getStart());
            s.setInt(3, r.getStay().getLength());
            s.setInt(4, tenant_id);
            var status = s.executeUpdate();
            if (status == 0)
                return 0;
            var rs = s.getGeneratedKeys();
            if (rs.next()) {
                new_id = rs.getInt(1);
            }
        }
        conn.close();
        return new_id;
    }

    public static boolean cancel(int id) throws SQLException {
        var conn = connect();
        var s = conn.prepareStatement("DELETE FROM " + TABLE_NAME + " WHERE id = ?");
        s.setInt(1, id);
        var status = s.executeUpdate();
        return status == 1;
    }

    public static ArrayList<Reservation> getReservations(ContactInfo c) throws SQLException {
        var conn = connect();
        ArrayList<Reservation> reservations = new ArrayList<>();
        int tenant_id;
        {
            var tenantQuery = conn.prepareStatement("SELECT id FROM users WHERE ? email = ? LIMIT 1");
            tenantQuery.setString(1, c.getEmail());
            var tenantResult = tenantQuery.executeQuery();
            if (tenantResult.next()) {
                tenant_id = tenantResult.getInt("id");
            } else {
                return reservations;
            }
        }
 
        var s = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE tenant_id = ?");
        s.setInt(1, tenant_id);
        var r = s.executeQuery();
        while (r.next()) {
            int room_id = r.getInt("room_id");
            Info info = null;
            {
                var roomQuery = conn.prepareStatement("SELECT * FROM rooms WHERE id = ?");
                roomQuery.setInt(1, room_id);
                var roomResult = roomQuery.executeQuery();
                if (roomResult.next())
                    info = new Info(roomResult);
            }
            if (info == null)
                continue;

            reservations.add(
                new Reservation(
                    r.getInt("room_id"), 
                    c,
                    info,
                    new Stay(r)
            ));
        }

        return reservations;
    }
}
