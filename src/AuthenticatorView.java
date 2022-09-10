import java.awt.*;
import javax.swing.*;

/**
 * @author Jean Carlo Molina San Juan
 */
public class AuthenticatorView extends JPanel {
	private LoginListener l;

	private JLabel lName = new JLabel("Email:");
	private JLabel lPhrase = new JLabel("Passphrase:");
	private JTextField inputName = new JTextField();
	private JPasswordField inputPhrase = new JPasswordField();
	private JButton bLogin = new JButton("Login");
	private JButton bGuest = new JButton("Guest");
	private JButton bRegister = new JButton("Register");
	enum Action {
		LOGIN,
		REGISTER,
		GUEST
	}

	public AuthenticatorView(Verifier v) {
		initComponents();
		l = new LoginListener(inputName, inputPhrase, bLogin, 10, v);
	}

	private void initComponents() {
		setMaximumSize(new Dimension(1920, 1080));
		setPreferredSize(new Dimension(1920, 1080));
		setInheritsPopupMenu(true);
		var font = new Font("Tahoma", Font.PLAIN, 24);
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

		// ---- bGuest ----
		bGuest.setFont(font);
		add(bGuest, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0));
		bGuest.setActionCommand("" + Action.GUEST);
		bGuest.addActionListener(this.l);

		// ---- button3 ----
		bRegister.setFont(font);
		add(bRegister, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 10, 10), 0, 0));
		// JFormDesigner - End of component initialization //GEN-END:initComponents
		bRegister.setActionCommand("" + Action.REGISTER);
		bRegister.addActionListener(this.l);
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Jean Carlo Molina San Juan

	// JFormDesigner - End of variables declaration //GEN-END:variables
}
