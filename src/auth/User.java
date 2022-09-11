package auth;

/**
 * {@summary For user authorization}
 * @see Registrar
 * @see Verifier
 */
public record User(String email, String phrase) {

}