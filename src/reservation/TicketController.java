package reservation;
import payment.PairConsumer;
import payment.Receipt;
import payment.ReceiptView;
import room.Info;
import room.QueryBar;
import room.RoomPanel;
/**
 * @author JCSJ
 */
public class TicketController implements PairConsumer<Receipt, Reservation>{
    ReservationTicketView ui;
    RoomPanel rp;
    ReceiptView infoPanel;
    public TicketController(ReservationTicketView ui) {
        this.ui = ui;
    }
    
    private void paintRoom(Info room) {
        if (rp != null)
            ui.roomPanel.remove(rp);

        rp = new RoomPanel(room, 600, 360);
        rp.button.setVisible(false);
        ui.roomPanel.removeAll();
        ui.roomPanel.add(rp);
        ui.validate();
    }
    private void paintReceipt(Receipt r) {
        if (infoPanel != null)
            ui.infoPanel.remove(infoPanel);
        infoPanel = new ReceiptView(r);
        ui.infoPanel.add(infoPanel);
        ui.validate();
    }

    /**
     * Reuses query bar but in a disabled state 
     */
    private void paintStay(Stay r) {
        var q = new QueryBar();
        q.start.setValue(r.start);
        q.start.ui.setEnabled(false);
        q.end.setValue(r.end);
        q.end.ui.setEnabled(false);
        q.setEnabled(false);
        q.checker.setVisible(false);
        ui.roomPanel.add(q);
    }

    @Override
    public void accept(Receipt a, Reservation b) {
        paintRoom(b.room);
        paintReceipt(a);
        paintStay(b.stay);
    }
}