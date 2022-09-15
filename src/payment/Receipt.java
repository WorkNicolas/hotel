package payment;

import java.util.ArrayList;

import service.Amenity;

public class Receipt {
    protected ArrayList<Amenity> amenities = new ArrayList<>();
    protected static final String[] modes = {"PAY ON ARRIVAL", "GCASH", "MAYA", "PHIL NATIONAL BANK", "UNION BANK OF THE PHILS", "CITIBANK", "BANK OF THE PHIL ISLANDS", "BPI", "METROBANK", "HSBC",  "CHINABANK", "Bank OF AMERICA"};

    public static String[] getModes() {
        return modes;
    }
}