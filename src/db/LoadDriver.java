package db;
/** 
 * Notice: Do not import com.mysql.jdbc.*
 * @implNote "download and extract the jar file from https://dev.mysql.com/downloads/connector/j/8.0.html" into the lib folder.
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