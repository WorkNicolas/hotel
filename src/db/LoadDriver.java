package db;
/** 
 * Notice: Do not import com.mysql.jdbc.*
 */
public class LoadDriver {
    {
        try {
            /* The newInstance() call is a work around for some broken Java implementations*/
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}