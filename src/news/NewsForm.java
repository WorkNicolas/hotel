package news;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * @author Mendoza, Carl Nicolas
 */
public class NewsForm extends JPanel {
	JPanel panelNews = new JPanel();
	
	//for panelAdder gbc.gridy
	int y = 0;
	
	Font font = new Font("Tahoma", Font.PLAIN, 24);
	
	boolean isCreator = false; //Adds button panel to create and delete news
	
	//Mock Data: newsContent
	ArrayList<News> newsContent = new ArrayList<>();
	
	//ArrayList of Panels
	ArrayList<JPanel> panelContent = new ArrayList<>();

	public JButton buttonCreate;
	public JButton buttonDelete;
	
	public NewsForm(){
		initComponents();
	}

	public NewsForm(boolean isCreator){
		this.isCreator = isCreator;
		initComponents();
	}
	public void setCreator(boolean isCreator) {
		this.isCreator = isCreator;
	}
	public boolean getCreator() {
		return isCreator;
	}
	public void setNewsContent(ArrayList<News> newsContent) {
		this.newsContent = newsContent;
	}
	public ArrayList<News> getNewsContent() {
		return newsContent;
	}
	//Related methods
	public void addNewsContent(int id, String title, String text) {
		newsContent.add(new News(id, title, text));
		createNewPanels(newsContent);
	}

	private void whenCreator() {
		//Adding: panelNews to frame
		if (isCreator == false)
			return;
		JPanel panelControl = initPanelButton();
		var gbc = new GridBagConstraints();
		var gbl = new GridBagLayout();

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.CENTER;
		gbc.anchor = GridBagConstraints.NORTH;
		gbl.setConstraints(panelControl, gbc);
		add(panelControl, gbc);
	}
	//Error: Index Array Out of Bounds
	public void createNewPanels(ArrayList<News> newsContent) {
		removeAll();
		this.newsContent = newsContent;
		JPanel panel = panelAdder();
		JScrollPane panePanel = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		

		//panePanel Properties
		panePanel.setPreferredSize(new Dimension(300, 300));
		
		//GridBagLayout
		var gbl = new GridBagLayout();
		var gbc = new GridBagConstraints();
		setLayout(gbl);
		
		//Adding: panel to frame
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 7;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.WEST;
		gbl.setConstraints(panePanel, gbc);
		add(panePanel, gbc);
		whenCreator();
		repaint();
		revalidate();
	}
	private void initComponents() {
		panelNews = panelAdder();
		JScrollPane panePanel = new JScrollPane(panelNews, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		//panePanel Properties
		panePanel.setPreferredSize(new Dimension(300, 300));
		
		//GridBagLayout
		var gbl = new GridBagLayout();
		var gbc = new GridBagConstraints();
		setLayout(gbl);
		
		//Adding: panel to frame
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 7;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.WEST;
		gbl.setConstraints(panePanel, gbc);
		add(panePanel, gbc);
		whenCreator();
	}
	//panelNews creator: for panelAdder
	private JPanel panelNewsCreator(String title, String text) {
		JPanel panel = new JPanel();
		JPanel panelText = new JPanel();
		
		JLabel labelTitle = new JLabel(title);
		JTextArea areaText = new JTextArea(10,30);
		areaText.setText(text);
		
		//for panel
		var gbc = new GridBagConstraints();
		var gbl = new GridBagLayout();
		
		//panel Properties
		panel.setFont(font);
		panel.setVisible(true);
		panel.setLayout(gbl);
		
		//panelText Properties
		panelText.setFont(font);
		panelText.setVisible(true);
		panelText.setLayout(gbl);
		
		//labelTitle Properties
		labelTitle.setFont(font);
		labelTitle.setVisible(true);
		labelTitle.setText(title);
		
		//areaText Properties
		areaText.setFont(font);
		areaText.setVisible(true);
		areaText.setText(text);
		areaText.setEditable(false);
		areaText.setLineWrap(true);
		
		//Adding: areaText to panelText
		JScrollPane areaTextPane = new JScrollPane(areaText, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panelText.add(areaTextPane);
		
		//Positioning: labelTitle to panel
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(10,10,10,10);
		
		gbl.setConstraints(labelTitle, gbc);
		panel.add(labelTitle, gbc);
		
		//Positioning: panelText to panel
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(10,10,10,10);
		
		gbl.setConstraints(panelText, gbc);
		panel.add(panelText, gbc);
		
		return panel;
	}
	//panelAdder: for News()
	private JPanel panelAdder() {
		JPanel panel = new JPanel();
		
		var gbc = new GridBagConstraints();
		var gbl = new GridBagLayout();
		
		//panel Properties
		panel.setFont(font);
		panel.setVisible(true);
		panel.setLayout(gbl);
		
		int panelNumber = 0;
		
		//panelAdder: latest to oldest
		for (var news: newsContent) {
				gbc.gridx = 0;
				gbc.gridy = y;
				gbc.gridwidth = 1;
				gbc.gridheight = 1;
				gbc.anchor = GridBagConstraints.WEST;					
				gbc.insets = new Insets(5,5,5,5);
				
				//Create a panel with title and text
				JPanel panelStore = panelNewsCreator(news.getTitle(), news.getText());
				panelContent.add(panelStore);
				
				panelContent.get(panelNumber).setVisible(true);
				
				gbl.setConstraints(panelContent.get(panelNumber), gbc);
				
				panel.add(panelContent.get(panelNumber), gbc);
				y++;
				panelNumber++;
		}
		
		return panel;
	}

	
	private JPanel initPanelButton() {
		JPanel panel = new JPanel();
		
		buttonCreate = new JButton("Create News");
		buttonDelete = new JButton("Delete News");
		
		var gbc = new GridBagConstraints();
		var gbl = new GridBagLayout();
		
		//panel Properties
		panel.setLayout(gbl);
		panel.setVisible(true);
		
		//buttonCreate Properties
		/* buttonCreate.setFont(font);
		buttonCreate.setVisible(true); */
		
		//buttonDelete Properties
		/* buttonDelete.setFont(font);
		buttonDelete.setVisible(true); */
		
		//Positioning: buttonCreate to Panel
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(10,10,10,10);
		
		gbl.setConstraints(buttonCreate, gbc);
		
		var cL = new CreateListener(this);
		buttonCreate.addActionListener(cL);
		
		panel.add(buttonCreate, gbc);
		
		//Positioning: buttonDelete to Panel
		gbc.gridx= 0;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(10,10,10,10);
		
		gbl.setConstraints(buttonDelete, gbc);
		
		panel.add(buttonDelete, gbc);
		
		return panel;
	}
}