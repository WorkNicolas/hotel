package auth;
import java.util.Optional;
public interface AnAuthenticator<U> {
    public boolean login(U user);
    public void logout();
    public Optional<U> getUser();
}