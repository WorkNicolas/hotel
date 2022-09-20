package reservation;
import payment.Payment;
import room.Info;
/**
 * @author JCSJ
 * Matches the reservations table
 * @implNote Queried record contains two FOREIGN KEYS:
 * 1. a room_id for RoomInfo
 * 2. a tenant_id for ContactInfo
 */
public class Reservation {
    protected int id;
    public ContactInfo tenant;
    public Info room;
    public Stay stay;
    public Payment payment;
    public Reservation(int id, ContactInfo tenant, Info room, Stay stay, Payment p) {
        this.id = id;
        this.tenant = tenant;
        this.room = room;
        this.stay = stay;
        this.payment = p;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public ContactInfo getTenant() {
        return tenant;
    }
    public void setTenant(ContactInfo tenant) {
        this.tenant = tenant;
    }
    public Info getRoom() {
        return room;
    }
    public void setRoom(Info room) {
        this.room = room;
    }
    public Stay getStay() {
        return stay;
    }
    public void setStay(Stay stay) {
        this.stay = stay;
    }

    public String toString() {
        return "" + id;
    }
}