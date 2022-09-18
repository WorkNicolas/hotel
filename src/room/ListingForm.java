package room;

import java.sql.SQLException;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import payment.Discount;
import payment.PaymentPanel;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * {@summary Controls and Combines both the QueryBar and RoomListing}
 * Should look like:
 * <QueryBar>
 * <RoomListing>
 */
public class ListingForm extends JPanel implements ActionListener {
    public ListingView ui;
    private QueryBar queryBar;
    private Dialog dialog;

    public ListingForm() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        queryBar = new QueryBar();
        add(queryBar);
        ui = new ListingView();
        add(ui);
        dialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(this), "Payment portal");
        dialog.add(new PaymentPanel<>(
            100f, //TODO change amount
            new String[] {
                "Test", 
                "Test 1"
            }, 
            new Discount[] {
                new Discount("NONE", 0f),
                new Discount("SENIOR CITIZEN", 0.2f),
                new Discount("PERSON WITH DISABILITY", 0.2f),
                new Discount("VOUCHER", 0.05f),
        }));
        dialog.pack();
        queryBar.checker.addActionListener(event -> {
            try {
                var a = Manager.getManager().fetchAvailable(
                        queryBar.getStay());
                if (a.size() == 0) {
                    JOptionPane.showMessageDialog(getRootPane(),
                            "There are no rooms available during your given dates.");
                }

                ui.updateEntries(a);
                for (var b : ui.buttons) {
                    b.addActionListener(this);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(getRootPane(),
                        "Server is unable to process your query. Please try again after some time.");
                e.printStackTrace();
            }
            revalidate();
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("c");
        dialog.setVisible(true);
    }
}