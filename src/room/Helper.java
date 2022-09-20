package room;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * @author JCSJ
 */
public class Helper {
    public static ImageIcon createImageIcon(Color color, int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setPaint(color);
        graphics.fillRect (0, 0, width, height);
        return new ImageIcon(image);
    }

    public static ImageIcon imageIconFromURL(String urlStr) throws MalformedURLException {
        return new ImageIcon(
                new URL(urlStr));
    }

       //Resize Image
	public static ImageIcon scale(ImageIcon imageIcon, int width, int height) {
		Image image = imageIcon.getImage();
		//Current Image Size: 200x200
		Image imageModified = image.getScaledInstance(width,height,java.awt.Image.SCALE_SMOOTH);
		return imageIcon = new ImageIcon(imageModified);
	}
    
    public static void main(String[] args) throws MalformedURLException {
        var a = Helper.imageIconFromURL("https://tip.edu.ph/assets/headerfooter/tip-logo.png");
        JOptionPane.showMessageDialog(null, "Hello, world!", "Image test", JOptionPane.INFORMATION_MESSAGE, a);
    }
}