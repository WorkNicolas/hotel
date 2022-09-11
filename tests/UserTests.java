import static org.junit.Assert.assertEquals;

import org.junit.Test;

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
    public void canRegisterAndDelete() {
        var r = new Registrar();
        UserInfo u = new UserInfo("Tempest", "temporary@account", "Temporary account");

        assertEquals(true, r.register(u));

        assertEquals(
            true, 
            r.delete(new User(u.email, u.phrase))
        );
    }
}