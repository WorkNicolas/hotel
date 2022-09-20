package reservation;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.awt.Font;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;

/**
 * @author JCSJ
 */
public class  DateComponent extends SpinnerDateModel {
    public JSpinner ui;
    private final String pattern = "yyyy-MM-dd";
    public DateComponent() {
        super(
            Date.valueOf(LocalDate.now()), 
            Date.valueOf(LocalDate.now()),
            null, 
            Calendar.DATE);
        ui = new JSpinner(this);
        ui.setFont(new Font("Consolas", Font.PLAIN, 28));
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