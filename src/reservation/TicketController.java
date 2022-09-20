package reservation;

import payment.PairConsumer;
import payment.Receipt;
import payment.ReceiptView;
import room.Info;
import room.RoomPanel;
public class TicketController implements PairConsumer<Receipt, Reservation>{
    ReservationTicketView ui;
    public TicketController(ReservationTicketView ui) {
        this.ui = ui;
      
    }
    
    private void paintRoom(Info room) {
        var p = new RoomPanel(room, 720, 480);
        p.button.setText("Return");
        ui.roomPanel.add(p);
    }
    private void paintReceipt(Receipt r) {
        ui.infoPanel.add(new ReceiptView(r));
    }

    private void paintStay(Stay r) {
        //TODO ui.infoPanel.add()
    }

    @Override
    public void accept(Receipt a, Reservation b) {
        paintRoom(b.room);
        paintReceipt(a);
        paintStay(b.stay);
    }
}