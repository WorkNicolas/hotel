package reservation;

import java.sql.Date;

import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

import room.Info;

public class ReservationFrame extends JFrame {
	private ReservationTicketView panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			var r = new Reservation(0,
			new ContactInfo("Jean", "jc@sj", "NY #122"),
			new Info(0, room.Type.BASIC, 2, "https://insights.ehotelier.com/wp-content/uploads/sites/6/2020/01/hotel-room-300x300.jpg", 100, "TEST room"),
			new Stay(new Date(100), new Date(1000))
			);
			ReservationFrame frame = new ReservationFrame();
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
	public ReservationFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 772, 397);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		panel = new ReservationTicketView();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
	}
}