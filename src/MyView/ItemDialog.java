/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyView;

import MyModel.Item;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author amr
 */
public class ItemDialog extends JDialog {
    private JTextField Item_Name;
    private JTextField Item_Count;
    private JTextField Item_Price;
    private JLabel Lbl_Item_Name;
    private JLabel Lbl_Item_Count;
    private JLabel Lbl_Item_Price;
    private JButton btn_Ok;
    private JButton btn_Cancel;
    
    public ItemDialog(AppFrame frame) {
        Item_Name = new JTextField(20);
        Lbl_Item_Name = new JLabel("Item Name");
        
        Item_Count = new JTextField(20);
        Lbl_Item_Count = new JLabel("Item Count");
        
        Item_Price = new JTextField(20);
        Lbl_Item_Price = new JLabel("Item Price");
        
        btn_Ok = new JButton("OK");
        btn_Cancel = new JButton("Cancel");
        
        btn_Ok.setActionCommand("newLineOK");
        btn_Cancel.setActionCommand("newLineCancel");
        
        btn_Ok.addActionListener(frame.getActionListener());
        btn_Cancel.addActionListener(frame.getActionListener());
        setLayout(new GridLayout(4, 2));
        
        add(Lbl_Item_Name);
        add(Item_Name);
        add(Lbl_Item_Count);
        add(Item_Count);
        add(Lbl_Item_Price);
        add(Item_Price);
        add(btn_Ok);
        add(btn_Cancel);
        
        pack();
    }

    public JTextField getItemNameField() {
        return Item_Name;
    }

    public JTextField getItemCountField() {
        return Item_Count;
    }

    public JTextField getItem_Price() {
        return Item_Price;
    }
    
}
