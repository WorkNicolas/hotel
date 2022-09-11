package auth;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Reacts to user input for the login form.
 * @see LoginForm
 */
public class LoginListener implements ActionListener {
    protected final int MAX_ATTEMPTS;
    protected int attempt = 0;
    protected AnAuthenticator<User> authenticator;
    protected LoginForm form;
    private LoginObserver o;
    public enum Action {
		LOGIN,
		REGISTER,
		GUEST
	}

    public LoginListener(LoginForm form, int MAX_ATTEMPTS, AnAuthenticator<User> authenticator, LoginObserver o) {
        this.MAX_ATTEMPTS = MAX_ATTEMPTS;
        this.form = form;
        this.authenticator = authenticator;
        this.o = o;
        form.bLogin.addActionListener(this);
        form.bLogin.setActionCommand("" + Action.LOGIN);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        switch (Action.valueOf(e.getActionCommand())) {
            case LOGIN:
                var u = form.getUser();

                if (authenticator.login(u))
                {
                    o.onSuccess(u);
                } else {
                    if (++attempt >= MAX_ATTEMPTS) {
                        o.onMaxTries();
                    } else
                        o.onFail();
                }
                break;
            case REGISTER:
                o.onRegister();
                break;
            case GUEST:
                o.onGuest();
                break;
            default:
                System.out.println("Unkown action event: " + e.getActionCommand());
        }
    }
}