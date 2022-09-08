import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginListener implements ActionListener {
    protected JTextField user;
    protected JPasswordField phrase;
    protected final int MAX_ATTEMPTS;
    protected JButton activator;
    private LoginObserver observer;
    protected int attempt = 0;
    public enum Action {
		LOGIN,
		REGISTER,
		GUEST
	}
    public LoginListener(JTextField user, JPasswordField phrase, JButton activator, int MAX_ATTEMPTS, LoginObserver l) {
        this.MAX_ATTEMPTS = MAX_ATTEMPTS;
        this.user = user;
        this.phrase = phrase;
        this.activator = activator;
        this.observer = l;
        activator.addActionListener(this);
        activator.setActionCommand("" + Action.LOGIN);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        switch (Action.valueOf(e.getActionCommand())) {
            case LOGIN:
                var u = new User(user.getText(), String.valueOf(phrase.getPassword()));

                if (Authenticator.instance.login(u))
                {
                    observer.onSuccess(u);
                } else {
                    if (++attempt >= MAX_ATTEMPTS) {
                        observer.onMaxTries();
                    } else
                        observer.onFail();
                }
                break;
            case REGISTER:
                observer.onRegister();
                break;
            case GUEST:
                observer.onGuest();
                break;
            default:
                System.out.println("Unkown action event: " + e.getActionCommand());
        }
    }
}