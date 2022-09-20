package reservation;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;

import auth.Registrar;
import auth.User;
import db.Connector;
import payment.Payment;
import room.Info;

public class Hotelier extends Connector{
    protected static final String TABLE_NAME = "reservations";
    public Hotelier() throws SQLException {
        Connector.executeSQL("CREATE TABLE IF NOT EXISTS `reservations` ( `id` int PRIMARY KEY AUTO_INCREMENT, `start` date NOT NULL, `end` date NOT NULL, `room_id` int NOT NULL, `tenant_id` int NOT NULL, `payment_id` int NOT NULL, FOREIGN KEY (`room_id`) REFERENCES `rooms` (`id`), FOREIGN KEY (`tenant_id`) REFERENCES `users` (`id`), FOREIGN KEY (`payment_id`) REFERENCES `payments`(`id`) )");

        Connector.executeSQL("CREATE TABLE IF NOT EXISTS `payments` ( `id` int PRIMARY KEY AUTO_INCREMENT, `amount` float NOT NULL, `method` varchar(64) NOT NULL, `account` varchar(128) NOT NULL, `discount` float NOT NULL)");
    }

    public static int getIDByEmail(String email) throws SQLException {
        var conn = connect();
        var s = conn.prepareStatement("SELECT id FROM users WHERE email = ?;");
        s.setString(1, email);
        var result = s.executeQuery();
        var id = 0;
        if (result.next()) {
            id = result.getInt("id");
        } 
        conn.close();
        return id;
    }
    /**
     * @return newly created record's id if n > 0. Otherwise it failed.
     */
    public static int commit(Reservation r) throws SQLException {
        var conn = connect();
        var tenant_id = getIDByEmail(r.getTenant().getEmail());
        int new_id = 0;
        if (tenant_id == 0)  {
            return 0;
        }
        var payment_id = pay(r.payment);
        var s = conn.prepareStatement("INSERT INTO " + TABLE_NAME + "(room_id, start, end, tenant_id, payment_id) VALUES(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        s.setInt(1, r.getRoom().getId());
        s.setDate(2, r.getStay().getStart());
        s.setDate(3, r.getStay().getEnd());
        s.setInt(4, tenant_id);
        s.setInt(5, payment_id);
        var status = s.executeUpdate();
        if (status == 0)
            return 0;
        var rs = s.getGeneratedKeys();
        if (rs.next()) {
            new_id = rs.getInt(1);
        }
        conn.close();
        return new_id;
    }

        /**
     * @return newly created record's id if n > 0. Otherwise it failed.
     */
    public static int pay(Payment p) {
        int new_id = 0;
        try {
            var conn = connect();
            var s = conn.prepareStatement("INSERT INTO payments(amount, method, account, discount) VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            s.setFloat(1, p.getAmount());
            s.setString(2, p.getMethod());
            s.setString(3, p.getAccount());
            s.setFloat(4, p.getDiscount_rate());
            int status = s.executeUpdate();
            if (status == 1) {
                var rs = s.getGeneratedKeys();
                if (rs.next()) {
                    new_id = rs.getInt(1);
                }
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

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
            var p = getPay(r.getInt("payment_id"));
            if (p.isEmpty()) {
                continue;
            }
            reservations.add(
                new Reservation(
                    r.getInt("id"), 
                    c,
                    info,
                    new Stay(r),
                    p.get()
            ));
        }

        return reservations;
    }

    public static Optional<Payment> getPay(int id) throws SQLException {
        var conn = connect();
        Payment p = null;
        var s = conn.prepareStatement("SELECT * FROM payments WHERE id = ?");
        s.setInt(1, id);
        var r = s.executeQuery();
        if (r.next()) {
            p = new Payment(r);
        }

        return Optional.ofNullable(p);
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

    public static Optional<Reservation> getLatest(ArrayList<Reservation> reservations) {
        if (reservations.size()== 0)
            return Optional.ofNullable(null);

        return Optional.of(reservations.get(0));
    }
    public static ReservationState getStatus(ArrayList<Reservation> reservations) {
        if (reservations.size() == 0) {
            return ReservationState.NONE;
        }
        Reservation latest = reservations.get(0);

        return latest.getStay().getStatus();
    }

    /**
     * Helper to calculate two dates.
     * @throws SQLException
     */
    public static int count(Stay stay) {
        try {
            var conn = connect();
            var s = conn.prepareStatement("SELECT DATEDIFF(?, ?)");
            s.setDate(1,stay.end);
            s.setDate(2,stay.start);
            var r = s.executeQuery();
            if (r.next()) {
                int span = r.getInt(1);
                conn.close();
                span = span < 0 ? span * -1: span; //Prevent negative spans
                span = Math.max(1, span + 1); //Make dates inclusive
                return span;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}