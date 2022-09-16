/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author amr
 */
public class HeaderInvoice {

   
    
    private int Header_No;
    private Date Header_Date;
private String Header_Name;
    private ArrayList<Item>InvoiceItems;
        private DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

     public HeaderInvoice() {
    }
     
    public HeaderInvoice(int Header_No, Date Header_Date, String Header_Name) {
        this.Header_No = Header_No;
        this.Header_Date = Header_Date;
        this.Header_Name = Header_Name;
    }

    public int getHeader_No() {
        return Header_No;
    }

    public Date getHeader_Date() {
        return Header_Date;
    }

    public String getHeader_Name() {
        return Header_Name;
    }

    public ArrayList<Item> getInvoiceItems() {
        
        if(InvoiceItems==null)
        {
            InvoiceItems=new ArrayList<>();
        }
        return InvoiceItems;
    }

    public void setHeader_No(int Header_No) {
        this.Header_No = Header_No;
    }

    public void setHeader_Date(Date Header_Date) {
        this.Header_Date = Header_Date;
    }

    public void setHeader_Name(String Header_Name) {
        this.Header_Name = Header_Name;
    }

    public void setInvoiceItems(ArrayList<Item> InvoiceItems) {
        this.InvoiceItems = InvoiceItems;
    }

    @Override
    public String toString() {
       return Header_No + "," + df.format(Header_Date) + "," + Header_Name;
    }
    
    public double getTotal()
    {
        double total=0;
        
        for(Item items:getInvoiceItems())
        {
          total+=items.getItemTotal();
            
        }
         return total;
    }
    
  
}
