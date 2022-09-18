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
import room.Type;
import samples.ReservationSample;
import service.Amenity;

public class ReservationTests {

    @Test
    public void shouldCommitAndCanel() throws FileNotFoundException, SQLException {
        Manager m = new Manager(1);
        var stay = ReservationSample.genStay();
        var rooms = m.fetchAvailable(stay);
        assertEquals(1, rooms.size());
        rooms.forEach(new Consumer<RawInfo>() {

            @Override
            public void accept(RawInfo raw) {
                Reservation r;
                
                try {
                    r = new Reservation(
                            0,
                            ReservationSample.genContactInfo(),
                            new Info(raw),
                            stay
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
    public void correctReceipt() {
        var r = new Reservation(0, ReservationSample.genContactInfo(), new Info(0, Type.BASIC, 1, "https://i0.wp.com/theluxurytravelexpert.com/wp-content/uploads/2014/03/trump-hotel-chicago-illinois-usa.jpg", 100, "Test roo"), ReservationSample.genStay());
        r.put(new Amenity(0, "Anything",service.Type.THING , 10, 10, 100));
        //TODO
    }

    @Test
    public void DateComponentCanParseDate() {
        var d = new DateComponent();
        assertDoesNotThrow(() -> d.getSQLDate());
    }
}