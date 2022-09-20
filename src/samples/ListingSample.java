package samples;
import java.util.ArrayList;
import javax.swing.JFrame;

import Style.Style;
import room.ListingForm;
import room.RawInfo;

/**
 * @author JCSJ
 * @author Mendoza, Carl Nicolas
 */
public class ListingSample {
    public static void main(String[] args) {
        Style.init();
        JFrame j = new JFrame();
        var form = new ListingForm();
        form.setConsumer(e -> {
            //PASS
        });
        form.setFetcher(f -> {
            return new ArrayList<RawInfo>(Generator.multipleInfo(25) );
        });
        j.add(form);
        j.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        try {
            j.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}