import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Login {
    @Test
    public void forCustomer() {
        Verifier v = new Verifier();
        assertEquals(
            true, 
            v.login(new User("hello@world", "world"))
        );
    }
}
