package samples;

import javax.swing.JFrame;

import room.ListingForm;

/**
 * TODO: Populate with sample data
 */
public class ListingSample {
    public static void main(String[] args) {
        JFrame j = new JFrame();
        try {
            j.add(
                    new ListingForm());
            j.setExtendedState(JFrame.MAXIMIZED_BOTH);
            j.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}