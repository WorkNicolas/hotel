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

public class RegistrationPanel extends JPanel {
	//User Information
	String name, email, address, phrase;
	public RegistrationPanel() {
		initComponents();
	}
	
	protected JTextField fieldName;
	protected JTextField fieldEmail;
	protected JTextField fieldAddress;
	protected JTextField fieldContact;
	protected JPasswordField fieldPhrase;
	
	public JButton buttonReturn;
	public JButton buttonRegister;
	
	private void initComponents() {
		//Panel
		JPanel panel = new JPanel();
		
		Font font = new Font("Tahoma", Font.PLAIN, 24);
		
		//Labels
		JLabel labelName = prepareLabel("Name:");
		JLabel labelEmail = prepareLabel("Email:");
		JLabel labelAddress = prepareLabel("Address:");
		JLabel labelContact = prepareLabel("Contact #:");
		JLabel labelPhrase = prepareLabel("Passphrase:");
		
		//Buttons
		buttonReturn = prepareButton("Return");
		buttonRegister = prepareButton("Register");
		
		//frame properties
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints fgbc = new GridBagConstraints();
		setLayout(gbl);
		
		//panel properties
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		panel.setLayout(new GridBagLayout());
		panel.setFont(font);
		gbc.insets = new Insets(5,5,5,0);
		
		//labelName
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		gbc.gridwidth = 1;
		
		gbl.setConstraints(labelName, gbc);
		
		panel.add(labelName, gbc);
		//fieldName
		fieldName = prepareTextField();
		
		gbc.gridwidth = 2;
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		
		gbl.setConstraints(fieldName, gbc);
		panel.add(fieldName, gbc);
		
		//labelEmail
		gbc.gridx = 0;
		gbc.gridy = 1;
		
		gbc.gridwidth = 1;
		
		gbl.setConstraints(labelEmail, gbc);
		panel.add(labelEmail, gbc);
		//fieldEmail
		fieldEmail = prepareTextField();
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		
		gbc.gridwidth = 2;
		
		gbl.setConstraints(fieldEmail, gbc);
		panel.add(fieldEmail, gbc);
		
		//labelAddress
		gbc.gridx = 0;
		gbc.gridy = 2;
		
		gbc.gridwidth = 1;
		
		gbl.setConstraints(labelAddress, gbc);
		panel.add(labelAddress, gbc);
		//fieldAddress
		fieldAddress = prepareTextField();
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		
		gbc.gridwidth = 2;
		
		gbl.setConstraints(fieldAddress, gbc);
		panel.add(fieldAddress, gbc);
		
		//labelContact
		gbc.gridx = 0;
		gbc.gridy = 3;
		
		gbc.gridwidth = 1;
		
		gbl.setConstraints(labelContact, gbc);
		panel.add(labelContact, gbc);
		//fieldContact
		fieldContact = prepareTextField();
		gbc.gridx = 1;
		gbc.gridy = 3;
		
		gbc.gridwidth = 2;
		
		gbl.setConstraints(fieldContact, gbc);
		panel.add(fieldContact,gbc);
		//labelPhrase
		gbc.gridx = 0;
		gbc.gridy = 4;
		
		gbc.gridwidth = 1;
		
		gbl.setConstraints(labelPhrase, gbc);
		panel.add(labelPhrase, gbc);
		//fieldPhrase
		fieldPhrase = new JPasswordField();
		fieldPhrase.setMaximumSize(new Dimension(300,40));
		fieldPhrase.setFont(font);
		
		gbc.gridx = 1;
		gbc.gridy = 4;
		
		gbc.gridwidth = 2;
		
		gbl.setConstraints(fieldPhrase, gbc);
		panel.add(fieldPhrase, gbc);
		
		//buttonReturn
		gbc.insets = new Insets(5,5,5,0);	
		
		gbc.gridx = 0;
		gbc.gridy = 5;
		
		gbc.gridwidth = 1;
		
		gbl.setConstraints(buttonReturn, gbc);
		panel.add(buttonReturn, gbc);
		//buttonRegister
		gbc.gridx = 1;
		gbc.gridy = 5;

		gbc.gridwidth = 2;
		
		gbl.setConstraints(buttonRegister, gbc);
		panel.add(buttonRegister, gbc);
		
		//frame: adding panels
		//panel
		fgbc.gridx = 0;
		fgbc.gridy = 0;
		gbl.setConstraints(panel, fgbc);
		
		//panel.setFont(font);
		add(panel, fgbc);
		
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
}