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
public class PaymentPanel<E> extends Box {
    private JComboBox<E> selectMode;
    private JComboBox<Discount> selectDiscount;
    private Font font = new Font("Consolas", Font.PLAIN, 24);
    public PaymentPanel(float amount, E[] modes, Discount[] discounts) {
        super(BoxLayout.Y_AXIS);
        setBorder(new EmptyBorder(5, 5, 5, 5));
        JLabel lblAmount = new JLabel("Amount: " + amount);
        JLabel lblMethod = new JLabel("Payment method: ", JLabel.LEADING);
        selectMode = new JComboBox<E>(modes);
        JLabel lblAccount = new JLabel("Account ID: ", JLabel.LEADING);
        JTextField inputAccount = new JTextField();
        var lblDiscounts = new JLabel("Additional discounts: ", JLabel.LEADING);
        selectDiscount =  new JComboBox<Discount>(discounts);
        var lblDiscount = new JLabel("Enter ID/CODE:");
        var lblDiscountAmount = new JLabel("Discount: ");
        JTextField inputDiscount = new JTextField();
        var pay = new JButton("Pay");
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

        }); 

        selectDiscount.addItemListener(e -> {
            Discount d = (Discount) e.getItem();
            setLabel(lblAmount, "Amount: ", amount);
            setLabel(lblDiscountAmount, "Discounted: ", d.deduct(amount));
        });
    }

    public void setLabel(JLabel l, String prefix, Object extra) {
        l.setText(prefix + extra);
    }
}