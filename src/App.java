import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import auth.LoginListener;
import auth.LoginObserver;
import auth.RegistrationForm;
import auth.User;
import auth.Verifier;
import reservation.Hotelier;
import reservation.Reservation;
import reservation.ReservationState;
import room.Manager;

/**
 * @author Jean Carlo Molina San Juan
 */
public class App extends View implements Subscriber<State> {
    Subscription subscription;
	private Verifier verifier = new Verifier();
    private Manager roomManager;
	private LoginListener loginListener;
    private LoginObserver loginObserver;
    private Subscriber<ReservationState> reservationObserver;
    public App() throws SQLException {
        super();
        {//MENU
            uLogout.addActionListener(e -> {
                Status.self.submit(State.auth);
                ReservationStatus.self.submit(ReservationState.NONE);
            });
    
            order.addActionListener(e -> {
    			activate(roomService);
            });
            rInfo.addActionListener(e -> {
                activate(ticketView);
                //DO NOT CHANGE Status
            });
           
            rCancel.addActionListener(e -> {
                verifier.getUser().ifPresent(u -> {
                    var reservations = Hotelier.getReservations(u);
                    if (reservations.size()== 0)
                        return;

                    var r = reservations.get(0);
                    var status = r.getStay().getStatus();
                    switch(status) {
                        case DONE: case NONE:
                            JOptionPane.showMessageDialog(this, "You have no upcoming or ongoing reservations.");
                        default:
                            break;
                    }
                    String input = JOptionPane.showInputDialog(this, "To confirm cancellation, type your email: " +  u.getEmail(), "Cancel reservation", JOptionPane.WARNING_MESSAGE);

                    if ((!u.getEmail().equals(input)))
                            return;
                    try {
                        Hotelier.cancel(r.getId());
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        return;
                    }
                    JOptionPane.showMessageDialog(this, "You have cancelled your booking. Please await your refund.");
                    Status.self.submit(State.BROWSE); //Redirect to listing
                    return;
                });
            });
        }
        {//Login related
            //TODO allow changing of default button per panel.
            getRootPane().setDefaultButton(loginForm.bLogin);
            RegistrationForm p = new RegistrationForm(created -> {
                loginListener.tryLogin(
                    new User(created)
                );
            });
            uRegister.addActionListener(e -> {
                activate(p);
            });
            loginForm.bRegister.addActionListener(uRegister.getActionListeners()[0]);

            p.buttonReturn.addActionListener(e -> {
                activate(loginForm);
            });
           loginObserver = new LoginObserver() {
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
                    JOptionPane.showMessageDialog(App.this, "Incorrect username or passphrase.\nAttempts left: " + loginListener.getAttemptsLeft(), "Login failed", JOptionPane.INFORMATION_MESSAGE);
                }
        
                @Override
                public void onMaxTries() {
                    Status.self.close();
                }
            };
            loginListener = new LoginListener(loginForm, 10, verifier, loginObserver);
        }
        {//RESERVATION & MENU
            reservationObserver = new Subscriber<ReservationState>() {
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
        }
        {//SUBSCRIPTIONS
            ReservationStatus.self.subscribe(reservationObserver);
            ReservationStatus.self.submit(ReservationState.NONE);
            Status.self.subscribe(this);
        }
    }
    @Override
	public void onSubscribe(Subscription s) {
		this.subscription = s;
		this.subscription.request(1);
	}

	@Override
	public void onNext(State s) {
		activate(s);
		subscription.request(1);
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

    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App a  = new App();
					a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}