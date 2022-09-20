CREATE TABLE IF NOT EXISTS orders(
    id int PRIMARY KEY AUTO_INCREMENT,
    reservation_id int NOT NULL,
    item_id int NOT NULL,
    payment_id int NOT NULL,
    amount int NOT NULL,
    Foreign Key (reservation_id) REFERENCES reservations(id),
    Foreign Key (item_Id) REFERENCES amenities(id),
    Foreign Key (payment_id) REFERENCES payments(id)
);