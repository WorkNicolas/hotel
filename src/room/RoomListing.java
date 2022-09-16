package room;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

public class RoomListing  extends JPanel{
	public ArrayList<JButton> buttons;
	//Change this value.
	int number = 25;
	ArrayList<RawInfo> data;
	int width = 200;
	int height = 200;
	public RoomListing(){
		data = new ArrayList<>();
		prepareFrame();
		Info.setFavicon(Helper.createImageIcon(Color.BLUE, width, height));
	}

	
	public void updateEntries(ArrayList<RawInfo> entries) {
		data = entries;
		prepareFrame();
	}
	private void prepareFrame() {
		{ // For updating room entries
			removeAll();
			buttons = new ArrayList<>();
		}

		//frame Properties
		
		GridBagLayout fgbl = new GridBagLayout();
		setLayout(fgbl);
		GridBagConstraints fgbc = new GridBagConstraints();
		fgbc.weightx = 3;
		fgbc.weighty = 7;
		fgbc.fill = GridBagConstraints.BOTH;
		
		//Main Panel
		
		JPanel mainPanel = panelAdder(data);
		
		//Scroll Pane
		JScrollPane scrollPane = new JScrollPane(mainPanel);
		
		//mainPanel properties
		fgbc.gridx = 0;
		fgbc.gridy = 0;
				
		fgbl.setConstraints(scrollPane, fgbc);
		
		fgbc.gridx = 0;
		fgbc.gridy = 1;
		fgbc.weightx = 1;
		fgbc.weighty = 1;
		
		add(scrollPane);
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public int getNumber() {
		return number;
	}
	
	//Panel adder
	private JPanel panelAdder(ArrayList<RawInfo> data) {
		//Main Panel Properties
		JPanel mainPanel = new JPanel();
		int count = data.size();
		GridBagLayout gbl = new GridBagLayout();
		mainPanel.setLayout(gbl);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		
		//Panel Properties
		
		JPanel[] panels = new JPanel[count];
		for (int i = 0; i < count; i++) {
			panels[i] = preparePanel(data.get(i));
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
					
				gbl.setConstraints(panels[panelNumber], gbc);
				
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
					
				gbl.setConstraints(panels[panelNumber], gbc);
				
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
					
				gbl.setConstraints(panels[panelNumber], gbc);
				
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
					
				gbl.setConstraints(panels[panelNumber], gbc);
				
				panelNumber++;
				
				x++;
			} while (x <= count%6-4);
		}
		//End of positioning
		
		//Adding all panels
		for(int i = 0; i < count; i++ ) {
			mainPanel.add(panels[i]);
		}
		
		return mainPanel;
	}
	

	//Panel Creator
	private JPanel preparePanel(RawInfo room) {
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
		imageLabel.setIcon(Info.favicon);
		var loader = new LazyLoader(imageLabel, room, width, height);
		loader.start();
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
		displayLabel.setText("<html>" + room.name + "<br/>"
				+ "Type: " + room.getType() + "<br/>"
				+ "Capacity: " + room.getSize() + "<br/>"
				+ "Rate: Php " + room.getRate()  + ".00</html>"
				);
		
		panelRight.add(displayLabel);
		
		panel.add(panelRight,gbc);
				
		//button properties
		gbc.gridx = 0;
		gbc.gridy = 1;
		//bottom and top margins: 10, 10
		gbc.insets = new Insets(10,0,0,10);
		
		JButton button = new JButton(room.getName());
		buttons.add(button);
		panel.add(button, gbc);
		
		return panel;
	}
	
	public static void main(String[] args) {
		new RoomListing();
	}
	
}