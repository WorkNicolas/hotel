/*  
Conditions:
1. room 1 is booked for sep 18-20,
2. room 2 is used for sep14-18, 
3. All other rooms are FREE.
Target 1:
check for rooms that are free between sep15-16 
Target 2:
Both input dates are at least today's date.
Expected:
Result should contain all rooms except room 2
*/
SELECT * FROM rooms WHERE id NOT IN 
(SELECT room_id FROM reservations WHERE 
    ("2022-09-23" BETWEEN
    start AND end) OR ("2022-09-28" BETWEEN start AND end)
) LIMIT 3;