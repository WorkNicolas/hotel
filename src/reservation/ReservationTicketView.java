package reservation;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Point;
import javax.swing.border.LineBorder;
/**
 * @author Bañez, Roxanne
 * @author JCSJ
 */
public class ReservationTicketView extends JPanel {
	public JButton btnProceed;
	public JPanel infoPanel;
	public JPanel roomPanel;
	/**
	 * Create the panel.
	 */
	public ReservationTicketView() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setPreferredSize(new Dimension(50, 180));
		add(panel, BorderLayout.SOUTH);
		
		JLabel lblExtraTitle = new JLabel(" Important Hotel Information and Check-in");
		panel.add(lblExtraTitle);
		lblExtraTitle.setFont(new Font("Arial Black", Font.PLAIN, 15));
		
		JLabel lblExtraInfo = new JLabel("<html><ul><li>Although Hotel San Juan does not charge a fee to change, or cancel your booking, El San Juan Hotel may still charge a fee in accordance with its own rules and regulation </li><li>During your stay, you may cancel anytime, the remaining nights shall be refunded.</li><li>View your online itinerary for additional rules and restrictions.</li><li>Check-in time starts at 8 AM.</li><li>If a late check-in is planned, contact us for the late check-in policy.</li></ul><html>");
		lblExtraInfo.setFont(new Font("Arial", Font.BOLD, 15));
		panel.add(lblExtraInfo);
		
		JPanel northPanel = new JPanel();
		northPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		northPanel.setPreferredSize(new Dimension(10, 60));
		add(northPanel, BorderLayout.NORTH);
		
		JLabel lblTicket = new JLabel("RESERVATION TICKET");
		lblTicket.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblTicket.setHorizontalTextPosition(SwingConstants.LEFT);
		lblTicket.setHorizontalAlignment(SwingConstants.LEFT);
		lblTicket.setFont(new Font("Arial Black", Font.PLAIN, 20));
		northPanel.add(lblTicket);
		
		infoPanel = new JPanel();
		infoPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		infoPanel.setPreferredSize(new Dimension(450, 50));
		add(infoPanel, BorderLayout.WEST);
		
		JLabel lblInfo = new JLabel("ORDER HISTORY");
		infoPanel.add(lblInfo);
		lblInfo.setVerticalTextPosition(SwingConstants.TOP);
		lblInfo.setVerticalAlignment(SwingConstants.TOP);
		lblInfo.setHorizontalAlignment(SwingConstants.LEFT);
		lblInfo.setHorizontalTextPosition(SwingConstants.LEFT);
		lblInfo.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblInfo.setLocation(new Point(5, 10));
		lblInfo.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		
		roomPanel = new JPanel();
		roomPanel.setLayout(new BoxLayout(roomPanel, BoxLayout.Y_AXIS));
		roomPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		roomPanel.setPreferredSize(new Dimension(450, 45));
		add(roomPanel, BorderLayout.CENTER);
		JLabel lblRoom = new JLabel("ROOM INFORMATION");
		roomPanel.add(lblRoom);
		lblRoom.setFont(new Font("Arial Black", Font.PLAIN, 15));
	}
}