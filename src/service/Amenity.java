package service;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Amenity {
    protected final int id;
    protected final String name;
    protected final Type type;
    protected final float price;
    protected int supply;
    protected int amount;
    protected float discount_rate;
    public float getDiscount_rate() {
        return discount_rate;
    }

    public void setDiscount_rate(float discount_rate) {
        this.discount_rate = discount_rate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Type getType() {
        return type;
    }

    public Amenity(ResultSet rs) throws SQLException {
        this(
            rs.getInt("id"),
            rs.getString("name"),
            Type.valueOf(rs.getString("type")),
            0,
            rs.getInt("price"),
            rs.getInt("supply"), 
            0
        );
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public float getDiscountedPrice() {
        return price - (price * discount_rate);
    }
    public void setSupply(int supply) {
        this.supply = supply;
    }

    public Amenity(int id, String name, float price, int supply, Type type) {
        this(id, name, type, 0, price, supply, 0);
    }

    public Amenity(int id, String name, Type type, int amount, float price, int supply, float discount_rate) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.amount = amount;
        this.price = price;
        this.supply = supply;
        this.discount_rate = discount_rate;
    }

    /**
     * @return whether the suply changed.
     */
    public boolean reduce(int n) {
        if ((supply - n) < 0)
            return false;
        supply -= n;
        amount += n;
        return true;
    }

    /**
     * @return whether the suply changed.
     */
    public boolean resupply(int n) {
        if (n <0 || n > amount) {
            return false;
        }
        supply += n;
        amount -= n;
        return true;
    }

    public int getSupply() {
        return supply;
    }

    public String toString() {
        return name;
    }

    public String hash() {
        return id + "" + getDiscount_rate();
    }

}