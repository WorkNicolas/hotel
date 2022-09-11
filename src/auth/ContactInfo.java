package auth;
public class ContactInfo {
    protected String name;
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
    public ContactInfo(String name, String email) {
        this.name = name;
        this.email = email;
    }
}