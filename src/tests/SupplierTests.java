package tests;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import service.Amenity;
import service.Supplier;
import service.Type;

public class SupplierTests {
    
    @Test
    public void shouldInsertAndDelete() throws SQLException {
        var id = Supplier.add(new Amenity(0, "test", 0f, 1, Type.THING));
        Supplier.remove(id);
    }

    @Test
    public void shouldAlter() {
    }
}
