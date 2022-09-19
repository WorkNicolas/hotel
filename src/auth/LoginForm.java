package auth;
import java.awt.*;
import javax.swing.*;


/**
 * @author Jean Carlo Molina San Juan
 */
public class LoginForm extends JPanel {
	private JLabel lName = new JLabel("Email:");
	private JLabel lPhrase = new JLabel("Passphrase:");
	private JTextField inputName = new JTextField();
	private JPasswordField inputPhrase = new JPasswordField();
	public JButton bLogin = new JButton("Login");
	public JButton bRegister = new JButton("Register");
	enum Action {
		LOGIN,
		REGISTER,
		GUEST
	}

	public LoginForm() {
		initComponents();
	}

	private void initComponents() {
		var font = new Font("Tahoma", Font.PLAIN, 30);
		setFont(font);
		setLayout(new GridBagLayout());
		// ---- lName ----
		lName.setHorizontalAlignment(SwingConstants.RIGHT);
		lName.setFont(font);
		add(lName, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 10, 10), 0, 0));

		// ---- inputName ----
		inputName.setToolTipText("username");
		inputName.setMaximumSize(new Dimension(300, 20));
		inputName.setFont(font);
		add(inputName, new GridBagConstraints(1, 0, 2, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 10, 0), 0, 0));

		// ---- lPhrase ----
		lPhrase.setHorizontalAlignment(SwingConstants.RIGHT);
		lPhrase.setFont(font);
		add(lPhrase, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 10, 10), 0, 0));

		// ---- inputPhrase ----
		inputPhrase.setMaximumSize(new Dimension(300, 40));
		inputPhrase.setToolTipText("Passphrase");
		inputPhrase.setFont(font);
		add(inputPhrase, new GridBagConstraints(1, 1, 2, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 10, 0), 0, 0));

		// ---- bLogin ----
		bLogin.setText("Login");
		bLogin.setFont(font);
		add(bLogin, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 10, 0), 0, 0));
		//bLogin's action command is set by LoginListener

		// ---- button3 ----
		bRegister.setFont(font);
		add(bRegister, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 10, 10), 0, 0));
		bRegister.setActionCommand("" + Action.REGISTER);
	}

	public User getUser() {
		return new User(inputName.getText(), String.valueOf(inputPhrase.getPassword()));
	}

	public void clear() {
		inputName.setText("");
		inputPhrase.setText("");
	}
}
