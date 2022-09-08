import java.awt.*;
import javax.swing.*;

/**
 * @author Jean Carlo Molina San Juan
 */
public class AuthenticatorView extends JPanel implements LoginObserver {
	private Authenticator ac;
	private LoginListener l;

	private JLabel lName = new JLabel();
	private JLabel lPhrase = new JLabel();
	private JTextField inputName = new JTextField();
	private JPasswordField inputPhrase = new JPasswordField();
	private JButton bLogin = new JButton();
	private JButton bGuest = new JButton();
	private JButton bRegister = new JButton();
	enum Action {
		LOGIN,
		REGISTER,
		GUEST
	}

	public AuthenticatorView(Authenticator ac) {
		initComponents();
		l = new LoginListener(inputName, inputPhrase, bLogin, 10, this);
		this.ac = ac;
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Jean Carlo Molina San Juan


		// ======== this ========
		setMaximumSize(new Dimension(1920, 1080));
		setPreferredSize(new Dimension(1920, 1080));
		setInheritsPopupMenu(true);
		setFont(new Font("Tahoma", Font.PLAIN, 24));
		setLayout(new GridBagLayout());

		// ---- lName ----
		lName.setText("Email:");
		lName.setHorizontalAlignment(SwingConstants.RIGHT);
		lName.setFont(new Font("Tahoma", Font.PLAIN, 24));
		add(lName, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 10, 10), 0, 0));

		// ---- inputName ----
		inputName.setToolTipText("Username");
		inputName.setMaximumSize(new Dimension(300, 20));
		inputName.setFont(new Font("Tahoma", Font.PLAIN, 24));
		add(inputName, new GridBagConstraints(1, 0, 2, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 10, 0), 0, 0));

		// ---- lPhrase ----
		lPhrase.setText("Passphrase:");
		lPhrase.setHorizontalAlignment(SwingConstants.RIGHT);
		lPhrase.setFont(new Font("Tahoma", Font.PLAIN, 24));
		add(lPhrase, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 10, 10), 0, 0));

		// ---- inputPhrase ----
		inputPhrase.setMaximumSize(new Dimension(300, 40));
		inputPhrase.setToolTipText("Passphrase");
		inputPhrase.setFont(new Font("Tahoma", Font.PLAIN, 24));
		add(inputPhrase, new GridBagConstraints(1, 1, 2, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 10, 0), 0, 0));

		// ---- bLogin ----
		bLogin.setText("Login");
		bLogin.setFont(new Font("Tahoma", Font.PLAIN, 24));
		add(bLogin, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 10, 0), 0, 0));
		//bLogin's action command is set by LoginListener

		// ---- bGuest ----
		bGuest.setText("Guest");
		bGuest.setFont(new Font("Tahoma", Font.PLAIN, 24));
		add(bGuest, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0));
		bGuest.setActionCommand("" + Action.GUEST);
		bGuest.addActionListener(this.l);

		// ---- button3 ----
		bRegister.setText("Register");
		bRegister.setFont(new Font("Tahoma", Font.PLAIN, 24));
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

	@Override
	public void onSuccess(User u) {
		this.setVisible(false);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFail() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMaxTries() {
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
