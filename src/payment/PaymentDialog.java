package payment;
import javax.swing.JDialog;
import javax.swing.JFrame;
/**
 * @author JCSJ
 */
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
}