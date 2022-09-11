import java.sql.Connection;
import java.sql.SQLException;

import auth.User;
import auth.UserInfo;

/**
 * {@summary} Handles database management for users}
 * @see Verifier
 */
public class Registrar extends Connector {
    public static final String TABLE_NAME = "users";

    public Registrar() throws SQLException {
        Connection conn = connect();
        var s =  conn.createStatement();
        s.execute(
            "CREATE TABLE IF NOT EXISTS `users` (`id` int NOT NULL AUTO_INCREMENT, `email` varchar(320) NOT NULL, `name` varchar(256) NOT NULL, `phrase` varchar(256) NOT NULL,PRIMARY KEY (`id`), UNIQUE KEY `email` (`email`))"
        );
        conn.close();
    }
    /**
     * 
     * @return whether the registration was successful
     */
    public boolean register(UserInfo u) {
        Connection conn = null;
        int status = 0;
        try {
            conn = connect();
            var p = conn.prepareStatement("INSERT INTO " + TABLE_NAME + "(name, phrase, email) VALUES(?,?,?)");
            p.setString(1, u.getName());
            p.setString(2, u.getPhrase());
            p.setString(3, u.getEmail());
            status = p.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace(); 
            return false;
        }

        return status == 1;
    }

    public boolean delete(User u) {
        Connection conn;
        int status = 0;
        try {
            conn = connect();
            var p  = conn.prepareStatement("DELETE FROM " + TABLE_NAME + " where email = ? AND phrase = ?;");
            p.setString(1, u.email());
            p.setString(2, u.phrase());
            status = p.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return status == 1;
    }
}