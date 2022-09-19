package room;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import reservation.DateComponent;
import reservation.Stay;

/**
 * should look like:
 * <label><input:date><label><input:date><button>
 */
public class QueryBar extends JPanel {
    public DateComponent start;
    public DateComponent end;
    public JButton checker;

    public QueryBar() {
        var lblStart = new JLabel("Stay period: ");
        var lblEnd = new JLabel(" until ");
        start = new DateComponent();
        end = new DateComponent();
        lblStart.setFont(start.ui.getFont());
        lblEnd.setFont(start.ui.getFont());
        add(lblStart);
        add(start.ui);
        add(lblEnd);
        add(end.ui);
        checker = new JButton("find");
        add(checker);
    }

    public Stay getStay() {
        return new Stay(
            start.getSQLDate(), 
            end.getSQLDate());
    }
}
