package news;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Random;

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
	
	//Change these values
	int randomNews = 12; //Number of news randomly generated
	int number = randomNews; //Number of news available in newsContent, used for adding new news.
	boolean isCreator = false; //Adds button panel to create and delete news
	
	//Mock Data: newsContent
	ArrayList<News> newsContent = mockNews(randomNews);
	
	//ArrayList of Panels
	ArrayList<JPanel> panelContent = new ArrayList<>();
	
	public static void main(String[] args) {
		NewsForm form = new NewsForm();
	}
	public NewsForm(){
		initComponents();
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getNumber() {
		return number;
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
		number++;
	}
	//Error: Index Array Out of Bounds
	public void createNewPanels(ArrayList<News> newsContent) {
		removeAll();
		repaint();
		revalidate();
		
		this.newsContent = newsContent;
		
		JPanel panel = panelAdder(number);
		JScrollPane panePanel = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		JPanel panelControl = initPanelButton();

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
		
		//Adding: panelNews to frame
		if(isCreator) {
			gbc.gridx = 1;
			gbc.gridy = 0;
			gbc.weightx = 0;
			gbc.weighty = 1;
			gbc.fill = GridBagConstraints.CENTER;
			gbc.anchor = GridBagConstraints.NORTH;
			gbl.setConstraints(panelControl, gbc);
			add(panelControl, gbc);
		}
	}
	private void initComponents() {
		panelNews = panelAdder(number);
		JScrollPane panePanel = new JScrollPane(panelNews, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		JPanel panelControl = initPanelButton();
		
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
		
		//Adding: panelNews to frame
		if(isCreator) {
			gbc.gridx = 1;
			gbc.gridy = 0;
			gbc.weightx = 0;
			gbc.weighty = 1;
			gbc.fill = GridBagConstraints.CENTER;
			gbc.anchor = GridBagConstraints.NORTH;
			gbl.setConstraints(panelControl, gbc);
			add(panelControl, gbc);
		}
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
	private JPanel panelAdder(int count) {
		JPanel panel = new JPanel();
		
		var gbc = new GridBagConstraints();
		var gbl = new GridBagLayout();
		
		//panel Properties
		panel.setFont(font);
		panel.setVisible(true);
		panel.setLayout(gbl);
		
		int panelNumber = 0;
		
		//panelAdder: latest to oldest
		for(int i = count-1; i >= 0; i--) {
			gbc.gridx = 0;
			gbc.gridy = y;
			gbc.gridwidth = 1;
			gbc.gridheight = 1;
			gbc.anchor = GridBagConstraints.WEST;					
			gbc.insets = new Insets(5,5,5,5);
			
			//Create a panel with title and text
			JPanel panelStore = panelNewsCreator(newsContent.get(y).getTitle(), newsContent.get(y).getText());
			panelContent.add(panelStore);
			
			panelContent.get(panelNumber).setVisible(true);
			
			gbl.setConstraints(panelContent.get(panelNumber), gbc);
			
			panel.add(panelContent.get(panelNumber), gbc);
			
			panelNumber++;
			y++;
		}
		
		return panel;
	}
	private ArrayList<News> mockNews(int count) {
		ArrayList<News> mockContent = new ArrayList<>();
		
		String title[] = {"The standard Lorem Ipsum passage",
				"Section 1.10.32 of de Finibus Bonorum et Malorum",
				"1914 translation by H. Rackham",
				"Section 1.10.33 of de Finibus Bonorum et MalorumC",
				"1914 translation by H. Rackham"
		};
		
		String text[] = {"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
				"Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?",
				"But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful. Nor again is there anyone who loves or pursues or desires to obtain pain of itself, because it is pain, but because occasionally circumstances occur in which toil and pain can procure him some great pleasure. To take a trivial example, which of us ever undertakes laborious physical exercise, except to obtain some advantage from it? But who has any right to find fault with a man who chooses to enjoy a pleasure that has no annoying consequences, or one who avoids a pain that produces no resultant pleasure?",
				"At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat.",
				"On the other hand, we denounce with righteous indignation and dislike men who are so beguiled and demoralized by the charms of pleasure of the moment, so blinded by desire, that they cannot foresee the pain and trouble that are bound to ensue; and equal blame belongs to those who fail in their duty through weakness of will, which is the same as saying through shrinking from toil and pain. These cases are perfectly simple and easy to distinguish. In a free hour, when our power of choice is untrammelled and when nothing prevents our being able to do what we like best, every pleasure is to be welcomed and every pain avoided. But in certain circumstances and owing to the claims of duty or the obligations of business it will frequently occur that pleasures have to be repudiated and annoyances accepted. The wise man therefore always holds in these matters to this principle of selection: he rejects pleasures to secure other greater pleasures, or else he endures pains to avoid worse pains."
		};
		
		for (int i = 0; i < count; i++) {
			mockContent.add(new News(i, 
					title[new Random().nextInt(title.length)], 
					text[new Random().nextInt(text.length)]));
		}
		
		return mockContent;
	}
	
	private JPanel initPanelButton() {
		JPanel panel = new JPanel();
		
		JButton buttonCreate = new JButton("Create News");
		JButton buttonDelete = new JButton("Delete News");
		
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
