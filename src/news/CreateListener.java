package news;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * @author Mendoza, Carl Nicolas
 * @author JCSJ
 */
import javax.swing.JOptionPane;
public class CreateListener implements ActionListener{
	private final NewsForm news;
	
	public CreateListener(NewsForm news) {
		this.news = news;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		promptNews();
	}
	
	private String promptTitle() {
		return JOptionPane.showInputDialog(news, "Enter news title:");
	}
	
	private String promptText() {
		return JOptionPane.showInputDialog(news, "Enter news text:");
	}
	
	private void promptNews() {
		String title = promptTitle();
		String text = promptText();
		if (title == null || text == null)
			return;
		news.addNewsContent(news.newsContent.size(), title, text);
		news.createNewPanels(news.newsContent);
	}
}
