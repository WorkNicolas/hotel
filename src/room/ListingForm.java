package room;

import java.sql.SQLException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import reservation.DateComponent;
import reservation.Stay;

public class ListingForm extends JPanel {
    private JButton checker;
    private DateComponent start;
    private DateComponent end;
    public RoomListing ui;

    public ListingForm() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(queryBar());
        ui = new RoomListing();
        add(ui);
        checker.addActionListener(event -> {
            try {
                var a = Manager.getManager().fetchAvailable(
                        new Stay(
                            start.getSQLDate(), 
                            end.getSQLDate())
                        );
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

    private JPanel queryBar() {
        var dates = new JPanel();
        var lblStart = new JLabel("Stay period: ");
        var lblEnd = new JLabel(" until ");
        start = new DateComponent();
        end = new DateComponent();
        dates.add(lblStart);
        dates.add(start.ui);
        dates.add(lblEnd);
        dates.add(end.ui);
        checker = new JButton("find");
        dates.add(checker);
        return dates;
    }

    public static void main(String[] args) {
        JFrame j = new JFrame();
        try {
            j.add(
                    new ListingForm());
            j.setExtendedState(JFrame.MAXIMIZED_BOTH);
            j.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
    }
}