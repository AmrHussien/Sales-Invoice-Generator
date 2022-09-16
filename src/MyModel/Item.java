/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyModel;

/**
 *
 * @author amr
 */
public class Item {
    
    private HeaderInvoice HInvoiceNo;
    private String Item_Name;
    
    private int Item_Count;
    
    private double Item_Price;

    public Item(HeaderInvoice HInvoiceNo, String Item_Name, int Item_Count, double Item_Price) {
        this.HInvoiceNo = HInvoiceNo;
        this.Item_Name = Item_Name;
        this.Item_Count = Item_Count;
        this.Item_Price = Item_Price;
    }

    @Override
    public String toString() {
       return HInvoiceNo.getHeader_No()+ "," + Item_Name + "," + Item_Price + "," + Item_Count;
    }

    public HeaderInvoice getHInvoiceNo() {
        return HInvoiceNo;
    }

    public String getItem_Name() {
        return Item_Name;
    }

    public int getItem_Count() {
        return Item_Count;
    }

    public double getItem_Price() {
        return Item_Price;
    }

    public void setHInvoiceNo(HeaderInvoice HInvoiceNo) {
        this.HInvoiceNo = HInvoiceNo;
    }

    public void setItem_Name(String Item_Name) {
        this.Item_Name = Item_Name;
    }

    public void setItem_Count(int Item_Count) {
        this.Item_Count = Item_Count;
    }

    public void setItem_Price(double Item_Price) {
        this.Item_Price = Item_Price;
    }
    
    
    public double getItemTotal()
    {
        return Item_Price * Item_Count;
    }
}
