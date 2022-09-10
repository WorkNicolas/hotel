import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

import javax.swing.*;

/**
 * @author Jean Carlo Molina San Juan
 */
public class App extends JFrame implements Subscriber<State> {
	private JMenuBar navbar = new JMenuBar();
	private JMenu menuUser  = new JMenu("User");
	private JMenuItem uLogin = new JMenuItem("Login");
	private JMenuItem uRegister = new JMenuItem("Register");
	private JMenuItem uLogout  = new JMenuItem("Logout");
	private JMenu menuReservation = new JMenu("Reservation");
	private JMenuItem rInfo = new JMenuItem("Info");
	private JMenuItem rNew = new JMenuItem("New");
	private JMenuItem rExtend = new JMenuItem("Extend");
	private JMenuItem rCancel = new JMenuItem("Cancel");
	private JMenu menuRoomService = new JMenu("Room Service");
	private Verifier verifier = new Verifier();
	private JPanel active;
	private AuthenticatorView authenticator;
	private HashMap<State, JPanel> panels = new HashMap<>();
	private Subscription subscription;
	public App() {
		setTitle("Hotel El San Juan");
		initMenu();
		initComponents();
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		pack();
		setLocationRelativeTo(getOwner());
		activate(authenticator);
		{	
			//TODO: Add JPanels
			panels.put(State.BOOKED, null);
			panels.put(State.CHECKEDIN, null);
			panels.put(State.BROWSE, new JPanel());
			panels.put(State.auth, authenticator);
		}
		Status.self.subscribe(this);
	}

	private void initMenu() {
		uLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Status.self.submit(State.auth);
			}
		});
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
		authenticator = new AuthenticatorView(verifier);
		add(authenticator);
	}

	public void activate(JPanel a) {
		if (a == null)
			return;

		if (active != null) 
			active.setVisible(false);
		active = a;
		a.setVisible(true);
	}

	@Override
	public void onSubscribe(Subscription subscription) {
		this.subscription = subscription;
		this.subscription.request(1);
	}

	@Override
	public void onNext(State item) {
		activate(
			panels.get(item)
		);
		this.subscription.request(1);
	}

	@Override
	public void onError(Throwable throwable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onComplete() {
		System.exit(0); //FORCE SHUTDOWN
	}
}