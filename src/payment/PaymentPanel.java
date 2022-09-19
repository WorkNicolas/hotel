package payment;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.awt.Dimension;
import java.awt.Font;

/**
 * @author San Juan, Jean Carlo
 * @implNote
 * Looks like:
 * <code>
 * <amount>
 * <label method>
 * <select>
 * <label id>
 * <input>
 * <button>
 * </code>
 */
public class PaymentPanel extends Box {
    public JComboBox<String> selectMode;
    public JComboBox<Discount> selectDiscount;
    private Font font = new Font("Consolas", Font.PLAIN, 24);
    public JButton pay;
    private JTextField inputAccount;
    private float amount;
    public void setAmount(float amount) {
        this.amount = amount;
        repaint();
    }

    public Discount discount;
    private String method = "";
    private JLabel lblAmount = new JLabel();
    private JLabel lblDiscountAmount = new JLabel();

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public float getAmount() {
        return amount;
    }

    public PaymentPanel(float amount, String[] modes, Discount[] discounts) {
        super(BoxLayout.Y_AXIS);
        this.amount = amount;
        if (discounts.length > 0) {
            discount = discounts[0];
        }

        setBorder(new EmptyBorder(5, 5, 5, 5));
        JLabel lblMethod = new JLabel("Payment method: ", JLabel.LEADING);
        selectMode = new JComboBox<String>(modes);
        JLabel lblAccount = new JLabel("Account ID: ", JLabel.LEADING);
        inputAccount = new JTextField();
        var lblDiscounts = new JLabel("Additional discounts: ", JLabel.LEADING);
        selectDiscount =  new JComboBox<Discount>(discounts);
        var lblDiscount = new JLabel("Enter ID/CODE:"); //SKIP THIS
        JTextField inputDiscount = new JTextField();
        pay = new JButton("Pay");
        pay.setFont(font);
        var row = new JPanel();
        row.add(pay);
      
        for (var child: new JComponent[] {lblAmount, lblDiscountAmount , lblMethod, selectMode, lblAccount, inputAccount,  lblDiscounts, selectDiscount, lblDiscount , inputDiscount, row }) {
            child.setAlignmentX(LEFT_ALIGNMENT);
            child.setFont(font);
            add(Box.createRigidArea(new Dimension(0, 10)));
            add(child);
        }
        
        selectMode.addItemListener(e-> {
            method = e.getItem().toString();
            System.out.println(method);
        }); 

      
        selectDiscount.addItemListener(e -> {
            discount = (Discount) e.getItem();
            repaint();
        });
    }
    public void setLabel(JLabel l, String prefix, Object extra) {
        if (l == null) {
            System.out.println(prefix);
        }
        l.setText(prefix + extra);
    }
    public void repaint() {
        setLabel(lblAmount, "Amount: ", amount);
        setLabel(lblDiscountAmount, "Discounted: ", discount == null ? amount: discount.deduct(amount));
    }
    public Payment getPayment() {
        if (amount <= 0)
            return null;
            
        return new Payment(amount, method, inputAccount.getText(), discount.getRate());
    }
}