import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author Jean Carlo M. San Juan
 * @summary A singleton that handles user verification.
 */
public class AccountController
{
    HashMap <String, String> credentials = new HashMap <String, String>();
    private final String database_path = ".\\account.db";
    private String user = "";
    private final String seperator = "   ";
    public static final AccountController instance = new AccountController();

    private AccountController()
    {
        initDefaults();
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
    private void initDefaults() {
        credentials.put("Carl", "CNM");
        credentials.put("Charimel", "CM");
        credentials.put("Ralph", "RS");
        credentials.put("Jean", "JCSJ");
        credentials.put("Roxanne", "RB");
    }

    public boolean login(String name, String phrase) {
        final String correctPhrase = credentials.get(name);
        boolean verified = correctPhrase != null && phrase.compareTo(correctPhrase) == 0;
    
        if (verified) {
            this.user = name;
        }

        return verified;
    }

    public void logout() {
        this.user = "";
    }
    public String getUser() {
        return this.user;
    }
}