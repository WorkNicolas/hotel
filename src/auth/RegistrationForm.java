package auth;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * @author Mendoza, Carl Nicolas
 * @author San Juan, Jean Carlo
 */
public class RegistrationForm extends RegistrationPanel implements ActionListener {
	protected int MIN_PASSWORD_LENGTH = 8;
	protected int EXACT_CONTACT_LENGTH = 11;
	protected final String SIMPLE_EMAIL_CHECK = ".+@.+";
	protected final String SIMPLE_CONTACT_CHECK = "\\d{11}";
	public RegistrationForm() {
		super();
		buttonRegister.addActionListener(this);
	}
	
	public void errorPane(String emptyPhrase) {
		JOptionPane.showMessageDialog(this, "Please fill up all fields.\n" + emptyPhrase, "Error", JOptionPane.WARNING_MESSAGE);
	}
	
	public void successPane() {
		JOptionPane.showMessageDialog(this,  "Successfully created a new account.", "Account Creation Successful", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * @implNote Performs a basic client side validation of the inputs
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(fieldName.getText().isEmpty() 
			|| fieldEmail.getText().isEmpty() 
			|| fieldAddress.getText().isEmpty() 
			){
				errorPane("");
			}
		else if (!(fieldContact.getText().matches(SIMPLE_CONTACT_CHECK))) {
			errorPane("Contact number must be 11 digits long.");
		}
		else if (!(fieldEmail.getText().matches(SIMPLE_EMAIL_CHECK))) {
			errorPane("Please enter a valid email address.");
		}
		else if (fieldPhrase.getPassword().length < MIN_PASSWORD_LENGTH) {
			errorPane("Passphrase length should be " + MIN_PASSWORD_LENGTH + " characters long or greater.");
		} else {
			try_register();
		}
	}

	public void clear() {
		fieldName.setText(null);
		fieldEmail.setText(null);
		fieldAddress.setText(null);
		fieldContact.setText(null);
		fieldPhrase.setText(null);
	}
	/**
	 * @implNote Creates a UserInfo regardless of the validity.
	 * @return
	 */
	public UserInfo getUser() {
		return new UserInfo(
			fieldName.getText(), 
			fieldEmail.getText(), 
			fieldAddress.getText(), 
			fieldContact.getText(),
			String.valueOf(fieldPhrase.getPassword())
		);
	}

	public void try_register() {
		int id = 0;
		try {
			var u = getUser();
			id = Registrar.getRegistrar().register(u);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (id == 0) {
			errorPane("Server couldn't handle your request. Please try again later.");
			return;
		}
		successPane();
	}
}