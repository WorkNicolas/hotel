import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Test;

import auth.User;
import auth.UserInfo;

public class UserTests {
    @Test
    public void customerCanLogin() {
        Verifier v = new Verifier();
        assertEquals(
            true, 
            v.login(new User("hello@world", "world"))
        );
    }

    @Test 
    public void canRegisterAndDelete() throws SQLException {
        var r = new Registrar();
        UserInfo u = new UserInfo("Tempest", "temporary@account", "Temporary account");
        assertEquals(true, r.register(u));

        assertEquals(
            true, 
            r.delete(new User(u.getEmail(), u.getPhrase()))
        );
    }
}