package room;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

/**
 * {@summary A view for the available rooms}
 */
public class ListingView  extends JPanel{
	public ArrayList<JButton> buttons;
	//Change this value.
	ArrayList<RawInfo> data;
	int width = 200;
	int height = 200;
	public ListingView(){
		data = new ArrayList<>();
		buttons = new ArrayList<>();
		Info.setFavicon(Helper.createImageIcon(Color.BLUE, width, height));
	}

	/**
	 * Rerenders with new data
	 */
	public void updateEntries(ArrayList<RawInfo> entries) {
		data = entries;
		populate(data);
	}

	/**
	 * {@summary Populates the JPanel with the rooms}
	 */
	private void populate(ArrayList<RawInfo> data) {
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

	//Panel adder
	private JPanel panelAdder(ArrayList<RawInfo> data) {
		//Main Panel Properties
		JPanel panel = new JPanel();
		int count = data.size();
		GridBagLayout gbl = new GridBagLayout();
		panel.setLayout(gbl);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		//Panel Properties
		
		RoomPanel[] panels = new RoomPanel[count];
		for (int i = 0; i < count; i++) {
			panels[i] = new RoomPanel(data.get(i), width, height);
			panels[i].button.setActionCommand("" + i); //Index
			buttons.add(panels[i].button);
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
			panel.add(panels[i]);
		}
		return panel;
	}
}