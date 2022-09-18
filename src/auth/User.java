package auth;

/**
 * {@summary For user authorization}
 * @apiNote When id is <= 0, it is invalid.
 * @see Registrar
 * @see Verifier
 */
public class User {
    protected final String email;
    protected final String phrase;
    protected final int id;
    public String getEmail() {
        return email;
    }
    public String getPhrase() {
        return phrase;
    }
    public int getId() {
        return id;
    }
    public User(String email, String phrase, int id) {
        this.email = email;
        this.phrase = phrase;
        this.id = id;
    }
    public User(String email, String phrase) {
        this.email = email;
        this.phrase = phrase;
        this.id = 0;
    }

    public User(UserInfo u) {
        this.email = u.getEmail();
        this.phrase = u.phrase;
        this.id = 0;
    }
}