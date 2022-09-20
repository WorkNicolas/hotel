package samples;
import payment.Discount;
import payment.Payment;
import payment.PaymentDialog;
/**
 * @author JCSJ
 */
public class PaymentSample {
    public static void main(String[] args) {
        var j = new PaymentDialog(null, "Test",  new Discount[] {
            new Discount("NONE", 0f),
            new Discount("SENIOR CITIZEN", 0.2f),
            new Discount("PERSON WITH DISABILITY", 0.2f),
            new Discount("5% off VOUCHER", 0.05f),
        });
        Payment result = j.prompt(100f);

        if (result == null) {
            System.out.println("operation cancelled");
        } else {
            System.out.println("you paid " + result.getAmount());
        }
    }
}