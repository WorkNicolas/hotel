package Style;
import java.awt.Color;

import javax.swing.UIManager;

public class Style {
    public final static Color SlateGray = Color.decode("#738290");
    public final static Color BabyPowder = Color.decode("#fffcf7");
    public final static Color SpaceCadet = Color.decode("#201e50");

    public static void init() {
        UIManager.put("Panel.background", BabyPowder);
        UIManager.put("Button.background", SlateGray);
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Frame.background", BabyPowder);
        UIManager.put("MenuItem.background", Color.WHITE);
        UIManager.put("MenuBar.background", SpaceCadet);
        UIManager.put("MenuBar.foreground", Color.WHITE);
        UIManager.put("Menu.foreground", Color.WHITE);
    }
}