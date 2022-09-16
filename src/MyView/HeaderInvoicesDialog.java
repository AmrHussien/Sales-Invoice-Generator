/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyView;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author amr
 */
public class HeaderInvoicesDialog extends JDialog{
     private JTextField CST_Name;
    private JTextField Invoice_Date;
    private JLabel LBL_CST_Name;
    private JLabel LBL_Invoice_Date;
    private JButton btn_Ok;
    private JButton btn_Cancel;
    
    public HeaderInvoicesDialog(AppFrame frame)
    {
        
        LBL_CST_Name = new JLabel("Customer Name:");
        CST_Name = new JTextField(20);
        LBL_Invoice_Date = new JLabel("Invoice Date:");
        Invoice_Date = new JTextField(20);
        btn_Ok = new JButton("OK");
        btn_Cancel = new JButton("Cancel");
        
        btn_Ok.setActionCommand("newInvoiceOK");
        btn_Cancel.setActionCommand("newInvoiceCancel");
        
        btn_Ok.addActionListener(frame.getActionListener());
        btn_Cancel.addActionListener(frame.getActionListener());
        setLayout(new GridLayout(3, 2));
        
        add(LBL_Invoice_Date);
        add(Invoice_Date);
        add(LBL_CST_Name);
        add(CST_Name);
        add(btn_Ok);
        add(btn_Cancel);
        
        pack();
        
    }


    public JTextField getCustNameField() {
        return CST_Name;
    }

    public JTextField getInvDateField() {
        return Invoice_Date;
    }
    
}
