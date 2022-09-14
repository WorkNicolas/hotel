import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.function.Consumer;

import auth.ContactInfo;
import payment.Hotelier;
import reservation.Reservation;
import reservation.Stay;
import room.Info;
import room.Manager;
import room.RawInfo;

public class ReservationTests {

    @Test
    public void shouldCommitAndCanel() throws FileNotFoundException, SQLException {
        Manager m = new Manager(1);
        assertEquals(1, m.fetchAvailable().size());
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
                            new Stay(
                                    Date.valueOf(LocalDate.now()),
                                    5));
                    } catch (IOException e) {
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
}