package reservation;

import java.awt.Dimension;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Point;
import javax.swing.border.LineBorder;

public class ReservationTicketView extends JPanel {

	/**
	 * Create the panel.
	 */
	public ReservationTicketView() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setPreferredSize(new Dimension(50, 180));
		add(panel, BorderLayout.SOUTH);
		
		JLabel lblExtraInfo = new JLabel(" Important Hotel Information and Check-in");
		panel.add(lblExtraInfo);
		lblExtraInfo.setFont(new Font("Arial Black", Font.PLAIN, 15));
		
		JLabel lblNewLabel_4 = new JLabel("● Although Hotel San Juan does not charge a fee to change, or cancel your booking, El San Juan Hotel may still charge a fee in accordance with its own rules and regulation");
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 15));
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("● Cancellations, or changes made after 11:59 PM (PHT) on June 19, 2022 or no-show are subjects to a hotel fee equal to the first night's rate plus taxes, and fee.");
		lblNewLabel_5.setFont(new Font("Arial", Font.BOLD, 15));
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("● View your online interary for additional rules and restrictions.");
		lblNewLabel_6.setFont(new Font("Arial", Font.BOLD, 15));
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("● Check-in time starts at 8 AM");
		lblNewLabel_7.setFont(new Font("Arial", Font.BOLD, 15));
		panel.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("If a late check-in is planned, contact this hotel directly for their late check-in policy.\r\n");
		lblNewLabel_8.setFont(new Font("Arial", Font.BOLD, 15));
		panel.add(lblNewLabel_8);
		
		JButton btnNewButton = new JButton("Continue");
		btnNewButton.addActionListener(e-> {
			//TODO
		});
		btnNewButton.setFont(new Font("Arial Narrow", Font.BOLD, 15));
		panel.add(btnNewButton);
		
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
		
		JPanel infoPanel = new JPanel();
		infoPanel.setBackground(Color.BLACK);
		infoPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		infoPanel.setPreferredSize(new Dimension(450, 50));
		add(infoPanel, BorderLayout.WEST);
		
		JLabel lblInfo = new JLabel("INFORMATION:");
		infoPanel.add(lblInfo);
		lblInfo.setVerticalTextPosition(SwingConstants.TOP);
		lblInfo.setVerticalAlignment(SwingConstants.TOP);
		lblInfo.setHorizontalAlignment(SwingConstants.LEFT);
		lblInfo.setHorizontalTextPosition(SwingConstants.LEFT);
		lblInfo.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblInfo.setLocation(new Point(5, 10));
		lblInfo.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		
		JPanel roomPanel = new JPanel();
		roomPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		roomPanel.setPreferredSize(new Dimension(450, 45));
		add(roomPanel, BorderLayout.CENTER);
		
		JLabel lblRoom = new JLabel("ROOM ");
		roomPanel.add(lblRoom);
		lblRoom.setFont(new Font("Arial Black", Font.PLAIN, 15));
	}

	public void setInfo(Reservation r) {
		//TODO: Use a receipt View
	}
}