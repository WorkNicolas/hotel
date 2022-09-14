import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;


import auth.Registrar;
import auth.User;
import auth.UserInfo;
import auth.Verifier;

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
        UserInfo u = new UserInfo("Tempest", "temporary@account", "Test", "Temporary account");
        assertNotEquals(0, r.register(u));

        assertEquals(
            true, 
            r.delete(new User(u.getEmail(), u.getPhrase()))
        );
    }
}