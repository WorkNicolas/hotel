package service;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import db.Connector;
import payment.Payment;
import payment.Receipt;
import reservation.Hotelier;

public class Waiter extends Connector {
    public static String TABLE_NAME = "orders";
    public static String TABLE_PAYMENT = "payments";

    /**
     * {@summary Adds all bought amenities and updates the available supply in the db}
     * @return IDs of the created records
     */
    public static ArrayList<Integer> order(Payment p, Receipt receipt, int reservation_id) {
        ArrayList<Integer> results = new ArrayList<>();
        int payment_id = Hotelier.pay(p);
        if (payment_id == 0)
            return results;
        try {
            var conn = connect();
            var insert = conn.prepareStatement("INSERT INTO " + TABLE_NAME + "(amount, item_id, reservation_id, payment_id) VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            var reduce = conn.prepareStatement("UPDATE " + Supplier.TABLE_NAME + " SET supply = supply - ? WHERE id = ?");
            for (var item:receipt.amenities.values()) {
                if (item.amount <= 0)
                    continue;
                insert.setFloat(1, item.amount);
                insert.setInt(2, item.id);
                insert.setInt(3, reservation_id);
                insert.setInt(4, payment_id);

                //UPDATE supply
                reduce.setInt(1,item.amount);
                reduce.setInt(2, item.id);
                reduce.addBatch();
                insert.addBatch();
            }
            reduce.executeBatch();
            insert.executeBatch();
            var rs = insert.getGeneratedKeys();
            while(rs.next()) {
                results.add(rs.getInt(1));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    /* GET all amenities columns. Include order's amount and the discounted payment */
    public static Receipt fetchReceipt(int reservation_id) throws SQLException {
        var conn = connect();
        var s = conn.prepareStatement(
            "SELECT amenities.*, orders.amount, payments.discount FROM orders JOIN payments ON orders.payment_id = payments.id JOIN amenities ON orders.item_id = amenities.id WHERE orders.reservation_id = ?");
            s.setInt(1, reservation_id);
        var r = s.executeQuery();
        var receipt = new Receipt();
        while(r.next()) {
            var a = new Amenity(r);
            a.setDiscount_rate(r.getFloat("discount"));
            receipt.putOrAddAmountWithoutReduce(a, r.getInt("amount"));
        }
        conn.close();
        return receipt;
    }
}