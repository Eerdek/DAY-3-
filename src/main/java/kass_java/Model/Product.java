package kass_java.Model;

public class Product {
    private int id;
    private String name;
    private double price;
    private int categoryId;
    private byte[] image;
    private String category;

    public Product(String name, double price, int categoryId, byte[] image, String category) {
        this.name = name;
        this.price = price;
        this.categoryId = categoryId;
        this.image = image;
        this.category = category;
    }

    public Product(int id, String name, double price, int categoryId, byte[] image, String category) {
        this(name, price, categoryId, image, category);
        this.id = id;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getCategoryId() { return categoryId; }
    public byte[] getImage() { return image; }
    public String getCategory() { return category; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }
    public void setImage(byte[] image) { this.image = image; }
    public void setCategory(String category) { this.category = category; }
}
