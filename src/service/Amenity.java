package service;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Amenity {
    protected final int id;
    protected final String name;
    protected final Type type;
    public Amenity(ResultSet rs) throws SQLException {
        this.id = rs.getInt("id");
        this.name = rs.getString("name");
        this.supply = rs.getInt("supply");
        this.type = Type.valueOf(rs.getString("type"));
        this.price = rs.getInt("price");
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

    public void setSupply(int supply) {
        this.supply = supply;
    }

    protected final float price;
    protected int supply;

    public Amenity(int id, String name, float price, int supply, Type type) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.supply = supply;
        this.type = type;
    }

    public boolean reduce(int amount) {
        if ((supply - amount) < 0)
            return false;
        supply -= amount;
        return true;
    }

    public int getSupply() {
        return supply;
    }

    public String toString() {
        return name;
    }

}