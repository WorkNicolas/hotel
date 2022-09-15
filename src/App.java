import java.awt.EventQueue;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.function.Consumer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import auth.LoginForm;
import auth.LoginListener;
import auth.LoginObserver;
import auth.User;
import auth.Verifier;
import payment.PaymentComponent;
import reservation.Hotelier;
import reservation.Reservation;
import reservation.ReservationState;
import reservation.ReservationTicketView;
import reservation.Stay;
import room.RoomListing;
import service.RoomServiceView;
import service.Supplier;

/**
 * @author Jean Carlo Molina San Juan
 */
public class App extends JFrame implements Subscriber<State> {
	private JMenuBar navbar = new JMenuBar();
	private JMenu menuUser = new JMenu("User");
	private JMenuItem uLogin = new JMenuItem("Login");
	private JMenuItem uRegister = new JMenuItem("Register");
	private JMenuItem uLogout = new JMenuItem("Logout");
	private JMenu menuReservation = new JMenu("Reservation");
	private JMenuItem rInfo = new JMenuItem("Info");
	private JMenuItem rNew = new JMenuItem("New");
	private JMenuItem rExtend = new JMenuItem("Extend");
	private JMenuItem rCancel = new JMenuItem("Cancel");
	private JMenu menuRoomService = new JMenu("Room Service");
	private Verifier verifier = new Verifier();
	private LoginForm loginForm = new LoginForm();
	private RoomListing roomListing = new RoomListing();
	private RoomServiceView roomService;
	private HashMap<State, JPanel> panels = new HashMap<>();
	private JPanel active;
	private Subscription subscription;

	public App() {
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
		Status.self.subscribe(this);
	}

	private void initMenu() {
		uLogout.addActionListener(e -> {
			Status.self.submit(State.auth);
		});

		var order = new JMenuItem("Order");
		order.addActionListener(e -> {
			Status.self.submit(State.CHECKEDIN);
		});
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

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App a = new App();
					a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private LoginObserver loginObserver = new LoginObserver() {
		/**
		 * 0 - no reservations
		 * 1 - upcoming
		 * 2 - ongoing
		 * 3 - has past reservations
		 */
		@Override
		public void onSuccess(User u) {
			ArrayList<Reservation> reservations = Hotelier.getReservations(u);
			ReservationState rs = ReservationState.NONE;
			if (reservations.size() == 0) {
				Status.self.submit(State.BROWSE);
				loginForm.clear();
				return;
			}
			var now = Date.valueOf(LocalDate.now());
			Reservation latest = reservations.get(0);

			Stay stay = latest.getStay();
			Calendar c = Calendar.getInstance();
			c.setTime(stay.getStart());
			c.add(Calendar.DATE, stay.getLength());
			var end = c.getTime();
			if (now.after(end)) {
				rs = ReservationState.DONE;
			} else if (stay.getStart().before(now)) {
				rs = ReservationState.UPCOMING;
				Status.self.submit(State.BOOKED);
			} else {
				rs = ReservationState.ONGOING;
				Status.self.submit(State.CHECKEDIN);
			}
			loginForm.clear();
		}

		@Override
		public void onFail() {
			// TODO Auto-generated method stub
		}

		@Override
		public void onMaxTries() {
			Status.self.close();
		}

		@Override
		public void onRegister() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onGuest() {
			// TODO Auto-generated method stub
			
		}
	};

	private void initComponents() {
		{
			// TODO: Add JPanels
			try {
				roomService = new RoomServiceView(Supplier.fetchAvailable());
				roomService.setBorder(new EmptyBorder(5, 5, 5, 5));
			} catch (Exception e) {
				// TODO: handle exception
			}

			var ticket = new ReservationTicketView();
			ticket.setBorder(new EmptyBorder(5, 5, 5, 5));
			panels.put(State.BOOKED, ticket);
			panels.put(State.CHECKEDIN, roomService);
			panels.put(State.BROWSE, roomListing);
			panels.put(State.auth, loginForm);
		}
		{ // Setup loginForm
			var l = new LoginListener(loginForm, 10, verifier, loginObserver);
			add(loginForm);
			activate(loginForm); // FIRST active
		}
		{// Setup roomListing
			PaymentComponent l = new PaymentComponent(this, new Consumer<String>() {
				@Override
				public void accept(String accountName) {
					JOptionPane.showMessageDialog(App.this, accountName);
				}
			});
			l.setPortalName("GCASH");
			Consumer<JButton> c = new Consumer<JButton>() {

				@Override
				public void accept(JButton t) {
					t.addActionListener(l);
				}
			};
			roomListing.buttons.forEach(c);
		}
	}

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

	@Override
	public void onSubscribe(Subscription subscription) {
		this.subscription = subscription;
		this.subscription.request(1);
	}

	@Override
	public void onNext(State item) {
		activate(
				panels.get(item));
		this.subscription.request(1);
	}

	@Override
	public void onError(Throwable throwable) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onComplete() {
		JOptionPane.showMessageDialog(this, "App will now exit.", "Max login attempts", JOptionPane.WARNING_MESSAGE);
		System.exit(0); // FORCE SHUTDOWN
	}
}