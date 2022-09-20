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

    public LoginListener(LoginForm form, int MAX_ATTEMPTS, AnAuthenticator<User> authenticator, LoginObserver o) {
        this.MAX_ATTEMPTS = MAX_ATTEMPTS;
        this.form = form;
        this.authenticator = authenticator;
        this.o = o;
        form.bLogin.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var u = form.getUser();
        tryLogin(u);
    }

    public int getAttempts() {
        return attempt;
    }

    public int getAttemptsLeft() {
        return MAX_ATTEMPTS - attempt;
    }
    public void tryLogin(User u) {
        if (authenticator.login(u))
        {
            o.onSuccess(u);
        } else {
            if (++attempt >= MAX_ATTEMPTS) {
                o.onMaxTries();
            } else
                o.onFail();
        }
    }
}