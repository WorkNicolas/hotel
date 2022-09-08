import java.awt.EventQueue;
import javax.swing.*;

/*
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch(state) {
            case auth:
                break;
            case booked:
                break;
            case browse:
                break;
            case checkedin:
                break;
            default:
                break;
        }
        this.pack();
        System.out.println(state);
    }

}
 */

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
    State state = State.CHECKEDIN;
	public App() {
		initMenu();
		initComponents();
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		pack();
		setLocationRelativeTo(getOwner());
	}

	private void initMenu() {
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

	private void initComponents() {
		add(new AuthenticatorView(Authenticator.instance));
	}
}
