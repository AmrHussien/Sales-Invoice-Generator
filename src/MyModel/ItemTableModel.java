/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyModel;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author amr
 */
public class ItemTableModel extends AbstractTableModel{
    
    
    private ArrayList<Item> linesArray;
    private String[] columns = {"Item Name", "Unit Price", "Count", "Line Total"};

    public ItemTableModel(ArrayList<Item> linesArray) {
        this.linesArray = linesArray;
    }

    @Override
    public int getRowCount() {
        return linesArray == null ? 0 : linesArray.size();
    }

    
        @Override
    public int getColumnCount() {
        return columns.length;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (linesArray == null) {
            return "";
        } else {
            Item line = linesArray.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return line.getItem_Name();
                case 1:
                    return line.getItem_Price();
                case 2:
                    return line.getItem_Count();
                case 3:
                    return line.getItemTotal();
                default:
                    return "";
            }
        }
    }
    
    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
}
