package db;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
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

    /**
     * @apiNote SHOULD NORMALLY be used for creating tables.
     */
    protected static boolean executeSQLFile(String path) throws SQLException, FileNotFoundException {
        var conn = connect();
        var s = new Scanner(new File(path));
        String sql = "";
        while(s.hasNextLine()) {
            sql += s.nextLine();
        }
        Statement st = conn.createStatement();
        s.close();
        var r = st.execute(sql);
        conn.close();
        return r;
    }

    /**
     * @apiNote For table creation
     */
    protected static boolean executeSQL(String sql) throws SQLException {
        var conn = connect();
        var s = conn.createStatement();
        var r = s.execute(sql);
        conn.close();
        return r;
    }
}