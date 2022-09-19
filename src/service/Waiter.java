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
            var insert = conn.prepareStatement("INSERT INTO " + TABLE_NAME + "(amount, item_id, reservation_id) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);
            var reduce = conn.prepareStatement("UPDATE " + Supplier.TABLE_NAME + " SET supply = ? WHERE id = ?");
            for (var item:receipt.amenities.values()) {
                if (item.amount <= 0)
                    continue;
                insert.setFloat(1, item.amount);
                insert.setInt(2, item.id);
                insert.setInt(3, reservation_id);

                //UPDATE supply
                reduce.setInt(1,item.supply);
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

    public static Receipt getReceipt(int reservation_id) throws SQLException {
        var conn = connect();
        //SELECT items that have been bought by reservation_id
        var amenityQuery =conn.prepareStatement("SELECT * FROM " + Supplier.TABLE_NAME + " WHERE id IN (SELECT item_id FROM " + TABLE_NAME + " WHERE reservation_id = ?)");
        amenityQuery.setInt(1, reservation_id);
        var amenitiesResult = amenityQuery.executeQuery();
        var amenities = Supplier.asAmenities(amenitiesResult);
        //SELECT item_id and amount
        var orderQuery = conn.prepareStatement("SELECT item_id, amount FROM " + TABLE_NAME + " WHERE reservation_id = ?");
        orderQuery.setInt(1, reservation_id);
        var orderResults = orderQuery.executeQuery();
        int i = 0;
        while(orderResults.next()) {
            //Update the amount
            amenities.get(i).setAmount(orderResults.getInt("amount"));
            i++;
        }
        conn.close();
        return new Receipt(amenities);
    }
}