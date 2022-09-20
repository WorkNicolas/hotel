/* GET all amenities columns. Include order's amount and the discounted payment */
SELECT 
    amenities.*, 
    orders.amount, 
    payments.discount 
FROM orders 
JOIN payments ON orders.payment_id = payments.id 
JOIN amenities ON orders.item_id = amenities.id
WHERE orders.reservation_id = 26