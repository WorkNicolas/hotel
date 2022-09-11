/**
 * Dataclass for creating new users
 */
public class UserInfo extends ContactInfo {
    protected String phrase;
    public UserInfo(String name, String email, String phrase) {
        super(name, email);
        this.phrase = phrase;
    }
}