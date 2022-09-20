package news;
/**
 * @author Mendoza, Carl Nicolas
 */
public class News {
	int id;
	String title, text;
	
	public News(int id, String title, String text) {
		this.id = id;
		this.title = title;
		this.text = text;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public News getContent() {
		return new News(id, title, text);
	}
	
}
