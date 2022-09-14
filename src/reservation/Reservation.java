package reservation;
import auth.ContactInfo;
import payment.Receipt;
import room.Info;

/**
 * Matches the reservations table
 * @implNote Queried record contains two FOREIGN KEYS:
 * 1. a room_id for RoomInfo
 * 2. a tenant_id for ContactInfo
 */
public class Reservation extends Receipt {
    protected int id;
    protected ContactInfo tenant;
    protected Info room;
    protected Stay stay;
    //TODO receipt
    public Reservation() {
        
    }
    public Reservation(int id, ContactInfo tenant, Info room, Stay stay) {
        this.id = id;
        this.tenant = tenant;
        this.room = room;
        this.stay = stay;
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
}