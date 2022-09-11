import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Reacts to user input for the login form.
 * @see LoginForm
 */
public class LoginListener implements ActionListener, LoginObserver {
    protected JTextField user;
    protected JPasswordField phrase;
    protected final int MAX_ATTEMPTS;
    protected JButton activator;
    protected int attempt = 0;
    protected AnAuthenticator<User> authenticator;
    public enum Action {
		LOGIN,
		REGISTER,
		GUEST
	}
    /**
     * @param user
     * @param phrase
     * @param activator
     * @param MAX_ATTEMPTS
     * @param authenticator
     */
    public LoginListener(JTextField user, JPasswordField phrase, JButton activator, int MAX_ATTEMPTS, AnAuthenticator<User> authenticator) {
        this.MAX_ATTEMPTS = MAX_ATTEMPTS;
        this.user = user;
        this.phrase = phrase;
        this.activator = activator;
        this.authenticator = authenticator;
        activator.addActionListener(this);
        activator.setActionCommand("" + Action.LOGIN);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        switch (Action.valueOf(e.getActionCommand())) {
            case LOGIN:
                var u = new User(user.getText(), String.valueOf(phrase.getPassword()));

                if (authenticator.login(u))
                {
                    onSuccess(u);
                } else {
                    if (++attempt >= MAX_ATTEMPTS) {
                        onMaxTries();
                    } else
                        onFail();
                }
                break;
            case REGISTER:
                onRegister();
                break;
            case GUEST:
                onGuest();
                break;
            default:
                System.out.println("Unkown action event: " + e.getActionCommand());
        }
    }

    @Override
	public void onSuccess(User u) {
		Status.self.submit(State.BROWSE);
	}

	@Override
	public void onFail() {
		// TODO Auto-generated method stub
	}

	@Override
	public void onMaxTries() {
		Status.self.close();
		// TODO Auto-generated method stub
	}

	@Override
	public void onRegister() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGuest() {
		// TODO Auto-generated method stub
		
	}
}