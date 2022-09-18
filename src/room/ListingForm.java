package room;

import java.sql.SQLException;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 * {@summary Controls and Combines both the QueryBar and RoomListing}
 * Should look like:
 * <QueryBar>
 * <RoomListing>
 */
public class ListingForm extends JPanel {
    public ListingView ui;
    private QueryBar queryBar;
    public ListingForm() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        queryBar = new QueryBar();
        add(queryBar);
        ui = new ListingView();
        add(ui);
        queryBar.checker.addActionListener(event -> {
            try {
                var a = Manager.getManager().fetchAvailable(
                       queryBar.getStay());
                if (a.size() == 0) {
                    JOptionPane.showMessageDialog(getRootPane(),"There are no rooms available during your given dates.");
                }
                ui.updateEntries(a);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(getRootPane(), "Unable to find rooms at this moment. Please try again after some time.");
                e.printStackTrace();
            }
            revalidate();
        });
    }
}