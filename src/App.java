import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;
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
import room.Manager;
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
	private ReservationTicketView ticket;
	private JPanel active;
	private Subscription subscription;
	private Manager manager;

	public App() throws SQLException {
		setTitle("Hotel El San Juan");
		{ // MAINTAIN CALL ORDER
			initMenu();
			manager = new Manager();
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
			ReservationStatus.self.submit(ReservationState.NONE);
		});

		var order = new JMenuItem("Order");
		order.addActionListener(e -> {
			Status.self.submit(State.CHECKEDIN);
		});
		rInfo.addActionListener(e -> {
			activate(ticket);
			//DO NOT CHANGE Status
		});

		order.addActionListener(e -> {
			activate(roomService);
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

		Subscriber<ReservationState> reservationStatusObserver = new Subscriber<ReservationState>() {
			private Subscription subscription;
			@Override
			public void onSubscribe(Subscription subscription) {
				this.subscription = subscription;	
				this.subscription.request(1);
			}

			@Override
			public void onNext(ReservationState item) {
				System.out.println("Item:" + item);
				switch (item) {
					case NONE: case DONE:
						//TODO: For case DONE: Allow viewing past tickets.
						rNew.setEnabled(true);
						rInfo.setEnabled(false);
						rExtend.setEnabled(false);
						rCancel.setEnabled(false);
						order.setEnabled(false);
						break;
					case ONGOING:
						rNew.setEnabled(false);
						rExtend.setEnabled(true);
						rCancel.setEnabled(true);
						order.setEnabled(true);
						rInfo.setEnabled(true);
						break;
					case UPCOMING:
						rNew.setEnabled(false);
						rCancel.setEnabled(true);
						order.setEnabled(false);
						rInfo.setEnabled(true);
						break;
					default:
						//PASS
						break;
				}
				this.subscription.request(1);
			}

			@Override
			public void onError(Throwable throwable) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onComplete() {
				// TODO Auto-generated method stub
			}
			
		};
		ReservationStatus.self.subscribe(reservationStatusObserver);
		ReservationStatus.self.submit(ReservationState.NONE);
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
		 * Determines where the user is redirected after login.
		 */
		@Override
		public void onSuccess(User u) {
			loginForm.clear();
			ArrayList<Reservation> reservations = Hotelier.getReservations(u);
			ReservationState rs = Hotelier.getStatus(reservations);
			switch(rs) {
				case DONE: case NONE:
					roomListing.updateEntries(
						new ArrayList<>(
							manager.fetchAvailable().values()
						)
					);
					Status.self.submit(State.BROWSE);

					break;
				case ONGOING:
					Status.self.submit(State.CHECKEDIN);
					break;
				case UPCOMING:
					Status.self.submit(State.BOOKED);
					break;
				default:
					break;
			}
			ReservationStatus.self.submit(rs);
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
				e.printStackTrace();
			}

			ticket = new ReservationTicketView();
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