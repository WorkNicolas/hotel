package room;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class RoomPanel extends JPanel {
    public JButton button;
	public JLabel imageLabel;
	public JLabel displayLabel;
	public void setImage(RawInfo room, int width, int height) {
		var loader = new LazyLoader(imageLabel, room, width, height);
		loader.start();
		displayLabel.setText("<html>" + room.name + "<br/>"
		+ "Type: " + room.getType() + "<br/>"
		+ "Capacity: " + room.getSize() + "<br/>"
		+ "Rate: Php " + room.getRate()  + ".00</html>"
		);
		button.setText(room.getName());
	}
	public RoomPanel(RawInfo room, int width, int height) {
		this();
		setImage(room, width, height);
	}
    public RoomPanel() {
		JPanel panelLeft = new JPanel();
		JPanel panelRight = new JPanel();
		//panel properties
		setVisible(true);
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		//panelLeft properties
		panelLeft.setVisible(true);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		//bottom and top margins: 20, 20
		gbc.insets = new Insets(20,0,0,20);
		
		//image properties
		imageLabel = new JLabel();
		imageLabel.setIcon(Info.favicon);
	
		panelLeft.add(imageLabel);
		add(panelLeft, gbc);
		//rightPanel properties
		panelRight.setVisible(true);
		gbc.gridx = 1;
		gbc.gridy = 0;
		//bottom, left and top margins: 20, 10, 20
		gbc.insets = new Insets(20,10,0,20);
		
		//displayLabel Properties
		displayLabel = new JLabel();
		displayLabel.setFont(new Font("Consolas", Font.PLAIN, 20));
		panelRight.add(displayLabel);
		
		add(panelRight,gbc);
				
		//button properties
		gbc.gridx = 0;
		gbc.gridy = 1;
		//bottom and top margins: 10, 10
		gbc.insets = new Insets(10,0,0,10);
		
		button = new JButton();
		add(button, gbc);
    }
}