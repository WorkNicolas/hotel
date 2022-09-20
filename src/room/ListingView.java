package room;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
/**
 * @author Mendoza, Carl Nicolas
 * @author JCSJ
 */
public class ListingView extends JPanel {
    public ArrayList<JButton> buttons;
	//Change this value.
	public ArrayList<RawInfo> data;
    public int width = 230;
	public int height = 200;
	private int columnCount = 3;
    public ListingView(){
		data = new ArrayList<>();
		buttons = new ArrayList<>();
		Info.setFavicon(Helper.createImageIcon(Color.BLUE, width, height));
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(3, 3, 3, 3));
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
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        int i = 0;
        JPanel row = new JPanel();
        for (var d: data) {
            if (i++%columnCount == 0) {
                panel.add(row);
                row = new JPanel(new FlowLayout());
            }
            var p = new RoomPanel(d, width, height);
			p.button.setActionCommand("" + i); //Index
			buttons.add(p.button);
            row.add(p);
        }
		JScrollPane scrollPane = new JScrollPane(panel);
        add(scrollPane, BorderLayout.CENTER);
		revalidate();
    }
}