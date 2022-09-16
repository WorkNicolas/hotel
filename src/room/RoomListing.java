package room;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collection;

public class RoomListing {
	RoomListing(){
		prepareFrame();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
	
	private JFrame frame = new JFrame();
	
	//Change this value.

	int number = 25;
	
	ArrayList<RawInfo> mockData = mockCollection(number);
	
	private void prepareFrame() {
		//frame Properties
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GridBagLayout fgbl = new GridBagLayout();
		frame.setLayout(fgbl);
		GridBagConstraints fgbc = new GridBagConstraints();
		fgbc.weightx = 3;
		fgbc.weighty = 7;
		fgbc.fill = GridBagConstraints.BOTH;
		
		//Main Panel
		
		JPanel mainPanel = panelAdder(number);
		
		//Collection
		Collection<RawInfo> rawInfo = mockCollection(number);
		
		//Scroll Pane
		JScrollPane scrollPane = new JScrollPane(mainPanel);
		
		//mainPanel properties
		fgbc.gridx = 0;
		fgbc.gridy = 0;
				
		fgbl.setConstraints(scrollPane, fgbc);
		
		//Next Button
		JPanel nextButtonPanel = nextComponent();
		
		fgbc.gridx = 0;
		fgbc.gridy = 1;
		fgbc.weightx = 1;
		fgbc.weighty = 1;
		fgbl.setConstraints(nextButtonPanel, fgbc);
		
		//frame: adding panels
		
		frame.add(scrollPane);
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public int getNumber() {
		return number;
	}
	
	//Panel adder
	private JPanel panelAdder(int count) {
		//Main Panel Properties
		JPanel mainPanel = new JPanel();
		
		GridBagLayout gbl = new GridBagLayout();
		mainPanel.setLayout(gbl);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		
		//Panel Properties
		
		JPanel panel[] = new JPanel[count];
		ImageIcon imageIcon[] = new ImageIcon[count];
		
		for (int i = 0; i < count; i++) {
			//URL url = new URL(mockData.get(i).getUrl());
			//Image image = ImageIO.read(url);
			//imageIcon[i] = new ImageIcon(image);
			imageIcon[i] = new ImageIcon(mockData.get(i).getUrl());
			
			panel[i] = preparePanel(imageIcon[i], "Room " + (i+1), i);
			
		}
		
		int panelNumber = 0;
		//Positioning the panels
		int c = count;
		int yContainer = 0;

		//c becomes the nearest 6
		c -= c%6;

		for (int y = 0; y <= c/3-1; y++) {
			for (int x = 0; x <= 2; x++) {
				gbc.gridx = x;
				gbc.gridy = y;
				gbc.gridwidth = 1;
				gbc.gridheight = 1;
				//bottom and top margins: 50, 0
				gbc.insets = new Insets(50,0,0,0);
					
				gbl.setConstraints(panel[panelNumber], gbc);
				
				panelNumber++;
				
				yContainer = y;
			}
		}

		if (count%6 > 0 && count%6 < 4) {
			int x = 0;
			
			gbc.gridy = yContainer + 1;
			
			do {
				gbc.gridx = x;
				
				gbc.gridwidth = 1;
				gbc.gridheight = 1;
				
				//bottom and top margins: 50, 0
				gbc.insets = new Insets(50,0,0,0);
					
				gbl.setConstraints(panel[panelNumber], gbc);
				
				panelNumber++;
				
				x++;
			} while (x <= count%6-1);
		} else if (count%6 > 3 && count%6 < 6) {
			gbc.gridy = yContainer + 1;
					
			for (int x = 0; x <= 2; x++) {
				gbc.gridx = x;
				
				gbc.gridwidth = 1;
				gbc.gridheight = 1;
				
				//bottom and top margins: 50, 0
				gbc.insets = new Insets(50,0,0,0);
					
				gbl.setConstraints(panel[panelNumber], gbc);
				
				panelNumber++;
			}
			
			gbc.gridy = yContainer + 2;
			
			int x = 0;
			
			do {
				gbc.gridx = x;

				gbc.gridwidth = 1;
				gbc.gridheight = 1;
				
				//bottom and top margins: 50, 0
				gbc.insets = new Insets(50,0,0,0);
					
				gbl.setConstraints(panel[panelNumber], gbc);
				
				panelNumber++;
				
				x++;
			} while (x <= count%6-4);
		}
		//End of positioning
		
		//Adding all panels
		for(int i = 0; i < count; i++ ) {
			mainPanel.add(panel[i]);
		}
		
		return mainPanel;
	}
	
	//Panel Creator
	private JPanel preparePanel(ImageIcon roomImage, String roomName, int id) {
		JPanel panel = new JPanel();
		JPanel panelLeft = new JPanel();
		JPanel panelRight = new JPanel();
		
		//panel properties
		panel.setVisible(true);
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		//panelLeft properties
		panelLeft.setVisible(true);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		//bottom and top margins: 20, 20
		gbc.insets = new Insets(20,0,0,20);
		
		//image properties
		JLabel imageLabel = new JLabel();
		imageLabel.setIcon(imageComponent(roomImage));
		
		panelLeft.add(imageLabel);
		
		panel.add(panelLeft, gbc);
		
		//rightPanel properties
		panelRight.setVisible(true);
		gbc.gridx = 1;
		gbc.gridy = 0;
		//bottom, left and top margins: 20, 10, 20
		gbc.insets = new Insets(20,10,0,20);
		
		//displayLabel Properties
		JLabel displayLabel = new JLabel();
		displayLabel.setText("<html>" + roomName + "<br/>"
				+ "Type: " + mockData.get(id).getType() + "<br/>"
				+ "Capacity: " + mockData.get(id).getSize() + "<br/>"
				+ "Rate: Php " + mockData.get(id).getRate() + ".00</html>"
				);
		
		panelRight.add(displayLabel);
		
		panel.add(panelRight,gbc);
				
		//button properties
		gbc.gridx = 0;
		gbc.gridy = 1;
		//bottom and top margins: 10, 10
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
	
	//Next Button Panel
	private JPanel nextComponent() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		JButton button = new JButton("next");
		button.setVisible(true);
		button.setBounds(1150,25,100,30);
		panel.add(button);
		
		return panel;
	}
	
	//Mock Data
	private ArrayList<RawInfo> mockCollection(int count) {
		//int id, Type type, int size, String url, int rate
		Type t[] = {Type.BASIC, Type.STANDARD, Type.SUITE};
		/*String url[] = {"https://drive.google.com/file/d/16-kejBnDr8gOxJnQroy9GpaisiAQqW4e/view",
				"https://drive.google.com/file/d/10GvHEGB7peEzEkJEFJL6agxGXmc07RMF/view",
				"https://drive.google.com/file/d/1XShAmYgy1yP2J_pd_SzyMBFGHvQ-KnOw/view",
				"https://drive.google.com/file/d/1BWGtFsBQhwMq35oad9rnbEzDCEnt1vC5/view",
				"https://drive.google.com/file/d/1BWGtFsBQhwMq35oad9rnbEzDCEnt1vC5/view",
				"https://drive.google.com/file/d/1BWGtFsBQhwMq35oad9rnbEzDCEnt1vC5/view",
				};*/
		String url[] = {"room1.jpg","room2.jpg","room3.jpg","room4.jpg","room5.jpg","room6.jpg"};
		ArrayList<RawInfo> mockData = new ArrayList<>();		
		for (int i = 0; i < count; i++) {
			mockData.add(new RawInfo(i, //id
					t[new Random().nextInt(t.length)], //type 
					(int) (Math.random() * (5 - 1)) + 1, //size max: 5, min: 1
					url[new Random().nextInt(url.length)], //url
					(int) (Math.random() * (6000 - 680)) + 680)); //rate
		}
		
		return mockData;
	}
	
	//toString
	private String getRawInfo(int count) {
		Collection<RawInfo> rawInfo = mockCollection(count);
		//TODO: toString
		return null;
	}
	
	public static void main(String[] args) {
		new RoomListing();
	}
	
}
