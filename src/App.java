import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import auth.LoginListener;
import auth.LoginObserver;
import auth.RegistrationPanel;
import auth.User;
import auth.UserInfo;
import auth.Verifier;
import payment.PaymentComponent;
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
    private PaymentComponent paymentComponent;
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
                /* if (Status.self.value == State.auth) {
                    Status.self.submit(State.CHECKEDIN);
                } */
    			activate(roomService);
            });
            rInfo.addActionListener(e -> {
                activate(ticketView);
                //DO NOT CHANGE Status
            });

           
        }
        {// Setup roomListing
            roomManager = new Manager();
			paymentComponent = new PaymentComponent(this, accountName -> {
                JOptionPane.showMessageDialog(this, accountName);
                paymentComponent.setPortalName("GCASH");
                roomListingView.buttons.forEach(jButton -> {
                    jButton.addActionListener(paymentComponent);
                });
            });
        }
        {//Login related
            var p = new RegistrationPanel();
            uRegister.addActionListener(e -> {
                activate(p);
            });

            loginForm.bRegister.addActionListener(uRegister.getActionListeners()[0]);

            p.buttonLogin.addActionListener( e -> {
                activate(loginForm);
            });
            p.buttonRegister.addActionListener(e -> {
                UserInfo u = p.getUser();
                //TODO: Create account
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
                            roomListingView.updateEntries(
                                new ArrayList<>(
                                    roomManager.fetchAvailable().values()
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