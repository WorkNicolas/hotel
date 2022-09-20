package db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * Helper class for communicating with the assigned DB
 * @author JCSJ
 */
public class Connector {
    protected static final String DB_HOST = System.getenv("DB_HOST");
    protected static final String DB_USER = System.getenv("DB_USER");
    protected static final String DB_PASS = System.getenv("DB_PASS");
    protected static final String DB_NAME = System.getenv("DB_NAME");
    protected static final String DB_URL = "jdbc:mysql://" + DB_HOST + "/" + DB_NAME;
    {
        DriverManager.setLoginTimeout(5);
    }
    /** 
     * {@summary Creates a connection using the Connector's credentials} 
     */
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(
            DB_URL,
            DB_USER, 
            DB_PASS
        );
    }
}