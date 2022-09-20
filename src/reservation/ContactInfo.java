package reservation;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * @author JCSJ
 */
public class ContactInfo {
    protected String name;
    protected String address;
    protected int id;
    public ContactInfo(String name, String address, int id, String contact, String email) {
        this.name = name;
        this.address = address;
        this.id = id;
        this.contact = contact;
        this.email = email;
    }

    /**
     * @apiNote For registration
     */
    public ContactInfo(String name, String address, String contact, String email) {
        this(name, address, 0, contact, email);
    }

    public ContactInfo(ResultSet r) throws SQLException {
        this(
            r.getString("name"), 
            r.getString("address"), 
            r.getInt("id"), 
            r.getString("contact"), 
            r.getString("email"));
    }

    protected String contact;
    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    protected String email;
 
}