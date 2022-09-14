package room;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Color;

import javax.naming.MalformedLinkException;
import javax.swing.ImageIcon;

/** 
 * Upgraded version of RawInfo, here the image resource is loaded. When it is unavailable a defualt image is used.
 * @implNote ImageIcon must retrieved from the URL
 * @see Manager
 */
public class Info extends RawInfo {
    public ImageIcon preview;
    protected static ImageIcon favicon = Helper.createImageIcon(Color.GREEN, 100, 100);

    public static void setFavicon(ImageIcon favicon) {
        Info.favicon = favicon;
    }
    public Info(int id, room.Type type, int length, String url, int rate) {
        super(id, type, length, url, rate);
        this.preview = favicon;
        try {
            this.preview = Helper.imageIconFromURL(url);
        } catch (IOException e) {
            //PASS
        } catch (MalformedLinkException e) {
            //PASS
        }
    }

    public Info(RawInfo r) throws IOException {
        this(r.id, r.type, r.size, r.url, r.rate);
    }

    public Info(ResultSet r) throws SQLException {
        super(r);
        try {
            this.preview = favicon;
            this.preview = Helper.imageIconFromURL(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}