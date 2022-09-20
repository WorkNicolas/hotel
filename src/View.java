import java.util.HashMap;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import auth.LoginForm;
import news.NewsForm;
import reservation.ReservationTicketView;
import room.ListingForm;
import service.RoomServicePanel;
/**
 * @author JCSJ
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
	protected JMenuItem rCancel = new JMenuItem("Cancel");
	protected JMenu menuRoomService = new JMenu("Room Service");
	protected JMenuItem sOrder = new JMenuItem("Order");
	protected JMenu menuNews = new JMenu("News");
	protected JMenuItem itemSeeNews = new JMenuItem("See");
	protected LoginForm loginForm = new LoginForm();
	protected ListingForm listingForm;
	protected NewsForm newsForm = new NewsForm();
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
		menuReservation.add(rCancel);
		menuRoomService.add(sOrder);
		menuNews.add(itemSeeNews);
		navbar.add(menuReservation);
		navbar.add(menuRoomService);
		navbar.add(menuNews);
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
			panels.put(State.AUTH, loginForm);
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