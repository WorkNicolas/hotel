/** 
 * Notice: Do not import com.mysql.jdbc.*
 */
public class LoadDriver {
    public static void main(String[] args) {
        try {
            /* The newInstance() call is a work around for some broken Java implementations*/
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}