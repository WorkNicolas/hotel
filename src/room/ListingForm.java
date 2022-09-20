package room;

import java.util.ArrayList;
import java.util.function.Consumer;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import db.Fetcher;
import payment.Discount;
import payment.PaymentDialog;
import reservation.ContactInfo;
import reservation.Reservation;
import reservation.Stay;

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
    public PaymentDialog dialog;
    private Consumer<Reservation> consumer;
    private Fetcher<Stay, ArrayList<RawInfo>> fetcher;
    private Fetcher<Stay, Integer> counter;
    public void setConsumer(Consumer<Reservation> consumer) {
        this.consumer = consumer;
    }

    public void setFetcher(Fetcher<Stay, ArrayList<RawInfo>> fetcher) {
        this.fetcher = fetcher;
    }

    public void setCounter(Fetcher<Stay, Integer> counter) {
        this.counter = counter;
    }
    public static Discount[] discounts = new Discount[] {
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
        dialog = new PaymentDialog(
            (JFrame) SwingUtilities.getWindowAncestor(this), 
            "Payment portal", 
            discounts);
  
        queryBar.checker.addActionListener(event -> {
            if (fetcher == null)
                return;
            last = queryBar.getStay();
            var a = fetcher.fetch(last);
            if (a.size() == 0) {
                JOptionPane.showMessageDialog(getRootPane(),
                        "There are no rooms available during your given dates.");
            }
        
            ui.updateEntries(a);
            for (var b : ui.buttons) {
                b.addActionListener(this);
            }
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
        if (tenant == null || counter == null || last == null)
            return;
        RawInfo r = ui.data.get(Integer.valueOf(e.getActionCommand()));
        int span = counter.fetch(last);
        var payment = dialog.prompt(r.getRate() * span);
        if (payment == null)
            return;
        consumer.accept(
            new Reservation(0, 
            tenant, 
            new Info(r), 
            last,
            payment
        ));
    }

    public void setTenant(ContactInfo tenant) {
        this.tenant = tenant;
    }
}