package kass_java.Model;

public class Product {
    private int id;
    private String name;
    private double price;
    private int categoryId;
    private byte[] image;
    private int stockQuantity; 

    public Product(String name, double price, int categoryId, byte[] image) {
        this.name = name;
        this.price = price;
        this.categoryId = categoryId;
        this.image = image;
        this.stockQuantity = 0; 
    }

    public Product(int id, String name, double price, int categoryId, byte[] image) {
        this(name, price, categoryId, image);
        this.id = id;
    }

    
    public Product(String name, double price, int categoryId, byte[] image, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.categoryId = categoryId;
        this.image = image;
        this.stockQuantity = stockQuantity;
    }

    public Product(int id, String name, double price, int categoryId, byte[] image, int stockQuantity) {
        this(name, price, categoryId, image, stockQuantity);
        this.id = id;
    }

    
    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getCategoryId() { return categoryId; }
    public byte[] getImage() { return image; }
    public int getStockQuantity() { return stockQuantity; }

    
    public void setId(int id) { this.id = id; }
    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }
}
