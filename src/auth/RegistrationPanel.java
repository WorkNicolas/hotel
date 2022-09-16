package auth;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class RegistrationPanel extends JPanel  {
	//User Information
	protected JTextField fieldName, fieldEmail, fieldAddress;
	JPasswordField fieldPhrase;
	public JButton buttonRegister;
	public JButton buttonLogin;
	public RegistrationPanel() {
		initComponents();
	}
	
	private void initComponents() {
		Font font = new Font("Tahoma", Font.PLAIN, 24);
		
		//Labels
		JLabel labelName = prepareLabel("Name:");
		JLabel labelEmail = prepareLabel("Email:");
		JLabel labelAddress = prepareLabel("Address:");
		JLabel labelPhrase = prepareLabel("Passphrase:");
		
		//TextFields
		fieldName = new JTextField();
		fieldEmail = new JTextField();
		fieldAddress = new JTextField();
		fieldPhrase = new JPasswordField();
		
		//Buttons
		buttonLogin = prepareButton("Return");
		buttonRegister = prepareButton("Register");
		
		//frame properties
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints fgbc = new GridBagConstraints();
		setLayout(gbl);
		setVisible(true);
		
		//panel properties
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		setLayout(new GridBagLayout());
		setFont(font);
		gbc.insets = new Insets(5,5,5,0);
		
		//labelName
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		gbc.gridwidth = 1;
		
		gbl.setConstraints(labelName, gbc);
		
		add(labelName, gbc);
		//fieldName
		fieldName = prepareTextField();
		
		gbc.gridwidth = 2;
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		
		gbl.setConstraints(fieldName, gbc);
		add(fieldName, gbc);
		
		//labelEmail
		gbc.gridx = 0;
		gbc.gridy = 1;
		
		gbc.gridwidth = 1;
		
		gbl.setConstraints(labelEmail, gbc);
		add(labelEmail, gbc);
		//fieldEmail
		fieldEmail = prepareTextField();
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		
		gbc.gridwidth = 2;
		
		gbl.setConstraints(fieldEmail, gbc);
		add(fieldEmail, gbc);
		
		//labelAddress
		gbc.gridx = 0;
		gbc.gridy = 2;
		
		gbc.gridwidth = 1;
		
		gbl.setConstraints(labelAddress, gbc);
		add(labelAddress, gbc);
		//fieldAddress
		fieldAddress = prepareTextField();
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		
		gbc.gridwidth = 2;
		
		gbl.setConstraints(fieldAddress, gbc);
		add(fieldAddress, gbc);
		
		//labelPhrase
		gbc.gridx = 0;
		gbc.gridy = 3;
		
		gbc.gridwidth = 1;
		
		gbl.setConstraints(labelPhrase, gbc);
		add(labelPhrase, gbc);
		//fieldPhrase
		fieldPhrase.setMaximumSize(new Dimension(300,40));
		fieldPhrase.setFont(font);
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		
		gbc.gridwidth = 2;
		
		gbl.setConstraints(fieldPhrase, gbc);
		add(fieldPhrase, gbc);
		
		//buttonReturn
		gbc.insets = new Insets(5,5,5,0);	
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		
		gbc.gridwidth = 1;
		
		gbl.setConstraints(buttonLogin, gbc);
		add(buttonLogin, gbc);
		//buttonRegister
		gbc.gridx = 1;
		gbc.gridy = 4;

		gbc.gridwidth = 2;
		
		gbl.setConstraints(buttonRegister, gbc);
		add(buttonRegister, gbc);
		
		//frame: adding panels
		//panel
		fgbc.gridx = 0;
		fgbc.gridy = 0;
		gbl.setConstraints(this, fgbc);
		
		//panel.setFont(font);
	}
	
	private JTextField prepareTextField() {
		JTextField textField = new JTextField();
		textField.setPreferredSize(new Dimension(300,35));
		textField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		return textField;
	}
	
	private JLabel prepareLabel(String name) {
		JLabel label = new JLabel(name, SwingConstants.RIGHT);
		label.setFont(new Font("Tahoma", Font.PLAIN, 24));
		return label;
	}
	
	private JButton prepareButton(String name) {
		JButton button = new JButton(name);
		button.setFont(new Font("Tahoma", Font.PLAIN, 24));
		return button;
	}
	
	public UserInfo getUser() {
		return new UserInfo(
			fieldName.getText(), 
			fieldEmail.getText(), 
			fieldAddress.getText(), 
			String.valueOf(fieldPhrase.getPassword())
		);
	}
	public static void main(String[] args) {
		new RegistrationPanel();
	}
}