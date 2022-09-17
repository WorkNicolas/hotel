package reservation;

import room.Info;
import room.RoomPanel;

public class TicketController {
    ReservationTicketView ui;
    public TicketController(ReservationTicketView ui) {
        this.ui = ui;
      
    }
    
	public void setInfo(Reservation r) {
		//TODO: Use receipt View
        paintRoom(r.room);
        paintReceipt(r);
	}

    private void paintRoom(Info room) {
        var p = new RoomPanel(room, 720, 480);
        p.button.setText("Return");
        p.button.addActionListener(e -> {
            //TODO: Signal app to navigate back
        });
        ui.roomPanel.add(p);
    }
    private void paintReceipt(Reservation r) {
        ui.infoPanel.add(new ReservationView(r));
    }
}
