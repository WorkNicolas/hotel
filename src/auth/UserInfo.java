package auth;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import reservation.ContactInfo;
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
    public UserInfo(String name, String email, String address, String contact, String phrase) {
        super(name, address, contact, email);
        this.phrase = hash(phrase);
    }

    /**
     * {@link https://www.baeldung.com/sha-256-hashing-java}
     */
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static String hash(String s) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashed = digest.digest(
                s.getBytes(StandardCharsets.UTF_8));
                return bytesToHex(hashed);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return s;
        }
    }
}