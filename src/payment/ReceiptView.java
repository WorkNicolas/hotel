package payment;

import java.sql.SQLException;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import service.Supplier;

/**
 * @implNote Price/Amount sorting is incorrect as the objects are sroted by String.
 */
public class ReceiptView extends JPanel {
    public Receipt r;
    public JLabel lblTotal;
    public JLabel lblDiscounted;
    public static final String[] columns = new String[] {
        "Name", "Price", "Amount", "Category"
    };
    public ReceiptView(Receipt r) {
        this.r = r;
        var count = r.amenities.size();
        Object[][] data = new Object[count][columns.length];
        {
            var iterator = r.amenities.values().iterator();
            int i = 0;
            while(iterator.hasNext())  {
                var a = iterator.next();
                data[i][0] = a.getName();
                data[i][1] = a.getPrice();
                data[i][2] = a.getAmount();
                data[i][3] = a.getType().toString();
                i++;
            }
        }
        JTable table = new JTable(data, columns);
        table.setEnabled(false);
        table.setAutoCreateRowSorter(true);
        JScrollPane scrollpane = new JScrollPane(table);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(scrollpane);
        lblTotal = new JLabel("Total: " + r.getTotal());
        lblDiscounted = new JLabel("Discounted Total: " + r.getDiscountedTotal());
        add(lblTotal);
        add(lblDiscounted);
    }

    public static void main(String[] args) throws SQLException {
        var r = new Receipt( Supplier.fetchAvailable());
        ReceiptView p = new ReceiptView(r);
        JFrame f = new JFrame();
        f.add(p);
        f.pack();
        f.setVisible(true);
    }
}