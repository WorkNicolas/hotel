package auth;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Optional;

import db.Connector;

/**
 * {@summary For user authentication}
 * @see Registrar
 */
public class Verifier extends Connector implements AnAuthenticator<User> {
    protected String TABLE_NAME = Registrar.TABLE_NAME;
    protected Optional<User> user = Optional.ofNullable(null);
    @Override
    public boolean login(User u) {
        try {
            var conn = connect();
            PreparedStatement s = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " where email = ? AND phrase = ?;");
            s.setString(1, u.email);
            s.setString(2, u.phrase);
            var r = s.executeQuery();
            var correct = r.next();
            if (correct) {
                user= Optional.of(u);
            }

            conn.close();
            return correct;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    @Override
    public void logout() {
        user = Optional.ofNullable(null);
    }

    @Override
    public Optional<User> getUser() {
        return user;
    }

    public boolean isLoggedIn() {
        return user.isPresent();
    }
}