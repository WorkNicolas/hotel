package tests;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import payment.Receipt;
import samples.Generator;
import service.Supplier;
import service.Waiter;

public class WaiterTests {
    
    @Test
    public void shouldOrder() throws SQLException {
        var r = new Receipt(Supplier.fetchAvailable());
        assertTrue(r.amenities.size() > 0); //Was actually added
        for (var item: r.amenities.values()) {
            item.reduce(3);
        }
        assertTrue(r.getTotal() > 0);
        var p = Generator.genPayment();
        p.setAmount(r.getTotal());
        assertTrue(p.getAmount() > 0);
        var results = Waiter.order(
            p,
            r,
            4 //Existing reservation
        );
        assertNotEquals(0, results.size());
    }
}