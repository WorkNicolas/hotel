package payment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;

import javax.swing.*;

/**
 * Creates an InputDialog with a listener attached
 */
public class PaymentComponent implements ActionListener {
    protected JFrame parent;
    protected String portalName;
    protected Consumer<String> onSuccess;
    /**
     * @param parent
     * @param onSuccess - Receives the account name entered by the user
     */
    public PaymentComponent(JFrame parent, Consumer<String> onSuccess) {
        this.parent = parent;
        portalName = "";
        this.onSuccess = onSuccess;
    }

    public void setPortalName(String portalName) {
        this.portalName = portalName;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String accountName = "";
        try {
            accountName = JOptionPane.showInputDialog(
                parent, 
                "Amount: TODO\nEnter account:", 
                "Payment Portal: " + portalName, 
                JOptionPane.QUESTION_MESSAGE);
        } catch (Exception ex) {
            return; //PASS
        }
        onSuccess.accept(accountName);
    }
}