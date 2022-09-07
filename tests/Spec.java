import static org.junit.Assert.assertEquals;

import org.junit.Test;
public class Spec {

    @Test
    public void canLaunch() {
        App a = new App();
        assertEquals(a, a); //Does not throw
    }
}