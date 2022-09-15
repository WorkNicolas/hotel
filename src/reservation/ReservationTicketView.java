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
		
		JLabel lblNewLabel_2 = new JLabel(" Important Hotel Information and Check-in");
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("Arial Black", Font.PLAIN, 15));
		
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
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setPreferredSize(new Dimension(10, 60));
		add(panel_1, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("RESERVATION TICKET");
		lblNewLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblNewLabel.setHorizontalTextPosition(SwingConstants.LEFT);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 20));
		panel_1.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setPreferredSize(new Dimension(450, 50));
		add(panel_2, BorderLayout.WEST);
		
		JLabel lblNewLabel_1 = new JLabel("INFORMATION:");
		panel_2.add(lblNewLabel_1);
		lblNewLabel_1.setVerticalTextPosition(SwingConstants.TOP);
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setHorizontalTextPosition(SwingConstants.LEFT);
		lblNewLabel_1.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblNewLabel_1.setLocation(new Point(5, 10));
		lblNewLabel_1.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setPreferredSize(new Dimension(450, 45));
		add(panel_3, BorderLayout.CENTER);
		
		JLabel lblNewLabel_3 = new JLabel("ROOM ");
		panel_3.add(lblNewLabel_3);
		lblNewLabel_3.setFont(new Font("Arial Black", Font.PLAIN, 15));
		viewComponents();
	}
	private void viewComponents(){
}
}