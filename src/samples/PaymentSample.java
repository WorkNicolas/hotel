package samples;
import javax.swing.JDialog;

import payment.Discount;
import payment.PaymentPanel;

public class PaymentSample {
    public static void main(String[] args) {
        try {
            var p = new PaymentPanel(
                100f,
                new String[] {
                    "Test", 
                    "Test 1"
                }, 
                new Discount[] {
                    new Discount("NONE", 0f),
                    new Discount("SENIOR CITIZEN", 0.2f),
                    new Discount("PERSON WITH DISABILITY", 0.2f),
                    new Discount("VOUCHER", 0.05f),
                }
            );
            var d = new JDialog();
            d.add(p);
            d.pack();
            d.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
    }
}