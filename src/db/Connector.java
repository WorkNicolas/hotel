package db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Helper class for communicating with the assigned DB
 */
public class Connector {
    protected static final String DB_HOST = "localhost";
    protected static final String DB_USER = "hotel-manager";
    protected static final String DB_PASS = "hotel-manager";
    protected static final String DB_NAME = "hotel";
    /** 
     * {@summary Creates a connection using the Connector's credentials} 
     */
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(
            "jdbc:mysql://" 
            + DB_HOST + "/" 
            +DB_NAME, 
            DB_USER, DB_PASS
        );
    }
}