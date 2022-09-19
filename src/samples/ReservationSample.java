package samples;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;

import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

import payment.Payment;
import payment.Receipt;
import reservation.ContactInfo;
import reservation.Reservation;
import reservation.ReservationTicketView;
import reservation.Stay;
import reservation.TicketController;
import room.Info;
import service.Amenity;

public class ReservationSample extends JFrame {
	private ReservationTicketView panel;

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
				(int) (Math.random() * 100),
				(float) (Math.random()* 100),
				(int) (Math.random()* 100)
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
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			var r = genReservation();
			ReservationSample frame = new ReservationSample();
			TicketController t = new TicketController(frame.panel);
			t.accept(genReceipt(), genReservation());
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public ReservationSample() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 772, 397);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		panel = new ReservationTicketView();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
	}
}