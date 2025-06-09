package kass_java.Model;

public class OrderItem {
    private int detailID;
    private int productID;
    private int qty;
    private double price;
    private double amount;
    private boolean isNew;

    
    public OrderItem(int productID, int qty, double price) {
        this.productID = productID;
        this.qty = qty;
        this.price = price;
        this.amount = qty * price;
        this.isNew = true;
    }

    public OrderItem(int detailID, int productID, int qty, double price, double amount, boolean isNew) {
        this.detailID = detailID;
        this.productID = productID;
        this.qty = qty;
        this.price = price;
        this.amount = amount;
        this.isNew = isNew;
    }

    
    public int getDetailID() { return detailID; }
    public int getProductID() { return productID; }
    public int getQty() { return qty; }
    public double getPrice() { return price; }
    public double getAmount() { return amount; }
    public boolean isNew() { return isNew; }

    
    public void setDetailID(int detailID) { this.detailID = detailID; }
}
