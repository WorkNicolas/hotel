import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    protected static final String DB_HOST = "localhost";
    protected static final String DB_USER = "hotel-manager";
    protected static final String DB_PASS = "hotel-manager";
    protected static final String DB_NAME = "hotel";
    /* 
     * Create a connection using the credentials
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