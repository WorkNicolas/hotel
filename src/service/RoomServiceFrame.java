package service;
import java.awt.EventQueue;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

public class RoomServiceFrame extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RoomServiceFrame frame = new RoomServiceFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException
	 */
	public RoomServiceFrame() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 772, 397);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		RoomServicePanel view = new RoomServicePanel();
		view.content = Supplier.fetchAvailable();
		view.RoomServiceComponents();
		view.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(view);
	}

}
