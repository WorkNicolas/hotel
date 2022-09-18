package tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import java.sql.SQLException;


import auth.Registrar;
import auth.User;
import auth.UserInfo;
import auth.Verifier;
import reservation.Hotelier;

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
        UserInfo u = new UserInfo(
            "Tempest", 
            "temporary@account", 
            "1234 Example", 
            "00000000000",
            "Temporary account"
        );
        assertNotEquals(0, r.register(u));

        assertEquals(
            true, 
            r.delete(new User(u.getEmail(), u.getPhrase()))
        );
    }

    @Test
    public void canGetContactInfo() {
        assertNotNull(Hotelier.getContactInfo(new User("jc@sj", "jcsj")));
    }

    @Test
    public void canGetReservations() {
        assertNotNull(
        Hotelier.getReservations(
            new User("jc@sj", "jcsj")
        ));
    }
}