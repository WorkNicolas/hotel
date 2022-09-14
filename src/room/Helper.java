package room;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.naming.MalformedLinkException;
import javax.swing.ImageIcon;

public class Helper {
    public static ImageIcon createImageIcon(Color color, int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setPaint(color);
        graphics.fillRect (0, 0, width, height);
        return new ImageIcon(image);
    }

    public static ImageIcon imageIconFromURL(String urlStr) throws IOException, MalformedLinkException {
        return new ImageIcon(
                new URL(urlStr));
    }
}