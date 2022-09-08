import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Jean Carlo Molina San Juan
 */
public class App extends JFrame {
	private JMenuBar navbar;
	private JMenu menuUser;
	private JMenuItem uLogin;
	private JMenuItem uRegister;
	private JMenuItem uLogout;
	private JMenu menuReservation;
	private JMenuItem rInfo;
	private JMenuItem rNew;
	private JMenuItem rExtend;
	private JMenuItem rCancel;
	private JMenu menuRoomService;
    State state = State.checkedin;

	public App() {
		initComponents();
		setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	private void initComponents() {
		navbar = new JMenuBar();
		menuUser = new JMenu("User");
		uLogin = new JMenuItem("Login");
		uRegister = new JMenuItem("Register");
		uLogout = new JMenuItem("Logout");
		menuReservation = new JMenu("Reservation");
		rInfo = new JMenuItem("Info");
		rNew = new JMenuItem("New");
		rExtend = new JMenuItem("Extend");
		rCancel = new JMenuItem("Cancel");
		menuRoomService = new JMenu("Room Service");

		setTitle("Hotel El San Juan");
		Container contentPane = getContentPane();

		menuUser.add(uLogin);
		menuUser.add(uRegister);
		menuUser.add(uLogout);
		navbar.add(menuUser);
		menuReservation.add(rInfo);
		menuReservation.add(rNew);
		menuReservation.add(rExtend);
		menuReservation.add(rCancel);
		navbar.add(menuReservation);
		navbar.add(menuRoomService);
		setJMenuBar(navbar);
		GroupLayout contentPaneLayout = new GroupLayout(contentPane);
		contentPane.setLayout(contentPaneLayout);
		contentPaneLayout.setHorizontalGroup(
				contentPaneLayout.createParallelGroup()
						.addGap(0, 398, Short.MAX_VALUE));
		contentPaneLayout.setVerticalGroup(
				contentPaneLayout.createParallelGroup()
						.addGap(0, 246, Short.MAX_VALUE));
		pack();
		setLocationRelativeTo(getOwner());
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App a = new App();
					a.setVisible(true);
					a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
