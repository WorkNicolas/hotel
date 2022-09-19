package reservation;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.awt.Font;
import java.sql.Date;
import java.text.SimpleDateFormat;
public class  DateComponent extends SpinnerDateModel {
    public JSpinner ui;
    private final String pattern = "yyyy-MM-dd";
    public DateComponent() {
        ui = new JSpinner(this);
        ui.setFont(new Font("Consolas", Font.PLAIN, 24));
        var e = new JSpinner.DateEditor(ui,pattern);
        ui.setEditor(e);
        ui.setToolTipText(pattern);
    }

    /**
     * @return an instance of java.sql.Date
     */
    public Date getSQLDate() {
        return Date.valueOf(
            new SimpleDateFormat(pattern)
            .format(getDate())
        );
    }
    public static void main(String[] args) {
        var c = new DateComponent();
        System.out.println(c.getSQLDate());
    }
}