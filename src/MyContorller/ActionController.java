/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyContorller;

import MyView.AppFrame;
import MyView.HeaderInvoicesDialog;
import MyView.ItemDialog;
import MyModel.HeaderInvoice;
import MyModel.HeaderInvoiceTable;
 import MyModel.Item;
 import MyModel.ItemTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;



/**
 *
 * @author amr
 */
public class ActionController implements ActionListener {

    private AppFrame Frame;
    private HeaderInvoicesDialog headerDialog;
    private ItemDialog lineDialog;

    private DateFormat Df=new SimpleDateFormat("dd-MM-yyyy");
    
    public ActionController(AppFrame Frame) {
        this.Frame = Frame;
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand())
                {
                    case "Load":
                        loadFile();
                    break;
                    
                    case "save":
                         saveFile();
                     break;

                    case "Save Invoice":
                        saveInvoice();
                    break;

                    case "Delete Invoice":
                        deleteInvoice();
                    break;

                    case "New Item":
                        newItem();
                    break;
 
                    case "Delete Item":
                        deleteItem();
                    break;

              case "newInvoiceOK":
                newInvoiceDialogOK();
                    break;


            case "newInvoiceCancel":
                newInvoiceDialogCancel();
                                    break;

             case "newLineOK":
                newItemDialogOK();
                                    break;

                 case "newLineCancel":
                newItemDialogCancel();
                                    break;


             
                }
        
         
     }

    private void loadFile() {
     JOptionPane.showMessageDialog(Frame,"Select the header file, please!","Attention Please",JOptionPane.WARNING_MESSAGE);
             JFileChooser fileChooser = new JFileChooser();
     try {
            int result = fileChooser.showOpenDialog(Frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File headerFile = fileChooser.getSelectedFile();
                Path headerPath = Paths.get(headerFile.getAbsolutePath());
                List<String> headerLines = Files.readAllLines(headerPath);
                ArrayList<HeaderInvoice> invoiceHeaders = new ArrayList<>();
                for (String headerLine : headerLines) {
                    String[] arr = headerLine.split(",");
                    String str1 = arr[0];
                    String str2 = arr[1];
                    String str3 = arr[2];
                    int HNum = Integer.parseInt(str1);
                    Date invoiceDate = AppFrame.dateFormat.parse(str2);
                    HeaderInvoice header = new HeaderInvoice(HNum,invoiceDate, str3 );
                    invoiceHeaders.add(header);
                }
                Frame.setInvoicesArray(invoiceHeaders);

                result = fileChooser.showOpenDialog(Frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File lineFile = fileChooser.getSelectedFile();
                    Path linePath = Paths.get(lineFile.getAbsolutePath());
                    List<String> lineLines = Files.readAllLines(linePath);
                    ArrayList<Item> invoiceLines = new ArrayList<>();
                    for (String lineLine : lineLines) {
                        String[] arr = lineLine.split(",");
                        String str1 = arr[0];    
                        String str2 = arr[1];    
                        String str3 = arr[2];    
                        String str4 = arr[3];    
                        int invCode = Integer.parseInt(str1);
                        double price = Double.parseDouble(str3);
                        int count = Integer.parseInt(str4);
                        HeaderInvoice inv = Frame.getInvObject(invCode);
                        
                        Item line = new Item(inv, str2, count, price);
                        inv.getInvoiceItems().add(line);
                    }
                }
             HeaderInvoiceTable headerTableModel = new HeaderInvoiceTable(invoiceHeaders);
                Frame.setHeaderTableModel(headerTableModel);
               Frame.getInvHTbl().setModel(headerTableModel);
                
            }

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(Frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(Frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveFile() {
          ArrayList<HeaderInvoice> invoicesArray = Frame.getInvoicesArray();
        JFileChooser fc = new JFileChooser();
        try {
            int result = fc.showSaveDialog(Frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File headerFile = fc.getSelectedFile();
                FileWriter hfw = new FileWriter(headerFile);
                String headers = "";
                String lines = "";
                for (HeaderInvoice invoice : invoicesArray) {
                    headers += invoice.toString();
                    headers += "\n";
                    for (Item line : invoice.getInvoiceItems()) {
                        lines += line.toString();
                        lines += "\n";
                    }
                }
               
                headers = headers.substring(0, headers.length()-1);
                lines = lines.substring(0, lines.length()-1);
                result = fc.showSaveDialog(Frame);
                File lineFile = fc.getSelectedFile();
                FileWriter lfw = new FileWriter(lineFile);
                hfw.write(headers);
                lfw.write(lines);
                hfw.close();
                lfw.close();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(Frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
     }

    private void saveInvoice() {
         headerDialog= new HeaderInvoicesDialog(Frame);
        headerDialog.setVisible(true);
     }

    private void deleteInvoice() {
          int selectedInvoiceIndex = Frame.getInvHTbl().getSelectedRow();
        if (selectedInvoiceIndex != -1) {
            Frame.getInvoicesArray().remove(selectedInvoiceIndex);
            Frame.getHeaderTableModel().fireTableDataChanged();

           Frame.getInvLTbl().setModel(new ItemTableModel(null));
            Frame.setLinesArray(null);
            Frame.getCustNameLbl().setText("");
            Frame.getInvNumLbl().setText("");
            Frame.getInvTotalIbl().setText("");
            Frame.getInvDateLbl().setText("");
        }
     }

    private void newItem() {
         lineDialog = new ItemDialog(Frame);
        lineDialog.setVisible(true);
     }

    private void deleteItem() {
         int selectedLineIndex = Frame.getInvLTbl().getSelectedRow();
        int selectedInvoiceIndex = Frame.getInvHTbl().getSelectedRow();
        if (selectedLineIndex != -1) {
            Frame.getLinesArray().remove(selectedLineIndex);
           ItemTableModel lineTableModel = (ItemTableModel) Frame.getInvLTbl().getModel();
           lineTableModel.fireTableDataChanged();
            Frame.getInvTotalIbl().setText("" + Frame.getInvoicesArray().get(selectedInvoiceIndex).getTotal());
            Frame.getHeaderTableModel().fireTableDataChanged();
            Frame.getInvHTbl().setRowSelectionInterval(selectedInvoiceIndex, selectedInvoiceIndex);
        }
     }

    
    private void newInvoiceDialogOK() {
        
         headerDialog.setVisible(false);

        String CST_Name = headerDialog.getCustNameField().getText();
        String str = headerDialog.getInvDateField().getText();
        Date date = new Date();
        try {
            date = AppFrame.dateFormat.parse(str);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(Frame, "Cannot parse date, resetting to today.", "Invalid date format", JOptionPane.ERROR_MESSAGE);
        }

        int Invoice_No = 0;
        for (HeaderInvoice inv : Frame.getInvoicesArray()) {
            if (inv.getHeader_No()> Invoice_No) {
                Invoice_No = inv.getHeader_No();
            }
        }
        Invoice_No++;
        HeaderInvoice newInv = new HeaderInvoice(Invoice_No,date, CST_Name);
        Frame.getInvoicesArray().add(newInv);
        Frame.getHeaderTableModel().fireTableDataChanged();
        headerDialog.dispose();
        headerDialog = null;
    }

    private void newInvoiceDialogCancel() {
         headerDialog.setVisible(false);
        headerDialog.dispose();
        headerDialog = null;
    }

    private void newItemDialogOK() {
        
         lineDialog.setVisible(false);

        String name = lineDialog.getItemNameField().getText();
        String str1 = lineDialog.getItemCountField().getText();
        String str2 = lineDialog.getItem_Price().getText();
        int count = 1;
        double price = 1;
        try {
            count = Integer.parseInt(str1);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(Frame, "Cannot convert number", "Invalid number format", JOptionPane.ERROR_MESSAGE);
        }

        try {
            price = Double.parseDouble(str2);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(Frame, "Cannot convert price", "Invalid number format", JOptionPane.ERROR_MESSAGE);
        }
        int selectedInvHeader = Frame.getInvHTbl().getSelectedRow();
        if (selectedInvHeader != -1) {
            HeaderInvoice invHeader = Frame.getInvoicesArray().get(selectedInvHeader);
            Item line = new Item(invHeader, name, count, price);
            //invHeader.getLines().add(line);
            Frame.getLinesArray().add(line);
            ItemTableModel lineTableModel = (ItemTableModel) Frame.getInvLTbl().getModel();
            lineTableModel.fireTableDataChanged();
            Frame.getHeaderTableModel().fireTableDataChanged();
        }
        Frame.getInvHTbl().setRowSelectionInterval(selectedInvHeader, selectedInvHeader);
        lineDialog.dispose();
        lineDialog = null;
    }
     

    private void newItemDialogCancel() {
         lineDialog.setVisible(false);
        lineDialog.dispose();
        lineDialog = null;
     }
    
}

