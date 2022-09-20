package tests;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.function.Consumer;

import reservation.DateComponent;
import reservation.Hotelier;
import reservation.Reservation;
import room.Info;
import room.Manager;
import room.RawInfo;
import samples.Generator;

public class ReservationTests {

    @Test
    public void shouldCommitAndCanel() throws FileNotFoundException, SQLException {
        Manager m = new Manager(1);
        var stay = Generator.genStay();
        var rooms = m.fetch(stay);
        assertEquals(1, rooms.size());
        var payment = Generator.genPayment();
        rooms.forEach(new Consumer<RawInfo>() {

            @Override
            public void accept(RawInfo raw) {
                Reservation r;
                payment.setAmount(raw.getRate() * Hotelier.count(stay));
                try {
                    r = new Reservation(
                            0,
                            Generator.genContactInfo(),
                            new Info(raw),
                            stay,
                            payment
                    );
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }

                try {
                    int id = Hotelier.commit(r);
                    assertNotEquals(0, id);
                    assertTrue(Hotelier.cancel(id));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Test
    public void DateComponentCanParseDate() {
        var d = new DateComponent();
        assertDoesNotThrow(() -> d.getSQLDate());
    }
}