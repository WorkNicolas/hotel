package payment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;
import java.util.function.Consumer;

import javax.swing.*;

/**
 * Creates an InputDialog with a listener attached.
 * @apiNote a callback `onSuccess` receives the account name.
 */
public class PaymentComponent implements ActionListener {
    protected JFrame parent;
    protected String portalName;
    public String getPortalName() {
        return portalName;
    }

    public void setPortalName(String portalName) {
        this.portalName = portalName;
    }

    protected float amount;
    public float getAmount() {
        return amount;
    }
    public void setAmount(float amount) {
        this.amount = amount;
    }

    protected Consumer<String> consumer;
    public Consumer<String> getConsumer() {
        return consumer;
    }
    public void setConsumer(Consumer<String> consumer) {
        this.consumer = consumer;
    }
    /**
     * @param parent
     * @param consumer receives the valid account name entered by the user.
     */
    public PaymentComponent(JFrame parent, Consumer<String> consumer) {
        this(parent, consumer, 0);
    }

    public PaymentComponent(JFrame parent, Consumer<String> consumer, float amount) {
        this.parent = parent;
        this.amount = amount;
        this.consumer = consumer;
        this.portalName = "";
    }

    public PaymentComponent(JFrame parent, Consumer<String> consumer, float amount, String portaleName) {
        this(parent, consumer, amount);
        this.portalName = portaleName;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Optional<String> maybeAccountName = Optional.ofNullable(null);
            maybeAccountName = Optional.ofNullable(
            JOptionPane.showInputDialog(
                parent, 
                "Amount: TODO\nEnter account:", 
                "Payment Portal: " + portalName, 
                JOptionPane.QUESTION_MESSAGE));
        maybeAccountName.ifPresent(consumer);
    }
}