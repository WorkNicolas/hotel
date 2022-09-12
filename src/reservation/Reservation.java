package reservation;
import auth.ContactInfo;
import room.Info;

/**
 * Matches the reservations table
 * @implNote Queried record contains two FOREIGN KEYS:
 * 1. a room_id for RoomInfo
 * 2. a tenant_id for ContactInfo
 */
public class Reservation {
    protected String id;
    protected ContactInfo tenant;
    protected Info room;
    protected Stay stay;

    //TODO: BODY
}