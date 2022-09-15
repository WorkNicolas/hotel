import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import service.Amenity;
import service.Supplier;
import service.Type;

public class SupplierTests {
    
    @Test
    public void shouldAdd() throws SQLException {
        Supplier.add(new Amenity(0, "test", 0f, 1, Type.THING));
    }

    @Test
    public void shouldAlter() {
    }
}
