package payment;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class PaymentDialog extends JDialog {
    private PaymentPanel panel;
    private Payment result;
    public PaymentDialog(JFrame owner, String title, Discount[] discounts) {
        super(owner, title);
        setModal(true);
        panel = new PaymentPanel(0, Receipt.modes, discounts);
        panel.pay.addActionListener(e -> {
            result = panel.getPayment();
            setVisible(false);
        });
        add(panel);
    }

    /**
     * Awaits user input
     */
    public Payment prompt(float amount) {
        panel.setAmount(amount);
        pack();
        setVisible(true);
        return result;
    }
    public static void main(String[] args) {
        var j = new PaymentDialog(null, "Test",  new Discount[] {
            new Discount("NONE", 0f),
            new Discount("SENIOR CITIZEN", 0.2f),
            new Discount("PERSON WITH DISABILITY", 0.2f),
            new Discount("5% off VOUCHER", 0.05f),
        });

        System.out.println(
            j.prompt(100f).amount
        );
    }
}