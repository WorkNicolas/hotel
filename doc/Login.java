import java.awt.*;
import javax.swing.*;
/*
 * Created by JFormDesigner on Thu Sep 08 16:06:47 SGT 2022
 */



/**
 * @author Jean Carlo Molina San Juan
 */
public class Login extends JPanel {
	public Login() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Jean Carlo Molina San Juan
		lName = new JLabel();
		inputName = new JTextField();
		lPhrase = new JLabel();
		inputPhrase = new JPasswordField();
		bLogin = new JButton();
		bGuest = new JButton();
		button3 = new JButton();

		//======== this ========
		setMaximumSize(new Dimension(1920, 1080));
		setPreferredSize(new Dimension(1920, 1080));
		setInheritsPopupMenu(true);
		setFont(new Font("Tahoma", Font.PLAIN, 24));
		setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new
		javax. swing. border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax
		. swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java
		.awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ), java. awt
		. Color. red) , getBorder( )) );  addPropertyChangeListener (new java. beans.
		PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062ord\u0065r" .
		equals (e .getPropertyName () )) throw new RuntimeException( ); }} );
		setLayout(new GridBagLayout());

		//---- lName ----
		lName.setText("Email:");
		lName.setHorizontalAlignment(SwingConstants.RIGHT);
		lName.setFont(new Font("Tahoma", Font.PLAIN, 24));
		add(lName, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 10, 10), 0, 0));

		//---- inputName ----
		inputName.setToolTipText("Username");
		inputName.setMaximumSize(new Dimension(300, 20));
		inputName.setFont(new Font("Tahoma", Font.PLAIN, 24));
		add(inputName, new GridBagConstraints(1, 0, 2, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 10, 0), 0, 0));

		//---- lPhrase ----
		lPhrase.setText("Passphrase:");
		lPhrase.setHorizontalAlignment(SwingConstants.CENTER);
		lPhrase.setFont(new Font("Tahoma", Font.PLAIN, 24));
		add(lPhrase, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 10, 10), 0, 0));

		//---- inputPhrase ----
		inputPhrase.setMaximumSize(new Dimension(300, 40));
		inputPhrase.setFont(new Font("Tahoma", Font.PLAIN, 24));
		add(inputPhrase, new GridBagConstraints(1, 1, 2, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 10, 0), 0, 0));

		//---- bLogin ----
		bLogin.setText("Login");
		bLogin.setFont(new Font("Tahoma", Font.PLAIN, 24));
		add(bLogin, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 10, 0), 0, 0));

		//---- bGuest ----
		bGuest.setText("Guest");
		bGuest.setFont(new Font("Tahoma", Font.PLAIN, 24));
		add(bGuest, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 0, 0), 0, 0));

		//---- button3 ----
		button3.setText("Register");
		button3.setFont(new Font("Tahoma", Font.PLAIN, 24));
		add(button3, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 10, 10), 0, 0));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Jean Carlo Molina San Juan
	private JLabel lName;
	protected JTextField inputName;
	private JLabel lPhrase;
	private JPasswordField inputPhrase;
	private JButton bLogin;
	private JButton bGuest;
	private JButton button3;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
