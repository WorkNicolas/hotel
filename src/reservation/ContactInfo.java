package reservation;

public class ContactInfo {
    protected String name;
    protected String address;
    protected int id;
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
    public ContactInfo(String name, String email, String address) {
        this(0, name, address, email);
    }
    public ContactInfo(int id, String name, String address, String email) {
        this.name = name;
        this.address = address;
        this.id = id;
        this.email = email;
    }
}