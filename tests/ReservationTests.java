import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.function.Consumer;

import reservation.ContactInfo;
import reservation.Hotelier;
import reservation.Reservation;
import reservation.Stay;
import room.Info;
import room.Manager;
import room.RawInfo;
import room.Type;
import service.Amenity;

public class ReservationTests {

    public Stay genStay() {
        Date start = Date.valueOf(LocalDate.of(2002, Month.SEPTEMBER, 14));
        Date end = Date.valueOf(LocalDate.of(2002, Month.SEPTEMBER, 18));
        return new Stay(start, end);
    }
    @Test
    public void shouldCommitAndCanel() throws FileNotFoundException, SQLException {
        Manager m = new Manager(1);
        assertEquals(1, m.fetchAvailable().size());
        var stay = genStay();

        m.infos.values().forEach(new Consumer<RawInfo>() {

            @Override
            public void accept(RawInfo raw) {
                Reservation r;
                
                try {
                    r = new Reservation(
                            0,
                            new ContactInfo("Jean Carlo M. San Juan",
                                    "jc@sj", ""),
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
    public void canRetrieveAvailable() throws SQLException {
        Manager m = new Manager(10);
        m.fetchAvailable();
        assertNotEquals(-1, m.infos.size());
    }

    @Test
    public void correctReceipt() {
        var r = new Reservation(0, new ContactInfo("Test", "example@email", "1234 NW Bobcat Lane, St. Robert"), new Info(0, Type.BASIC, 1, "https://i0.wp.com/theluxurytravelexpert.com/wp-content/uploads/2014/03/trump-hotel-chicago-illinois-usa.jpg", 100, "Test roo"), genStay());
        r.put(new Amenity(0, "Anything",service.Type.THING , 10, 10, 100));
    }
}