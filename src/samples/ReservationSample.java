package samples;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;

import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

import reservation.ContactInfo;
import reservation.Reservation;
import reservation.ReservationTicketView;
import reservation.Stay;
import reservation.TicketController;
import room.Info;

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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			var r = new Reservation(0,
				genContactInfo(),
				genInfo(),
				genStay());
			ReservationSample frame = new ReservationSample();
			TicketController t = new TicketController(frame.panel);
			t.setInfo(r);
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