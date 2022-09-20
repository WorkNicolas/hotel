package service;

import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import payment.Discount;
import payment.PairConsumer;
import payment.Payment;
import payment.PaymentDialog;
import payment.Receipt;

/**
 * @author JCSJ
 */
public class ServiceController {
    public RoomServicePanel ui;
    private Receipt receipt = new Receipt();
    private Amenity latest;
    private PairConsumer<Receipt, Payment> consumer;
    public ServiceController(RoomServicePanel ui,  PairConsumer<Receipt, Payment> consumer) throws SQLException {
        this.ui = ui;
        this.consumer = consumer;
        var dialog = new PaymentDialog((JFrame) SwingUtilities.getWindowAncestor(ui), "Payment portal", new Discount[] {
            new Discount("NONE", 0),
            new Discount("SENIOR CITIZEN", 0.2f),
            new Discount("PERSON WITH DISABILITY", 0.2f)
        });
        for (var list: ui.lists) {
            list.addListSelectionListener(l -> {
                latest = list.getSelectedValue();
                ui.orderList.setSelectedIndex(ui.orderListModel.size() - 1);
            });
        }
        ui.addOrder.addActionListener(e -> {
            if (latest == null)
                return;

            ui.orderListModel
            .addElement(latest);
            receipt.putOrAddAmount(latest, 1);
            ui.orderList.setSelectedIndex(ui.orderListModel.size() - 1);
            ui.orderList.setModel(ui.orderListModel);
            showTotal();
        });
        ui.btnRemoveOrder.addActionListener(e -> {
            int i = ui.orderList.getSelectedIndex();
            if (i < 0)
                return;
            var a = ui.orderListModel.remove(i);
            receipt.reduceOrRemove(a, 1);
            ui.orderList.setSelectedIndex(ui.orderListModel.size() - 1);
            showTotal();
        });
        ui.commitOrder.addActionListener(e -> {
            float total = receipt.getTotal();
            if (total <= 0) {
                return;
            }
            Payment p = dialog.prompt(total);
            if (p == null)
                return;
            this.consumer.accept(receipt, p);
        });

        ui.btnClear.addActionListener(e -> {
            clear();
        });
    }
    public void showTotal() {
        ui.lblTotal.setText("Total: " + receipt.getTotal());
    }

    public void clear() {
        this.receipt = new Receipt();
        latest = null;
        ui.orderListModel.clear();
        showTotal();
    }
}