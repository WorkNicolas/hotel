package payment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import service.Amenity;
/**
* {@link https://www.dti.gov.ph/archives/news-archives/dti-urges-e-commerce-platforms-to-adopt-online-discount-guidelines/} 
 */
public class Receipt {
    public static final String[] modes = {"PAY ON ARRIVAL", "GCASH", "MAYA", "PHIL NATIONAL BANK", "UNION BANK OF THE PHILS", "CITIBANK", "BANK OF THE PHIL ISLANDS", "BPI", "METROBANK", "HSBC",  "CHINABANK", "Bank OF AMERICA"};
    protected String discountKey;
    protected static float DEFAULT_DISCOUNT = 0f;
    public String getDiscountKey() {
        return discountKey;
    }

    public void setDiscountKey(String discountKey) {
        this.discountKey = discountKey;
    }

    public static final HashMap<String, Float> discountMap = new HashMap<String, Float>();
    {
        //TODO: if there's time: move these to a table
        discountMap.put("MEMBER", 0.1f);
        discountMap.put("SENIOR CITIZEN", 0.2f);
        discountMap.put("PERSON WITH DISABILITY", 0.2f);
    }
    public HashMap<String, Amenity> amenities = new HashMap<String, Amenity>();
    public Receipt() {
        this("");
    }

    public Receipt(ArrayList<Amenity> items) {
        this();
        items.forEach(n -> put(n));
    }

    public Receipt(String discountKey) {
        this.discountKey = discountKey;
    }

    public float getTotal() {
        float total = 0;
        for (var a:amenities.values()) {
            total += a.getPrice() * a.getAmount();
        }
        return total;
    }

    /**
     * Discount = Total(T) * Percentage (P)
     * where P is taken from dscountMap
     * @implNote P should already be in its decimal form (E.g - P should be 0.1 for a 10% discount)
     * @return discounted total
     */
    public float getDiscountedTotal() {
        float percentage = Optional
            .ofNullable(discountMap.get(discountKey))
            .orElse(DEFAULT_DISCOUNT);
            
        return getTotal() * percentage;
    }

    public boolean put(Amenity a) {
        if (amenities.containsValue(a)) {
            return false;
        } 
        amenities.put(a.getName(), a);
        return true;
    }

    public boolean putOrAddAmount(Amenity a, int n) {
        put(a);
        return a.reduce(n);
    }
    public boolean reduceOrRemove(Amenity a, int n) {
        if (amenities.containsValue(a)) {
            return a.resupply(n);
        }

        if (a.getAmount() <= 0) {
            amenities.remove(a.getName());
        }

        return false;
    }
    /**
     * @implNote removed's amount is added to the supply field.
     * @return whether it was removed
     */
    public boolean remove(String key) {
        if (!amenities.containsKey(key))
            return false;

        var removed = amenities.remove(key);
        removed.resupply(removed.getAmount());
        return true;
    }

    public boolean remove(Amenity a)  {
        return remove(a.getName());
    }
}