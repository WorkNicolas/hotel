package payment;

public class Amenity {
    protected final int id;
    protected final String name;
    protected final float price;
    protected int supply;

    public Amenity(int id, String name, float price, int supply) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.supply = supply;
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
}