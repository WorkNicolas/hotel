import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Optional;
import java.util.Scanner;

/**
 * @author Jean Carlo M. San Juan
 * @summary A singleton that handles user verification.
 */
public class Authenticator implements AnAuthenticator<User>
{
    HashMap <String, String> credentials = new HashMap <String, String>();
    {
        credentials.put("Carl", "CNM");
        credentials.put("Charimel", "CM");
        credentials.put("Ralph", "RS");
        credentials.put("Jean", "JCSJ");
        credentials.put("Roxanne", "RB");
    };
    private final String database_path = ".\\account.db";
    private User user = null;
    private final String seperator = "   ";
    public static final Authenticator instance = new Authenticator();

    private Authenticator()
    {
        getAccountsFromDB();
    }

    private void getAccountsFromDB() {
        File f = new File(database_path);
        Scanner sc;
        try {
            sc = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("invalid file: " + database_path);
            return;
        }
        String line;
        do {
            try {
                line = sc.nextLine();
            } catch (Exception e) {
                break;
            }
            String[] parts = line.split(seperator);
            if (parts.length != 2) 
                continue;

            credentials.put(parts[0], parts[1]);
        } while(line != null);
        
        sc.close();
    }

    public boolean login(User u) {
        final String correctPhrase = credentials.get(u.name());
        boolean verified = correctPhrase != null && u.phrase().compareTo(correctPhrase) == 0;
    
        if (verified) {
            this.user = u;
        }

        return verified;
    }

    public void logout() {
        this.user = null;
    }
    public Optional<User> getUser() {
        return Optional.ofNullable(this.user);
    }
}