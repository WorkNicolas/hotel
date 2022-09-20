package samples;

import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;
import reservation.ReservationTicketView;
import reservation.TicketController;

public class ReservationSample extends JFrame {
	private ReservationTicketView panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ReservationSample frame = new ReservationSample();
			TicketController t = new TicketController(frame.panel);
			t.accept(Generator.genReceipt(),Generator.genReservation());
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