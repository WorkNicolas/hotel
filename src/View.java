import java.util.HashMap;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import auth.LoginForm;
import reservation.ReservationTicketView;
import room.ListingForm;
import service.RoomServicePanel;
/**
 * @author Jean Carlo Molina San Juan
 */
public class View extends JFrame {
	protected JMenuBar navbar = new JMenuBar();
	protected JMenu menuUser = new JMenu("User");
	protected JMenuItem uLogin = new JMenuItem("Login");
	protected JMenuItem uRegister = new JMenuItem("Register");
	protected JMenuItem uLogout = new JMenuItem("Logout");
	protected JMenu menuReservation = new JMenu("Reservation");
	protected JMenuItem rInfo = new JMenuItem("Info");
	protected JMenuItem rNew = new JMenuItem("New");
	protected JMenuItem rExtend = new JMenuItem("Extend");
	protected JMenuItem rCancel = new JMenuItem("Cancel");
	protected JMenu menuRoomService = new JMenu("Room Service");
	protected LoginForm loginForm = new LoginForm();
	protected ListingForm listingForm;
	protected JMenuItem order = new JMenuItem("Order");
	protected RoomServicePanel roomService;
	protected HashMap<State, JPanel> panels = new HashMap<>();
	protected ReservationTicketView ticketView;
	protected JPanel active;

	public View() {
		setTitle("Hotel El San Juan");
		{ // MAINTAIN CALL ORDER
			initMenu();
			initComponents();
			setExtendedState(JFrame.MAXIMIZED_BOTH); // MAXIMIZE
			pack(); // FORCE RESIZE
			setVisible(true);
			setMinimumSize(getSize()); // SAVE THE NEW DIMENSIONS
			for (JPanel p : panels.values()) {
				if (p != null) {
					p.setPreferredSize(getSize());
					p.setMinimumSize(getMinimumSize());
				}
			}
		}

		setLocationRelativeTo(getOwner());
	}

	private void initMenu() {
		menuUser.add(uLogin);
		menuUser.add(uRegister);
		menuUser.add(uLogout);
		navbar.add(menuUser);
		menuReservation.add(rInfo);
		menuReservation.add(rNew);
		menuReservation.add(rExtend);
		menuReservation.add(rCancel);
		menuRoomService.add(order);
		navbar.add(menuReservation);
		navbar.add(menuRoomService);
		setJMenuBar(navbar);
	}

	/**
	 * @implNote For all State value, a JPanel must be defined in this method.
	 */
	private void initComponents() {
		{
			roomService = new RoomServicePanel();
			roomService.setBorder(new EmptyBorder(5, 5, 5, 5));
			listingForm = new ListingForm();
			ticketView = new ReservationTicketView();
			ticketView.setBorder(new EmptyBorder(5, 5, 5, 5));
			panels.put(State.BOOKED, ticketView);
			panels.put(State.CHECKEDIN, roomService);
			panels.put(State.BROWSE, listingForm);
			panels.put(State.auth, loginForm);
		}
		{ // Setup loginForm
			add(loginForm);
			activate(loginForm); // FIRST active
		}
	}

	/**
	 * Changes the visible JPanel
	 */
	public void activate(JPanel a) {
		if (a == null)
			return;

		if (active != null) {
			// Temporarily remove 'a' from the component hierarchy
			active.setVisible(false);
			remove(active);
		}
		active = a;
		add(active);
		active.setVisible(true);
		pack();
	}

	public void activate(State s) {
		activate(panels.get(s));
	}
}