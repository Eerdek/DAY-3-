package kass_java.Model;

public class Product {
    private int id;
    private String name;
    private double price;
    private int categoryId;
    private byte[] image;

    public Product(String name, double price, int categoryId, byte[] image) {
        this.name = name;
        this.price = price;
        this.categoryId = categoryId;
        this.image = image;
    }

    public Product(int id, String name, double price, int categoryId, byte[] image) {
        this(name, price, categoryId, image);
        this.id = id;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getCategoryId() { return categoryId; }
    public byte[] getImage() { return image; }

    // Setters
    public void setId(int id) { this.id = id; }
}
