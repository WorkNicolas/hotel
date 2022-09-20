package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import payment.Receipt;
import samples.ReservationSample;
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
        var p = ReservationSample.genPayment();
        p.setAmount(r.getTotal());
        assertTrue(p.getAmount() > 0);
        var results = Waiter.order(
            p,
            r,
            4 //Existing reservation
        );
        assertNotEquals(0, results.size());
    }

    @Test
    public void shouldGetReceipt() throws SQLException {
        var result = Waiter.getReceipt(4);
        assertEquals(33, result.amenities.size());
    }
    public static void main(String[] args) {
        try {
            var result = Waiter.getReceipt(4);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}