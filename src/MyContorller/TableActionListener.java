/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyContorller;

import MyView.AppFrame;
import MyModel.HeaderInvoice;
import MyModel.Item;
import MyModel.ItemTableModel;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author amr
 */
public class TableActionListener implements ListSelectionListener{
 private AppFrame frame;

    public TableActionListener(AppFrame frame) {
        this.frame = frame;
    }
    @Override
    public void valueChanged(ListSelectionEvent e) {
         int selectedInvIndex = frame.getInvHTbl().getSelectedRow();
         if (selectedInvIndex != -1) {
            HeaderInvoice selectedInv = frame.getInvoicesArray().get(selectedInvIndex);
            ArrayList<Item> lines = selectedInv.getInvoiceItems();
            ItemTableModel lineTableModel = new ItemTableModel(lines);
            frame.setLinesArray(lines);
            frame.getInvLTbl().setModel(lineTableModel);
            frame.getCustNameLbl().setText(selectedInv.getHeader_Name());
            frame.getInvNumLbl().setText("" + selectedInv.getHeader_No());
            frame.getInvTotalIbl().setText("" + selectedInv.getTotal());
            frame.getInvDateLbl().setText(AppFrame.dateFormat.format(selectedInv.getHeader_Date()));
    }
    
}
}
