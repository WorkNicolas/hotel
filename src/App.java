import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Style.Style;
import auth.LoginListener;
import auth.LoginObserver;
import auth.RegistrationForm;
import auth.User;
import auth.Verifier;
import news.NewsController;
import payment.Payment;
import payment.Receipt;
import reservation.Hotelier;
import reservation.Reservation;
import reservation.ReservationState;
import reservation.TicketController;
import room.Manager;
import service.ServiceController;
import service.Waiter;

/**
 * @author JCSJ
 */
public class App extends View implements Subscriber<State> {
    Subscription subscription;
	private Verifier verifier = new Verifier();
    private Manager roomManager;
	private LoginListener loginListener;
    private LoginObserver loginObserver;
    private Subscriber<ReservationState> reservationObserver;
    private ArrayList<Reservation> reservations;
    private ServiceController serviceController;
    public App() throws SQLException {
        super();
        {//MENU
            uLogin.addActionListener(e -> {
                Status.self.submit(State.AUTH);
            });
            uLogout.addActionListener(e -> {
                Status.self.submit(State.AUTH);
                ReservationStatus.self.submit(ReservationState.NONE);
            });
            rNew.addActionListener(e -> {
                activate(listingForm);
            });
            sOrder.addActionListener(e -> {
    			activate(roomService);
            });
            var t = new TicketController(ticketView);
            rInfo.addActionListener(e -> {

                String id = null;
                {
                    int size = reservations.size();
                    var ids = new String[size];
                    for (int i = 0; i < size; i++) {
                        ids[i] = reservations.get(i).toString();
                    }
                    id = (String) JOptionPane.showInputDialog(this, "Select a reservation:", "Past Transactions", JOptionPane.QUESTION_MESSAGE, null, ids, ids[0]);
                    if (id == null)
                        return;
                }

                try {
                    for (var r:reservations) {
                        if (r.toString().equals(id)) {
                            Receipt a = Waiter.fetchReceipt(r.getId());
                            t.accept(a, r);
                            break;
                        }
                    }
                    
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                activate(ticketView);
                //DO NOT CHANGE Status
            });
            {//NEWS
                var c = new NewsController();
                itemSeeNews.addActionListener(e -> {
                    var news = c.fetch(10);
                    if (news.size() == 0) {
			            JOptionPane.showMessageDialog(this, "No news at the moment.");
                    } else {
                        newsForm.createNewPanels(news);
                        activate(newsForm);
                    }
                });
            }
         
            rCancel.addActionListener(e -> {
                verifier.getUser().ifPresent(user -> {
                    reservations = Hotelier.getReservations(user);
                    if (reservations.size()== 0)
                        return;

                    Reservation latest = reservations.get(0);
                    var status = latest.getStay().getStatus();
                    switch(status) {
                        case DONE: case NONE:
                            JOptionPane.showMessageDialog(this, "You have no upcoming or ongoing reservations.");
                        case ONGOING:
                     
                            break;
                        default:
                            break;
                    }
                    String input = JOptionPane.showInputDialog(this, "To confirm cancellation, type your email: " +  user.getEmail(), "Cancel reservation", JOptionPane.WARNING_MESSAGE);

                    if ((!user.getEmail().equals(input)))
                            return;
                    try {
                        Hotelier.cancel(latest.getId());
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        return;
                    }
                    JOptionPane.showMessageDialog(this, "You have cancelled your booking. Please await your refund.");
                    Status.self.submit(State.AUTH);
                    loginListener.tryLogin(user);
                });
            });
        }
        {//Login related
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
                    reservations = Hotelier.getReservations(u);
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
                            rNew.setEnabled(true);
                            rInfo.setEnabled(false);
                            rCancel.setEnabled(false);
                            sOrder.setEnabled(false);
                            break;
                        case ONGOING:
                            rNew.setEnabled(false);
                            rCancel.setEnabled(true);
                            sOrder.setEnabled(true);
                            rInfo.setEnabled(true);
                            break;
                        case UPCOMING:
                            rNew.setEnabled(false);
                            rCancel.setEnabled(true);
                            sOrder.setEnabled(false);
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
                    //PASS
                }
    
                @Override
                public void onComplete() {
                    //PASS
                }
                
            };
        }
      
        {//Payment 
            roomManager = new Manager();
            listingForm.setCounter(latest -> {
                return Hotelier.count(latest);
            });
            listingForm.setFetcher(roomManager);
            listingForm.setConsumer(r -> {
                try {
                    Hotelier.commit(r);
                    listingForm.dialog.setVisible(false);
                    listingForm.remove(listingForm.dialog);
                    listingForm.onSuccess();
                    verifier.getUser().ifPresent(user -> {
                        Status.self.submit(State.AUTH);
                        loginListener.tryLogin(user);
                    });
                    return;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                listingForm.onFail();
            });
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
        switch(s) {
            case BOOKED:
            case BROWSE:
            case CHECKEDIN:
                verifier.getUser().ifPresent(user -> {
                    listingForm.setTenant(Hotelier.getContactInfo(user));
                     //ROOM SERVICE

                     Hotelier.getLatest(reservations).ifPresent(r -> {
                        try {
                            roomService.setContent(service.Supplier.fetchAvailable());
                            this.serviceController = new ServiceController(
                                roomService, 
                                (Receipt receipt, Payment p) -> {
                                    Waiter.order(p, receipt, r.getId());
                                    JOptionPane.showMessageDialog(this, "Your order is being processed.", "Acknowledgement", JOptionPane.INFORMATION_MESSAGE);
                                    Status.self.submit(State.CHECKEDIN); //FORCE REQUIRY 
                            });
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    });
                });
                break;
            case AUTH:
                getRootPane().setDefaultButton(loginForm.bLogin);
            default:
                break;
        }
		activate(s);
		subscription.request(1);
	}

	@Override
	public void onError(Throwable throwable) {
		//PASS

	}

	@Override
	public void onComplete() {
		JOptionPane.showMessageDialog(this, "App will now exit.", "Max login attempts", JOptionPane.WARNING_MESSAGE);
		System.exit(0); // FORCE SHUTDOWN
	}

    public static void main(String[] args) {
        Style.init();
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