package payment;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * @author JCSJ
 */
public class Payment {
    protected int id;

    /**
     * @apiNote Template for new payments.
     */
    public Payment(float amount, String method, String account, float discount_rate) {
        this(0, amount, method, account, discount_rate);
    }

    /**
     * @apiNote Template for records from db.
     */
    public Payment(int id, float amount, String method, String account, float discount_rate) {
        this.id = id;
        this.amount = amount;
        this.method = method;
        this.account = account;
        this.discount_rate = discount_rate;
    }

    public Payment(ResultSet s) throws SQLException {
        this(s.getInt("id"), 
        s.getFloat("amount"), 
        s.getString("method"), 
        s.getString("account"), 
        s.getFloat("discount"));
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    protected float amount;
    protected String method;
    protected String account;
    protected float discount_rate;
    public float getAmount() {
        return amount;
    }
    public void setAmount(float amount) {
        this.amount = amount;
    }
    public String getMethod() {
        return method;
    }
    public void setMethod(String method) {
        this.method = method;
    }
    public String getAccount() {
        return account;
    }
    public void setAccount(String account) {
        this.account = account;
    }
    public float getDiscount_rate() {
        return discount_rate;
    }
    public void setDiscount_rate(float discount_rate) {
        this.discount_rate = discount_rate;
    }
}