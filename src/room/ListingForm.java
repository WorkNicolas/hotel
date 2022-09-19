package room;

import java.sql.SQLException;
import java.util.function.Consumer;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import payment.Discount;
import payment.PaymentPanel;
import payment.Receipt;
import reservation.ContactInfo;
import reservation.Hotelier;
import reservation.Reservation;
import reservation.Stay;

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
    public Dialog dialog;
    private PaymentPanel paymentPanel;
    private Consumer<Reservation> consumer;
    public void setConsumer(Consumer<Reservation> consumer) {
        this.consumer = consumer;
    }

    private Discount[] discounts = new Discount[] {
        new Discount("NONE", 0f),
        new Discount("SENIOR CITIZEN", 0.2f),
        new Discount("PERSON WITH DISABILITY", 0.2f),
        new Discount("5% off VOUCHER", 0.05f),
    };
    private Stay last;
    private ContactInfo tenant;

    public ListingForm() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        queryBar = new QueryBar();
        add(queryBar);
        ui = new ListingView();
        add(ui);
        dialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(this), "Payment portal");
  
        queryBar.checker.addActionListener(event -> {
            try {
                last = queryBar.getStay();
                var a = Manager.getManager().fetchAvailable(last);
                if (a.size() == 0) {
                    JOptionPane.showMessageDialog(getRootPane(),
                            "There are no rooms available during your given dates.");
                }
          
                ui.updateEntries(a);
                for (var b : ui.buttons) {
                    b.addActionListener(this);
                }
            } catch (SQLException e) {
                onFail();
                e.printStackTrace();
            }
            revalidate();
        });
    }
    public void onFail() {
        JOptionPane.showMessageDialog(getRootPane(),
        "Server is unable to process your query. Please try again after some time.");
    }

    public void onSuccess() {
        JOptionPane.showMessageDialog(getRootPane(), "Reservation success!", "Reservation", JOptionPane.INFORMATION_MESSAGE);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (paymentPanel !=null) {
            dialog.remove(paymentPanel);
       }
        RawInfo r = ui.data.get(Integer.valueOf(e.getActionCommand()));
        int span = Hotelier.count(last);
        span = span < 0 ? span * -1: span; //Prevent negative spans
        span = Math.max(1, span + 1); //Make dates inclusive
        paymentPanel = new PaymentPanel(
            r.getRate() * span,
            Receipt.modes,
            discounts);
        paymentPanel.pay.addActionListener(ignore -> {
            if (tenant == null)
                return;
            consumer.accept(
                new Reservation(0, 
                tenant, 
                new Info(r), 
                last,
                paymentPanel.getPayment()
                )
            );
           
        });
        dialog.add(paymentPanel);    
        dialog.pack();
        dialog.setVisible(true);
    }

    public void setTenant(ContactInfo tenant) {
        this.tenant = tenant;
    }
}