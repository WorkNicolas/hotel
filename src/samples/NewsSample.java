package samples;

import javax.swing.JFrame;

import news.NewsForm;

public class NewsSample  extends JFrame {
    public static void main(String[] args) {
        new NewsSample();
    }

    public NewsSample() {
        setTitle("News");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setExtendedState(JFrame.MAXIMIZED_BOTH);
        add(new NewsForm());
		setVisible(true);
		setResizable(true);
    }
}
