package auth;
/**
 * Dataclass for creating new users
 */
public class UserInfo extends ContactInfo {
    protected String phrase;
    public String getPhrase() {
        return phrase;
    }
    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }
    public UserInfo(String name, String email, String phrase) {
        super(name, email);
        this.phrase = phrase;
    }
}