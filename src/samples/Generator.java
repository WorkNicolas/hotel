package samples;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import payment.Payment;
import payment.Receipt;
import reservation.ContactInfo;
import reservation.Reservation;
import reservation.Stay;
import room.Info;
import room.RawInfo;
import service.Amenity;

public class Generator {
    
	public static Stay genStay() {
		Date start = Date.valueOf(LocalDate.of(2022, Month.SEPTEMBER, 15));
		Date end = Date.valueOf(LocalDate.of(2022, Month.SEPTEMBER, 16));
		return new Stay(start, end);
	}

	public static ContactInfo genContactInfo() {
		return new ContactInfo(
			"Tempest",
			"1234 Example", 
			"00000000000",
			 "example@domain"
		);
	}

	public static Info genInfo() {
		return new Info(
			0, 
			room.Type.BASIC, 
			2,
			"https://insights.ehotelier.com/wp-content/uploads/sites/6/2020/01/hotel-room-300x300.jpg", 
			100,
			"TEST room"
		);
	}
    static String[] sampleURLS = {
        "https://github.githubassets.com/images/modules/logos_page/GitHub-Mark.png",
        "",
        "https://scontent.fmnl8-2.fna.fbcdn.net/v/t1.6435-9/117641368_3837968559553792_3432887483917428594_n.jpg?stp=dst-jpg_p526x395&_nc_cat=103&ccb=1-7&_nc_sid=730e14&_nc_ohc=97q3KRHfI4sAX9Y74KP&_nc_ht=scontent.fmnl8-2.fna&oh=00_AT9obQgmzR2rp1HCnkr26nhcnA6XW-IAeSOhoJxxppvVwA&oe=634DB627",
        "https://tip.edu.ph/assets/Uploads/T.I.P-1.png",
        "https://tip.edu.ph/assets/Uploads/TIP-INFORMAL-LOGO-04-2.png",
        "https://cdn-icons-png.flaticon.com/512/226/226777.png"
    };
    public static int randint(int limit) {
        return (int) (Math.random() * 1000) % limit;
    }
    public static ArrayList<RawInfo> multipleInfo(int size) {
        var rooms = new ArrayList<RawInfo>(size);

        for (int i = 0; i<size; i++) {
            rooms.add(new RawInfo(
                i, 
                room.Type.BASIC, randint(7),
                 sampleURLS[randint(sampleURLS.length)],
                  randint(1000),
                   "Random room " + i
            ));
        }

        return rooms;
	}

	public static Payment genPayment() {
		return new Payment(100f, "SAMPLE METHOD", "00000", 0.2f);
	}

	public static Receipt genReceipt() {
		var r = new Receipt();
		for (int i = 0; i < 10; i++) {
			r.put(new Amenity(
				i, 
				"Item " + i, 
				service.Type.THING, 
				randint(100),
				(float) (Math.random()* 100),
				randint(100),
				(float) (Math.random() * 100)
			));
		}

		return r;
	}

	public static Reservation genReservation() {
		return new Reservation(0,
			genContactInfo(),
			genInfo(),
			genStay(),
			genPayment()
		);
	}
}
