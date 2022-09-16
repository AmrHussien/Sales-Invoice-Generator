/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import MyView.AppFrame;


/**
 *
 * @author amr
 */
public class HeaderInvoiceTable extends AbstractTableModel{
     private ArrayList<HeaderInvoice> invoicesArray;
    private String[] columns = {"Invoice Num", "Invoice Date", "Customer Name", "Invoice Total"};
      private DateFormat Df=new SimpleDateFormat("dd-MM-yyyy");

    public HeaderInvoiceTable(ArrayList<HeaderInvoice> invoicesArray) {
        this.invoicesArray = invoicesArray;
    }

    @Override
    public int getRowCount() {
        return invoicesArray.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        HeaderInvoice inv = invoicesArray.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return inv.getHeader_No();
            case 1:
                return AppFrame.dateFormat.format(inv.getHeader_Date());
            case 2:
                return inv.getHeader_Name();
            case 3:
                return inv.getTotal();
        }
        return "";
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
    
}
