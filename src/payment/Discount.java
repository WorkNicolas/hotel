package payment;

/**
 * @author JCSJ
 * @implNote
 * <b>rate</b> is treated as the decimal form of a percentage.
 * so: <b>10%</b> should be <b>0.1f</b>
 */
public class Discount {
    protected String name;
    protected float rate;

    public Discount(String name, float rate ) {
        this.name = name;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    /**
     * @Returns Remaining value when <b>rate</b> is applied.
     */
    public float deduct(float n) {
        return n - (n * rate);
    }

    /**
     * @implNote Shows the name
     */
    @Override
    public String toString() {
        return name;
    }
}