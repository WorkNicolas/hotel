package reservation;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import auth.Registrar;
import auth.User;
import db.Connector;
import room.Info;

public class Hotelier extends Connector{
    protected static final String TABLE_NAME = "reservations";
    public Hotelier() throws SQLException {
        Connector.executeSQL("CREATE TABLE IF NOT EXISTS `reservations` ( `id` int NOT NULL AUTO_INCREMENT COMMENT 'Primary Key', `start` date NOT NULL, `room_id` int NOT NULL, `tenant_id` int NOT NULL, `end` date NOT NULL, PRIMARY KEY (`id`), KEY `room_id` (`room_id`), KEY `tenant_id` (`tenant_id`), CONSTRAINT `reservations_ibfk_1` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`id`), CONSTRAINT `reservations_ibfk_2` FOREIGN KEY (`tenant_id`) REFERENCES `users` (`id`) )");
    }
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
            var s = conn.prepareStatement("INSERT INTO " + TABLE_NAME + "(room_id, start, end, tenant_id) VALUES(?,?,?, ?)", Statement.RETURN_GENERATED_KEYS);
            s.setInt(1, r.getRoom().getId());
            s.setDate(2, r.getStay().getStart());
            s.setDate(3, r.getStay().getEnd());
            s.setInt(4, tenant_id);
            //TODO: Receipt
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

    public static ContactInfo getContactInfo(User u) throws IllegalArgumentException{
        ContactInfo c = null;
        try {
            var conn = connect();
            var tenantQuery = conn.prepareStatement("SELECT * FROM " + Registrar.TABLE_NAME + " WHERE email = ?");
            tenantQuery.setString(1, u.getEmail());
            var tenantResult = tenantQuery.executeQuery();
            if (tenantResult.next()) {
                c = new ContactInfo(tenantResult);
            } 
            conn.close();
        } catch (SQLException e) {
            //PASS
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (c == null) {
            throw new IllegalArgumentException("Unknown user:" + u.getEmail());
        }
        
        return c;
    }

    /**
     * @apiNote If size > 0, head is the latest reservation.
     */
    public static ArrayList<Reservation> getReservations(ContactInfo c) throws SQLException {
        var conn = connect();
        ArrayList<Reservation> reservations = new ArrayList<>();
        var s = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE tenant_id = ? ORDER BY start DESC");
        s.setInt(1, c.getId());
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

    public static ArrayList<Reservation> getReservations(User u) {
        try {
            var c = getContactInfo(u);
            return getReservations(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static ReservationState getStatus(ArrayList<Reservation> reservations) {
        if (reservations.size() == 0) {
            return ReservationState.NONE;
        }
        Reservation latest = reservations.get(0);

        return latest.getStay().getStatus();
    }
}