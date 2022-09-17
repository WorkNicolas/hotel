package payment;

import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import service.Supplier;

public class ReceiptView extends JPanel {
    public Receipt r;
    public static final String[] columns = new String[] {
        "Name", "Price", "Amount", "Category"
    };
    public ReceiptView(Receipt r) {
        this.r = r;
        var count = r.amenities.size();
        String[][] data = new String[count][columns.length];
        {
            var iterator = r.amenities.values().iterator();
            int i = 0;
            while(iterator.hasNext())  {
                var a = iterator.next();
                data[i][0] = a.getName();
                data[i][1] = "" + a.getPrice();
                data[i][2] = "" + a.getAmount();
                data[i][3] = a.getType().toString();
                i++;
            }
        }
        JTable table = new JTable(data, columns);
        table.setAutoCreateRowSorter(true);
        table.setEnabled(false);
        JScrollPane scrollpane = new JScrollPane(table);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(scrollpane);
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