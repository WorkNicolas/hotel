package reservation;

import javax.swing.JLabel;

import payment.ReceiptView;

public class ReservationView extends ReceiptView {

    public ReservationView(Reservation r) {
        super(r);
        JLabel lblStay = new JLabel("Stay period: " + r.getStay().toString());
        add(lblStay);
    }
}
