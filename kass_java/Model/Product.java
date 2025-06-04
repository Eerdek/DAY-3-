package kass_java.Model;

public class Product {
    private int id;
    private String name;
    private double price;
    private int categoryId;
    private byte[] image;
    private int stockQuantity; // Added stock quantity field

    public Product(String name, double price, int categoryId, byte[] image) {
        this.name = name;
        this.price = price;
        this.categoryId = categoryId;
        this.image = image;
        this.stockQuantity = 0; // Default stock quantity
    }

    public Product(int id, String name, double price, int categoryId, byte[] image) {
        this(name, price, categoryId, image);
        this.id = id;
    }

    // Constructor with stock quantity
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

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getCategoryId() { return categoryId; }
    public byte[] getImage() { return image; }
    public int getStockQuantity() { return stockQuantity; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }
}
