package news;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import db.Connector;
import db.Fetcher;
/**
 * @author JCSJ
 */
public class NewsController extends Connector  implements Fetcher<Integer, ArrayList<News>>{
    public static final String TABLE_NAME = "news";
    public NewsController() {
        
    }

    /**
     * @return n > 0, the new record's id. Otherwise, it failed.
     */
    public int publish(News news) throws SQLException {
        var conn = connect();
        var s = conn.prepareStatement("INSERT INTO " + TABLE_NAME + " (title, content) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);
        s.setString(1, news.text);
        s.setString(2, news.title);
        s.executeUpdate();
        var result =s.getGeneratedKeys();
        int new_id = 0;
        if (result.next()) {
            new_id = result.getInt(1);
        }
        conn.close();
        return new_id;
    }

    @Override
    public ArrayList<News> fetch(Integer limit) {
        var news = new ArrayList<News>();
        try {
            var conn = connect();
            var s = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " LIMIT ?");
            s.setInt(1, limit);
            var r = s.executeQuery();
            while(r.next()) {
                news.add(
                    asNews(r)
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return news;
    }

    public static News asNews(ResultSet r) throws SQLException {
        return new News(r.getInt("id"), r.getString("title"), r.getString("content"));
    }
}