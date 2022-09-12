package room;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

public class RoomListing extends JPanel {
	public RoomListing(){
		prepareComponents();
	}
	
	private void prepareComponents() {
		setLayout(new GridLayout(2,3));
		/*
		 * 1. create ImageIcon
		 * 2. create panel using preparePanel parameters: ImageIcon, Button Name
		 * 3. add panel to frame
		 */
		
		//First Panel
		ImageIcon firstRoomImage = new ImageIcon("room.jpg");
		JPanel firstPanel = preparePanel(firstRoomImage, "Room 1");
		add(firstPanel);
		
		//Second Panel
		ImageIcon secondRoomImage = new ImageIcon("room.jpg");
		JPanel secondPanel = preparePanel(secondRoomImage, "Room 2");
		add(secondPanel);
		
		//Third Panel
		ImageIcon thirdRoomImage = new ImageIcon("room.jpg");
		JPanel thirdPanel = preparePanel(thirdRoomImage, "Room 3");
		add(thirdPanel);
		
		//Fourth Panel
		ImageIcon fourthRoomImage = new ImageIcon("room.jpg");
		JPanel fourthPanel = preparePanel(fourthRoomImage, "Room 4");
		add(fourthPanel);
		
		//Fifth Panel
		ImageIcon fifthRoomImage = new ImageIcon("room.jpg");
		JPanel fifthPanel = preparePanel(fifthRoomImage, "Room 5");
		add(fifthPanel);
		
		//Sixth Panel
		ImageIcon sixthRoomImage = new ImageIcon("room.jpg");
		JPanel sixthPanel = preparePanel(sixthRoomImage, "Room 6");
		add(sixthPanel);
		
		//Next Button
	}
	
	//Panel Creator
	private JPanel preparePanel(ImageIcon roomImage, String roomName) {
		JPanel panel = new JPanel();
		//panel properties
		panel.setVisible(true);
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		//image properties
		gbc.gridx = 0;
		gbc.gridy = 0;
		//Top and bottom margins: 10, 10
		gbc.insets = new Insets(10,0,0,10);
		
		JLabel imageLabel = new JLabel();
		imageLabel.setIcon(imageComponent(roomImage));
		
		panel.add(imageLabel, gbc);
		
		//button properties
		gbc.gridx = 0;
		gbc.gridy = 1;
		//Top and bottom margins: 10, 10
		gbc.insets = new Insets(10,0,0,10);
		
		JButton button = new JButton(roomName);
		
		panel.add(button, gbc);
		
		
		return panel;
	}
	
	//Resize Image
	private ImageIcon imageComponent(ImageIcon imageIcon) {
		Image image = imageIcon.getImage();
		//Current Image Size: 200x200
		Image imageModified = image.getScaledInstance(200,200,java.awt.Image.SCALE_SMOOTH);
		return imageIcon = new ImageIcon(imageModified);
	}
	
	//Next Button
	/* private JButton nextComponent() {
		JButton nextButton = new nextButton("Next");
		
	} */
}
