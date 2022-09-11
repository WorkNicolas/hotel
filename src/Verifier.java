import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Optional;

public class Verifier extends Connector implements AnAuthenticator<User> {
    protected String TABLE_NAME = "users";
    /* 
     CREATE TABLE `users` (
        `id` int NOT NULL AUTO_INCREMENT,
        `email` varchar(320) NOT NULL,
        `name` varchar(256) NOT NULL,
        `phrase` varchar(256) NOT NULL,
        PRIMARY KEY (`id`),
        UNIQUE KEY `email` (`email`)
    )
     * 
     */
    protected Optional<User> user = Optional.ofNullable(null);
    @Override
    public boolean login(User u) {
        Connection conn = null;
        try {
            conn = connect();
            PreparedStatement s = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " where email = ? AND phrase = ?;");
            s.setString(1, u.email());
            s.setString(2, u.phrase());
            var r = s.executeQuery();
            return r.next();
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
